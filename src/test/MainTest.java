package test;

import entities.*;
import enums.*;
import java.util.*;
import java.time.LocalDateTime;
import entities.DemandeDeCourse;

public class MainTest {
    public static List<Course> allCourses = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("======== USTHB COVOITURAGE - TEST ========\n");

        // Create test users (2 passengers, 2 drivers)
        System.out.println("Création des utilisateurs...");
        User[] users = createUsers();

        // Display users
        System.out.println("\n===== Utilisateurs créés =====");
        for (User user : users) {
            System.out.println("Nom: " + user.getNom() + " Prenom: " + user.getPrenom() +
                               ", Itinéraire: " + user.getProfile().getDisponibilite().getItineraire().getPoints());
            System.out.println("------------------------------");
        }

        // Test matching
        System.out.println("\n===== Test de matching des courses =====");
        testCourseMatching(users);

        // Print statistics
        System.out.println("\n===== Statistiques =====");
        System.out.println("Nombre d'étudiants: " + Etudiant.getNbEtudiants());
        System.out.println("Nombre d'enseignants: " + Enseignant.getNbEnseignants());
        System.out.println("Nombre d'ATS: " + ATS.getNbATS());

        // Menu loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Afficher tous les chauffeurs");
            System.out.println("2. Afficher tous les passagers");
            System.out.println("3. Terminer une course (par chauffeur)");
            System.out.println("4. Quitter");
            System.out.print("Votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choix) {
                case 1:
                    System.out.println("Liste des chauffeurs:");
                    for (Course c : allCourses) {
                        User d = c.getDriver();
                        System.out.println("Nom: " + d.getNom() + ", Prénom: " + d.getPrenom() +
                            ", Itinéraire: " + d.getProfile().getDisponibilite().getItineraire().getPoints());
                    }
                    break;
                case 2:
                    System.out.println("Liste des passagers:");
                    for (Course c : allCourses) {
                        User p = c.getPassenger();
                        System.out.println("Nom: " + p.getNom() + ", Prénom: " + p.getPrenom() +
                            ", Itinéraire: " + p.getProfile().getDisponibilite().getItineraire().getPoints());
                    }
                    break;
                case 3:
                    System.out.println("Courses en cours:");
                    for (int i = 0; i < allCourses.size(); i++) {
                        Course c = allCourses.get(i);
                        if (c.getStatusCourse() == CourseStatus.EN_COURS) {
                            System.out.println(i + ". " + c);
                        }
                    }
                    System.out.print("Entrez l'indice de la course à terminer: ");
                    int idx = scanner.nextInt();
                    scanner.nextLine();
                    if (idx >= 0 && idx < allCourses.size() && allCourses.get(idx).getStatusCourse() == CourseStatus.EN_COURS) {
                        allCourses.get(idx).terminerLaCourse();
                    } else {
                        System.out.println("Indice invalide ou course déjà terminée.");
                    }
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static User[] createUsers() {
        // Create common LocalDateTime for testing
        List<LocalDateTime> morningSchedule = new ArrayList<>();
        morningSchedule.add(LocalDateTime.of(2025, 4, 25, 8, 0));

        List<LocalDateTime> afternoonSchedule = new ArrayList<>();
        afternoonSchedule.add(LocalDateTime.of(2025, 4, 25, 17, 0));

        // 1. Student (Passenger) - EL_MOURADIA → USTHB
        List<Communes> student1Route = Arrays.asList(Communes.EL_MOURADIA, Communes.USTHB);
        Itineraire student1Itineraire = new Itineraire(student1Route);
        Disponibilite student1Dispo = new Disponibilite(DisponibiliteType.QUOTIDIEN, morningSchedule, student1Itineraire);
        Preferences student1Prefs = new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.SANS_BAGAGES);
        Profile student1Profile = new Profile(StatusUser.PASSAGER, TypeCourse.ALLER_SIMPLE, student1Prefs, student1Dispo);
        Etudiant student1 = new Etudiant("Amrouni", "Ahmed", "ET12345", 4.5, student1Profile, 2022, FacultesUSTHB.INFORMATIQUE, Specialite.LICENCE_INFORMATIQUE);

        // 2. Teacher (Driver) - USTHB → EL_MOURADIA → BIR_MOURAD_RAIS → KOUBA
        List<Communes> teacher1Route = Arrays.asList(Communes.USTHB, Communes.EL_MOURADIA, Communes.BIR_MOURAD_RAIS, Communes.KOUBA);
        Itineraire teacher1Itineraire = new Itineraire(teacher1Route);
        Disponibilite teacher1Dispo = new Disponibilite(DisponibiliteType.QUOTIDIEN, afternoonSchedule, teacher1Itineraire);
        Preferences teacher1Prefs = new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.SANS_BAGAGES);
        Profile teacher1Profile = new Profile(StatusUser.CHAUFFEUR, TypeCourse.RETOUR_SIMPLE, teacher1Prefs, teacher1Dispo);
        Enseignant teacher1 = new Enseignant("Benz", "Yacine", "EN54321", 4.8, teacher1Profile, 2015, FacultesUSTHB.INFORMATIQUE);

