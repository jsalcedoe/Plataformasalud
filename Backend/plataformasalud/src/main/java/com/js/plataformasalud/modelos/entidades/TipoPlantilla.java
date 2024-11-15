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
@Table(name = "tytemp")

public class TipoPlantilla implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtytemp;
	
	@Column(unique = true, length = 10, nullable = false)
	@NotEmpty(message = "El campo tipo de plantilla no puede ser vacio")
	private String nomtytemp;
	
	@Column(nullable = false,unique = true)
	@NotEmpty(message = "El campo detalle de la plantilla no puede ser vacio")
	private String detytemp;
	
	@Temporal(TemporalType.DATE)
	private Date creadatetytemp;
	
	@PrePersist
	public void createdatetemp() {
		creadatetytemp = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado esttytemp_fk;

	
	
	
	
	private static final long serialVersionUID = 1L;

}
