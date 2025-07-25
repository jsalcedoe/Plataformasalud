package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "desqx")
public class DescripcionQuirurgica implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long idqx;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaprocqx;
	
	@Column(nullable = false, length = 5)
	@NotNull(message = "El campo hora del procedimiento no puede ser vacio")
	private LocalTime horainicioprocqx;
	
	@Column(nullable = false, length = 5)
	@NotNull(message = "El campo hora final no puede ser vacio")
	private LocalTime horafinprocqx;
	
	@Column(nullable = false, length = 2000)
	@NotEmpty(message = "El campo descripcion no puede ser vacio")
	private String descqx;
	
	private String matprot; // describe si se coloco material o protesis y si se coloca lo describe
	
	private String muespato; // Descripcion de las muestras anatopatologicas si es el caso incluye el tipo la cantidad la localizacion el aspecto
	
	private String complicqx; // Describe el tipo de complicacion detalladamente
	
	private String hallaqx; // describe los hallazgos encontrados
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Conducta conducqx_fk;//la conducta puede ser, hospitalizar, observacion, o salida
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Evento eventpxqx_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoAnestesia anestesia_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estpxqx_fk;
	
	@OneToMany(mappedBy = "descqx_fk", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
    private List<ProcedimientoDescripcionQX> procedimientos;

    @OneToMany(mappedBy = "desqx_fk", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<EquipoQx> equipoQx;
    
    @OneToMany(mappedBy = "desqx_fk", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DiagnosticoDescripcionQx> dxdesqx;
	
	private static final long serialVersionUID = 1L;
	
}
