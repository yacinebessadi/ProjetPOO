package entities;
//travaille de celina 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

 public class admin {
	private List<User> utilisateurs; // users normaux
	private List<User> utilisateursBannis;//users bannis
	private List<Course> historiqueCourses;
    private List<Course> courseEnCours;
	
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
		if (course !=null && courseEnCours.contains(course)) {
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
     public void top10Chauffeurs() {
     
     }
     }
    	    
