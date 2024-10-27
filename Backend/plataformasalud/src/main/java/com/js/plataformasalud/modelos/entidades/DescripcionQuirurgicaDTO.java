package com.js.plataformasalud.modelos.entidades;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class DescripcionQuirurgicaDTO {
	
	private DescripcionQuirurgica descripcionQuirurgica;
    private List<ProcedimientoDescripcionQX> procedimientos;
    private List<EquipoQx> equipoQx;
    private List<DiagnosticoAtencion>dxatencion;
    

}
