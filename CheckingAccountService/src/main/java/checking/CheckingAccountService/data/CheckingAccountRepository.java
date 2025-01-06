package checking.CheckingAccountService.data;

import checking.CheckingAccountService.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class CheckingAccountRepository {
    private Map<Integer, Account> accounts = new HashMap<>();

    public Account findById(int accountNumber) {
        return accounts.get(accountNumber);
    }

    public Account save(Account account) {
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    public void delete(Account account) {
        accounts.remove(account.getAccountNumber());
    }

    public Collection<Account> findAll() {
        return accounts.values();
    }

}
