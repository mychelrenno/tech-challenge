package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.repository.CustomerRepository;
import com.fiap.tech_challenge.infrastructure.entity.CustomerJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaCustomer;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUser;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUserType;
import com.fiap.tech_challenge.interfaces.mapper.AddressMapper;
import com.fiap.tech_challenge.interfaces.mapper.CustomerMapper;
import com.fiap.tech_challenge.interfaces.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryJpa implements CustomerRepository {

    private final SpringDataJpaCustomer springDataJpaCustomer;
    private final SpringDataJpaUser springDataJpaUser;
    private final SpringDataJpaUserType springDataJpaUserType;

    public CustomerRepositoryJpa(SpringDataJpaCustomer springDataJpaCustomer, SpringDataJpaUser springDataJpaUser, SpringDataJpaUserType springDataJpaUserType) {
        this.springDataJpaCustomer = springDataJpaCustomer;
        this.springDataJpaUser = springDataJpaUser;
        this.springDataJpaUserType = springDataJpaUserType;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerJpa customerJpa = CustomerMapper.convertEntityToJpa(customer);
        // check if user already registered
        Optional<UserJpa> foundUser = springDataJpaUser.findByUsername(customerJpa.getUserJpa().getUsername());
        foundUser.ifPresent(customerJpa::setUserJpa);

        if(foundUser.isEmpty()){
            // check if user type already registered
            UserTypeJpa userTypeJpa = springDataJpaUserType.findByName(customerJpa.getUserJpa().getUserTypeJpa().getName());
            // save new user
            UserJpa newUserJpa = new UserJpa(
                customer.getUser().getName(),
                    customer.getUser().getEmail(),
                    customer.getUser().getUsername(),
                    customer.getUser().getPassword(),
                    null,
                    AddressMapper.convertEntityToJpa(customer.getUser().getAddress()),
                    new Date(),
                    true
            );
            if(userTypeJpa!=null){
                newUserJpa.setUserTypeJpa(userTypeJpa);
            }else{
                throw new IllegalArgumentException("User type not found on system.");
            }
            UserJpa savedUser = springDataJpaUser.save(newUserJpa);
            customerJpa.setUserJpa(savedUser);
        }

        // save customer
        return CustomerMapper.convertJpaToEntity(springDataJpaCustomer.save(customerJpa));
    }

    @Override
    public Boolean delete(Long customerId) {
        Optional<CustomerJpa> customerJpa = springDataJpaCustomer.findById(customerId);
        if(customerJpa.isPresent()){
            Optional<UserJpa> userJpa = springDataJpaUser.findById(customerJpa.get().getUserJpa().getId());
            if(userJpa.isPresent()){
                userJpa.get().setActive(false);
                userJpa.get().setLastUpdateDate(new Date());
                springDataJpaUser.save(userJpa.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Customer> listAllCustomers() {
        List<CustomerJpa> customerJpaList = springDataJpaCustomer.findAll();
        return CustomerMapper.convertJpaToEntityList(customerJpaList);
    }

    @Override
    public Customer findById(Long customerId) {
        Optional<CustomerJpa> foundCustomer = springDataJpaCustomer.findById(customerId);
        return foundCustomer.map(CustomerMapper::convertJpaToEntity).orElse(null);
    }

    @Override
    public Customer update(Long customerId, Customer customer) {
        Optional<CustomerJpa> foundCustomerOpt = springDataJpaCustomer.findById(customerId);
        if(foundCustomerOpt.isPresent()){
            CustomerJpa foundCustomer = foundCustomerOpt.get();
            foundCustomer.setDocument(customer.getDocument());
            // check if user type already registered
            UserTypeJpa foundUserTypeJpa = springDataJpaUserType.findByName(foundCustomer.getUserJpa().getUserTypeJpa().getName());
            // check user
            Optional<UserJpa> foundUserJpa = springDataJpaUser.findById(foundCustomer.getUserJpa().getId());
            if(foundUserJpa.isPresent()){
                UserJpa userJpa = UserMapper.convertEntityToJpa(customer.getUser());
                if(foundUserTypeJpa!=null){
                    userJpa.setUserTypeJpa(foundUserTypeJpa);
                    userJpa.setPassword(foundUserJpa.get().getPassword());
                }else{
                    throw new IllegalArgumentException("User type not found on system.");
                }
                // user
                foundCustomer.setUserJpa(userJpa);
                // save customer
                return CustomerMapper.convertJpaToEntity(springDataJpaCustomer.save(foundCustomer));

            } else {
                throw new IllegalArgumentException("User not found.");
            }
           } else {
            throw new IllegalArgumentException("Customer not found.");
        }
    }
}
