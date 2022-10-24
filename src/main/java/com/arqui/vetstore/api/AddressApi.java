package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.AddressBl;
import com.arqui.vetstore.dto.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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
   @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public AddressDto saveAddress(@RequestBody AddressDto address){
        return addressBl.saveAddress(address);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping
    public AddressDto updateAddress(@RequestBody AddressDto address){
        return addressBl.updateAddress(address);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping(path = "/{id}")
    public void deleteAddress(@PathVariable Integer id){
        addressBl.deleteAddress(id);
    }
}
