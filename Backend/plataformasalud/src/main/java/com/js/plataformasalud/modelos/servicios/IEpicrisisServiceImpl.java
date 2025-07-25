package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

//import com.js.plataformasalud.modelos.dao.IDiagnosticoAtencionDao;
import com.js.plataformasalud.modelos.dao.IEvolucionEventoDao;
import com.js.plataformasalud.modelos.dao.IHistoriaClinicaDao;
//import com.js.plataformasalud.modelos.dao.IDescripcionQuirurgicaDao;
import com.js.plataformasalud.modelos.dao.IRegistroMedicamentoDao;
import com.js.plataformasalud.modelos.dao.ISignosVitalesDao;
//import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgica;
//import com.js.plataformasalud.modelos.entidades.DiagnosticoAtencion;
import com.js.plataformasalud.modelos.entidades.EpicrisisDTO;
import com.js.plataformasalud.modelos.entidades.Evento;
import com.js.plataformasalud.modelos.entidades.EvolucionEvento;
import com.js.plataformasalud.modelos.entidades.HistoriaClinica;
import com.js.plataformasalud.modelos.entidades.RegistroMedicamento;
import com.js.plataformasalud.modelos.entidades.SignosVitales;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service

public class IEpicrisisServiceImpl implements IEpicrisisService{
	
	private IHistoriaClinicaDao hcpacdao;
	private IEvolucionEventoDao evoeventdao;
	//private IDiagnosticoAtencionDao dxatedao;
	//private IDescripcionQuirurgicaDao pxqxdao;
	private IRegistroMedicamentoDao remedinsdao;
	private ISignosVitalesDao sigvitdao;
	
	@Override
	public EpicrisisDTO getEpicrisisByEvento(Long idevent) {
		
		HistoriaClinica hcpacepi = hcpacdao.findByEventpac_Fk(idevent);
		List<EvolucionEvento> evoeventepi = evoeventdao.findByEventevo_Fk(idevent);
		//List<DiagnosticoAtencion> dxateepi = dxatedao.findByEventdxate_Fk(idevent);
		//List<DescripcionQuirurgica> desqxepi = pxqxdao.findByEventpxqx_Fk(idevent);
		List<RegistroMedicamento> regmedinsepi = remedinsdao.findByEventoregistromedins_Fk(idevent);
		List<SignosVitales> sigvitepi = sigvitdao.findByEventsigvit_Fk(idevent);
		
		EpicrisisDTO epicrisisDTO = new EpicrisisDTO();
        epicrisisDTO.setEvento(new Evento(idevent)); // O ajusta según cómo crees los objetos Evento
        epicrisisDTO.setHcpacepi(hcpacepi);
        epicrisisDTO.setEvoeventepi(evoeventepi);
        //epicrisisDTO.setDxateepi(dxateepi);
        //epicrisisDTO.setDesqxepi(desqxepi);
        epicrisisDTO.setRegmedinsepi(regmedinsepi);
        epicrisisDTO.setSigvitepi(sigvitepi);
        return epicrisisDTO;
	}	
}