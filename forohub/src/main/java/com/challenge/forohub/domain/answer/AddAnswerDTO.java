package com.challenge.forohub.domain.answer;

import com.challenge.forohub.domain.author.User;
import com.challenge.forohub.domain.topic.Topic;
import jakarta.validation.constraints.NotNull;

public record AddAnswerDTO(
        @NotNull String message,
        @NotNull Topic topic,
        @NotNull User author,
        @NotNull String solution
) {
}
