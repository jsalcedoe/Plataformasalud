package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idusuario;
	
	@Column(unique = true, length = 20)
	private String username;
	
	@Column(length = 60, nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String nombresusuario;
	
	@Column (nullable = false)
	private String apellidosusuario;
	
	private String tarjprofusuario;
	
	private String firma;
	
	@Column (nullable = false)
	private Boolean habilitado;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "especialidades_idespecialidades")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Especialidades especialidad ;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<OrdenesProcExam> ordxuser;
	
	public Usuario() {
		this.ordxuser = new ArrayList<OrdenesProcExam>();
	}



	private static final long serialVersionUID = 1L;

}
