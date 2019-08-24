package com.cyberdev.lumpas.controller;

import com.cyberdev.lumpas.model.OompaLoompa.OompaLoompaBasicDTO;
import com.cyberdev.lumpas.model.OompaLoompa.OompaLoompaDetailDTO;
import com.cyberdev.lumpas.model.PageOf;
import com.cyberdev.lumpas.service.OompaLoompaException;
import com.cyberdev.lumpas.service.OompaLoompaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class OompaLoompaController {

    @Autowired
    private OompaLoompaService oompaLoompaService;

    @GetMapping("/list")
    public PageOf<OompaLoompaBasicDTO> getOompaLoompas(
            @RequestParam("page") int page,
            @RequestParam("size") int size){
        return oompaLoompaService.getAllOompaLoompasPaged(page,size);
    }

    @GetMapping("/{id}")
    public OompaLoompaDetailDTO getOompaLoompa(
            @PathVariable("id") String id) throws OompaLoompaException {
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
            @RequestBody OompaLoompaDetailDTO oompaLoompa) throws OompaLoompaException {
        return oompaLoompaService.editOompaLoompa(oompaLoompa);
    }

}
