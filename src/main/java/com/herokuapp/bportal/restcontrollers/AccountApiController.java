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

    @DeleteMapping("/api/accounts/{id}")
    public void deleteAccount(@PathVariable(value = "id") Long accountId) {
        Optional<Account> accountExisted = accountRepository.findById(accountId);
        if (accountExisted.isPresent()) {
            accountRepository.deleteById(accountExisted.get().getId());
        }
    }

    /**
     * insert rows for test
     * @return
     */
    @GetMapping("/api/rows")
    public String insertRows() {
        Long numOfRows = 100L;
        Account account = new Account();
        account.setId(1L);
        account.setUsername("anhngoquoc");
        account.setEmail("anhngoquoc@gmail.com");
        account.setPassword("123456");
        account.setFirstName("Anh");
        account.setLastName("Ngo Quoc");
        accountRepository.save(account);
        for (long i = 2; i <= numOfRows; i++) {
            account.setId(i);
            account.setEmail("Ma" + i + "@mail.com");
            account.setFirstName("Fa" + i);
            account.setLastName("La" + i);
            account.setPassword("123456");
            account.setUsername("Ua" + i);
            accountRepository.save(account);
        }
        return "Inserted Success";
    }

    /**
     * delete all rows
     * @return
     */
    @DeleteMapping("/api/delall")
    public String deleteRows() {
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            accountRepository.deleteById(account.getId());
        }
        return "Deleted Success";
    }
}
