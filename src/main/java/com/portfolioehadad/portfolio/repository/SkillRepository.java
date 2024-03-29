package com.portfolioehadad.portfolio.repository;

import com.portfolioehadad.portfolio.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
