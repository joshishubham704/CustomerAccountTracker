package com.bank.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.customer.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	@Modifying
    @Query(value="DELETE account c WHERE c.customer.custid = ?1", nativeQuery=true)
    void deleteAccountByCustomerId(int customerId);
}
