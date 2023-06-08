package com.iwi.board.repository;

import com.iwi.board.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);
    @Query(value = "SELECT MAX(id) FROM UserEntity")
    Long findMaxId();
}
