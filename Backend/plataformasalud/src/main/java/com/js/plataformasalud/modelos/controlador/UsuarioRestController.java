package com.js.plataformasalud.modelos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.Usuario;
import com.js.plataformasalud.modelos.servicios.IUsuarioServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class UsuarioRestController {
	@Autowired
	private IUsuarioServiceImpl userservice;
	
	@GetMapping("/usuarios")
	public List<Usuario> index(){
		return userservice.findAll();
	}
	
	@GetMapping("/usuario/{idusuario}")
	public Usuario mostrar(@PathVariable Long idusuario) {
		return userservice.findById(idusuario);
	}
	
	@PostMapping("/usuario")
	public Usuario crear (@RequestBody Usuario user) {
		return userservice.save(user);
	}
	
	@PutMapping("/usuario/{idusuario}")
	public Usuario update (@RequestBody Usuario user, @PathVariable Long idusuario) {
		Usuario userActualizado = userservice.findById(idusuario);
		
		userActualizado.setApellidosusuario(user.getApellidosusuario());
		userActualizado.setEspecialidad(user.getEspecialidad());
		userActualizado.setFirma(user.getFirma());
		userActualizado.setHabilitado(user.getHabilitado());
		userActualizado.setNombresusuario(user.getNombresusuario());
		userActualizado.setPassword(user.getPassword());
		userActualizado.setTarjprofusuario(user.getTarjprofusuario());
		userActualizado.setUsername(user.getUsername());
		
		return userservice.save(userActualizado);
		
	}

}
