package com.fiap.tech_challenge.interfaces.controller;

import com.fiap.tech_challenge.core.domain.restaurant.Restaurant;
import com.fiap.tech_challenge.core.usecase.restaurant.CreateRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.UpdateRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.DeleteRestaurantUseCase;
import com.fiap.tech_challenge.core.usecase.restaurant.GetRestaurantUseCase;
import com.fiap.tech_challenge.interfaces.dto.restaurant.RestaurantInputDto;
import com.fiap.tech_challenge.interfaces.dto.restaurant.RestaurantOutputDto;
import com.fiap.tech_challenge.interfaces.mapper.RestaurantMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final CreateRestaurantUseCase createUseCase;
    private final UpdateRestaurantUseCase updateUseCase;
    private final DeleteRestaurantUseCase deleteUseCase;
    private final GetRestaurantUseCase getUseCase;

    public RestaurantController(CreateRestaurantUseCase createUseCase,
                               UpdateRestaurantUseCase updateUseCase,
                               DeleteRestaurantUseCase deleteUseCase,
                               GetRestaurantUseCase getUseCase) {
        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.getUseCase = getUseCase;
    }

    @PostMapping
    public ResponseEntity<RestaurantOutputDto> create(@RequestBody RestaurantInputDto dto) {
        Restaurant restaurant = RestaurantMapper.convertInputDtoToDomain(dto);
        Restaurant created = createUseCase.execute(restaurant);
        return ResponseEntity.ok(RestaurantMapper.convertEntitytoOutputDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantOutputDto> update(@PathVariable Long id, @RequestBody RestaurantInputDto dto) {
        Restaurant restaurant = RestaurantMapper.convertInputDtoToDomain(dto);
        restaurant = new Restaurant(id, restaurant.getName(), restaurant.getAddress(), restaurant.getCuisineType(), restaurant.getOpeningHours(), restaurant.getOwner());
        Restaurant updated = updateUseCase.execute(restaurant);
        return ResponseEntity.ok(RestaurantMapper.convertEntitytoOutputDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantOutputDto> getById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = getUseCase.findById(id);
        return restaurant.map(r -> ResponseEntity.ok(RestaurantMapper.convertEntitytoOutputDto(r)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RestaurantOutputDto>> getAll() {
        List<RestaurantOutputDto> list = getUseCase.findAll().stream()
                .map(RestaurantMapper::convertEntitytoOutputDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}

