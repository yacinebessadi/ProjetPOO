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

    // Default constructor
    public Preferences() {
        this.sexePreferences = SexePreferences.SANS_PREFERENCE;
        this.musiquePreferences = MusiquePreferences.SANS_PREFERENCE;
        this.bagagesPreferences = BagagesPreferences.SANS_PREFERENCE;
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
        // Logic for comparing preferences
        public boolean isCompatibleWith(Preferences other) {
            return (this.sexePreferences == other.sexePreferences || this.sexePreferences == SexePreferences.SANS_PREFERENCE) &&
                   (this.musiquePreferences == other.musiquePreferences || this.musiquePreferences == MusiquePreferences.SANS_PREFERENCE) &&
                   (this.bagagesPreferences == other.bagagesPreferences || this.bagagesPreferences == BagagesPreferences.SANS_PREFERENCE);
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
