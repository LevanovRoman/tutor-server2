package com.testing.questions_history.repository;

import com.testing.questions_history.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
//    Optional<Users> findByEmail(String email);
}
