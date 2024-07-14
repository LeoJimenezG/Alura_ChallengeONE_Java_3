package com.challenge.forohub.service;

import com.challenge.forohub.domain.author.UpdateAuthorDTO;
import com.challenge.forohub.domain.author.User;
import com.challenge.forohub.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Optional<User> findAuthorById(Long id){
        return authorRepository.findById(id);
    }

    public Optional<User> findAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    public Optional<User> findAuthorByEmail(String email) {
        return authorRepository.findByEmail(email);
    }

    @Transactional
    public User updateAuthor(UpdateAuthorDTO updateAuthorDTO, User user) {
        // Update the target author with the new information
        user.updateAuthor(updateAuthorDTO);
        // Return the updated author
        return authorRepository.findById(user.getId()).get();
    }
}
