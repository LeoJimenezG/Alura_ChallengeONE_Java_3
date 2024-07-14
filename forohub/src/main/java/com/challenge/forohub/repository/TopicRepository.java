package com.challenge.forohub.repository;

import com.challenge.forohub.domain.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTitle(String title);
    Optional<Topic> findByMessage(String message);
    List<Topic> findByAuthorId(Long id);
    List<Topic> findByStatusTrue();
    @Query("SELECT t FROM topics t WHERE t.status = true ORDER BY t.creation_date ASC LIMIT 10")
    List<Topic> findTenStatusTrueAsc();
    List<Topic> findByCourseId(Long id);
}
