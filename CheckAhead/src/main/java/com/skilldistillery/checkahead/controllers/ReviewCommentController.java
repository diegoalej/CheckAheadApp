package com.skilldistillery.checkahead.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.checkahead.entities.ReviewComment;
import com.skilldistillery.checkahead.services.ReviewCommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class ReviewCommentController {

	@Autowired
	ReviewCommentService svc;

	@GetMapping("comments")
	public List<ReviewComment> showAllComments(HttpServletResponse resp){
		List<ReviewComment> comments = svc.findAllComments();
		if (comments.size() > 0) {
			return comments;
		}
		else {
			resp.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("comments/{id}")
	public ReviewComment findCommentById(@PathVariable Integer id, HttpServletResponse resp) {
		ReviewComment comment = svc.findCommentById(id);
		if(comment != null) {
			return comment;
		}else {
			resp.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("locations/reviews/{reviewId}/comments")
	public List<ReviewComment> findByReview(
			@PathVariable("reviewId") Integer reviewId
			) {
		return svc.findByReview(reviewId);
	}
	
	@GetMapping("user/{userId}/comments")
	public List<ReviewComment> findByUser(
			@PathVariable("userId") Integer userId
			) {
		return svc.findByUser(userId);
	}
	

	@PostMapping("locations/reviews/{reviewid}/comments")
	public ReviewComment createNewComment(
			@RequestBody ReviewComment comment,
			@PathVariable Integer reviewid,
			HttpServletResponse resp,
			Principal principal
		){
		System.out.println(comment.getReview());
		ReviewComment newComment = svc.createComment(comment, reviewid, principal.getName());
		if (newComment != null) {
			return newComment;
		}
		else {
			resp.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("comments/{id}")
	public ReviewComment updateComment(
			@RequestBody ReviewComment comment, 
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		ReviewComment editComment = svc.updateComment(id, comment, principal.getName());
		if (editComment != null) {
			return editComment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("comments/{id}")
	public void deleteComment(
			@PathVariable int id, 
			HttpServletResponse resp,
			Principal principal
			){
		boolean result = false;
		try {
			result = svc.deleteComment(id, principal.getName());
			if (result == true) {
				resp.setStatus(204);
			}
		} catch (Exception e) {			
			resp.setStatus(404);
		}
	}
	
}
