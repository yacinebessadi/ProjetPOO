package entities;

import enums.SexePreferences;
import enums.MusiquePreferences;
import enums.BagagesPreferences;

public class Preferences {
    private SexePreferences sexePreferences; // Gender preference
    private MusiquePreferences musiquePreferences; // Music preference
    private BagagesPreferences bagagesPreferences; // Luggage preference

    // Constructor
    public Preferences(SexePreferences sexePreferences, MusiquePreferences musiquePreferences, BagagesPreferences bagagesPreferences) {
        this.sexePreferences = sexePreferences;
        this.musiquePreferences = musiquePreferences;
        this.bagagesPreferences = bagagesPreferences;
    }
    
    // Implicite
    public Preferences() {
        this.sexePreferences = null;
        this.musiquePreferences = null;
        this.bagagesPreferences = null;
    }


    // Getters and Setters
    public SexePreferences getSexePreferences() {
        return sexePreferences;
    }

    public void setSexePreferences(SexePreferences sexePreferences) {
        this.sexePreferences = sexePreferences;
    }

    public MusiquePreferences getMusiquePreferences() {
        return musiquePreferences;
    }

    public void setMusiquePreferences(MusiquePreferences musiquePreferences) {
        this.musiquePreferences = musiquePreferences;
    }

    public BagagesPreferences getBagagesPreferences() {
        return bagagesPreferences;
    }

    public void setBagagesPreferences(BagagesPreferences bagagesPreferences) {
        this.bagagesPreferences = bagagesPreferences;
    }

    @Override
    public String toString() {
        return "Preferences{" +
                "sexePreferences=" + sexePreferences +
                ", musiquePreferences=" + musiquePreferences +
                ", bagagesPreferences=" + bagagesPreferences +
                '}';
    }
}