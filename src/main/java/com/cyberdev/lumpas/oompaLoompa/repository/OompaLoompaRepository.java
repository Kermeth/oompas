package com.cyberdev.lumpas.oompaLoompa.repository;

import com.cyberdev.lumpas.oompaLoompa.OompaLoompaData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface OompaLoompaRepository extends MongoRepository<OompaLoompaData,String> {

    @Query(value = "{}",fields = "{ height: 0, weight: 0, description: 0}")
    public Page<OompaLoompaData> findAllBasicDTO(Pageable pageable);

    @Async
    @Query("{}")
    public CompletableFuture<List<OompaLoompaData>> findAllReactive();
}
