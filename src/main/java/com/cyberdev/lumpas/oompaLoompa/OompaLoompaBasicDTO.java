package com.cyberdev.lumpas.oompaLoompa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class OompaLoompaBasicDTO implements Serializable {

    private String id;
    private String name;
    private int age;
    private String job;

}
