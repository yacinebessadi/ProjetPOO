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
    




    
    // Constructor explicite
    public Profile(StatusUser statut, TypeCourse typeCourse, Preferences preferences, Disponibilite disponibilite) {
        this.statut = statut;
        this.typeCourse = typeCourse;
        this.preferences = preferences;
        this.disponibilite = disponibilite;
    }
    
    
    public int icrementRide(){
       return rideCount++;

    }
    // implicite
    public Profile() {
        this.statut = null;
        this.typeCourse = null;
        this.preferences = null;
        this.disponibilite = null;
    }

    









    // Getters and Setters
    public StatusUser getStatut() {
        return statut;
    }

    public void setStatut(StatusUser statut) {
        this.statut = statut;
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