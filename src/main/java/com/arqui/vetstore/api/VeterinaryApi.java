package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.VeterinaryBl;
import com.arqui.vetstore.dto.VeterinaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/veterinary")
@CrossOrigin(origins = "*")
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
    @GetMapping
    public Page<VeterinaryDto> getVeterinaries(@RequestParam Integer page, @RequestParam Integer size){
        return veterinaryBl.getAllVeterinaries(page, size);
    }
}
