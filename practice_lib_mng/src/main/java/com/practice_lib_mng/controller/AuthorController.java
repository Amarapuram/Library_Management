package com.practice_lib_mng.controller;

import com.practice_lib_mng.domain.Author;
import com.practice_lib_mng.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

@Autowired
    private LibraryService libraryService;

@GetMapping
    public List<Author> getAllAuthors(){
    return libraryService.getAllAuthors();
    }
@GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable String id){
       Optional<Author> authorOptional=libraryService.getAuthorById(id);
       if(authorOptional.isPresent()){
           return new ResponseEntity<>(authorOptional.get(), HttpStatus.OK);
       }
       else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public Author createAuthor(@RequestBody Author author){
        return libraryService.saveAuthor(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable String id,@RequestBody Author author){
    Optional<Author> authorOptional=libraryService.getAuthorById(id);
    if(authorOptional.isPresent()){
        Author exsistingAuthor=authorOptional.get();
        exsistingAuthor.setName(author.getName());
        exsistingAuthor.setBooks(author.getBooks());
        return new ResponseEntity<>(authorOptional.get(),HttpStatus.OK);
    }
    else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteById(@PathVariable String id){
   Optional<Author>authorOptional= libraryService.getAuthorById(id);
   if(authorOptional.isPresent()){
       libraryService.deleteAuthor(id);
       return new ResponseEntity<>(HttpStatus.OK);
   }
   else {
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

    }
}
