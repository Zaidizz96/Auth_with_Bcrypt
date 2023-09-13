package com.example.Auth_with_Bcrypt.controllers;

import com.example.Auth_with_Bcrypt.models.Posts;
import com.example.Auth_with_Bcrypt.reposetories.PostsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    private final PostsRepository postsRepository;

    public HomeController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @GetMapping("/successLogin")
    public String getHomePageWithSecret(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("username") == null) {
            return "error.html";
        }

        String username= session.getAttribute("username").toString();

        model.addAttribute("username", username);
        return getLoggedInMemberPosts(request, model);
    }

    private String getLoggedInMemberPosts(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        Long memberId = Long.parseLong(session.getAttribute("memberId").toString());

        if (username == null || username.isEmpty()) {
            return "login.html";
        }

        List<Posts> postsList = postsRepository.findByMemberId(memberId);
        model.addAttribute("posts",postsList);

        return "home.html";
    }

}
