package entities;

public class ATS extends User {
    private int anneeRecrutement;
    private String serviceRattachement;

    // Constructor
    public ATS(String nom, String prenom, String matricule, double reputation, int anneeRecrutement, String serviceRattachement) {
        super(nom, prenom, matricule, reputation); // Call the superclass constructor
        this.anneeRecrutement = anneeRecrutement;
        this.serviceRattachement = serviceRattachement;
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

    @Override
    public String toString() {
        return super.toString() + ", ATS{" +
                "anneeRecrutement=" + anneeRecrutement +
                ", serviceRattachement='" + serviceRattachement + '\'' +
                '}';
    }
}
