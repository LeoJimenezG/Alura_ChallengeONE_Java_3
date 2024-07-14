package com.challenge.forohub.controller;

import com.challenge.forohub.domain.answer.AddAnswerDTO;
import com.challenge.forohub.domain.answer.Answer;
import com.challenge.forohub.domain.answer.ShowAnswerDTO;
import com.challenge.forohub.domain.answer.UpdateAnswerDTO;
import com.challenge.forohub.domain.topic.Topic;
import com.challenge.forohub.repository.AnswerRepository;
import com.challenge.forohub.service.AnswerService;
import com.challenge.forohub.service.AuthorService;
import com.challenge.forohub.service.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private AnswerService answerService;

    @GetMapping("/{id}")
    public ResponseEntity<ShowAnswerDTO> showAnswerById(@PathVariable @Valid @NotNull Long id){
        // If the answer with that id is found
        if (answerService.findAnswerById(id).isPresent()){
            return ResponseEntity.ok(new ShowAnswerDTO(answerService.findAnswerById(id).get()));
        }
        // If the answer is not fund, return the 404 status code
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ShowAnswerDTO> addNewAnswer(
            @RequestBody @Valid @NotNull AddAnswerDTO addAnswerDTO, UriComponentsBuilder uriComponentsBuilder){
        // If the author and topic exist
        if (authorService.findAuthorById(addAnswerDTO.author().getId()).isPresent() &&
                topicService.findTopicById(addAnswerDTO.topic().getId()).isPresent()){
            // Save the answer and get it
            Answer answer = answerRepository.save(new Answer(addAnswerDTO));
            // Add the answer to the topic
            topicService.findTopicById(addAnswerDTO.topic().getId()).get().addAnswer(answer);
            // Create an url to locate the new answer
            URI url = uriComponentsBuilder.path("/answer/{id}").buildAndExpand(answer.getId()).toUri();
            // Return the 201 status code and the created answer
            return ResponseEntity.created(url).body(new ShowAnswerDTO(answer));
        }
        // Return the 404 status code
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ShowAnswerDTO> updateAnswerById(
            @PathVariable @Valid @NotNull Long id, @RequestBody @Valid UpdateAnswerDTO updateAnswerDTO){
        // If the answer with that id is found
        if (answerService.findAnswerById(id).isPresent()){
            // Update the answer sending the new information and the target answer
            ShowAnswerDTO updated_answer = new ShowAnswerDTO(answerService.updateAnswer
                    (updateAnswerDTO, answerService.findAnswerById(id).get()));
            // Return the updated answer and the 200 status code
            return ResponseEntity.ok(updated_answer);
        }
        // If the answer is not fund, return the 404 status code
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Topic> deleteAnswerById(@PathVariable @Valid @NotNull Long id){
        // If the answer with that id is found
        if (answerService.findAnswerById(id).isPresent()){
            // Delete the topic from the database
            answerService.deleteAnswer(id);
            // Return the 200 status code
            return ResponseEntity.ok().build();
        }
        // If the answer is not fund, return the 404 status code
        return ResponseEntity.notFound().build();
    }
}
