package org.myfm.team.repositories;

import org.myfm.team.entities.SurveyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SurveyItemRepository extends JpaRepository<SurveyItem, Long>, QuerydslPredicateExecutor<SurveyItem> {
}
