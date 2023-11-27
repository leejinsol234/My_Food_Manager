package org.myfm.team.repositories;

import org.myfm.team.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ResultRepository extends JpaRepository<Result, Long>, QuerydslPredicateExecutor<Result> {
}
