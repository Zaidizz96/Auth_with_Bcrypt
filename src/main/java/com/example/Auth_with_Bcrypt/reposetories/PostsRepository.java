package com.example.Auth_with_Bcrypt.reposetories;

import com.example.Auth_with_Bcrypt.models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository  extends JpaRepository<Posts, Long> {

    List<Posts> findByMemberId(Long memberId);
}
