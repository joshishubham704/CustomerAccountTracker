package com.bank.service;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.account.Account;
import com.bank.repository.AccountRepository;
import com.bank.repository.CustomerRepository;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)

public class ServiceTest {
	
	@InjectMocks
	CustomerService custService;
	@Mock
	CustomerRepository custRepo;
	@Mock
	AccountRepository accRepo;
	
	@Test
	@Order(1)
	void test_getAccount() 
	{
//		Account account = new Account();
//		account.setAccType("Savings");
//		account.setAccBal(12345);
//		
//		Account mockAccount = new Account();
//		mockAccount.setAccNo(1);
//		mockAccount.setAccType("Savings");
//		mockAccount.setAccBal(456);
		
//		Optional<Account> account = Optional.of(new Account(2, "Savings", 456));
//		when(accRepo.findById(any(Integer.class))).thenReturn(account);
//		Account savedAccount = custService.getAccountById(1);
//
//		System.out.println(savedAccount.getAccNo() + "\t" + savedAccount.getAccNo());
//		assertEquals(2, savedAccount.getAccNo());

	}
	
}
