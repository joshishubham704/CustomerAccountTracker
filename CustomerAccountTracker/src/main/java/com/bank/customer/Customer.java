package com.bank.customer;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bank.account.Account;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int custid;
	private String first_name;
	private String last_name;
	private String email;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "customer_account",
			joinColumns = @JoinColumn(name="custid"),
			inverseJoinColumns = @JoinColumn(name="accNo")
			)
	private Set<Account> accounts;
	public int getCustid() {
		return custid;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
	@Override
	public String toString() {
		return "Customer [custid=" + custid + ", first_name=" + first_name + ", last_name=" + last_name + ", email="
				+ email + ", accounts=" + accounts + "]";
	}
	public Customer(int custid, String first_name, String last_name, String email, Set<Account> accounts) {
		super();
		this.custid = custid;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.accounts = accounts;
	}
	public Customer() {
		super();
	}
	public Customer(int custid) {
		super();
		this.custid = custid;
	}
	public Customer(int custid, String first_name, String last_name, String email) {
		super();
		this.custid = custid;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
	}
	public Customer(int custid, Set<Account> accounts) {
		super();
		this.custid = custid;
		this.accounts = accounts;
	}
	
}
