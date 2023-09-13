package com.example.Auth_with_Bcrypt.controllers;

import com.example.Auth_with_Bcrypt.models.Member;
import com.example.Auth_with_Bcrypt.models.Posts;
import com.example.Auth_with_Bcrypt.reposetories.MemberRepository;
import com.example.Auth_with_Bcrypt.reposetories.PostsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class PostController {

    private final PostsRepository postsRepository;

    private final MemberRepository memberRepository;

    public PostController(PostsRepository postsRepository, MemberRepository memberRepository) {
        this.postsRepository = postsRepository;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/add-post-page")
    public String goToAddPostPage(HttpServletRequest request,Model model){
        HttpSession session= request.getSession();
        session.getAttribute("username");

        model.addAttribute("username" , session.getAttribute("username"));
        return "add-post.html";
    }

    @PostMapping("/add-post")
    public RedirectView addPost(HttpServletRequest request, String textContent){
        HttpSession session = request.getSession();

        if (session == null ||
                (session.getAttribute("username") == null || session.getAttribute("memberId") == null)) {
            return new RedirectView("error.html");
        }

        Long memberId = Long.parseLong(session.getAttribute("memberId").toString());
        Optional<Member> member = memberRepository.findById(memberId);

        if (!member.isPresent()) {
            throw new RuntimeException("this member is not exists");
        }

        Posts post = new Posts(textContent,member.get());

        postsRepository.save(post);
        return new RedirectView("/successLogin");
    }

}
