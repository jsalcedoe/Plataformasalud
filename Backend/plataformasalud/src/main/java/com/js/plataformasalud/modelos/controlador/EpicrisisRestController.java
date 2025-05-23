package com.js.plataformasalud.modelos.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.EpicrisisDTO;
//import com.js.plataformasalud.modelos.entidades.Evento;
import com.js.plataformasalud.modelos.servicios.IEpicrisisServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class EpicrisisRestController {
	
	private IEpicrisisServiceImpl episerv;
	 @GetMapping("/epicrisis/{idevent}")
	    public ResponseEntity<EpicrisisDTO> getEpicrisisByEvent(@PathVariable Long idevent) {
	        EpicrisisDTO epicrisisDTO = episerv.getEpicrisisByEvento(idevent);
	        return ResponseEntity.ok(epicrisisDTO);
	    }
}
