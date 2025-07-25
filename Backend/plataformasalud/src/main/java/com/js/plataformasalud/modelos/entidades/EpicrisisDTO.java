package com.js.plataformasalud.modelos.entidades;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EpicrisisDTO {
		
	private Evento evento;
    private HistoriaClinica hcpacepi;
    private List<EvolucionEvento> evoeventepi;
    private List<DiagnosticoDescripcionQx> dxateepi;
    private List<DescripcionQuirurgica> desqxepi;
    private List<RegistroMedicamento> regmedinsepi;
    private List<SignosVitales> sigvitepi;
    
}
