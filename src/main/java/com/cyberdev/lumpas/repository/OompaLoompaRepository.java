package com.cyberdev.lumpas.repository;

import com.cyberdev.lumpas.model.OompaLoompa.OompaLoompaData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OompaLoompaRepository extends MongoRepository<OompaLoompaData,String> {
}