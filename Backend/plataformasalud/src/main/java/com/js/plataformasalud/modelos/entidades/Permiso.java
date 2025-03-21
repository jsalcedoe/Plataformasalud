package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "permission")

public class Permiso implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idperm;
	
	@Column(unique = true, length = 35)
	@NotEmpty(message = "El campo permiso no puede ser vacio")
	private String namperm;
	
	@Column(length = 100)
	@NotEmpty(message = "El campo detalle del permiso no puede ser vacio")
	private String detperm;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatperm;
	
	@PrePersist
	@PreUpdate
	public void datecreatpermission(){
		datecreatperm = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"},allowSetters = true)
	private Estado estperm_fk;

	
	private static final long serialVersionUID = 1L;

}
