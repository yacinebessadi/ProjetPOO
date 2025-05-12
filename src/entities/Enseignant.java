package entities;

import enums.FacultesUSTHB;

public class Enseignant extends User {
    private int anneeRecrutement;
    private FacultesUSTHB faculte;
    private static int nbEnseignants=0;

    public Enseignant(String nom, String prenom, String matricule, double reputation, Profile profile, int anneeRecrutement, FacultesUSTHB faculte) {
        super(nom, prenom, matricule, reputation, profile); // Pass the Profile object
        this.anneeRecrutement = anneeRecrutement;
        this.faculte = faculte;
        nbEnseignants++;
    }

    public int getAnneeRecrutement() {
        return anneeRecrutement;
    }

    public FacultesUSTHB getFaculte() {
        return faculte;
    }
    public static int getNbEnseignants(){
        return nbEnseignants;
    }

    @Override
    public String toString() {
        return super.toString() + ", Enseignant{" +
                "anneeRecrutement=" + anneeRecrutement +
                ", faculte='" + faculte + '\'' +
                '}';
    }
}