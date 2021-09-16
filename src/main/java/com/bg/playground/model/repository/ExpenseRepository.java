package com.bg.playground.model.repository;

import com.bg.playground.model.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

}
