package com.challenge.forohub.repository;

import com.challenge.forohub.domain.login.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    UserDetails findByUsername(String username);
}
