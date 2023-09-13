package com.example.Auth_with_Bcrypt.controllers;

import com.example.Auth_with_Bcrypt.models.Member;
import com.example.Auth_with_Bcrypt.reposetories.MemberRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/")
    public String getHomePage(){
        return "login.html";
    }

    @GetMapping("/signup")
    public String getSignupPage(Model model){
        Member member = new Member();
        model.addAttribute("member" , member);
        return "/signup.html";
    }

    @PostMapping("/login")
    public RedirectView logIn(HttpServletRequest request, String userName , String password){
        Member memberFromDb = memberRepository.findByUserName(userName);

        if(memberFromDb == null
                || (!BCrypt.checkpw(password, memberFromDb.getHashedPassword()))){
            return new RedirectView("/login");
        }

        HttpSession session= request.getSession();
        session.setAttribute("username", userName);
        session.setAttribute("memberId", memberFromDb.getId());
        return new RedirectView ("/successLogin");
    }

    @PostMapping("/singUp-member")
    public String createMember(@ModelAttribute(value = "member") Member member){
        String hashedPassword = BCrypt.hashpw(member.getHashedPassword(), BCrypt.gensalt(12));
        member.setHashedPassword(hashedPassword);
        memberRepository.save(member);
        return "/login.html";
    }

    @PostMapping("/logout")
    public String logOut(HttpServletRequest request){
        HttpSession session= request.getSession();
        session.invalidate();

        return "/login.html";
    }

}
