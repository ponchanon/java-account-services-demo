package checking.CheckingAccountService.services;

import checking.CheckingAccountService.domain.Transaction;

import java.util.List;

public class CheckingAccountDTO {
    private int accountNumber;
    private String fullName;
    private String email;
    private List<Transaction> transactionList;

    public CheckingAccountDTO(){}
    public CheckingAccountDTO(int accountNumber, String fullName, String email,List<Transaction> transactions) {
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.email = email;
        this.transactionList = transactions;
    }

    public void addTransaction(Transaction transaction){
        this.transactionList.add(transaction);
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