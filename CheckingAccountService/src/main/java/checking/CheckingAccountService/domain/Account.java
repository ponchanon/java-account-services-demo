package checking.CheckingAccountService.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountNumber;
    private String fullName;
    private String email;
    private List<Transaction> transactionList;

    public Account(){}
    public Account(int accountNumber, String fullName, String email) {
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.email = email;
        this.transactionList = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction){
        this.transactionList.add(transaction);
    }

    public void deposit(double amount){
        addTransaction( new Transaction(amount, "Deposit", LocalDate.now()));
    }
    public boolean withdraw(double amount){
        if (getBalance() >= amount){
            addTransaction( new Transaction(-amount, "Withdraw", LocalDate.now()));
            return true;
        }
        else{
            return false;
        }
    }

    public double getBalance() {
        return transactionList.stream().map(Transaction::getAmount)
                .reduce(Double::sum)
                .get();
    }
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
