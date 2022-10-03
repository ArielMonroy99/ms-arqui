package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.VeterinaryBl;
import com.arqui.vetstore.dto.VeterinaryDto;
import com.arqui.vetstore.dto.entity.VeterinaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import javax.persistence.GeneratedValue;

@RestController
@RequestMapping("/api/veterinary")
@Validated
public class VeterinaryApi {


    private VeterinaryBl veterinaryBl;
    @Autowired
    public VeterinaryApi(VeterinaryBl veterinaryBl) {
        this.veterinaryBl = veterinaryBl;
    }
    @PostMapping
    public VeterinaryDto createVeterinary(@RequestBody VeterinaryDto veterinaryDto){
        return veterinaryBl.saveVeterinary(veterinaryDto);
    }

    @GetMapping("/{id}")
    public VeterinaryDto getVeterinaryById(@PathVariable Integer id){
        return veterinaryBl.getVeterinaryById(id);
    }

}
