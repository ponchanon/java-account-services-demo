package checking.CheckingAccountService.services;

import checking.CheckingAccountService.domain.Account;
import checking.CheckingAccountService.data.CheckingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckingAccountService {

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    public void deposit(int accountNumber, double amount) {
        Account account = checkingAccountRepository.findById(accountNumber);
        account.deposit(amount);
    }

    public boolean withdraw(int accountNumber, double amount) {
        Account account = checkingAccountRepository.findById(accountNumber);
        return account.withdraw(amount);
    }


    public double getBalance(int accountNumber) {
        Account account = checkingAccountRepository.findById(accountNumber);
        return account.getBalance();
    }

    public void createAccount(CheckingAccountDTO savingAccountDTO) {
        Account account = CheckingAccountAdapter.fromCheckingAccountDTOToCheckingAccount(savingAccountDTO);
        checkingAccountRepository.save(account);
    }

    public CheckingAccountDTO getAccount(int accountNumber) {
        Account account = checkingAccountRepository.findById(accountNumber);
        CheckingAccountDTO checkingAccountDTO = CheckingAccountAdapter.fromCheckingAccountToCheckingAccountDTO(account);
        return checkingAccountDTO;
    }

}