        // 3. Student (Passenger) - KOUBA → USTHB
        List<Communes> student2Route = Arrays.asList(Communes.KOUBA, Communes.USTHB);
        Itineraire student2Itineraire = new Itineraire(student2Route);
        Disponibilite student2Dispo = new Disponibilite(DisponibiliteType.QUOTIDIEN, morningSchedule, student2Itineraire);
        Preferences student2Prefs = new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.SANS_PREFERENCE);
        Profile student2Profile = new Profile(StatusUser.PASSAGER, TypeCourse.ALLER_SIMPLE, student2Prefs, student2Dispo);
        Etudiant student2 = new Etudiant("Ferhat", "Sarah", "ET67890", 4.7, student2Profile, 2021, FacultesUSTHB.BIOLOGIE, Specialite.BIOLOGIE);

        // 4. ATS (Driver) - HYDRA → EL_MOURADIA → KOUBA → USTHB
        List<Communes> atsRoute = Arrays.asList(Communes.HYDRA, Communes.EL_MOURADIA, Communes.KOUBA, Communes.USTHB);
        Itineraire atsItineraire = new Itineraire(atsRoute);
        Disponibilite atsDispo = new Disponibilite(DisponibiliteType.QUOTIDIEN, morningSchedule, atsItineraire);
        Preferences atsPrefs = new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.SANS_BAGAGES);
        Profile atsProfile = new Profile(StatusUser.CHAUFFEUR, TypeCourse.ALLER_SIMPLE, atsPrefs, atsDispo);
        ATS ats1 = new ATS("Chabane", "Karim", "ATS9876", 4.2, atsProfile, 2018, "Administration");

        return new User[] { student1, teacher1, student2, ats1 };
    }

    private static void testCourseMatching(User[] users) {
        Etudiant student1 = (Etudiant) users[0];
        Enseignant teacher1 = (Enseignant) users[1];
        Etudiant student2 = (Etudiant) users[2];
        ATS ats1 = (ATS) users[3];

        // Test Case 1: Ahmed (EL_MOURADIA->USTHB) with Yacine (USTHB->EL_MOURADIA->BIR_MOURAD_RAIS->KOUBA)
        System.out.println("\nTest 1: Ahmed (EL_MOURADIA->USTHB) avec Yacine (USTHB->EL_MOURADIA->BIR_MOURAD_RAIS->KOUBA)");
        if (DemandeDeCourse.canMatch(student1, teacher1)) {
            Course course1 = new Course(student1, teacher1);
            course1.acceptCourse();
            allCourses.add(course1);
            System.out.println("Résultat: Acceptée");
        } else {
            System.out.println("Résultat: Refusée");
        }

        // Test Case 2: Ahmed (EL_MOURADIA->USTHB) with Karim (HYDRA->EL_MOURADIA->KOUBA->USTHB)
        System.out.println("\nTest 2: Ahmed (EL_MOURADIA->USTHB) avec Karim (HYDRA->EL_MOURADIA->KOUBA->USTHB)");
        if (DemandeDeCourse.canMatch(student1, ats1)) {
            Course course2 = new Course(student1, ats1);
            course2.acceptCourse();
            allCourses.add(course2);
            System.out.println("Résultat: Acceptée");
        } else {
            System.out.println("Résultat: Refusée");
        }

        // Test Case 3: Sarah (KOUBA->USTHB) with Yacine (USTHB->EL_MOURADIA->BIR_MOURAD_RAIS->KOUBA)
        System.out.println("\nTest 3: Sarah (KOUBA->USTHB) avec Yacine (USTHB->EL_MOURADIA->BIR_MOURAD_RAIS->KOUBA)");
        if (DemandeDeCourse.canMatch(student2, teacher1)) {
            Course course3 = new Course(student2, teacher1);
            course3.acceptCourse();
            allCourses.add(course3);
            System.out.println("Résultat: Acceptée");
        } else {
            System.out.println("Résultat: Refusée");
        }

        // Test Case 4: Sarah (KOUBA->USTHB) with Karim (HYDRA->EL_MOURADIA->KOUBA->USTHB)
        System.out.println("\nTest 4: Sarah (KOUBA->USTHB) avec Karim (HYDRA->EL_MOURADIA->KOUBA->USTHB)");
        if (DemandeDeCourse.canMatch(student2, ats1)) {
            Course course4 = new Course(student2, ats1);
            course4.acceptCourse();
            allCourses.add(course4);
            System.out.println("Résultat: Acceptée");
        } else {
            System.out.println("Résultat: Refusée");
        }
    }
}