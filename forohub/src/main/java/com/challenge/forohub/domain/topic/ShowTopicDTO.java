package com.challenge.forohub.domain.topic;

import java.time.LocalDateTime;

public record ShowTopicDTO(
        String title,
        String message,
        LocalDateTime creation_date,
        boolean status,
        Long author_id,
        Long course_id
){
    public ShowTopicDTO(Topic topic){
        this(topic.getTitle(), topic.getMessage(), topic.getCreation_date(), topic.getStatus(),
                topic.getAuthor().getId(), topic.getCourse().getId());
    }
}
