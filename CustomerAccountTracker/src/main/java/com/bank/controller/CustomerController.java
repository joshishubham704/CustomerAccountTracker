package com.bank.controller;

import java.rmi.ServerException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.Account;
import com.bank.customer.Customer;
import com.bank.service.CustomerService;
import com.bank.transferFunds.TransferFund;

import javassist.NotFoundException;

@RestController
public class CustomerController {

	@Autowired
	CustomerService custService;
	
	@GetMapping("/api/account")
	@ResponseBody
	public List<Account> getAllAccounts()
	{
		return custService.getAllAccounts();
	}
	
	@GetMapping("/api/account/{accNo}")
	public Account getAccountById(@PathVariable("accNo") int accNo) throws NotFoundException 
	{
		Account account = custService.getAccountById(accNo);
		if(account == null) 
		{
			throw new NotFoundException("Account with account no. "+accNo+" does not exist");
		}
		return account;
	}
	
	@GetMapping("/api/customer")
	public List<Customer> getAllCustomers()
	{
		return custService.getAllCustomers();
	}
	
	@GetMapping("/api/customer/{custId}")
	public Customer getCustomerById(@PathVariable("custId" )int custId) throws ServerException 
	{
		Customer customer = custService.getCustomerById(custId);
		if(customer == null) 
		{
			throw new ServerException ("Account with account no. "+custId+" does not exist");
		}
		return customer;
	}
	
	@GetMapping("/api/account/balance/{accNo}")
	public int getBalanceOf(@PathVariable("accNo") int accNo) throws NotFoundException 
	{
		Account account = custService.getBalanceOf(accNo);
		if(account == null) 
		{
			throw new NotFoundException("Account with account no. "+accNo+" does not exist");
		}
		int accBal = account.getAccBal();
		return accBal;
	}
	
	@PostMapping(path = "/api/customer/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws ServerException
	{
		Customer customerToSave = custService.addCustomer(customer);
		if ( customerToSave == null) {
	        throw new ServerException("Null Values");
	    } else {
	        return new ResponseEntity<>(customerToSave, HttpStatus.CREATED);
	    }
	}
	
	@PostMapping(path = "/api/customer/{custId}/account/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createAccount(@RequestBody Set<Account> account, @PathVariable("custId") int custId) throws ServerException
	{
		Customer customer = custService.getCustomerById(custId);
		Customer accountToSave = null;
		if(customer != null) 
		{
			System.out.println("Customer Found");
			accountToSave = custService.addAccount(account,custId);
		}
		
		if ( accountToSave == null) {
	        throw new ServerException("Null Values");
	    } else {
	        return new ResponseEntity<Customer>(accountToSave, HttpStatus.CREATED);
	    }
	}
	
	@DeleteMapping("/api/customer/{custid}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("custid") int custid) 
	{
		Customer tempCustomer = custService.deleteCustomer(custid);
		if(tempCustomer==null) 
		{
			throw new CustomerNotFound("Not Found---> "+custid);
		}
		else {
	        return new ResponseEntity<>(tempCustomer, HttpStatus.OK);
	    }
	}
	
	@DeleteMapping("/api/account/{accNo}")
	public ResponseEntity<Account> deleteAccount(@PathVariable("accNo") int accNo)
	{
		Account tempAccount = custService.deleteAccount(accNo);
		if(tempAccount == null) 
		{
			throw new CustomerNotFound("Not Found---> "+accNo);
		}
		else {
	        return new ResponseEntity<>(tempAccount, HttpStatus.OK);
	    }
	}
	
	@PutMapping(path = "/api/transfer-fund", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> transferFund(@RequestBody TransferFund transferFund)
	{
		Account account = custService.getBalanceOf(transferFund.getFrom());
		if(account.getAccBal()<transferFund.getFund()) 
		{
			System.out.println("Low Account Balance");
			Account transferFunds = null ;
			return new ResponseEntity<Account>(transferFunds, HttpStatus.NOT_ACCEPTABLE);		
		}
		else 
		{
			Account transferFunds =  custService.transferFund(transferFund.getFrom(), transferFund.getTo(), transferFund.getFund());
			
			return new ResponseEntity<Account>(transferFunds, HttpStatus.OK);
		}
		
		
	}
}
