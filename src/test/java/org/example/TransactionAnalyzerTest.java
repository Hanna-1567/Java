package org.example;

import opg.example.Transaction;
import opg.example.TransactionAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static opg.example.TransactionAnalyzer.*;

public class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance() {
        // Створення тестових даних
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Створення екземпляру TransactionAnalyzer з тестовими даними

        // Виклик методу, який потрібно протестувати
        double result = calculateTotalBalance(transactions);

        // Перевірка результату
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }
    @Test
    public void testCountTransactionsByMonth() {
        // Підготовка тестових даних
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Створення екземпляру TransactionAnalyzer з тестовими даними

        int countFeb = countTransactionsByMonth(transactions, "02-2023");
        int countMar = countTransactionsByMonth(transactions, "03-2023");

        // Перевірка результатів
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }
    @Test
    public void testFindTopExpenses(){
        // Підготовка тестових даних
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("01-12-2023", 10000.0, "Зарплата")); // Дохід (не повинен потрапити)
        for (int i = 1; i <= 12; i++) {
            transactions.add(new Transaction("02-12-2023", -i * 100.0, "Витрата " + i));
        }

        List<Transaction> topExpenses =findTopExpenses(transactions);

        Assertions.assertEquals(10, topExpenses.size(), "Має повертатися рівно 10 найбільших витрат");
        Assertions.assertEquals(-1200.0, topExpenses.get(0).getAmount());
        Assertions.assertEquals(-300.0, topExpenses.get(9).getAmount());
    }
    @Test
    public void testFindHighestAndLowestExpenses(){
        Transaction big = new Transaction("01-03-2023", -500.0, "Витрата 1");   // початок періоду
        Transaction mid = new Transaction("15-03-2023", -120.0, "Витрата 2");
        Transaction small = new Transaction("31-03-2023", -5.0, "Витрата 3");   // кінець періоду
        Transaction income = new Transaction("10-03-2023", 1000.0, "Дохід");    // дохід, ігнорується
        Transaction before = new Transaction("28-02-2023", -9999.0, "До періоду");
        Transaction after = new Transaction("01-04-2023", -1.0, "Після періоду");
        List<Transaction> transactions = Arrays.asList(mid, before, big, income, after, small);

        ExpenseRange result = findHighestAndLowestExpenses(transactions, "01-03-2023", "31-03-2023");

        Assertions.assertSame(big, result.highest(), "Найбільша витрата визначена неправильно");
        Assertions.assertSame(small, result.lowest(), "Найменша витрата визначена неправильно");
    }
}

