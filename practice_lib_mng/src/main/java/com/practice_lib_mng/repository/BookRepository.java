package com.practice_lib_mng.repository;

import com.practice_lib_mng.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,String> {
}
