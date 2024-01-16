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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="prestserv")

/* Esta entidad es la encargada de crear la IPS prestadora de salud */

public class PrestadorServicio implements Serializable  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, length = 2)
	private Long idprestserv;
	
	@Column(unique = true, length = 45)
	@NotEmpty(message = "El campo no puede ser vacio, digite el nombre del prestador de servicios")
	private String nomprestserv;
	
	@Column(nullable = false, length = 11, unique = true)
	@NotEmpty(message = "El campo NIT no puede ser vacio")
	@Pattern(regexp = "^[0-9]+$", message = "El campo solo debe contener números")
	private Long docprestserv;
	
	@Column(nullable = false,length = 50)
	@NotEmpty(message = "El campo direccion del prestador no puede ser vacio")
	private String dirprestserv;
	
	@Column(nullable = false, length = 10)
	@NotEmpty(message = "El campo telefono de IPS no puede ser vacio")
	private String telprestserv;
	
	@Column(nullable = false, length = 30)
	@Email(message = "la direccion de correo es incorrecta")
	@NotEmpty(message = "El campo email de IPS no puede ser vacio")
	private String emailprestserv;
	
	@Temporal(TemporalType.DATE)
	private Date fechacreaprestserv;
	
	@PrePersist
	@PreUpdate
	public void prePersis() {
		fechacreaprestserv = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Ciudad ciuprestserv_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoDocumento tipdocprestserv_fk;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estadoprestserv_fk;
	
	private static final long serialVersionUID = 1L;

}
