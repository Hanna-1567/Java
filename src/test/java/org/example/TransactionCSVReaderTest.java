package org.example;

import opg.example.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static opg.example.TransactionCSVReader.readTransactions;

public class TransactionCSVReaderTest {
    @Test
    public void testReadTransactionsFromCSV(@TempDir Path tempDir) throws IOException {
        Path csv = tempDir.resolve("test.csv");
        Files.writeString(csv, """
                15-01-2024,-1200,Оренда квартири
                16-01-2024,-350,Аптека
                17-01-2024,10000,Зарплата
                """);

        List<Transaction> transactions = readTransactions(csv.toUri().toURL().toString());

        Assertions.assertEquals(3, transactions.size(), "Кількість транзакцій з CSV неправильна");
        Assertions.assertEquals("15-01-2024", transactions.get(0).getDate());
        Assertions.assertEquals(-1200.0, transactions.get(0).getAmount());
        Assertions.assertEquals("Оренда квартири", transactions.get(0).getDescription());
        Assertions.assertEquals(10000.0, transactions.get(2).getAmount());
    }
}
