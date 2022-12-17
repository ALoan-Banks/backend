package com.revature.services;

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.TransactionType;
import com.revature.models.User;
import com.revature.repositories.AccountRepository;
import com.revature.repositories.TransactionRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepositoryMock;
    @Mock
    private TransactionRepository transactionRepositoryMock;
    private AccountService accountService;
    private User tmpUser;
    private Account tmpAccount;
    private Transaction tmpTransaction;
    private Transaction[] tmpTransactions;


    @BeforeEach
    void setUp() {
        // service setup
        this.accountService = new AccountService(this.accountRepositoryMock, this.transactionRepositoryMock);

        // setup data
        this.tmpUser = new User(0, "fakefakefake@email.com", "Blunder", "Buster", "password", "02-20-84", "43 ElectricBoogalooLanes", "555-555-5555");
        this.tmpAccount = new Account(0, "Checking", 500.0, "my personal acccount", new Date(), this.tmpUser);
        this.tmpTransaction = new Transaction(0, 1000.0, "direct deposit", TransactionType.Income, this.tmpAccount);
        this.tmpTransactions = new Transaction[]{this.tmpTransaction};

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByUserId() {
        // mock response
        when(this.accountRepositoryMock.findByUserId(this.tmpUser.getId())).thenReturn(Optional.of(this.tmpAccount));

        // correct account returned
        assertEquals(this.tmpAccount, this.accountService.findByUserId(this.tmpUser.getId()).get());
    }

    @Test
    void upsertAccount() {
        // mock
        when(this.accountRepositoryMock.existsById(this.tmpAccount.getId())).thenReturn(true);
        when(this.accountRepositoryMock.getById(this.tmpAccount.getId())).thenReturn(this.tmpAccount);
        when(this.accountRepositoryMock.saveAndFlush(this.tmpAccount)).thenReturn(this.tmpAccount);

        // correct account returned
        assertEquals(this.tmpAccount, this.accountService.upsertAccount(this.tmpAccount, this.tmpUser));
    }

    @Test
    void getAllTransactions() {
        // mock
        when(this.accountRepositoryMock.getById(this.tmpAccount.getId())).thenReturn(this.tmpAccount);
        when(this.transactionRepositoryMock.findByAccount(this.tmpAccount)).thenReturn(Arrays.asList(this.tmpTransactions));

        // correct transactions returned
        assertArrayEquals(this.tmpTransactions, this.accountService.getAllTransactions(this.tmpAccount.getId()).toArray());
    }

    @Test
    void getAllPositiveTransactions() {
        // mock
        when(this.accountRepositoryMock.getById(this.tmpAccount.getId())).thenReturn(this.tmpAccount);
        when(this.transactionRepositoryMock.findByAccountAndType(this.tmpAccount, TransactionType.Income)).thenReturn(Arrays.asList(this.tmpTransactions));

        // correct transactions returned
        assertArrayEquals(this.tmpTransactions, this.accountService.getAllPositiveTransactions(this.tmpAccount.getId()).toArray());
    }

    @Test
    void getTopTransactions() {
        // mock
        when(this.accountRepositoryMock.getById(this.tmpAccount.getId())).thenReturn(this.tmpAccount);
        when(this.transactionRepositoryMock.findByAccountOrderByIdDesc(this.tmpAccount)).thenReturn(Arrays.asList(this.tmpTransactions));

        // correct transactions returned
        assertTrue(this.accountService.getTopTransactions(this.tmpAccount.getId(), 5).size()<=5);
    }

    @Test
    void upsertTransaction() {
        // mock
        when(this.accountRepositoryMock.getById(this.tmpAccount.getId())).thenReturn(this.tmpAccount);
        when(this.accountRepositoryMock.saveAndFlush(this.tmpAccount)).thenReturn(this.tmpAccount);
        when(this.transactionRepositoryMock.save(this.tmpTransaction)).thenReturn(this.tmpTransaction);

        // correct transaction returned
        assertEquals(this.tmpTransaction, this.accountService.upsertTransaction(this.tmpAccount.getId(), this.tmpTransaction));
    }
}