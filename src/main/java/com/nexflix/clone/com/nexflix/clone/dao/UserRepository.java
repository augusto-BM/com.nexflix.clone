package com.nexflix.clone.com.nexflix.clone.dao;

import com.nexflix.clone.com.nexflix.clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
