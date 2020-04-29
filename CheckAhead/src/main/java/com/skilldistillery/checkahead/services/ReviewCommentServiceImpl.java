package com.skilldistillery.checkahead.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Review;
import com.skilldistillery.checkahead.entities.ReviewComment;
import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.repositories.ReviewCommentRepository;
import com.skilldistillery.checkahead.repositories.ReviewRepository;
import com.skilldistillery.checkahead.repositories.UserRepository;

@Service
public class ReviewCommentServiceImpl implements ReviewCommentService {

	@Autowired
	private ReviewCommentRepository repo;
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private ReviewRepository rRepo;

	@Override
	public List<ReviewComment> findAllComments() {
		return repo.findAll();
	}

	@Override
	public ReviewComment findCommentById(int id) {
		Optional<ReviewComment> comment = repo.findById(id);
		if(comment.isPresent()) {
			return comment.get();
		}
		else {
			return null;
		}
	}

	@Override
	public ReviewComment createComment(ReviewComment comment, int userId, int reviewId) {
		Optional<Review> rOpt = rRepo.findById(reviewId);
		if (rOpt.isPresent()) {
			comment.setReview(rOpt.get());			
		}else {
			return null;
		}
		Optional<User> uOpt = uRepo.findById(userId);
		if (uOpt.isPresent()) {
			comment.setUser(uOpt.get());	
		}else {
			return null;
		}
		comment.setCreatedAt(LocalDateTime.now());
		ReviewComment newComment = repo.saveAndFlush(comment);
		if (newComment != null) {
			return newComment;
		}
		return null;
	}

	@Override
	public ReviewComment updateComment(int id, ReviewComment comment) {
		Optional<ReviewComment> opt = repo.findById(id);
		if (opt.isPresent()) {
			ReviewComment managed = opt.get();
			managed.setId(id);
			managed.setContent(comment.getContent());
			managed.setReviewRating(comment.getReviewRating());
			managed.setActive(comment.isActive());
			managed.setUpdatedAt(LocalDateTime.now());
			return repo.saveAndFlush(managed);
		}
		return null;
	}

	@Override
	public boolean deleteComment(int id) {
		boolean result = false;
		Optional<ReviewComment> comment = repo.findById(id);
		if (comment.isPresent()) {
			repo.deleteById(id);
			result = true;
		}
		
		return result;
	}
}
