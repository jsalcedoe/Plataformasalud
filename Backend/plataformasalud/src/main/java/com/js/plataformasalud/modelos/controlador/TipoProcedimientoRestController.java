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

import com.js.plataformasalud.modelos.entidades.TipoProcedimiento;
import com.js.plataformasalud.modelos.servicios.ITipoProcServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class TipoProcedimientoRestController {
	@Autowired
	private ITipoProcServiceImpl itprocimp;
	
	@GetMapping("/tprocedimientos")
	public List<TipoProcedimiento> index(){
		return itprocimp.findAll();
	}
	
	@GetMapping("/tprocedimientos/{idtproc}")
	@ResponseStatus(code = HttpStatus.OK)
	public TipoProcedimiento mostrar(@PathVariable Long idtproc) {
		return itprocimp.findById(idtproc);
	}
	
	@PostMapping("/tprocedimientos")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TipoProcedimiento crear (@RequestBody TipoProcedimiento tproc) {
		return itprocimp.save(tproc);
		
	}
	
	@PutMapping("/tprocedimientos/{idtproc}")
	public TipoProcedimiento modificar (@RequestBody TipoProcedimiento tproc, @PathVariable Long idtproc) {
		TipoProcedimiento tprocActual = itprocimp.findById(idtproc);
		tprocActual.setNomtproc(tproc.getNomtproc());
		tprocActual.setEstado(tproc.getEstado());
		
		return itprocimp.save(tproc);
				
	}

}
