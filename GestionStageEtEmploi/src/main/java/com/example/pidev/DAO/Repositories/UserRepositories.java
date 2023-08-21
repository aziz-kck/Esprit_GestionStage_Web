package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Repository
public interface UserRepositories extends JpaRepository<User,Integer> {
    @Override
    User getById(Integer integer);
    // User findByMail(String s);
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.mail = ?1")
    int enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.password = ?1    WHERE a.mail = ?2")
    void resetPassword(String password,String email);
    @Query("SELECT u FROM User u WHERE u.mail = ?1")
    public User findByMail(String email);

    @Query("SELECT u FROM User u WHERE u.PasswordToken = ?1")
    public Optional<User> findByPasswordToken(String token);
    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public Optional<User> findByVerificationCode(String code);

    Optional<User> findByUsername(String username);
    boolean existsByMail(String mail);
}
