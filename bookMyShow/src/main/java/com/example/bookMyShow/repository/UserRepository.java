package com.example.bookMyShow.repository;

import com.example.bookMyShow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUserName(String username);
}
