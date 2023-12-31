package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
//import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "pacientes")



public class Paciente implements Serializable {
	@Id
	@Column(nullable = false, unique = true )
	private Long numdocpac;
	
	@Column (nullable = false, length = 5 )
	@NotEmpty(message = "El tipo de documento no puede ser vacio")
	private String tipodoc;
	
	@Column (nullable = false, length = 15 )
	@NotEmpty(message = "El campo no puede ser vacio, digite el primer nombre")
	private String primernompac;
	
	
	private String segundonompac;
	
	@Column (nullable = false, length = 15 )
	@NotEmpty (message = "El campo no puede ser vacio, digite el primer apellido")
	private String primerapepac;
	
	
	
	private String segundoapepac;
	
	@Column (nullable = false, length = 10 )
	@NotEmpty (message = "El campo no puede ser vacio, seleccione el sexo del paciente")
	private String sexopac;
	
	@Column (nullable = false, length = 12 )
	@Temporal(TemporalType.DATE)
	@NotNull (message = "El campo fecha de nacimiento no puede ser vacio, seleccione fecha de nacimiento")
	private LocalDate fechanacpac;
	
	@Column (nullable = false, length = 3 )
	private Long edadpac;
	
	@PostPersist
	private void calculaedad() {
		if(fechanacpac != null) {
			LocalDate fechaactual = LocalDate.now();
			Period periodo = Period.between(fechanacpac, fechaactual);
			edadpac = (long) periodo.getYears();
		}
	}
	
	@Column (nullable = false)
	@NotEmpty(message = "El campo estado civil no puede ser vacio, por favor seleccione el estado civil")
	private String estadocivilpac;
	
	@Column (nullable = false, length = 40 )
	@NotEmpty(message = "El campo direccion del paciente no puede ser vacio, por favor digite la dirección")
	private String direccionpac;
	
	@Column (nullable = false, length = 40 )
	@NotEmpty(message = "El campo email no puede ser vacio, por favor digite el email del paciente")
	private String emailpac;
		
	@Column (nullable = false, length = 15 )
	private String contactopac;
	
	@Column (nullable = false, length = 30 )
	private String acudientepac;
	
	@Column (nullable = false, length = 15 )
	private String contactoacudientepac;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ciudad_idciudad")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Ciudad ciudad;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoPaciente tipac;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Entidad entidad;
	
	@Column(nullable = false, length = 12)
	@Temporal(TemporalType.DATE)
	private Date fechacreacionpac;
	
	@PrePersist
	public void prePersis() {
		fechacreacionpac = new Date();
	}
	
	@Column (nullable = false, length = 10 )
	private String estado;
	//Los estados pueden ser: Creado, Modificado, Eliminado
	
	private static final long serialVersionUID = 1L;

}
