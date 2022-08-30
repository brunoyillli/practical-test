package com.example.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.api.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findAllByOrderByNameAsc();

	@Query("FROM Customer c " + "WHERE LOWER(c.name) like %:paramTerm% OR LOWER(c.email) like %:paramTerm%")
	Page<Customer> findByNomeOrEmail(@Param("paramTerm") String paramTerm, Pageable pageable);
}
