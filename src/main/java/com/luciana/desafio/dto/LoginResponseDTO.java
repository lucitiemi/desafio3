package com.luciana.desafio.dto;

import java.time.Instant;

public record LoginResponseDTO(String name, String token, Instant creationDate, Instant expirationDate) { 

}
