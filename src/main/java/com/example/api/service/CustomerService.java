package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}
	
	public Customer create(Customer customer) {
		return repository.save(customer);
	}
	
	public void atualizar(  Long id, Customer customerAtualizado) {
		repository.findById(id)
		.map( customer -> {
			customer.setName(customerAtualizado.getName());
			customer.setEmail(customerAtualizado.getEmail());
			return repository.save(customer);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer não encontrado"));
	}
	
	public void deletar(Long id) {
		repository.findById(id)
		.map( customer -> {
			repository.delete(customer);
			return Void.TYPE;
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer não encontrado"));		
	}
	
	public Page<Customer> findByNomeOrEmail(String searchTerm, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");

		return repository.findByNomeOrEmail(searchTerm.toLowerCase(), pageRequest);
	}

}
