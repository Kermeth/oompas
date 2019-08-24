package com.cyberdev.lumpas.service;

import com.cyberdev.lumpas.model.OompaLoompa.OompaLoompaData;
import com.cyberdev.lumpas.model.OompaLoompa.OompaLoompaDetailDTO;
import com.cyberdev.lumpas.repository.OompaLoompaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OompaLoompaServiceTest {

    private OompaLoompaRepository oompaLoompaRepository = Mockito.mock(OompaLoompaRepository.class);
    private OompaLoompaService oompaLoompaService;

    @BeforeEach
    void initService(){
        oompaLoompaService = new OompaLoompaService(oompaLoompaRepository);
        when(oompaLoompaRepository.save(any(OompaLoompaData.class))).then(returnsFirstArg());
    }

    @Test
    void saveOompaLoompa(){
        OompaLoompaDetailDTO oompaLoompa = new OompaLoompaDetailDTO(
                "" ,
                "Test OompaLoompa",
                1,
                "Test OompaLoompa Job",
                1.2f,
                2.1f,
                "Test OompaLoompa Description"
        );
        OompaLoompaDetailDTO savedOompaLoompa = oompaLoompaService.addOompaLoompa(oompaLoompa);
        assertTrue(!savedOompaLoompa.getId().isEmpty());
    }

}
