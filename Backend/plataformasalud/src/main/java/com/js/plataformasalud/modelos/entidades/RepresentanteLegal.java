package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "repleg")

public class RepresentanteLegal implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idrepleg;
	
	@Column(nullable = false, length = 15)
	//@NotEmpty(message = "El campo documento del representante no puede ser vacio")
	@NotNull(message = "El campo documento del representante no puede ser vacio")
	private long docrepleg;
	
	@Column(nullable = false, length = 20)
	@NotEmpty(message = "El campo primer nombre no puede ser vacio")
	private String pnomrepleg;
	
	@Column(nullable = false, length = 20)
	@NotEmpty(message = "El campo de segundo nombre no puede ser vacio")
	private String snomrepleg;
	
	@Column(nullable = false, length = 20)
	@NotEmpty(message = "El campo de primer apellido no puede ser vacio")
	private String paperepleg;
	
	@Column(nullable = false, length = 20)
	@NotEmpty(message = "El campo de primer apellido no puede ser vacio")
	private String saperepleg;
	
	@Column(nullable = false, length = 40)
	@NotEmpty(message = "El campo de email del representante legal no puede ser vacio")
	private String emailrepleg;
	
	@Column(nullable = false)
	private Date datecreaterepleg;
	
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoDocumento tipdoc_fk;
	
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private PrestadorServicio idprestserv_fk;
	
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado status_fk;
	
	
	
	private static final long serialVersionUID = 1L;

}