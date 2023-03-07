package com.portfolioehadad.portfolio.repository;

import com.portfolioehadad.portfolio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository <User, String> {
}
