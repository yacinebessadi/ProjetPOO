package entities;

public class Etudiant extends User {
    private int anneeAdmission;
    private String faculte;
    private String specialite;

    public Etudiant(String nom, String prenom, String matricule, double reputation, int anneeAdmission, String faculte, String specialite) {
        super(nom, prenom, matricule, reputation);
        this.anneeAdmission = anneeAdmission;
        this.faculte = faculte;
        this.specialite = specialite;
    }

    public int getAnneeAdmission() {
        return anneeAdmission;
    }

    public void setAnneeAdmission(int anneeAdmission) {
        this.anneeAdmission = anneeAdmission;
    }

    public String getFaculte() {
        return faculte;
    }

    public void setFaculte(String faculte) {
        this.faculte = faculte;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
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