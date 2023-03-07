package com.portfolioehadad.portfolio.repository;

import com.portfolioehadad.portfolio.models.Profile;
import com.portfolioehadad.portfolio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository <Profile, User> {

}
