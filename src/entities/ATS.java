package entities;

public class ATS extends User {
    private int anneeRecrutement;
    private String serviceRattachement;
    private static int nbATS=0;

    // Constructor
    public ATS(String nom, String prenom, String matricule, double reputation, Profile profile, int anneeRecrutement, String serviceRattachement) {
        super(nom, prenom, matricule, reputation, profile); // Pass the Profile object
        this.anneeRecrutement = anneeRecrutement;
        this.serviceRattachement = serviceRattachement;
        nbATS++;
    }

    // Getters and Setters
    public int getAnneeRecrutement() {
        return anneeRecrutement;
    }
    public String getServiceRattachement() {
        return serviceRattachement;
    }
    public static int getNbATS() {
        return nbATS;
    }

    @Override
    public String toString() {
        return super.toString() + ", ATS{" +
                "anneeRecrutement=" + anneeRecrutement +
                ", serviceRattachement='" + serviceRattachement + '\'' +
                '}';
    }
}