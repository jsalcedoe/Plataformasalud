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
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "repleg")

public class RepresentanteLegal implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idrepleg;
	
	@Column(nullable = false, length = 15, unique = true)
	@NotNull(message = "El campo documento del representante no puede ser vacio")
	//@Pattern(regexp = "^[0-9]+$", message = "El campo solo debe contener números")
	private Long docrepleg;
	
	@Column(nullable = false, length = 20)
	@NotEmpty(message = "El campo primer nombre no puede ser vacio")
	private String pnomrepleg;
	
	@Column(nullable = false, length = 20)
	private String snomrepleg;
	
	@Column(nullable = false, length = 20)
	@NotEmpty(message = "El campo de primer apellido no puede ser vacio")
	private String paperepleg;
	
	@Column(nullable = false, length = 20)
	private String saperepleg;
	
	@Column(nullable = false, length = 40)
	@Email(message = "la direccion de correo electronico es incorrecta")
	@NotEmpty(message = "El campo de email del representante legal no puede ser vacio")
	private String emailrepleg;
	
	@Temporal(TemporalType.DATE)
	private Date datecreaterepleg;
	
	@PrePersist
	@PreUpdate
	public void prePersis() {
		datecreaterepleg = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoDocumento tipdocrepleg_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private PrestadorServicio idprestservrepleg_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado statusrepleg_fk;
	
	
	
	private static final long serialVersionUID = 1L;

}
