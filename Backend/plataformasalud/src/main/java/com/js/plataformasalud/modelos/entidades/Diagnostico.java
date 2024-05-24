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
@Table(name = "dx")

public class Diagnostico implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clavedx;
	
	@NotEmpty(message = "El campo codigo del diagnostico no puede ser vacio, por favor indique el codigo del diagnostico")
	private String iddx;
	
	@Column(nullable = false,unique = true)
	@NotEmpty(message = "El campo nombre del diagnostico no puede ser vacio, por favor digite el nombre del diagnostico")
	private String nomdx;
	
	@Column(nullable = false)
	@NotEmpty(message = "El campo descripcion del diagnostico no puede ser vacio")
	private String descdx;
	
	@Column(nullable = false, length = 10)
	private String sexdx; // este campo hace referencia al sexo al cual aplica el diagnostico puede ser 1. masculino, 2. femenino, 3. Ambos
	
	@Column(nullable = false, length = 1)
	private Long edadmindx;
	
	@Column(nullable = false, length = 2)
	private Long edadmaxdx;
	
	@Column(nullable = false, length = 3)
	private String capdx;
	
	@Temporal(TemporalType.DATE)
	private Date creatdatedx;
	
	@PrePersist
	@PreUpdate
	public void creadx () {
		creatdatedx = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estdx_fk;// El estado puede ser: activo o inactivo
	
	private static final long serialVersionUID = 1L;

}
