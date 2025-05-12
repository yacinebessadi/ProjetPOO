package entities;
import enums.StatusUser;
import enums.TypeCourse;

public class Profile {
    private StatusUser statut; // User's status (e.g., CHAUFFEUR, PASSAGER)
    private TypeCourse typeCourse; // Trip type preference
    private Preferences preferences; // Encapsulates sexe, musique, and bagages preferences
    private Disponibilite disponibilite; // User's availability

    //ajouter 
    private int rideCount;  //ajouter une methode qui fait rideCount++
    private int nbPlaces; // Number of places if chauffeur, null if passenger

   // Chauffeur constructor
    public Profile(StatusUser statut, TypeCourse typeCourse, Preferences preferences, Disponibilite disponibilite, int nbPlaces) {
    this.statut = statut;
    this.typeCourse = typeCourse;
    this.preferences = preferences;
    this.disponibilite = disponibilite;
    this.nbPlaces = nbPlaces;
   }
   //passenger constructor
   public Profile(StatusUser statut, TypeCourse typeCourse, Preferences preferences, Disponibilite disponibilite) {
    this.statut = statut;
    this.typeCourse = typeCourse;
    this.preferences = preferences;
    this.disponibilite = disponibilite;
    this.nbPlaces = 0; 
}
    
   // Getters and Setters
    public StatusUser getStatut() {
        return statut;
    }

    public TypeCourse getTypeCourse() {
        return typeCourse;
    }

    public void setTypeCourse(TypeCourse typeCourse) {
        this.typeCourse = typeCourse;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public Disponibilite getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Disponibilite disponibilite) {
        this.disponibilite = disponibilite;
    }
    public int getRideCount()
	    { return rideCount;}

    public int icrementRide(){
        return rideCount++;
 
     }
    public int getnbPlaces(){
        return this.nbPlaces;

    }


    public void decrementNbPlaces(){
        if(this.statut==StatusUser.CHAUFFEUR && this.nbPlaces>0){
            this.nbPlaces--; //i call this methode each time a Course is created because
                              //the number of places is decremented each time a course is created
        }
    }

    // Une méthode pour modifier le statut de l'utilisateur
    public void changerStatus(StatusUser statut){
    this.statut = statut;
     }
    
    
    @Override
    public String toString() {
        return "Profile{" +
                "statut=" + statut +
                ", typeCourse=" + typeCourse +
                ", preferences=" + preferences +
                ", disponibilite=" + disponibilite +
                '}';
    }
}