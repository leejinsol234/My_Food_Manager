package org.myfm.team.repositories;

import org.myfm.team.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SurveyRepository extends JpaRepository<Survey, Long>, QuerydslPredicateExecutor<Survey> {
}
