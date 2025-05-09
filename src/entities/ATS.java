package entities;

import enums.FacultesUSTHB;

public class ATS extends User {
    private int anneeRecrutement;
    private String serviceRattachement;
    private static int nbATS=0;
    private FacultesUSTHB faculte;

    // Constructor
    public ATS(String nom, String prenom, String matricule, double reputation, Profile profile, int anneeRecrutement, String serviceRattachement, FacultesUSTHB faculte) {
        super(nom, prenom, matricule, reputation, profile);
        this.anneeRecrutement = anneeRecrutement;
        this.serviceRattachement = serviceRattachement;
        this.faculte = faculte;
        nbATS++;
    }

    // Getters and Setters
    public int getAnneeRecrutement() {
        return anneeRecrutement;
    }

    public void setAnneeRecrutement(int anneeRecrutement) {
        this.anneeRecrutement = anneeRecrutement;
    }

    public String getServiceRattachement() {
        return serviceRattachement;
    }

    public void setServiceRattachement(String serviceRattachement) {
        this.serviceRattachement = serviceRattachement;
    }

    public static int getNbATS() {
        return nbATS;
    }

    public FacultesUSTHB getFaculte() {
        return faculte;
    }

    @Override
    public String toString() {
        return super.toString() + ", ATS{" +
                "anneeRecrutement=" + anneeRecrutement +
                ", serviceRattachement='" + serviceRattachement + '\'' +
                '}';
    }
}