package com.challenge.forohub.domain.topic;

public record TopicResponseDTO(
        String title,
        String message,
        Long course_id,
        Long author_id
) {
    public TopicResponseDTO(Topic topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getCourse().getId(), topic.getAuthor().getId());
    }
}
