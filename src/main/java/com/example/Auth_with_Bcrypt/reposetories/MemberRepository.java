package com.example.Auth_with_Bcrypt.reposetories;

import com.example.Auth_with_Bcrypt.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member , Long> {

    Member findByUserName(String userName);
}
