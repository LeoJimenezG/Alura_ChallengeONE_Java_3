package com.challenge.forohub.domain.topic;

import com.challenge.forohub.domain.answer.Answer;
import com.challenge.forohub.domain.course.Course;
import com.challenge.forohub.domain.author.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "topics")
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creation_date;
    private boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private List<Answer> answerList;

    public Topic(RegisterTopicDTO registerTopicDTO) {
        this.title = registerTopicDTO.title();
        this.message = registerTopicDTO.message();
        this.author = registerTopicDTO.author();
        this.course = registerTopicDTO.course();
        this.creation_date = LocalDateTime.now();
        this.status = true;
        this.answerList = null;
    }

    public void updateTopic(UpdateTopicDTO updateTopicDTO){
        if (updateTopicDTO.title() != null){
            this.title = updateTopicDTO.title();
        }
        if (updateTopicDTO.message() != null){
            this.message = updateTopicDTO.message();
        }
        if (updateTopicDTO.author() != null){
            this.author = updateTopicDTO.author();
        }
        if (updateTopicDTO.course() != null){
            this.course = updateTopicDTO.course();
        }
    }

    public void addAnswer(Answer answer){
        this.answerList.add(answer);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public boolean getStatus() {
        return status;
    }

    public User getAuthor() {
        return author;
    }

    public Course getCourse() {
        return course;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }
}
