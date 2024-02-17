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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "habcami")

public class Camas implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idhab;
	
	@Column(unique = true, length = 10)
	@NotEmpty(message = "El campo habitacion o camilla no puede ser vacio")
	private String nomhab;
	
	@Column(length = 100)
	@NotEmpty(message = "El campo detalle no puede ser vacio")
	private String dethab;
	
	@Temporal(TemporalType.DATE)
	private Date fechacreahab;
	
	@PrePersist
	public void datecreahab() {
		fechacreahab = new Date ();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Ubicacion ubicahab;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado esthab_fk;

	
	private static final long serialVersionUID = 1L;

}
