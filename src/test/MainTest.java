package test;

import entities.*;
import enums.*;
import java.util.*;
import java.time.LocalDateTime;

public class MainTest {
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
    }
    
    private static User[] createUsers() {
        // Create common LocalDateTime for testing
        List<LocalDateTime> morningSchedule = new ArrayList<>();
        morningSchedule.add(LocalDateTime.of(2025, 4, 25, 8, 0));
        
        List<LocalDateTime> afternoonSchedule = new ArrayList<>();
        afternoonSchedule.add(LocalDateTime.of(2025, 4, 25, 17, 0));
        
        // Create users (2 drivers, 2 passengers)
        
        // 1. Student (Passenger) - Going from Golf to USTHB
        List<String> student1Route = Arrays.asList("Golf", "USTHB");
        Itineraire student1Itineraire = new Itineraire(student1Route);
        Disponibilite student1Dispo = new Disponibilite(DisponibiliteType.QUOTIDIEN, morningSchedule, student1Itineraire);
        Preferences student1Prefs = new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.SANS_BAGAGES);
        Profile student1Profile = new Profile(StatusUser.PASSAGER, TypeCourse.ALLER_SIMPLE, student1Prefs, student1Dispo);
        Etudiant student1 = new Etudiant("Amrouni", "Ahmed", "ET12345", 4.5, student1Profile, 2022, "Informatique", "GL");
        
        // 2. Teacher (Driver) - Going from USTHB to several locations
        List<String> teacher1Route = Arrays.asList("USTHB", "golf", "El Mouradia", "Kouba");
        Itineraire teacher1Itineraire = new Itineraire(teacher1Route);
        Disponibilite teacher1Dispo = new Disponibilite(DisponibiliteType.QUOTIDIEN, afternoonSchedule, teacher1Itineraire);
        Preferences teacher1Prefs = new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.SANS_BAGAGES);
        Profile teacher1Profile = new Profile(StatusUser.CHAUFFEUR, TypeCourse.RETOUR_SIMPLE, teacher1Prefs, teacher1Dispo);
        Enseignant teacher1 = new Enseignant("Benz", "Yacine", "EN54321", 4.8, teacher1Profile, 2015, "Informatique");
        
        // 3. Student (Passenger) - Going from Kouba to USTHB
        List<String> student2Route = Arrays.asList("Kouba", "USTHB");
        Itineraire student2Itineraire = new Itineraire(student2Route);
        Disponibilite student2Dispo = new Disponibilite(DisponibiliteType.QUOTIDIEN, morningSchedule, student2Itineraire);
        Preferences student2Prefs = new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.SANS_PREFERENCE);
        Profile student2Profile = new Profile(StatusUser.PASSAGER, TypeCourse.ALLER_SIMPLE, student2Prefs, student2Dispo);
        Etudiant student2 = new Etudiant("Ferhat", "Sarah", "ET67890", 4.7, student2Profile, 2021, "Biologie", "BIM");
        
        // 4. ATS (Driver) - Going from several locations to USTHB
        List<String> atsRoute = Arrays.asList("Bab Ezzouar", "Golf", "Kouba", "USTHB");
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
        
        // Test Case 1: Student1 (Golf->USTHB) with Teacher1 (USTHB->Golf...) - Should succeed
        System.out.println("\nTest 1: Ahmed (Golf->USTHB) avec Yacine (USTHB->Golf...)");
        Course course1 = new Course(student1, teacher1);
        course1.demandeDeCourse();
        System.out.println("Résultat: " + (course1.isAccepter() ? "Acceptée" : "Refusée"));
        
        // Test Case 2: Student1 (Golf->USTHB) with ATS1 (Bab Ezzouar->Golf->USTHB) - Should succeed
        System.out.println("\nTest 2: Ahmed (Golf->USTHB) avec Karim (Bab Ezzouar->Golf->USTHB)");
        Course course2 = new Course(student1, ats1);
        course2.demandeDeCourse();
        System.out.println("Résultat: " + (course2.isAccepter() ? "Acceptée" : "Refusée"));
        
        // Test Case 3: Student2 (Kouba->USTHB) with Teacher1 (USTHB->...Kouba) - Should fail (incompatible directions)
        System.out.println("\nTest 3: Sarah (Kouba->USTHB) avec Yacine (USTHB->...Kouba)");
        Course course3 = new Course(student2, teacher1);
        course3.demandeDeCourse();
        System.out.println("Résultat: " + (course3.isAccepter() ? "Acceptée" : "Refusée"));
        
        // Test Case 4: Student2 (Kouba->USTHB) with ATS1 (...Kouba->USTHB) - Should succeed
        System.out.println("\nTest 4: Sarah (Kouba->USTHB) avec Karim (...Kouba->USTHB)");
        Course course4 = new Course(student2, ats1);
        course4.demandeDeCourse();
        System.out.println("Résultat: " + (course4.isAccepter() ? "Acceptée" : "Refusée"));
    }
}