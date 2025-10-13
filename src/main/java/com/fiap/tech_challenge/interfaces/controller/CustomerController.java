package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.usecase.customer.*;
import com.fiap.tech_challenge.interfaces.dto.customer.CustomerInputDto;
import com.fiap.tech_challenge.interfaces.dto.customer.CustomerOutputDto;
import com.fiap.tech_challenge.interfaces.mapper.CustomerMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final ListAllCustomersUseCase listAllCustomersUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase,
                              DeleteCustomerUseCase deleteCustomerUseCase,
                              ListAllCustomersUseCase listAllCustomersUseCase,
                              UpdateCustomerUseCase updateCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.listAllCustomersUseCase = listAllCustomersUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CustomerInputDto customerInputDto) {
        return createCustomerUseCase.execute(CustomerMapper.convertDtoToEntity(customerInputDto));
    }

    @DeleteMapping
    public Boolean deleteCustomer(@RequestParam Long customerId){
        return deleteCustomerUseCase.delete(customerId);
    }

    @GetMapping
    public List<Customer> listAllCustomers(){
        return listAllCustomersUseCase.listAllCustomers();
    }

    @PutMapping
    public Customer updateCustomer(@RequestParam Long customerId, @RequestBody CustomerOutputDto customerOutputDto){
        Customer customer = CustomerMapper.convertOutputDtoToEntity(customerOutputDto);
        return updateCustomerUseCase.execute(customerId, customer);
    }
}
