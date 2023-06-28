package com.algaworks.algafood.api.controller;


import com.algaworks.algafood.domain.exception.EntityInUse;
import com.algaworks.algafood.domain.exception.EntityNotFound;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> AllRestaurants(){
        return restaurantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> RestaurantById(@PathVariable Long id){
        Restaurant restaurant = restaurantService.findById(id);
        if(restaurant != null){
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> newRestaurant(@RequestBody Restaurant restaurant){
        try{
            restaurant = restaurantService.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurant);
        }catch(EntityNotFound e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Long id,
                                              @RequestBody Restaurant restaurant) {
        Restaurant restaurantUpdate = restaurantRepository.findById(id);

        try{
            if (restaurantUpdate != null) {
                BeanUtils.copyProperties(restaurant, restaurantUpdate, "id");
                restaurantService.save(restaurantUpdate);
                return ResponseEntity.ok(restaurantUpdate);
            }
            return ResponseEntity.notFound().build();

        }catch(EntityNotFound e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartialRestaurant(@PathVariable Long id, @RequestBody Map<String, Object> fields){
            Restaurant RestaurantActual = restaurantRepository.findById(id);

            if (RestaurantActual == null) {
                return ResponseEntity.notFound().build();
            }

            merge(fields, RestaurantActual);

            return updateRestaurant(id, RestaurantActual);
        }

        private void merge(Map<String, Object> orige, Restaurant destiny) {
            ObjectMapper objectMapper = new ObjectMapper();
            Restaurant restaurantOrige = objectMapper.convertValue(orige, Restaurant.class);

            orige.forEach((name, value) -> {
                Field field = ReflectionUtils.findField(Restaurant.class, name);
                field.setAccessible(true);

                Object newValue = ReflectionUtils.getField(field, restaurantOrige);

                ReflectionUtils.setField(field, destiny, newValue);
            });

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id){
        try{
            restaurantService.delete(id);
            return ResponseEntity.noContent().build();

        }catch (EntityNotFound ex){
            return ResponseEntity.notFound().build();

        }catch (EntityInUse e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
