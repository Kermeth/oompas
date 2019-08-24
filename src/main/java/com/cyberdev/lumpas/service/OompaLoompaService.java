package com.cyberdev.lumpas.service;

import com.cyberdev.lumpas.model.OompaLoompa.OompaLoompaBasicDTO;
import com.cyberdev.lumpas.model.OompaLoompa.OompaLoompaData;
import com.cyberdev.lumpas.model.OompaLoompa.OompaLoompaDetailDTO;
import com.cyberdev.lumpas.repository.OompaLoompaRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        );
        return this.saveOompaLoompa(oompaLoompaData);
    }

    public OompaLoompaDetailDTO getOompaLoompa(String id) throws OompaLoompaException {
        OompaLoompaData oompaLoompaData = this.getOompaLoompaById(id);
        return new OompaLoompaDetailDTO(
                oompaLoompaData.getId().toHexString(),
                oompaLoompaData.getName(),
                oompaLoompaData.getAge(),
                oompaLoompaData.getJob(),
                oompaLoompaData.getHeight(),
                oompaLoompaData.getWeight(),
                oompaLoompaData.getDescription());
    }

    public OompaLoompaDetailDTO editOompaLoompa(OompaLoompaDetailDTO oompaLoompa) throws OompaLoompaException {
        OompaLoompaData oompaLoompaData = this.getOompaLoompaById(oompaLoompa.getId());
        oompaLoompaData.setName(oompaLoompa.getName());
        oompaLoompaData.setAge(oompaLoompa.getAge());
        oompaLoompaData.setJob(oompaLoompa.getJob());
        oompaLoompaData.setHeight(oompaLoompa.getHeight());
        oompaLoompaData.setWeight(oompaLoompa.getWeight());
        oompaLoompaData.setDescription(oompaLoompa.getDescription());
        return this.saveOompaLoompa(oompaLoompaData);
    }

    private OompaLoompaData getOompaLoompaById(String id) throws OompaLoompaException {
        Optional<OompaLoompaData> oompaOptional = oompaLoompaRepository.findById(id);
        if(!oompaOptional.isPresent()){
            throw new OompaLoompaException("Oompaloompa with id: "+id+" not found");
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

    public List<OompaLoompaBasicDTO> getAllOompaLoompas() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<OompaLoompaData> page = oompaLoompaRepository.findAll(pageRequest);
        return page.stream().map(oompaLoompaData -> {
            return new OompaLoompaBasicDTO(
                    oompaLoompaData.getId().toHexString(),
                    oompaLoompaData.getName(),
                    oompaLoompaData.getAge(),
                    oompaLoompaData.getJob());
        }).collect(Collectors.toList());
    }
}
