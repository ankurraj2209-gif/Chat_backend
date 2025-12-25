package com.gaurav.chat_backend.repository;

import com.gaurav.chat_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    User findUserByid(Long id);
}
