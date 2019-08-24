package com.cyberdev.lumpas.model.oompaLoompa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "oompaloompas")
public class OompaLoompaData {

    @Id
    public ObjectId id;
    private String name;
    private Integer age;
    private String job;
    private Float height;
    private Float weight;
    private String description;

}
