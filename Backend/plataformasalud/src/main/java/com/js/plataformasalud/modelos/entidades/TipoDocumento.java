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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tipdoc")

public class TipoDocumento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, length = 2)
	private Long idtipdoc;
	
	@Column(nullable = false, length = 5, unique = true)
	private String tipdoc;
	
	@Column(nullable = false, length = 45)
	private String detatipdoc;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date datecreatetipdoc;
	
	@PrePersist
	public void prePersis() {
		datecreatetipdoc = new Date();
	}
		
	private static final long serialVersionUID = 1L;

}
