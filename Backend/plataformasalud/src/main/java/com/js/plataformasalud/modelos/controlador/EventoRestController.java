package com.js.plataformasalud.modelos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.Evento;
import com.js.plataformasalud.modelos.servicios.EventoServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class EventoRestController {
	
	@Autowired
	private EventoServiceImpl eventoservice;
	
	@GetMapping("/eventos")
	public List<Evento> index(){
		return eventoservice.findAll();
	}
	
	@GetMapping("/eventos/{ideventpac}")
	public Evento cosultaeventoId(@PathVariable Long ideventpac) {
		return eventoservice.findById(ideventpac);
	}
	
	@PostMapping("/eventos")
	@ResponseStatus(HttpStatus.CREATED)
	public Evento crear (@RequestBody Evento evento) {
		return eventoservice.save(evento);
	}
	
	@PutMapping("/eventos/{ideventpac}")
	public Evento update (@RequestBody Evento evento, @PathVariable Long ideventpac) {
		Evento eventoActual = eventoservice.findById(ideventpac);
		
		eventoActual.setFechafineventpac(evento.getFechafineventpac());
		eventoActual.setFechainieventpac(evento.getFechainieventpac());
		eventoActual.setMoteventpac(evento.getMoteventpac());
		eventoActual.setPaciente(evento.getPaciente());
		eventoActual.setEstado(evento.getEstado());
		
		return eventoservice.save(eventoActual);
	}
	

}
