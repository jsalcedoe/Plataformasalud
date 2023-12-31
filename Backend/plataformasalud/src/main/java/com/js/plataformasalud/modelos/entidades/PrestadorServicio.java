package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="prestadorservicio")

/* Esta entidad es la encargada de crear la IPS prestadora de salud */

public class PrestadorServicio implements Serializable  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, length = 2)
	private Long idprestserv;
	
	@Column(unique = true, length = 45)
	@NotEmpty(message = "El campo no puede ser vacio, digite el nombre del prestador de servicios")
	private String nomprestserv;
	
	@Column(length = 11)
	@NotEmpty(message = "El campo NIT no puede ser vacio")
	private String nitprestserv;
	
	@Column(length = 50)
	@NotEmpty(message = "El campo direccion del prestador no puede ser vacio")
	private String dirprestserv;
	
	@Column(length = 10)
	@NotEmpty(message = "El campo telefono de IPS no puede ser vacio")
	private String telprestserv;
	
	@Column(length = 3)
	private String tipdoc;
	
	@Column(length = 15)
	@NotEmpty(message = "El campo numero de documento del representante no puede ser vacio")
	private long numdocreprlegprest;
	
	@Column(length = 45)
	@NotEmpty(message = "El campo nombre del representante legal no puede ser vacio")
	private String reprlegprest;
		
	
	@Column(nullable = false, length = 12)
	@Temporal(TemporalType.DATE)
	private Date fechacreaprestserv;
	
	@PrePersist
	public void prePersis() {
		fechacreaprestserv = new Date();
	}
	
	@Column (nullable = false, length = 10 )
	private String estado;
	
	private static final long serialVersionUID = 1L;

}
