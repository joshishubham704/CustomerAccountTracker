package com.bank.transferFunds;

public class TransferFund {

	private int from;
	private int to;
	private int fund;
	
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getFund() {
		return fund;
	}
	public void setFund(int fund) {
		this.fund = fund;
	}
	
	@Override
	public String toString() {
		return "TransferFund [from=" + from + ", to=" + to + ", fund=" + fund + "]";
	}
	
	public TransferFund(int from, int to, int fund) {
		super();
		this.from = from;
		this.to = to;
		this.fund = fund;
	}
	
	public TransferFund() {
		super();
	}
	
	
}
