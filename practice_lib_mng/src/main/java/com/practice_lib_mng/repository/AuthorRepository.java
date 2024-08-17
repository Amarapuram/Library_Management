package com.practice_lib_mng.repository;

import com.practice_lib_mng.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author,String> {
}
