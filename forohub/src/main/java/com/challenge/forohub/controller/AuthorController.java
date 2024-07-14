package com.challenge.forohub.controller;

import com.challenge.forohub.domain.author.ShowAuthorDTO;
import com.challenge.forohub.domain.author.UpdateAuthorDTO;
import com.challenge.forohub.repository.AuthorRepository;
import com.challenge.forohub.service.AuthorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<ShowAuthorDTO> showAuthorById(@PathVariable @Valid @NotNull Long id){
        // If the author with that id is found
        if (authorService.findAuthorById(id).isPresent()){
            // Create a ShowAuthorDTO object and return the 200 status code and the object
            return ResponseEntity.ok(new ShowAuthorDTO(authorService.findAuthorById(id).get()));
        }
        // If the author is not found, return the 404 status code
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ShowAuthorDTO> showAuthorByName(@PathVariable @Valid @NotNull String name){
        // If the author with that name is found
        if (authorService.findAuthorByName(name).isPresent()){
            // Create a ShowAuthorDTO object and return the 200 status code and the object
            return ResponseEntity.ok(new ShowAuthorDTO(authorService.findAuthorByName(name).get()));
        }
        // If the author is not found, return the 404 status code
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ShowAuthorDTO> showAuthorByEmail(@PathVariable @Valid @NotNull String email){
        // If the author with that email is found
        if (authorService.findAuthorByEmail(email).isPresent()){
            // Create a ShowAuthorDTO object and return the 200 status code and the object
            return ResponseEntity.ok(new ShowAuthorDTO(authorService.findAuthorByEmail(email).get()));
        }
        // If the author is not found, return the 404 status code
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowAuthorDTO> updateAuthorById(
            @PathVariable @Valid @NotNull Long id, @RequestBody UpdateAuthorDTO updateAuthorDTO){
        // If the author with that id is found
        if (authorService.findAuthorById(id).isPresent()){
            // Update the author sending the new information and the target author
            ShowAuthorDTO updated_author = new ShowAuthorDTO(authorService.updateAuthor(
                    updateAuthorDTO, authorService.findAuthorById(id).get()));
            // Return the updated topic and the 200 status code
            return ResponseEntity.ok(updated_author);
        }
        // If the author is not found, return the 404 status code
        return ResponseEntity.notFound().build();
    }
}
