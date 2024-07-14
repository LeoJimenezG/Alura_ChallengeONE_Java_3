package com.challenge.forohub.domain.answer;

import java.time.LocalDateTime;

public record ShowAnswerDTO(
        String message,
        Long topic_id,
        LocalDateTime creation_date,
        Long author_id,
        String solution
) {
    public ShowAnswerDTO(Answer answer){
        this(answer.getMessage(), answer.getTopic().getId(), answer.getCreationDate(),
                answer.getAuthor().getId(), answer.getSolution());
    }
}
