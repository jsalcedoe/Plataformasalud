package com.js.plataformasalud.modelos.entidades;


import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ciudades")
@Getter
@Setter

public class Ciudad implements Serializable {
		 
	@Id
	@Column (nullable = false, length = 5, unique = true )
	private Long codciu;
	
	@Column (nullable = false, length = 30)
	private String nomciu;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatciu;
	
	@PrePersist
	@PreUpdate
	public void prePersis() {
		datecreatciu = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estciu_fk;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Departamento depciu_fk;
	
	
	
	private static final long serialVersionUID = 1L;

}
