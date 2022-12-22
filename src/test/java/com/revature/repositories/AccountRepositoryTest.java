//package com.revature.repositories;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.revature.models.Account;
//import com.revature.models.User;
//
//@ExtendWith(MockitoExtension.class)
//public class AccountRepositoryTest {
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @InjectMocks
//    private Account account;
//
//    @InjectMocks
//    private User user;
//
//    @Test
//    public void testFindByUser() {
//        when(accountRepository.findByUser(any())).thenReturn(Optional.of(account));
//        Optional<Account> result = accountRepository.findByUser(user);
//        assertEquals(Optional.of(account), result);
//    }
//
//    @Test
//    public void testFindByUserId() {
//        when(accountRepository.findByUserId(any())).thenReturn(Optional.of(account));
//        Optional<Account> result = accountRepository.findByUserId(1);
//        assertEquals(Optional.of(account), result);
//    }
//}