package com.challenge.forohub.domain.author;

public record UpdateAuthorDTO(
        String name,
        String email,
        String password
) {
}
