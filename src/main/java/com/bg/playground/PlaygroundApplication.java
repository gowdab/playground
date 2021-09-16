package com.bg.playground;

import com.bg.playground.model.Expense;
import com.bg.playground.model.repository.ExpenseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
    CommandLineRunner init(ExpenseRepository expenseRepository) {
        return args -> {
            Expense expense1 = new Expense();
            expense1.setItem("book");
            expense1.setPrice(23.6f);

            Expense expense2 = new Expense();
            expense2.setItem("tie");
            expense2.setPrice(20.9f);

            Expense expense3 = new Expense();
            expense3.setItem("bag");
            expense3.setPrice(29.6f);

            Expense expense4 = new Expense();
            expense4.setItem("pen");
            expense4.setPrice(2.6f);

            expenseRepository.saveAll(List.of(expense1, expense2, expense3, expense4));

        };
    }

}
