package com.skilldistillery.checkahead.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="review_rating")
public class ReviewRating {
	
	// f i e l d s
	
	@EmbeddedId
	private ReviewRatingId id;
	
	@Column(name="rating_value")
	private int ratingValue;

	// m e t h o d s

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewRating other = (ReviewRating) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ReviewRatingId getId() {
		return id;
	}

	public void setId(ReviewRatingId id) {
		this.id = id;
	}

	public int getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(int ratingValue) {
		this.ratingValue = ratingValue;
	}

	public ReviewRating(ReviewRatingId id, int ratingValue) {
		super();
		this.id = id;
		this.ratingValue = ratingValue;
	}

	public ReviewRating() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReviewRating [id=").append(id).append(", ratingValue=").append(ratingValue).append("]");
		return builder.toString();
	}
	
	
	
}
