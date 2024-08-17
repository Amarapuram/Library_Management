package com.practice_lib_mng.controller;

import com.practice_lib_mng.domain.Book;
import com.practice_lib_mng.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private LibraryService libraryService;
    @GetMapping
    public List<Book> getAllBooks(){
        return libraryService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id){
        Optional<Book> bookOptional=libraryService.getBookById(id);
        if(bookOptional.isPresent()){
            return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
@PostMapping
    public Book createBook(@RequestBody Book book){
        return libraryService.saveBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id,@RequestBody Book book){
        Optional<Book> bookOptional=libraryService.getBookById(id);
        if(bookOptional.isPresent()){
            Book book1=bookOptional.get();
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            return new ResponseEntity<>(bookOptional.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteById(String id){
        Optional<Book>bookOptional=libraryService.getBookById(id);
        if(bookOptional.isPresent()){
            libraryService.deleteAuthor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
