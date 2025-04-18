package entities;

public class Enseignant extends User {
    private int anneeRecrutement;
    private String faculte;

    public Enseignant(String nom, String prenom, String matricule, double reputation, int anneeRecrutement, String faculte) {
        super(nom, prenom, matricule, reputation);
        this.anneeRecrutement = anneeRecrutement;
        this.faculte = faculte;
    }

    public int getAnneeRecrutement() {
        return anneeRecrutement;
    }

    public void setAnneeRecrutement(int anneeRecrutement) {
        this.anneeRecrutement = anneeRecrutement;
    }

    public String getFaculte() {
        return faculte;
    }

    public void setFaculte(String faculte) {
        this.faculte = faculte;
    }

    @Override
    public String toString() {
        return super.toString() + ", Enseignant{" +
                "anneeRecrutement=" + anneeRecrutement +
                ", faculte='" + faculte + '\'' +
                '}';
    }
}