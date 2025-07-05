package com.js.plataformasalud.modelos.entidades;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class HistoriaClinicaDTO {
	private HistoriaClinica hcdto;
	private List<DiagnosticoHistoriaClinica>dxhcdto;

}
