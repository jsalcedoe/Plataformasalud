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
@Table(name = "template")

public class Plantillas implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtemp;
	
	@Column(unique = true, length = 6)
	@NotEmpty(message = "El campo nombre de la plantilla no puede ser vacio")
	private String nametemp;
	
	@Column (length = 5000)
	@NotEmpty(message = "El campo detalle de la plantilla no puede ser vacio")
	private String dettemp;
	
	@Temporal(TemporalType.DATE)
	private Date creatdatetemp;
	
	@PrePersist
	public void datecreattemp() {
		creatdatetemp = new Date();
	}
	
	@Temporal(TemporalType.DATE)
	private Date edittemp;
	
	@PreUpdate
	public void dateedittemp() {
		edittemp = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoPlantilla typtemp_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado esttemp_fk;

	private static final long serialVersionUID = 1L;

}
