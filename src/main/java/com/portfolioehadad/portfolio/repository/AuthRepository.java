package com.portfolioehadad.portfolio.repository;

import com.portfolioehadad.portfolio.models.UserPorfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository <UserPorfolio, String> {
     Optional<UserPorfolio> findOneByEmail(String email);

}
