package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name="regmedins")

// Esta entidad hace referencia a la aplcicacion de los medicamentos 

public class RegistroMedicamento implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idregmedins;
	
	@Column(length = 50)
	@NotEmpty(message = "El campo dosis no puede ser vacio")
	private String regdosismedins;
	
	@Column(length = 200)
	private String obsregmedins;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateregpmedins;
	
	@Column(nullable = false, length = 5)
	@NotNull(message = "El campo hora de aplicación no puede ser vacio")
	private LocalTime horaaplmedins;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private MedicamentoInsumo medinsreg_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private ViaAdministracion viadm_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Evento eventoregistromedins_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estregmedins_fk;

	private static final long serialVersionUID = 1L;

}
