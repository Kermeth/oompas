package com.cyberdev.lumpas.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageOf <T>{
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private int pagesTotal;
}
