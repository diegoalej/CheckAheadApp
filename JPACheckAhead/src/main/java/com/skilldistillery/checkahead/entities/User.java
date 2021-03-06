package com.skilldistillery.checkahead.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	// f i e l d s
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String role;
	
	private boolean active;
	
	@Column(name = "date_created")
	private LocalDateTime dateCreated;
	
	@Column(name = "date_updated")
	private LocalDateTime dateUpdated;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@JsonIgnore
	@OneToMany(mappedBy = "creator")
	private List<Location> createdLocations;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<ReviewComment> comments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Review> reviews;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_favorite", 
		joinColumns = @JoinColumn(name = "location_id"), 
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<Location> favorites;
	
	// m e t h o d s

	public User() {
		super();
	}

	public User(int id, String username, String password, String email, String role, boolean active,
			LocalDateTime dateCreated, LocalDateTime dateUpdated, Address address, List<Location> createdLocations,
			List<ReviewComment> comments, List<Review> reviews, Set<Location> favorites) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.active = active;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.address = address;
		this.createdLocations = createdLocations;
		this.comments = comments;
		this.reviews = reviews;
		this.favorites = favorites;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Location> getCreatedLocations() {
		return createdLocations;
	}

	public void setCreatedLocations(List<Location> createdLocations) {
		this.createdLocations = createdLocations;
	}

	public Set<Location> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<Location> favorites) {
		this.favorites = favorites;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<ReviewComment> getComments() {
		return comments;
	}

	public void setComments(List<ReviewComment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
				+ role + ", active=" + active + ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated
				+ ", address=" + address + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	

	
}
