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
@Table(name = "orgdes")

public class OrigenDestino implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idorgdes;
	
	
	@Column(length = 6, unique = true)
	@NotEmpty(message = "El campo origen o destino no puede ser vacio")
	private String nomorgdes;
	
	@Column(length = 30, unique = true)
	@NotEmpty(message = "El campo descripcion del origen o destino no puede ser vacio")
	private String detorgdes;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatorgdes;
	
	@PrePersist
	public void datecreat() {
		datecreatorgdes = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estorgdes_fk;
	
	
	
	private static final long serialVersionUID = 1L;

}
