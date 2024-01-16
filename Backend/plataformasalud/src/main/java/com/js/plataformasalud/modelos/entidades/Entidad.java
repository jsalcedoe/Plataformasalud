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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="eapb")

public class Entidad implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ideapb;
	
	@Column(nullable = false, length = 50, unique = true)
	@NotEmpty(message = "El campo entidad no puede ser vacio")
	private String nomeapb;
	
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "El campo direccion de la entidad no puede ser vacio")
	private String direapb;
	
	@Column(nullable = false, length = 15)
	@NotEmpty(message = "El campo número de contacto de la entidad no puede ser vacio")
	private String contaceapb;
	
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "El campo email de la entidad no puede ser vacio")
	@Email(message = "Digite una direccion de email valida")
	private String emaileapb;
	
	@Column(nullable = false, length = 45)
	@NotEmpty(message = "El campo gerente no puede ser vacio")
	private String gerenteapb;
	
	@Temporal(TemporalType.DATE)
	private Date datecreateapb;
	
	@PrePersist
	public void createapb () {
		datecreateapb = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoEAPB tipent;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado esteapb_fk;
	
	private static final long serialVersionUID = 1L;

}
