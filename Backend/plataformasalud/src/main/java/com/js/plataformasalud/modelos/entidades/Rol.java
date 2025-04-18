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
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="rol")
@Getter
@Setter
public class Rol implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idrol;
	
	@Column(unique = true,length = 20)
	private String nomrol;
	
	@Column(unique = true, length = 50)
	private String desrol; // realiza la descripcion detallada del rol que se esta creando
	
	@Temporal(TemporalType.DATE)
	private Date creatrol;
	
	@PrePersist
	@PreUpdate
	public void prePersis() {
		creatrol = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estrol_fk;

	
	private static final long serialVersionUID = 1L;

}
