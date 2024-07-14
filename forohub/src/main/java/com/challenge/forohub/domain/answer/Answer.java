package com.challenge.forohub.domain.answer;

import com.challenge.forohub.domain.topic.Topic;
import com.challenge.forohub.domain.author.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "answers")
@Table(name = "answers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    private LocalDateTime creationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    private String solution;

    public Answer(AddAnswerDTO addAnswerDTO) {
        this.message = addAnswerDTO.message();
        this.topic = addAnswerDTO.topic();
        this.author = addAnswerDTO.author();
        this.solution = addAnswerDTO.solution();
        this.creationDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Topic getTopic() {
        return topic;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public User getAuthor() {
        return author;
    }

    public String getSolution() {
        return solution;
    }

    public void updateAnswer(UpdateAnswerDTO updateAnswerDTO) {
        if (updateAnswerDTO.message() != null){
            this.message = updateAnswerDTO.message();
        }
        if (updateAnswerDTO.solution() != null){
            this.solution = updateAnswerDTO.solution();
        }
    }
}
