package com.tmt.hurricane.model.jobs;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

import java.util.Currency;
import java.util.Objects;

import com.tmt.hurricane.model.global.ERecurrence;

/**
 * the payment for a specified job
 * 
 * ERecurrence recurrence		the recurrence of the payment
 * double salary				the salary for this job
 * Currency currency			the currency of the salary
 */
public class Payment {
	
	private ERecurrence recurrence;			// the recurrence of the payment
	private double salary;					// the salary for this job
	private Currency currency;				// the currency of the salary
	
	public Payment(
			ERecurrence recurrence, 
			double salary, 
			Currency currency) {
		super();
		
		this.recurrence = recurrence;
		this.salary = salary;
		this.currency = currency;
	}

	public ERecurrence getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(ERecurrence recurrence) {
		this.recurrence = recurrence;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, recurrence, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(currency, other.currency) && recurrence == other.recurrence
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary);
	}

	@Override
	public String toString() {
		return "Payment [recurrence=" + recurrence + ", salary=" + salary + ", currency=" + currency + "]";
	}	
}
