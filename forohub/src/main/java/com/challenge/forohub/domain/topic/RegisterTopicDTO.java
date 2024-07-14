package com.challenge.forohub.domain.topic;

import com.challenge.forohub.domain.course.Course;
import com.challenge.forohub.domain.author.User;
import jakarta.validation.constraints.NotNull;

public record RegisterTopicDTO(
        @NotNull String title,
        @NotNull String message,
        @NotNull User author,
        @NotNull Course course
) {
    public RegisterTopicDTO(UpdateTopicDTO updateTopicDTO) {
        this(updateTopicDTO.title(), updateTopicDTO.message(), updateTopicDTO.author(), updateTopicDTO.course());
    }
}
