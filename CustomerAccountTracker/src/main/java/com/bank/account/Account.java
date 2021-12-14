package com.bank.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, nullable = true)
	private int accNo;
	private String accType;
	private int accBal;
	
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public int getAccBal() {
		return accBal;
	}
	public void setAccBal(int accBal) {
		this.accBal = accBal;
	}
	
	@Override
	public String toString() {
		return "Account [accNo=" + accNo + ", accType=" + accType + ", accBal=" + accBal + "]";
	}
	
	public Account(int accNo, String accType, int accBal) {
		super();
		this.accNo = accNo;
		this.accType = accType;
		this.accBal = accBal;
	}
	
	public Account() {
		super();
	}
	
	public Account(int accNo, String accType) {
		super();
		this.accNo = accNo;
		this.accType = accType;
	}
	
	public Account(int accNo, int accBal) {
		super();
		this.accNo = accNo;
		this.accBal = accBal;
	}
	
	
}
