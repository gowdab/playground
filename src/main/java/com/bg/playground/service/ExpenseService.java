package com.bg.playground.service;

import com.bg.playground.model.Expense;
import com.bg.playground.model.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return (List<Expense>) expenseRepository.findAll();
    }
}
