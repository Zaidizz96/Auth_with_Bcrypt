package com.example.Auth_with_Bcrypt.controllers;

import com.example.Auth_with_Bcrypt.models.Member;
import com.example.Auth_with_Bcrypt.reposetories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;



}
