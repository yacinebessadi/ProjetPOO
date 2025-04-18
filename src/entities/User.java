/**
 * 
 */
package entities;

/**
 * 
 */
public class User {
    private String nom;
    private String prenom;
    private String matricule;
    private double reputation;

    // Constructor
    public User(String nom, String prenom, String matricule, double reputation) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.reputation = reputation;
    }

    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public double getReputation() {
        return reputation;
    }

    public void setReputation(double reputation) {
        this.reputation = reputation;
    }

    // toString method for displaying user information
    @Override
    public String toString() {
        return "User{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", matricule='" + matricule + '\'' +
                ", reputation=" + reputation +
                '}';
    }
}