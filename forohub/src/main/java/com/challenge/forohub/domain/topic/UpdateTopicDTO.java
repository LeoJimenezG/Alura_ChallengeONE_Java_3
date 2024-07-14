package com.challenge.forohub.domain.topic;

import com.challenge.forohub.domain.course.Course;
import com.challenge.forohub.domain.author.User;

public record UpdateTopicDTO(
        String title,
        String message,
        User author,
        Course course
) {
}
