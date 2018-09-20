package com.herokuapp.bportal.restcontrollers;

import com.herokuapp.bportal.entities.Account;
import com.herokuapp.bportal.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountApiController {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Get all accounts
     * @return
     */
    @GetMapping("/api/accounts")
    public List<Account> retrieveAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * create an account
     * @param account
     * @return
     */
    @PostMapping("/api/account")
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    /**
     * get account by id
     * @param accountId
     * @return
     */
    @GetMapping("/api/accounts/{id}")
    public Optional<Account> retrieveAccount(@PathVariable(value = "id") Long accountId) {
        return accountRepository.findById(accountId);
    }

    /**
     * update an account
     * @param accountId
     * @param account
     * @return
     */
    @PutMapping("/api/accounts/{id}")
    public Account updateAccount(@PathVariable(value = "id") Long accountId, Account account) {
        Optional<Account> accountExisted = accountRepository.findById(accountId);
        accountExisted.get().setUsername(account.getUsername());
        accountExisted.get().setPassword(account.getPassword());
        return accountRepository.save(accountExisted.get());
    }

}
