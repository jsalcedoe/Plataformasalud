package com.js.plataformasalud.modelos.entidades;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvolucionEventoDTO {
	
	private EvolucionEvento evoeventdto;
	private List<DiagnosticoEvolucion> dxevendto;

}
