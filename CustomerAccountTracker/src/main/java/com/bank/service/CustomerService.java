package com.bank.service;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.Account;
import com.bank.customer.Customer;
import com.bank.repository.AccountRepository;
import com.bank.repository.CustomerRepository;

import javassist.NotFoundException;

@Service
public class CustomerService {

	@Autowired
	AccountRepository accRepo;
	
	@Autowired
	CustomerRepository custRepo;
	
	public Customer addCustomer(Customer customer) 
	{
		Customer savedCustomer = custRepo.save(customer);
		return savedCustomer;
	}
	
	public Customer addAccount(Set<Account> account, int custId) 
	{
		Optional<Customer> customerById = custRepo.findById(custId);
		Customer updatedCustomer = customerById.get();
		updatedCustomer.setAccounts(account);
		custRepo.save(updatedCustomer);
		
		return updatedCustomer;
	}

	public List<Account> getAllAccounts()
	{
		List<Account> accounts = new ArrayList<Account>();
		accRepo.findAll().forEach(account->accounts.add(account));
		return accounts;
	}
	
	public List<Customer> getAllCustomers()
	{
		List<Customer> customers = new ArrayList<Customer>();
		custRepo.findAll().forEach(customer->customers.add(customer));
		return customers;
	}
	
	public Account getAccountById(int accNo) throws NotFoundException
	{
		Optional<Account> account = accRepo.findById(accNo);
		Account account1 = null;
		if(account!= null) 
		{
			System.out.println("checking account");
			account1 = account.get();
		}
		else 
		{
			throw new NotFoundException("Data Not Found");
		}
		return account1;
	}
	
	public Customer getCustomerById(int custId) throws ServerException 
	{
		Optional<Customer> customer = custRepo.findById(custId);
		Customer customer1 = null;
		if(customer != null) 
		{
			System.out.println("checking customer");
			customer1 = customer.get();
		} 
		else 
		{
			throw new ServerException("Not Found");
		}
		return customer1;
	}
	
	public Account getBalanceOf(int accNo) 
	{
		System.out.println(accNo+" in service");
		Optional<Account> accountCheck = accRepo.findById(accNo);
		Account account = null;
		if(accountCheck!= null) 
		{
			System.out.println("Fetching Balance");
			account = accRepo.getBalanceOf(accNo);
		}
		
		return account;
	}
	
	public Customer deleteCustomer(int id) {
		Optional<Customer> customers = custRepo.findById(id);
		Customer customer = customers.get();
		custRepo.delete(customer);
		//custRepo.deleteAccountByCustomerId(id);
		return customer;
	}
	
	public Account deleteAccount(int id) {
		accRepo.deleteFromCustomerAccount(id);
		Optional<Account> accounts = accRepo.findById(id);
		Account account = accounts.get();
		accRepo.delete(account);
		//custRepo.deleteAccountByCustomerId(id);
		return account;
	}
	
	public Account transferFund(int from, int to, int fund) 
	{
		Optional<Account> sender = accRepo.findById(from);
		Optional<Account> receiver = accRepo.findById(to);
		Account reduceFund = sender.get();
		Account raiseFund = receiver.get();
		reduceFund.setAccBal(reduceFund.getAccBal()-fund);
		raiseFund.setAccBal(raiseFund.getAccBal()+fund);
		accRepo.save(reduceFund);
		return accRepo.save(raiseFund);
	}
}
