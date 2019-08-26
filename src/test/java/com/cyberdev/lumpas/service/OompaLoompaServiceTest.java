package com.cyberdev.lumpas.service;

import com.cyberdev.lumpas.model.PageOf;
import com.cyberdev.lumpas.model.oompaLoompa.OompaLoompaBasicDTO;
import com.cyberdev.lumpas.model.oompaLoompa.OompaLoompaData;
import com.cyberdev.lumpas.model.oompaLoompa.OompaLoompaDetailDTO;
import com.cyberdev.lumpas.repository.OompaLoompaRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OompaLoompaServiceTest {

    @Mock
    private OompaLoompaRepository oompaLoompaRepository;
    private OompaLoompaService oompaLoompaService;

    OompaLoompaData oompaLoompaData = new OompaLoompaData(
            new ObjectId(),
            "Test OompaLoompa",
            1,
            "Test OompaLoompa Job",
            1.2f,
            2.1f,
            "Test OompaLoompa Description"
    );
    OompaLoompaDetailDTO oompaLoompaDetail = new OompaLoompaDetailDTO(
            oompaLoompaData.getId().toHexString() ,
            "Test OompaLoompa",
            1,
            "Test OompaLoompa Job",
            1.2f,
            2.1f,
            "Test OompaLoompa Description"
    );


    @Before
    public void initService(){
        oompaLoompaService = new OompaLoompaService(oompaLoompaRepository);
        //Configuring mocks
        List<OompaLoompaData> listOfOompaLoompaDatas = new ArrayList();
        listOfOompaLoompaDatas.add(oompaLoompaData);
        when(oompaLoompaRepository.save(any(OompaLoompaData.class))).then(returnsFirstArg());
        when(oompaLoompaRepository.findById(oompaLoompaData.getId().toHexString())).thenReturn(Optional.of(oompaLoompaData));
        when(oompaLoompaRepository.findAllBasicDTO(any(PageRequest.class))).thenReturn(new PageImpl<>(listOfOompaLoompaDatas));
        when(oompaLoompaRepository.findAllReactive()).thenReturn(CompletableFuture.<List<OompaLoompaData>>completedFuture(listOfOompaLoompaDatas));
    }

    @Test
    public void saveOompaLoompa(){
        OompaLoompaDetailDTO savedOompaLoompa = oompaLoompaService.addOompaLoompa(oompaLoompaDetail);
        assertTrue(savedOompaLoompa!=null);
    }

    @Test
    public void editOompaLoompa(){
        oompaLoompaDetail.setAge(100);
        OompaLoompaDetailDTO editedOompaLoopma = oompaLoompaService.editOompaLoompa(oompaLoompaDetail);
        assertTrue(editedOompaLoopma.getAge().equals(100));
    }

    @Test
    public void getOompaLoompa(){
        OompaLoompaDetailDTO retrievedOompaLoompa = oompaLoompaService.getOompaLoompa(oompaLoompaDetail.getId());
        assertTrue(retrievedOompaLoompa.getName().equals("Test OompaLoompa"));
    }

    @Test
    public void getOompaLoompasFirstPage(){
        PageOf<OompaLoompaBasicDTO> page = oompaLoompaService.getAllOompaLoompasPaged(0, 10);
        assertTrue(!page.getContent().isEmpty());
        assertTrue(page.getContent().size()==1);
    }

    @Test
    public void getOompaLoompasReactive() throws InterruptedException {
        oompaLoompaService.getAllOopaLoompasReactive().subscribe(oompaLoompaBasicDTO -> {
            assertTrue(oompaLoompaBasicDTO.getName().equals("Test OompaLoompa"));
        });
        Thread.sleep(3000);
    }

}
