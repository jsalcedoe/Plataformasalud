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

@Setter
@Getter
@Entity
@Table(name = "typdx")

public class TipoDiagnostico implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtypdx;
	
	@Column(unique = true, length = 6)
	@NotEmpty(message = "El campo tipo de diagnostico no puede ser vacio")
	private String namtypdx;
	
	private String detypdx;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatypdx;
	
	@PrePersist
	public void datecreat () {
		datecreatypdx = new Date ();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estyodx_fx;
	
	private static final long serialVersionUID = 1L;

}
