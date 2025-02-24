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
@Table(name="medins")

// Esta entidad hace referencia a los diferentes medicamentos que existen en la base de datos

public class MedicamentoInsumo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idmedins;
	
	@Column(unique = true, length = 20)
	@NotEmpty(message = "El campo codigo del medicamento o insumo no puede ser vacio")
	private String cummedins;
	
	@Column(length = 100)
	@NotEmpty(message = "El campo medicamento o insumo no puede ser vacio")
	private String medins;
	
	@Column(length = 100)
	private String consmedins;
	
	@Column(length = 100)
	private String atcmedins;
	
	
	
	@Column(unique = true, length = 100)
	@NotEmpty(message = "El campo registro invima del medicamento o insumo no puede ser vacio")
	private String reginvmedinv;
		
	@Temporal(TemporalType.DATE)
	private Date datecreamedins;
	
	@PrePersist
	public void datecreamedins() {
		datecreamedins = new Date();
	}
	
	@Temporal(TemporalType.DATE)
	private Date datedimedins;
	
	@PreUpdate
	public void datedimedins() {
		datedimedins = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoMedicamentoInsumo tmedins_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private PresentacionMedicamentoInsumo pmedins_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private UnidadMedida umedins_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private FabricanteMedicamentoInsumo fmedins_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estmedins_fk;

	
	private static final long serialVersionUID = 1L;

}
