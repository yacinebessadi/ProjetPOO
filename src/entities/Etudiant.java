package entities;

import enums.FacultesUSTHB;
import enums.Specialite;

public class Etudiant extends User {
    private int anneeAdmission;
    private FacultesUSTHB faculte;
    private Specialite specialite;
    private static int nbEtudiants=0;

    public Etudiant(String nom, String prenom, String matricule, double reputation, Profile profile, int anneeAdmission, FacultesUSTHB faculte, Specialite specialite) {
        super(nom, prenom, matricule, reputation, profile); // Pass the Profile object
        this.anneeAdmission = anneeAdmission;
        this.faculte = faculte;
        this.specialite = specialite;
        nbEtudiants++;
    }

    public int getAnneeAdmission() {
        return anneeAdmission;
    }

    public void setAnneeAdmission(int anneeAdmission) {
        this.anneeAdmission = anneeAdmission;
    }

    public FacultesUSTHB getFaculte() {
        return faculte;
    }

    public void setFaculte(FacultesUSTHB faculte) {
        this.faculte = faculte;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
    public static int getNbEtudiants() {
        return nbEtudiants;
    }


    @Override
    public String toString() {
        return super.toString() + ", Etudiant{" +
                "anneeAdmission=" + anneeAdmission +
                ", faculte='" + faculte + '\'' +
                ", specialite='" + specialite + '\'' +
                '}';
    }
}