package com.luciana.desafio.dto;

import java.time.Instant;

public record TokenDatesDTO(Instant issuedDate, Instant expirationDate) {

}
