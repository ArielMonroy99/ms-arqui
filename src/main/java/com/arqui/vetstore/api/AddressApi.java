package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.AddressBl;
import com.arqui.vetstore.dto.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressApi {
    private final AddressBl addressBl;

    @Autowired
    public AddressApi(AddressBl addressBl) {
        this.addressBl = addressBl;
    }
    @GetMapping
    public Page<AddressDto> getAddress(@RequestParam Integer page, @RequestParam Integer size, @RequestParam Integer userId){
        return addressBl.getAllAddresses(page, size, userId);
    }

    @PostMapping
    public AddressDto saveAddress(@RequestBody AddressDto address){
        return addressBl.saveAddress(address);
    }

    @PutMapping
    public AddressDto updateAddress(@RequestBody AddressDto address){
        return addressBl.updateAddress(address);
    }
    @DeleteMapping(path = "/{id}")
    public void deleteAddress(@PathVariable Integer id){
        addressBl.deleteAddress(id);
    }
}
