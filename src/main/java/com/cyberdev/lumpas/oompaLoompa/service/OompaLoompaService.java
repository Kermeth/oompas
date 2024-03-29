package com.cyberdev.lumpas.oompaLoompa.service;

import com.cyberdev.lumpas.util.PageOf;
import com.cyberdev.lumpas.oompaLoompa.OompaLoompaBasicDTO;
import com.cyberdev.lumpas.oompaLoompa.OompaLoompaData;
import com.cyberdev.lumpas.oompaLoompa.OompaLoompaDetailDTO;
import com.cyberdev.lumpas.oompaLoompa.exceptions.OompaLoompaNotFoundException;
import com.cyberdev.lumpas.oompaLoompa.repository.OompaLoompaRepository;
import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OompaLoompaService {

    private final OompaLoompaRepository oompaLoompaRepository;

    public OompaLoompaDetailDTO addOompaLoompa(OompaLoompaDetailDTO newOompaLoompa){
        OompaLoompaData oompaLoompaData = new OompaLoompaData(
                new ObjectId(),
                newOompaLoompa.getName(),
                newOompaLoompa.getAge(),
                newOompaLoompa.getJob(),
                newOompaLoompa.getHeight(),
                newOompaLoompa.getWeight(),
                newOompaLoompa.getDescription()
        ); //create OompaLoompaData with DetailDTO
        return this.saveOompaLoompa(oompaLoompaData);
    }

    public OompaLoompaDetailDTO getOompaLoompa(String id) throws OompaLoompaNotFoundException {
        OompaLoompaData oompaLoompaData = this.getOompaLoompaById(id);
        //return DetailDTO
        return new OompaLoompaDetailDTO(
                oompaLoompaData.getId().toHexString(),
                oompaLoompaData.getName(),
                oompaLoompaData.getAge(),
                oompaLoompaData.getJob(),
                oompaLoompaData.getHeight(),
                oompaLoompaData.getWeight(),
                oompaLoompaData.getDescription());
    }

    public OompaLoompaDetailDTO editOompaLoompa(OompaLoompaDetailDTO oompaLoompa) throws OompaLoompaNotFoundException {
        OompaLoompaData oompaLoompaData = this.getOompaLoompaById(oompaLoompa.getId());
        //Edit the Data with DetailDTO info
        oompaLoompaData.setName(oompaLoompa.getName());
        oompaLoompaData.setAge(oompaLoompa.getAge());
        oompaLoompaData.setJob(oompaLoompa.getJob());
        oompaLoompaData.setHeight(oompaLoompa.getHeight());
        oompaLoompaData.setWeight(oompaLoompa.getWeight());
        oompaLoompaData.setDescription(oompaLoompa.getDescription());
        return this.saveOompaLoompa(oompaLoompaData);
    }

    public PageOf<OompaLoompaBasicDTO> getAllOompaLoompasPaged(int pageNumber, int size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        Page<OompaLoompaBasicDTO> page = oompaLoompaRepository.findAllBasicDTO(pageRequest) //Find the page requested of OompaLoompaData without extra info
                //Map the Data to BasicDTO
                .map(oompaLoompaData -> {
                    return new OompaLoompaBasicDTO(
                            oompaLoompaData.getId().toHexString(),
                            oompaLoompaData.getName(),
                            oompaLoompaData.getAge(),
                            oompaLoompaData.getJob()
                    );
                });
        return new PageOf<OompaLoompaBasicDTO>(page.getContent(),page.getNumber(),page.getSize(),page.getTotalPages());
    }

    public Observable<OompaLoompaBasicDTO> getAllOopaLoompasReactive(){
        return Observable.fromFuture(oompaLoompaRepository.findAllReactive())
                .flatMapIterable(list -> list)
                .map(oompaLoompaData -> {
                            return new OompaLoompaBasicDTO(
                                    oompaLoompaData.getId().toHexString(),
                                    oompaLoompaData.getName(),
                                    oompaLoompaData.getAge(),
                                    oompaLoompaData.getJob());
                });
    }

    private OompaLoompaData getOompaLoompaById(String id) throws OompaLoompaNotFoundException {
        Optional<OompaLoompaData> oompaOptional = oompaLoompaRepository.findById(id);
        if(!oompaOptional.isPresent()){
            throw new OompaLoompaNotFoundException("Oompaloompa with id: "+id+" not found");
        }else {
            return oompaOptional.get();
        }
    }

    private OompaLoompaDetailDTO saveOompaLoompa(OompaLoompaData oompaLoompaData){
        OompaLoompaData oompaLoompaPersisted = oompaLoompaRepository.save(oompaLoompaData);
        return new OompaLoompaDetailDTO(
                oompaLoompaPersisted.getId().toHexString(),
                oompaLoompaPersisted.getName(),
                oompaLoompaPersisted.getAge(),
                oompaLoompaPersisted.getJob(),
                oompaLoompaPersisted.getHeight(),
                oompaLoompaPersisted.getWeight(),
                oompaLoompaPersisted.getDescription());
    }

}
