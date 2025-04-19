package entities;

import enums.StatusUser;
import enums.TypeCourse;

public class Profile {
    private StatusUser statut; // User's status (e.g., CHAUFFEUR, PASSAGER)
    private TypeCourse typeCourse; // Trip type preference
    private Preferences preferences; // Encapsulates sexe, musique, and bagages preferences

    // Constructor
    public Profile(StatusUser statut, TypeCourse typeCourse, Preferences preferences) {
        this.statut = statut;
        this.typeCourse = typeCourse;
        this.preferences = preferences;
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

    @Override
    public String toString() {
        return "Profile{" +
                "statut=" + statut +
                ", typeCourse=" + typeCourse +
                ", preferences=" + preferences +
                '}';
    }
}