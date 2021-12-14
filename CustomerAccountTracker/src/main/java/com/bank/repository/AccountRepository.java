package com.bank.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bank.account.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

	
	@Query(value="select acc_no from account where acc_no = ?1", nativeQuery= true)
	public Account getBalanceOf(int accNo);
	
	@Modifying
	@Transactional
	@Query(value="delete from customer_account where acc_no = ?1", nativeQuery= true)
	public void deleteFromCustomerAccount(int acc_no);
}
