package com.cyberdev.lumpas.controller;

import com.cyberdev.lumpas.model.PageOf;
import com.cyberdev.lumpas.model.oompaLoompa.OompaLoompaBasicDTO;
import com.cyberdev.lumpas.model.oompaLoompa.OompaLoompaDetailDTO;
import com.cyberdev.lumpas.service.OompaLoompaService;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class OompaLoompaController {

    @Autowired
    private OompaLoompaService oompaLoompaService;

    @GetMapping("/list")
    @CachePut("loompas")
    public PageOf<OompaLoompaBasicDTO> getOompaLoompas(
            @RequestParam("page") int page,
            @RequestParam("size") int size){
        return oompaLoompaService.getAllOompaLoompasPaged(page,size);
    }

    @GetMapping(value = "/reactive",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Observable<OompaLoompaBasicDTO> getOompaLoompasReactive(){
        return oompaLoompaService.getAllOopaLoompasReactive()
                .subscribeOn(Schedulers.single());
    }

    @GetMapping("/{id}")
    public OompaLoompaDetailDTO getOompaLoompa(
            @PathVariable("id") String id) {
        return oompaLoompaService.getOompaLoompa(id);
    }

    @PostMapping("/add")
    public OompaLoompaDetailDTO createOompaLoompa(
            @Valid
            @RequestBody OompaLoompaDetailDTO oompaLoompa){
        return oompaLoompaService.addOompaLoompa(oompaLoompa);
    }

    @PutMapping("/edit")
    public OompaLoompaDetailDTO editOompaLoompa(
            @Valid
            @RequestBody OompaLoompaDetailDTO oompaLoompa) {
        return oompaLoompaService.editOompaLoompa(oompaLoompa);
    }

}
