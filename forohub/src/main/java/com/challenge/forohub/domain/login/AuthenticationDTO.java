package com.challenge.forohub.domain.login;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
        @NotNull String username,
        @NotNull String password
) {
}
