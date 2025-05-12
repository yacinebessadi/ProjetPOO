package utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import entities.DemandeDeCourse;
import entities.User;
import enums.StatusUser;


public class CourseUtils {


    //pour voir si le  un user est bien un pass et un chauff
    public static boolean isValidPassengerAndDriver(User passenger, User driver) {
        return passenger.getProfile().getStatut() == StatusUser.PASSAGER &&
               driver.getProfile().getStatut() == StatusUser.CHAUFFEUR;
    }

    // cette methode va faire tous le travaille du matching elle va commencer par utiliser la methode canMatch 
    // du classe DemandeCourse qui va verifier itiniraire prefernces nbplaces du chauffeur si il sont compatibles
    //alors elle va afficher tous les chauffeur qui on compatible , le passager va choisir un chauffeur avec sont matricule
    // la methode choisirchaufeur va verifier avec la methode chauffeurExiste que la mat est correcte et eventuellemnt dans le 
    //main on ca cre une Course
    public static boolean afficherChauffeursDisponible(User passenger, List<User> utilisateurs) {
        System.out.println("Chauffeurs disponibles pour " + passenger.getNom() + " " + passenger.getPrenom() + " :\n");
        boolean found = false;
        for (User user : utilisateurs) {
            if(isValidPassengerAndDriver(passenger, user)){
            if (DemandeDeCourse.canMatch(passenger, user)) {
                System.out.println("Matricule: " + user.getMatricule() + " | " + user.getNom() + " " + user.getPrenom() + " | Réputation: " + user.getReputation());
                found = true;

            }
        }}
        if (!found) {
            System.out.println("Aucun chauffeur compatible trouvé. sorry :(");
        }
        return found;
    }



    //modify this to return the driver as type User
    //i dont use this in the GUI we use it only if we use a scanner and we execute in the terminal without interface
    public static boolean choisirChauffeur(List<User> utilisateurs) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le Matricule du chauffeur choisi : ");
        String matchauffeur = scanner.next();
        boolean exists = chauffeurExiste(matchauffeur, utilisateurs);
        if (exists) {
            System.out.println("Chauffeur trouvé ! L'application va cre une course !");
        } else {
            System.out.println("Wrong Matricule ! pas de Chauffeur avec ce Matricule");
            // je vais verifier est ce que je peut retourner null si le chauffeur n existe pas 
        }
        scanner.close();
        return exists;
    }

   public static boolean chauffeurExiste(String matricule, List<User> utilisateurs) {
    for (User user : utilisateurs) {
        if (user.getMatricule().equals(matricule)) {
            return true;
        }
    }
    return false;
}











































































        
    
    
    
    
    
    
    
    // example passenger ["Golf","USTHB"]
        //DRIVER (usthb is alwyas the last or the first in both lists)  [ "elbiar","Birmouradrais","Golf","Usthb"]
        //example2:
        //passenger[usthb, golf]
        //driver[usthb , golf,birmouradrais,elbiar]
        //we check if passenger[0]==driver[0] then they are in the same place , then we verify if passenger[1] is in driver
        // if yes then they are in the same itiniraire .
        //if passenger[0](usthb)==driver[0](usthb) && driver.contains(passenger[2]) then we match them because the driver pass by golf
        //else if (example 1)
        //now the condition one isnt verified like passenger[0]!=driver[0] so it must be passenger[1]=driver[last index=3]
        //because the app is for usthb students only like the university would always be either drop off or pick off
        //so else if (condition is verfied (the last two elements are the same==usthb)) && same we do driver.conatins(passenger[0])
       // and in the project it did not ask for the current location of the driver as les course sont prevues before.


//the first if treates these two examples
//passenger=[Usthb , Point c]
//driver [Usthb ,point a , point b , point c ,point d]
//passenger= [point a, usthb]
//driver = [point a, point b ,point c ,usthb]

// ce cas est quand le first if n'est pas true [usthb point c] and [point d point c point b point a usthb ]

//pour if numero 2
//passenger [point c , usthb]
//driver [ point a, point b ,point c ,point d ,usthb]



}
                                                                                                                                                
