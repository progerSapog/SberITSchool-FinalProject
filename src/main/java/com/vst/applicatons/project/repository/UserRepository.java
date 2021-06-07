package com.vst.applicatons.project.repository;

import com.vst.applicatons.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>
{
    @Override
    List<User> findAll();

    User findByEmail(String email);
}
