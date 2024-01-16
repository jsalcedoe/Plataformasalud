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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "departamentos")
@Getter
@Setter

public class Departamento implements Serializable {
	
	@Id
	@Column (nullable = false, length = 2, unique = true )
	private Long coddep;
	
	@Column (nullable = false, length = 30 )
	private String nomdep;
	
	@Column(nullable = false)
	private Date datecreatdep;
	@Temporal(TemporalType.DATE)
	
	@PrePersist
	public void prePersis() {
		datecreatdep = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estdep_fk;
	
	
	private static final long serialVersionUID = 1L;

}
