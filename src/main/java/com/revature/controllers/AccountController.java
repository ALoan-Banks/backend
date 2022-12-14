package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://aloanbanks-frontend-bucket.s3-website-us-east-1.amazonaws.com"}, allowCredentials = "true")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

//    @Authorized
    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") int accountId) {
        Optional<Account> optional = accountService.findByUserId(accountId);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

//    @Authorized
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> createAccount(@RequestBody Account account, @RequestHeader("Current-User") String userId) {
        User user = userService.findById(Integer.parseInt(userId));
        return ResponseEntity.ok(accountService.upsertAccount(account, user));
    }

//    @Authorized
    @GetMapping("/{id}/transaction")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("id") int accountId) {
        return ResponseEntity.ok(accountService.getAllTransactions(accountId));
    }

//    @Authorized
    @GetMapping("/{id}/{limit}/transactionTop")
    public ResponseEntity<List<Transaction>> getTopTransactions(@PathVariable("id") int accountId, @PathVariable("limit") int limit) {
        return ResponseEntity.ok(accountService.getTopTransactions(accountId, limit));
    }

//    @Authorized
    @PostMapping(value = "/{id}/transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> addTransaction(@PathVariable("id") int accountId, @RequestBody Transaction transaction) {
        return new ResponseEntity<>(accountService.upsertTransaction(accountId, transaction), HttpStatus.CREATED);
    }

//    @Authorized
    @GetMapping("/{id}/income")
    public ResponseEntity<List<Transaction>> getAllByType(@PathVariable("id") int accountId) {
        return ResponseEntity.ok(accountService.getAllPositiveTransactions(accountId));
    }

}
