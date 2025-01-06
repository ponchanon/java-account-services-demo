package saving.SavingAccountService.services;

import saving.SavingAccountService.domain.Account;

public class SavingAccountAdapter {
    public static SavingAccountDTO fromSavingAccountToSavingAccountDTO(Account account) {
        return new SavingAccountDTO(account.getAccountNumber(), account.getFullName(), account.getEmail(), account.getTransactionList());
    }

    public static Account fromSavingAccountDTOToSavingAccount(SavingAccountDTO savingAccountDTO) {
        return new Account(savingAccountDTO.getAccountNumber(), savingAccountDTO.getFullName(), savingAccountDTO.getEmail());
    }
}
