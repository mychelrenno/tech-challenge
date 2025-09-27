package com.fiap.tech_challenge.infrastructure.repository;

import com.fiap.tech_challenge.core.domain.Customer;
import com.fiap.tech_challenge.core.domain.user.User;
import com.fiap.tech_challenge.core.repository.CustomerRepository;
import com.fiap.tech_challenge.infrastructure.entity.CustomerJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserJpa;
import com.fiap.tech_challenge.infrastructure.entity.UserTypeJpa;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaCustomer;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUser;
import com.fiap.tech_challenge.infrastructure.repository.jpa.SpringDataJpaUserType;
import com.fiap.tech_challenge.interfaces.mapper.AddressMapper;
import com.fiap.tech_challenge.interfaces.mapper.CustomerMapper;
import com.fiap.tech_challenge.interfaces.mapper.UserTypeMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

            System.out.println(userTypeJpa);
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
}
