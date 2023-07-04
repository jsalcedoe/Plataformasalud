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

import com.js.plataformasalud.modelos.entidades.DiagnosticoEvent;
import com.js.plataformasalud.modelos.servicios.IDxEventServiceImp;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class DxEventRestController {
	@Autowired
	private IDxEventServiceImp dxevent;
	
	@GetMapping("/dxeventos")
	public List<DiagnosticoEvent> index(){
		return dxevent.findAll();
	}
	
	@GetMapping("/dxeventos/{iddxevent}")
	public DiagnosticoEvent mostrar(@PathVariable Long iddxevent) {
		return dxevent.findById(iddxevent);
	}
	
	@PostMapping("/dxeventos")
	public DiagnosticoEvent save (@RequestBody DiagnosticoEvent dxeventos) {
		return dxevent.save(dxeventos);
	}
	
	@PutMapping("/dxeventos/{iddxevent}")
	public DiagnosticoEvent Actualizar (@RequestBody DiagnosticoEvent dxeventos, @PathVariable Long iddxevent) {
		DiagnosticoEvent ActualizaDxEventos = dxevent.findById(iddxevent);
		
		ActualizaDxEventos.setDxpac(dxeventos.getDxpac());
		ActualizaDxEventos.setEventpac(dxeventos.getEventpac());
		ActualizaDxEventos.setFechadxpac(dxeventos.getFechadxpac());
		ActualizaDxEventos.setIddxpac(dxeventos.getIddxpac());
		ActualizaDxEventos.setTipdxpac(dxeventos.getTipdxpac());
		
		return dxevent.save(ActualizaDxEventos);
		
	}

}
