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
@Table(name = "tproc")

public class TipoProcedimiento implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtproc;
	
	@Column(nullable = false, length = 6, unique = true)
	@NotEmpty(message = "El campo nombre del tipo de procedimiento no puede ser vacio")
	private String nomtproc;
	
	@Column(nullable = false, unique = true)
	@NotEmpty(message = "El campo detalle del tipo de procedimiento no puede ser vacio")
	private String detproc;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatypx;
	
	@PrePersist
	public void persistencia() {
		datecreatypx = new Date ();
	}
	@Temporal(TemporalType.DATE)
	private Date dateeditypx;
	
	@PreUpdate
	public void dateedit() {
		dateeditypx = new Date();
	}
	
	/*Los tipos de procedimientos pueden ser:
	 1. quirurgicos
	 2. Laboratorio
	 3. Ecografias
	 4. Rx
	 5. procedimientos no invasivos*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estypx_fk;
	
	private static final long serialVersionUID = 1L;

}
