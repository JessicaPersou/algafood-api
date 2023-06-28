package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUse;
import com.algaworks.algafood.domain.exception.EntityNotFound;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> AllCities(){
        return cityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> CityById(@PathVariable Long id){
        City city = cityRepository.findById(id);

        if(city != null){
            return ResponseEntity.ok(city);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public City newCity(@RequestBody City city){
        return cityService.save(city);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id,
                                           @RequestBody City city){
        City cityUpdate = cityService.findById(id);

            if(cityUpdate != null){
            BeanUtils.copyProperties(city, cityUpdate, "id");
            cityService.save(cityUpdate);
            return ResponseEntity.ok(cityUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Long id){
        try {
            cityService.delete(id);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFound e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUse e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
