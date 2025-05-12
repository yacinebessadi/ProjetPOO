package entities;

public class Review {
	private int rating;
	private String comment;
	private User reviewer;  // Who wrote the review
	private User reviewee;  // Who is being reviewed
	
	public Review(int rating, String comment, User reviewer, User reviewee) {
		this.rating = rating;
		this.comment = comment;
		this.reviewer = reviewer;
		this.reviewee = reviewee;
	}

	public int getRating() {
		return rating;
	}

	public User getReviewee() {
		return reviewee;
	}
	
	public User getReviewer() {
		return reviewer;
	}

	// toString method for displaying review's information
	@Override
	public String toString () {
		return rating + "★:" + "comment: " + comment + "(by " + reviewer.getNom() + ")";
	}
}
