package entities;

public class Review {
	private Course ride;
	private int rating;
	private String comment;
	private User reviewer;  // Who wrote the review
	private User reviewee;  // Who is being reviewed	

	public Review(Course ride, int rating, String comment, User reviewer, User reviewee) {
	    this.ride = ride;
		this.rating = rating;
	    this.comment = comment;
	    this.reviewer = reviewer;
	    this.reviewee = reviewee;
	}
	
	public Course getRide() {
		return ride;
	}

	public int getRating() {
		return rating;
	}
	
	public String getcomment() {
		return comment;
	}

	public User getReviewer() {
		return reviewer;
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
