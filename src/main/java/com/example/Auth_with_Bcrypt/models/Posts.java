package com.example.Auth_with_Bcrypt.models;

import javax.persistence.*;

@Entity
@Table
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String textContent;
    @ManyToOne
    @JoinColumn(name = "member_id" , nullable = false)
    private Member member;

    public Posts() {
    }

    public Posts(String textContent, Member member) {
        this.textContent = textContent;
        this.member = member;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
