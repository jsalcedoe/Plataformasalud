package com.js.plataformasalud.modelos.servicios;

import com.js.plataformasalud.modelos.entidades.EpicrisisDTO;


public interface IEpicrisisService {
	EpicrisisDTO getEpicrisisByEvento(Long idevent);
}