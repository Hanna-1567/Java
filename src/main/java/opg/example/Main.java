package opg.example;

import java.util.List;

import static opg.example.TransactionAnalyzer.*;
import static opg.example.TransactionCSVReader.readTransactions;
import static opg.example.TransactionReportGenerator.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = readTransactions(filePath);

        double totalBalance = calculateTotalBalance(transactions);

        String monthYear = "01-2024";
        int transactionsCount = countTransactionsByMonth(transactions, monthYear);

        printBalanceReport(totalBalance);
        printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = findTopExpenses(transactions);
        printTopExpensesReport(topExpenses);

        String startDate = "01-12-2023";
        String endDate = "31-12-2023";
        ExpenseRange expenseRange = findHighestAndLowestExpenses(transactions, startDate, endDate);
        printExpenseRangeReport(startDate, endDate, expenseRange);

        printExpensesByCategory(transactions);
        printExpensesByMonth(transactions);
    }
}
