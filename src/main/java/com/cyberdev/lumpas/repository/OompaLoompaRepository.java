package com.cyberdev.lumpas.repository;

import com.cyberdev.lumpas.model.oompaLoompa.OompaLoompaData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface OompaLoompaRepository extends MongoRepository<OompaLoompaData,String> {

    @Async
    @Query("{}")
    public CompletableFuture<List<OompaLoompaData>> findAllReactive();
}
