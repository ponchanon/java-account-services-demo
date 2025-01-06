package checking.CheckingAccountService.services;

import checking.CheckingAccountService.domain.Account;

public class CheckingAccountAdapter {
    public static CheckingAccountDTO fromCheckingAccountToCheckingAccountDTO(Account account) {
        return new CheckingAccountDTO(account.getAccountNumber(), account.getFullName(), account.getEmail(),account.getTransactionList());
    }

    public static Account fromCheckingAccountDTOToCheckingAccount(CheckingAccountDTO checkingAccountDTO) {
        return new Account(checkingAccountDTO.getAccountNumber(), checkingAccountDTO.getFullName(), checkingAccountDTO.getEmail());
    }
}
