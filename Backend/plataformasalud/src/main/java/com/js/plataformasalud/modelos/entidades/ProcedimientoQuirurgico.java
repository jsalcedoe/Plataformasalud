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
@Table(name = "desqx")

public class ProcedimientoQuirurgico implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private long idqx;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date fechaprocqx;
	
	@PrePersist
	public void creafechaqx() {
		fechaprocqx = new Date();
	}
	
	@Column(nullable = false, length = 5)
	@NotEmpty(message = "El campo hora del procedimiento no puede ser vacio")
	private Date horainicioprocqx;
	
	@Column(nullable = false, length = 5)
	@NotEmpty(message = "El campo hora final no puede ser vacio")
	private Date horafinprocqx;
	
	@Column(nullable = false, length = 20)
	@NotEmpty(message = "El campo tipo de herida no puede ser vacio, por favor seleccione el tipo de herida")
	private String tipoherida;
	
	@Column(nullable = false, length = 2000)
	@NotEmpty(message = "El campo descripcion no puede ser vacio")
	private String descqx;
	
	
	private String muespato;
	
	private String complicqx;
	
	private String hallaqx;
	
	@Column(nullable = false)
	@NotEmpty(message = "seleccione la conducta del paciente posterior al procedimiento")
	private String conducqx;
	//la conducta puede ser, hospitalizar, observacion, o salida
	
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private ProcedimientosExamenes qx;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoAnestesia anestesia;
	
	private static final long serialVersionUID = 1L;
	
}
