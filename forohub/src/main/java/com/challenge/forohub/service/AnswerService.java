package com.challenge.forohub.service;

import com.challenge.forohub.domain.answer.Answer;
import com.challenge.forohub.domain.answer.UpdateAnswerDTO;
import com.challenge.forohub.repository.AnswerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public Optional<Answer> findAnswerById(Long id){
        return answerRepository.findById(id);
    }

    @Transactional
    public Answer updateAnswer(UpdateAnswerDTO updateAnswerDTO, Answer answer){
        // Update the target answer with the new information
        answer.updateAnswer(updateAnswerDTO);
        // Return the updated answer
        return answerRepository.findById(answer.getId()).get();
    }

    @Transactional
    public void deleteAnswer(Long id) {
        // Delete the answer by id
        answerRepository.deleteById(id);
    }
}
