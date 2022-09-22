package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.VeterinaryBl;
import com.arqui.vetstore.dto.VeterinaryDto;
import com.arqui.vetstore.dto.entity.VeterinaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public VeterinaryDto getVeterinaryById(@PathVariable Integer id){
        return veterinaryBl.getVeterinaryById(id);
    }

}
