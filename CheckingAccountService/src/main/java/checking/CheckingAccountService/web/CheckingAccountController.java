package checking.CheckingAccountService.web;

import checking.CheckingAccountService.services.CheckingAccountDTO;
import checking.CheckingAccountService.services.CheckingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CheckingAccountController {
    @Autowired
    CheckingAccountService checkingAccountService;

    @PostMapping("/checking")
    public ResponseEntity<?> createCheckingAccount(@RequestBody CheckingAccountDTO checkingAccountDTO) {
        checkingAccountService.createAccount(checkingAccountDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/checking/{accountNumber}")
    public CheckingAccountDTO getAccount(@PathVariable int accountNumber) {
        return checkingAccountService.getAccount(accountNumber);
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestParam ("accountNumber") int accountNumber, @RequestParam ("amount") double amount) {
        checkingAccountService.deposit(accountNumber, amount);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withDraw(@RequestParam ("accountNumber") int accountNumber, @RequestParam ("amount") double amount) {
        boolean result = checkingAccountService.withdraw(accountNumber, amount);
        if (result)
           return new ResponseEntity("withdraw succeeded", HttpStatus.OK);
        else
            return new ResponseEntity("not enough balance", HttpStatus.CONFLICT);
    }
}
