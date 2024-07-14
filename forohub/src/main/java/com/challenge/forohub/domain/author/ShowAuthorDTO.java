package com.challenge.forohub.domain.author;

public record ShowAuthorDTO(
        String name,
        String email,
        Long profile_id
) {
    public ShowAuthorDTO(User user) {
        this(user.getName(), user.getEmail(), user.getProfile().getId());
    }
}
