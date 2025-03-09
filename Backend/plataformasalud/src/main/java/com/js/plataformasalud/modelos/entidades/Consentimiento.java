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
import jakarta.persistence.Lob;
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
@Table(name="coninfpx")

public class Consentimiento implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idconsinf;
	
	@Column(unique = true, length = 100)
	@NotEmpty(message = "El campo para el codigo del consentimiento no puede ser vacio")
	private String codconinf;
	
	@Lob
	@Column(nullable = false, columnDefinition = "LONGTEXT")
	@NotEmpty(message = "La descripcion del consentimiento no puede ser vacio")
	private String detconinf;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatconinf;
	
	@PrePersist
	public void datecreatconinf () {
		datecreatconinf = new Date ();
		
	}
	
	@Temporal(TemporalType.DATE)
	private Date editconinf;
	
	@PreUpdate
	public void editconinf(){
		editconinf = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estconinf_fk;
	
	
	private static final long serialVersionUID = 1L;

}
