package com.portfolioehadad.portfolio.repository;

import com.portfolioehadad.portfolio.models.UserPorfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPorfolio, String> {
}
