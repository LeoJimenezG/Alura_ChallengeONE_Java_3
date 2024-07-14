package com.challenge.forohub.controller;

import com.challenge.forohub.domain.topic.*;
import com.challenge.forohub.repository.TopicRepository;
import com.challenge.forohub.service.CourseService;
import com.challenge.forohub.service.TopicService;
import com.challenge.forohub.service.AuthorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicService topicService;
    @Autowired
    private AuthorService userService;
    @Autowired
    private CourseService courseService;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponseDTO> registerNewTopic(
            @RequestBody @Valid @NotNull RegisterTopicDTO registerTopicDTO, UriComponentsBuilder uriComponentsBuilder){
        // If the topic is already register, return the 400 status code
        if (topicService.checkForDuplicates(registerTopicDTO)){
            // Return the 400 status code
            return ResponseEntity.badRequest().build();
        }
        // Save the new topic and get it
        Topic topic = topicRepository.save(new Topic(registerTopicDTO));
        // Build an object to give it as a response
        TopicResponseDTO registerTopicResponseDTO = new TopicResponseDTO(topic);
        // Create an url to locate the new topic
        URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();
        // Return the 201 status code and the created topic
        return ResponseEntity.created(url).body(registerTopicResponseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShowTopicDTO>> showAllActiveTopics(){
        // Make a List of all topics where status is true
        List<ShowTopicDTO> all_topics = topicRepository.findByStatusTrue().stream().map(ShowTopicDTO::new).toList();
        // Return the 200 status code and the list of all topics
        return ResponseEntity.ok(all_topics);
    }

    @GetMapping("/firstTen")
    public ResponseEntity<List<ShowTopicDTO>> showTenActiveTopicsAsc(){
        // Make a List of the ten topics where status is true and ordered by asc
        List<ShowTopicDTO> ten_topics = topicRepository.findTenStatusTrueAsc().stream().map(ShowTopicDTO::new).toList();
        // Return the 200 status code and the ten topics
        return ResponseEntity.ok(ten_topics);
    }

    @GetMapping("title/{title}")
    public ResponseEntity<ShowTopicDTO> showTopicByTitle(@PathVariable @Valid @NotNull String title){
        // If the topic with that title is found
        if (topicService.findTopicByTitle(title).isPresent()){
            // Create a ShowTopicDTO object and return the 200 status code and the object
            return ResponseEntity.ok(new ShowTopicDTO(topicService.findTopicByTitle(title).get()));
        }
        // If the topic is not return the 404 status code
        return ResponseEntity.notFound().build();
    }

    @GetMapping("author/{id}")
    public ResponseEntity<List<ShowTopicDTO>> showTopicByAuthorId(@PathVariable @Valid @NotNull Long id){
        // Verify the author exists
        if (userService.findAuthorById(id).isPresent()){
            // Create a List of ShowTopicDTo object and return the 200 status code and the list
            return ResponseEntity.ok(topicService.findTopicsByAuthorId(id).stream().map(ShowTopicDTO::new).toList());
        }
        // If the author is not found, return the 404 status code
        return ResponseEntity.notFound().build();
    }

    @GetMapping("course/{id}")
    public ResponseEntity<List<ShowTopicDTO>> showTopicByCourseId(@PathVariable @Valid @NotNull Long id){
        // Verify the course exists
        if (courseService.findCourseById(id).isPresent()){
            // Create a List of ShowTopicDTO object and return the 200 status code and the list
            return ResponseEntity.ok(topicService.findTopicsByCourseId(id).stream().map(ShowTopicDTO::new).toList());
        }
        // If the course is not found, return the 404 status code
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowTopicDTO> showTopicById(@PathVariable @Valid @NotNull Long id){
        // If the topic with that id is found
        if (topicService.findTopicById(id).isPresent()){
            // Create a ShowTopicDTO object and return the 200 status code and the object
            return ResponseEntity.ok(new ShowTopicDTO(topicService.findTopicById(id).get()));
        }
        // If the topic is not found, return the 404 status code
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowTopicDTO> updateTopicById(
            @PathVariable @Valid @NotNull Long id, @RequestBody UpdateTopicDTO updateTopicDTO){
        // If the topic with that id is found
        if (topicService.findTopicById(id).isPresent()){
            // Verify if the update can be done
            RegisterTopicDTO verify_topic = new RegisterTopicDTO(updateTopicDTO);
            // If the update can't be done
            if (topicService.checkForDuplicates(verify_topic)){
                // Return the 400 status code
                return ResponseEntity.badRequest().build();
            }
            // If the update can be done
            // Update the topic sending the new information and the target topic
            ShowTopicDTO updated_topic = new ShowTopicDTO(topicService.updateTopic(
                    updateTopicDTO, topicService.findTopicById(id).get()));
            // Return the updated topic and the 200 status code
            return ResponseEntity.ok(updated_topic);
        }
        // If the topic is not found, return the 404 status code
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Topic> deleteTopicById(@PathVariable @Valid @NotNull Long id){
        // If the topic with that id is found
        if (topicService.findTopicById(id).isPresent()){
            // Delete the topic from the database
            topicService.deleteTopic(id);
            // Return the 200 status code
            return ResponseEntity.ok().build();
        }
        // If the topic is not found, return the 404 status code
        return ResponseEntity.notFound().build();
    }
}
