package com.luciana.desafio.dto;

public record RelatorioDTO(Integer qtdeTotalVendas, Double valorTotalVendas, 
		Integer qtdeTotalVendasFechadas, Double valorTotalVendasFechadas, 
		Integer qtdeTotalVendasPendentes, Double valorTotalVendasPendentes, 
		Integer qtdeTotalVendasCanceladas, Double valorTotalVendasCanceladas) {

}
