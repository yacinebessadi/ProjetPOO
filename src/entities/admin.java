package entities;
//travaille de celina 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import enums.StatusUser;
import enums.CourseStatus;
import enums.FacultesUSTHB;

public class admin {
	private List<User> utilisateurs; // users normaux
	private List<User> utilisateursBannis;//users bannis
	private List<Course> historiqueCourses;
    private List<Course> courseEnCours;
	
	/* ajout constructeur pour eviter les erreur d'exécu si liste nulle
	   si on oublie d'initialiser lors de la declarartion */
	public admin() {
		utilisateurs = new ArrayList<>();
		utilisateursBannis = new ArrayList<>();
		historiqueCourses = new ArrayList<>();
		courseEnCours = new ArrayList<>();
	}
	
	void ajouterUtilisateur(User user) {
	        if (user != null && !utilisateurs.contains(user)) {
	            utilisateurs.add(user);
	            System.out.println("Utilisateur ajouté: " + user.getNom()+" "+user.getPrenom());
	        }} // il faut ajouter la methode affichernom afficherprenom , matricule ...
	    
	void bannirUtilisateur(User user) {
	        if (user != null && user.getReputation() < 0.5 && !utilisateursBannis.contains(user)) {
	            utilisateursBannis.add(user);
	            utilisateurs.remove(user);
	            System.out.println("utilisateurs bannis:"+user.getNom()+" "+user.getPrenom());
	        }
	    }
	  
	 // méthodes pour retourner le nombre des enseignants,ATS...
	 /* 
	 int nb_etudiant()
	 {return nbEtudiants;}
	 int nb_enseignant()
	 { return nbEnseignants;}
     int nb_ATS()
     { return nbATS;}
	  */
     
     /* voir les courses en cours à un instant donnée quand une course est acceptée elle est
     ajouter a la liste a l'instant meme faudra l'implementer f l'execution juste apres que le passenger trouve un driver.*/
     public void ajoutercourse(Course course)
     { if (course!=null && course.isAccepter() )
     { courseEnCours.add(course);}
     }
	// quand la course est terminé on l'enleve de la liste course encours et l'ajoute dans l'historique
	public void courseterminé(Course course) {
		if (course !=null && courseEnCours.contains(course) && course.getStatusCourse() == CourseStatus.TERMINEE) {
			courseEnCours.remove(course);
			historiqueCourses.add(course);
		}}
    	
	// voir les courses les utilisateurs...etc
	public List<Course> getCoursesEnCours() {
		return Collections.unmodifiableList(courseEnCours); }

	public List<Course> getHistoriqueCourses() {
		return Collections.unmodifiableList(historiqueCourses); }
	
	public List<User> getUtilisateurs() {
		return Collections.unmodifiableList(utilisateurs); } 

	public List<User> getUtilisateursBannis() {
		return Collections.unmodifiableList(utilisateursBannis);    }
	// j'ai fait return unmodifiablelist pour ne pas permettre les modifications

	// pour voir categorie qui a le plus de course rien de plus simple que un compteur qui traverse l'historique
	public void afficherCategoriePlusActive() {
		int compteurEtudiants = 0;
		int compteurEnseignants = 0;
		int compteurATS = 0;
		// boucle for each course de la liste
		for  (Course course : historiqueCourses) {
			User conducteur = course.getDriver();
			if (conducteur instanceof Etudiant) {
				compteurEtudiants++;
			} else if (conducteur instanceof Enseignant) {
				compteurEnseignants++;
			} else if (conducteur instanceof ATS) {
				compteurATS++; }
		}
		// Trouver la categorie la plus active
		String categoriePlusActive = "Étudiants";
		int maxCourses = compteurEtudiants;

		if (compteurEnseignants > maxCourses) {
			categoriePlusActive = "Enseignants";
			maxCourses = compteurEnseignants;
		}
		if (compteurATS > maxCourses) {
			categoriePlusActive = "ATS";
		}

		System.out.println("Courses par catégorie :");
		System.out.println("- Étudiants : " + compteurEtudiants + " courses\n" +
				"- Enseignants : " + compteurEnseignants + " courses\n" +
				"- ATS : " + compteurATS + " courses");
		System.out.println("\n Catégorie la plus active : " + categoriePlusActive);
	}

	/* méthode utile pour top/pire 10 chauffeur/passager
     selon leur reputation , elle est privé car on a pas besoin de l'appeler en dehors de la classe */
	private void afficherTopUtilisateursParStatut(StatusUser statutRecherche, boolean meilleurs, int limite) {
		List<User> filtres = utilisateurs.stream()
			.filter(u -> u.getProfile().getStatut() == statutRecherche)
			.collect(Collectors.toList());
		
		// filtrer la liste des users et garder que ceux qui ont le bon statut
		// ensuite on les trie par ordre croissant ou decroissant selon meilleurs(true) ou pire(false)
		if(meilleurs) {
			filtres.sort((u1, u2) -> Double.compare(u2.getReputation(), u1.getReputation()));
		} else {
			filtres.sort((u1, u2) -> Double.compare(u1.getReputation(), u2.getReputation()));
		}
		
		// je limite l'affichage au limite qui sera 10 premiers users
		int count = Math.min(limite, filtres.size());
		for (int i = 0; i < count; i++) {
			User u = filtres.get(i);
			System.out.println((i+1) + ". " + u.getNom() + " " + u.getPrenom() + " - Reputation:" + u.getReputation());
		}
	}
	
