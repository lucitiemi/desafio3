package com.luciana.desafio.dto;

import java.time.Instant;


public class ConsultaDataDTO {
	
	
	Instant dataInicial;
	Instant dataFinal;
	
	
	public Instant getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Instant dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Instant getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Instant dataFinal) {
		this.dataFinal = dataFinal;
	}
	
}
