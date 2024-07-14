package com.challenge.forohub.service;

import com.challenge.forohub.domain.answer.Answer;
import com.challenge.forohub.domain.topic.RegisterTopicDTO;
import com.challenge.forohub.domain.topic.Topic;
import com.challenge.forohub.repository.TopicRepository;
import com.challenge.forohub.domain.topic.UpdateTopicDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;

    public Optional<Topic> findTopicById(Long id){
        return topicRepository.findById(id);
    }

    public Optional<Topic> findTopicByTitle(String title){
        return topicRepository.findByTitle(title);
    }

    public List<Topic> findTopicsByAuthorId(Long id){
        return topicRepository.findByAuthorId(id);
    }

    public List<Topic> findTopicsByCourseId(Long id){
        return topicRepository.findByCourseId(id);
    }

    public boolean checkForDuplicates(RegisterTopicDTO registerTopicDTO){
        // Search the topic by title
        Optional<Topic> topic_by_title = topicRepository.findByTitle(registerTopicDTO.title());
        // Search the topic by message
        Optional<Topic> topic_by_message = topicRepository.findByMessage((registerTopicDTO.message()));
        // If a topic with the title and message is found, it can't be registered
        return topic_by_title.isPresent() && topic_by_message.isPresent();
    }

    @Transactional
    public Topic updateTopic(UpdateTopicDTO updateTopicDTO, Topic topic){
        // Update the target topic with the new information
        topic.updateTopic(updateTopicDTO);
        // Return the updated topic
        return topicRepository.findById(topic.getId()).get();
    }

    @Transactional
    public void deleteTopic(Long id){
        // Delete the topic using the id
        topicRepository.deleteById(id);
    }

    @Transactional
    public List<Answer> addAnswer(Answer answer, Topic topic){
        // Add the answer to the answer list of the topic
        topic.addAnswer(answer);
        // Return the answer list
        return topic.getAnswerList();
    }
}
