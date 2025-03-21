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
@Table(name = "cargo_user")
public class Cargo implements Serializable {
	
	//Esta entidad es la que almacena los cargos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcarguser;
	
	@Column(unique = true, length = 10)
	@NotEmpty(message = "El campo del cargo no puede ser vacio")
	private String nomcarg;
		
	@Column(length = 45, unique = true)
	@NotEmpty(message = "El campo detalle del cargo no puede estar vacio")
	private String detcarguser;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatcarguser;
	
	@PrePersist
	@PreUpdate
	public void prePersis() {
		datecreatcarguser = new Date();
	}
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estado_carguser;
	//El estado puede ser creado, modificado o eliminado
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Rol roluser;
	
	private static final long serialVersionUID = 1L;

}
