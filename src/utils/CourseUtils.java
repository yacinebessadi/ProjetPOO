package utils;

import entities.User;
import enums.StatusUser;


public class CourseUtils {


    //pour voir si le  un user est bien un pass et un chauff
    public static boolean isValidPassengerAndDriver(User passenger, User driver) {
        return passenger.getProfile().getStatut() == StatusUser.PASSAGER &&
               driver.getProfile().getStatut() == StatusUser.CHAUFFEUR;
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
                                                                                                                                                
