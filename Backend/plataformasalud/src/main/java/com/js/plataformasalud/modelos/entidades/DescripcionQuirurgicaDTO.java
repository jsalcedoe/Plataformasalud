package com.js.plataformasalud.modelos.entidades;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class DescripcionQuirurgicaDTO {
	
	private DescripcionQuirurgica descripcionQuirurgicadto;
    private List<ProcedimientoDescripcionQX> pxdto;
    private List<EquipoQx> equipoQxdto;
    private List<DiagnosticoDescripcionQx>dxdexqxdto;
    
}