	// ensuite je l'utilise dans ces méthodes
	public void top10Chauffeurs() {
		System.out.println("\n Top 10 Chauffeurs :");
		afficherTopUtilisateursParStatut(StatusUser.CHAUFFEUR, true, 10);
	}
	
	public void pire10Chauffeurs() {
		System.out.println("\n Pire 10 Chauffeurs :");
		afficherTopUtilisateursParStatut(StatusUser.CHAUFFEUR, false, 10);
	}
	
	public void top10Passagers() {
		System.out.println("\n Top 10 Passagers :");
		afficherTopUtilisateursParStatut(StatusUser.PASSAGER, true, 10);
	}
	
	public void pire10Passagers() {
		System.out.println("\n Pire 10 Passagers :");
		afficherTopUtilisateursParStatut(StatusUser.PASSAGER, false, 10);
	}
	
	// pour la méthode suivante facultePlusActive on résonne comme ça:
	// ceux qui proposent plus de courses les facultés ayant la myenne de ridecount la plus élevées jusqua plus basse
	public void facultesPlusActives() {
		List<StatFaculte> stats = new ArrayList<>();
		for (FacultesUSTHB fac : FacultesUSTHB.values()) {
			stats.add(new StatFaculte(fac));
		}

		// Parcourt tous des user
		for (User user : utilisateurs) {
			FacultesUSTHB fac = null;

			// On identifie la faculté selon le type d'utilisateur
			if (user instanceof Etudiant) {
				fac = ((Etudiant) user).getFaculte();
			} else if (user instanceof Enseignant) {
				fac = ((Enseignant) user).getFaculte();
			} else if (user instanceof ATS) {
				fac = ((ATS) user).getFaculte();
			}

			// Si une faculté a été trouvée, on ajoute les stats
			if (fac != null) {
				for (StatFaculte stat : stats) {
					if (stat.getFaculte() == fac) {
						int rides = user.getProfile().getRideCount();
						stat.ajouterUtilisateur(rides);
						break;
					}
				}
			}
		}

		// Tri des facultés par moyenne de rides décroissante
		stats.sort((a, b) -> Double.compare(b.getMoyenneRides(), a.getMoyenneRides()));

		System.out.println("=== Facultés les plus actives ===");
		for (StatFaculte stat : stats) {
			System.out.println(stat); // toString() appelé automatiquement
		}
	}

	/* pour cette methode on prend le nb des users qui ont un ride count
	 * du plus elevé et on descend par exemple les 20 plus actifs
	 */
	public void nbUserActifs(int limite) {
		System.out.println("===Utilisateurs les plus actifs (Top " + limite + ")===");
		// On trie tous les uuser par rideCount décroissant
		List<User> trie = utilisateurs.stream()
			.sorted((u1, u2) -> Integer.compare(
				u2.getProfile().getRideCount(),
				u1.getProfile().getRideCount()))
			.limit(limite)
			.collect(Collectors.toList());
		
		// les afficher 
		for (int i = 0; i < trie.size(); i++) {
			User u = trie.get(i);
			System.out.printf("%2d. %s %s - %d trajets\n", i + 1, u.getNom(), u.getPrenom(), u.getProfile().getRideCount());
		}
	}

	// nb total user car pourquoi pas
	public int getNombreTotalUser() {
		return (ATS.getNbATS() + Enseignant.getNbEnseignants() + Etudiant.getNbEtudiants());
	}
	
	//afficherPlanning to display all les courses prévues 
		public void afficherPlanning() {
			System.out.println("\n=== PLANNING DES CHAUFFEURS ===");
			
			ArrayList<User> listdriver = new ArrayList<>();
			for(User user : utilisateurs) {
				if(user.getProfile().getStatut() == StatusUser.CHAUFFEUR) {
					listdriver.add(user);
				}
			}
			
			for(User driver : listdriver) {
				System.out.println("Chauffeur :" + driver.getNom() + " " + driver.getPrenom());
				
				System.out.println("Type de disponibilité : " + driver.getProfile().getDisponibilite().getType());
				
				System.out.println("\n Horaires prévus :");
				for(LocalDateTime hours : driver.getProfile().getDisponibilite().getHoraires()) {
					System.out.println("-" + hours);
				}
				
				System.out.println("\n Itinéraire :");
				System.out.println("  " + driver.getProfile().getDisponibilite().getItineraire().getPoints());
				
	            System.out.println("Type de course : " +driver.getProfile().getTypeCourse());
				
				System.out.println("----------------------------------------");
				
			}
			
		}
}
    	    
