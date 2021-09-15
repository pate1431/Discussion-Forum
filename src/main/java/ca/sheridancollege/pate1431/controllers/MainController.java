package ca.sheridancollege.pate1431.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.pate1431.beans.Comment;
import ca.sheridancollege.pate1431.beans.Members;
import ca.sheridancollege.pate1431.beans.Threads;
import ca.sheridancollege.pate1431.repositories.CommentRepository;
import ca.sheridancollege.pate1431.repositories.MembersRepository;
import ca.sheridancollege.pate1431.repositories.RoleRepository;
import ca.sheridancollege.pate1431.repositories.ThreadsRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController {

	private MembersRepository membersRepository;
	private ThreadsRepository threadsRepository;
	private RoleRepository roleRepository;
	private CommentRepository commentRepository;

	private String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public String registrationForm(@RequestParam String username, @RequestParam String password) {

		Members members = new Members(username, encodePassword(password), true);
		members.getRoleList().add(roleRepository.findByRolename("ROLE_USER"));
		membersRepository.save(members);

		return "redirect:/login";
	}

	@GetMapping("/secure")
	public String discussionIndex(Model model, Authentication authentication) {

		String name = authentication.getName();
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}

		model.addAttribute("name", name);
		model.addAttribute("roles", roleList);
		model.addAttribute("threadsList", threadsRepository.findAll());
		model.addAttribute("commentList", commentRepository.findAll());

		return "secure/index";
	}

	@GetMapping("/createThread")
	public String createThread(Authentication authentication) {
		String name = authentication.getName();
		return "/secure/createDiscussion";
	}

	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return "error/permission-denied";
	}

	@GetMapping("/addThread")
	public String addThread(Model model, Authentication authentication, @RequestParam String topic,
			@RequestParam String message) {
		String name = authentication.getName();
		Threads thread = new Threads();
		thread.setTopic(topic);
		thread.setMessage(message);
		thread.setName(authentication.getName());
		thread.setThreadDate(LocalDate.now());
		thread.setThreadTime(LocalTime.now());
		threadsRepository.save(thread);
		model.addAttribute("threadsList", threadsRepository.findAll());
		return "secure/index";
	}

	@GetMapping("/comment/{id}")
	public String comment(Model model, @PathVariable Long id, Authentication authentication) {
		String name = authentication.getName();
		model.addAttribute("threadList", threadsRepository.findAll());
		model.addAttribute("commentList", commentRepository.findById(id).get());
		return "secure/comment";
	}

	@PostMapping("/comment/{id}")
	public String comment(Model model, @PathVariable Long id, Authentication authentication,
			@RequestParam String comment) {
		String name = authentication.getName();
		List<Threads> threadList = threadsRepository.findAll();
		model.addAttribute("threadList", threadList);

		Threads thread = threadsRepository.findById(id).get();

		Comment comm = new Comment();
		comm.setComment(comment);
		comm.setName(authentication.getName());
		comm.setCdate(LocalDate.now());
		comm.setCtime(LocalTime.now());
		commentRepository.save(comm);

		thread.getCommentList().add(comm);

		List<Comment> commentList = commentRepository.findAll();
		threadsRepository.save(thread);
		model.addAttribute("threadsList", threadsRepository.findAll());
		model.addAttribute("comment", new Comment());
		model.addAttribute("commentList", commentList);
		return "secure/index";
	}

}
