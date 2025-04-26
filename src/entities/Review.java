package entities;

public class Review {
	private int rating;
	private String comment;
	private User reviewer;  // Who wrote the review
	private User reviewee;  // Who is being reviewed
	

	public int getRating() {
		return rating;
	}

	public User getReviewee() {
		return reviewee;
	}
	
	// toString method for displaying review's information
	@Override
	public String toString () {
		return rating + "★:" + "comment: " + comment + "(by " + reviewer.getNom() + ")";
	}
}
