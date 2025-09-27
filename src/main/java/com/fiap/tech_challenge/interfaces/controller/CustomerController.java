package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.usecase.customer.*;
import com.fiap.tech_challenge.interfaces.dto.customer.CustomerInputDto;
import com.fiap.tech_challenge.interfaces.mapper.CustomerMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private CreateCustomerUseCase createCustomerUseCase;
    private DeleteCustomerUseCase deleteCustomerUseCase;
//    private ListAllCustomersUseCase listAllCustomersUseCase;
//    private UpdateCustomerUseCase updateCustomerUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase,
                              DeleteCustomerUseCase deleteCustomerUseCase
                              /*ListAllCustomersUseCase listAllCustomersUseCase,*/
                              /*UpdateCustomerUseCase */) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
//        this.listAllCustomersUseCase = listAllCustomersUseCase;
//        this.updateCustomerUseCase = updateCustomerUseCase;
    }

    @PostMapping
    public Customer create(@RequestBody CustomerInputDto customerInputDto) {
        return createCustomerUseCase.execute(CustomerMapper.convertDtoToEntity(customerInputDto));
    }

    @DeleteMapping
    public Boolean delete(@RequestParam Long customerId){
        return deleteCustomerUseCase.delete(customerId);
    }
}
