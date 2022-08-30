package com.example.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Endereco {
	
	 	@Id
	    @Column
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(length = 100)
	    private String rua;
	    
}
