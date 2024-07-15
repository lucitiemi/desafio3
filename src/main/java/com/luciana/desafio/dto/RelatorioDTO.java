package com.luciana.desafio.dto;

import java.time.Instant;

public record RelatorioDTO(
		Instant dataInicial, Instant dataFinal,
		Integer qtdeTotalVendas, Double valorTotalVendas, 
		Integer qtdeTotalVendasFechadas, Double valorTotalVendasFechadas, 
		Integer qtdeTotalVendasPendentes, Double valorTotalVendasPendentes, 
		Integer qtdeTotalVendasCanceladas, Double valorTotalVendasCanceladas) {

}
