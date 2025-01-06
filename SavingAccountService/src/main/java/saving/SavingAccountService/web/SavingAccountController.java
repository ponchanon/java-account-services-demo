package saving.SavingAccountService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saving.SavingAccountService.services.SavingAccountDTO;
import saving.SavingAccountService.services.SavingAccountService;

@RestController
public class SavingAccountController {
    @Autowired
    SavingAccountService savingAccountService;

    @PostMapping("/saving")
    public ResponseEntity<?> createSavingAccount(@RequestBody  SavingAccountDTO savingAccountDTO) {
        savingAccountService.createAccount(savingAccountDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/saving/{accountNumber}")
    public SavingAccountDTO getAccount(@PathVariable int accountNumber) {
        return savingAccountService.getAccount(accountNumber);
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestParam ("accountNumber") int accountNumber, @RequestParam ("amount") double amount) {
        savingAccountService.deposit(accountNumber, amount);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withDraw(@RequestParam ("accountNumber") int accountNumber, @RequestParam ("amount") double amount) {
        boolean result = savingAccountService.withdraw(accountNumber, amount);
        if (result)
            return new ResponseEntity("withdrawn", HttpStatus.OK);
        else
            return new ResponseEntity("Insufficient Balance", HttpStatus.OK);
    }
}
