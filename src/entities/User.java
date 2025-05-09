package entities;


import java.util.ArrayList;

import enums.CourseStatus;

public abstract class User {
    private String nom;
    private String prenom;
    private String matricule;

    private double reputation;
    private Profile profile; // Profile object with StatusUser
    
    private ArrayList<Review> receivedReviews = new ArrayList<>();

    // Constructor
    public User(String nom, String prenom, String matricule, double reputation, Profile profile) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.reputation = reputation;
        this.profile = profile;
    }

    // Default constructor
    public User() {
        this.nom = null;
        this.prenom = null;
        this.matricule = null;
        this.reputation = 0.0;
        this.profile = null;
    }

    // Getters and Setters

   public String getNom() { return nom; }

   // public void setNom(String nom) {
     //   this.nom = nom;
    //}

    public String getPrenom() {
        return prenom;
    }

    // public void setPrenom(String prenom) {
    //     this.prenom = prenom;
    // }

    // public String getMatricule() {
    //     return matricule;
    // }

    // public void setMatricule(String matricule) {
    //     this.matricule = matricule;
    // }

    public double getReputation() {
        return reputation;
    }

    public void setReputation(double reputation) {
        this.reputation = reputation;
    }

    public Profile getProfile() {
        return profile;
    }

//    public void setProfile(Profile profile) {
  //      this.profile = profile;
   // }
    
 // getreviews method to display the list of reviews
    public void getreviews() {
    	if (receivedReviews.size() == 0) {
            System.out.println(nom + " has no reviews yet.");
            return;
        }
    	
    	System.out.println("Reviews for " + nom + ":");
        for (Review review : receivedReviews) {
            System.out.println(" - " + review);
        }
    }
    
    // addReview method to add a review to the list of reviews and update the user's reputation
    public void addReview(Review review) {
    	if (review.getRide().getStatusCourse() == CourseStatus.TERMINEE) {
            System.out.println("Cannot add review. The ride is not completed.");
            return;
        }
    	receivedReviews.add(review);
    	updatereputation();
    }
    
    // updatereputation method to calculate the user's reputation based on the reviews they have
    private void updatereputation() {
    	double sum = 0.0;
    	int size = receivedReviews.size();
    	
    	if (size == 0) {
    		return;
    	}
    	
    	for (int i = 0; i < size; i++) {
    		sum = sum + receivedReviews.get(i).getRating();
    	}
    	reputation = sum / size ;
    }

    // toString method for displaying user information
    @Override
    public String toString() {
        return "User{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", matricule='" + matricule + '\'' +
                ", reputation=" + reputation +
                ", profile=" + profile +
                '}';
    }
}




