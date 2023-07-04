package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUse;
import com.algaworks.algafood.domain.exception.EntityNotFound;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.service.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    public List<Kitchen> AllKitchens(){
        return kitchenRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> KitchenById(@PathVariable Long id){
        Optional<Kitchen> kitchen = kitchenRepository.findById(id);

        if(kitchen.isPresent()){
            return ResponseEntity.ok(kitchen.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Kitchen newKitchen(@RequestBody Kitchen kitchen){
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> updateKitchen(@PathVariable Long id,
                                                 @RequestBody Kitchen kitchen){
        Kitchen kitchenUpdate = kitchenRepository.findById(id).orElse(null);

        if(kitchenUpdate != null){
            BeanUtils.copyProperties(kitchen, kitchenUpdate, "id");

            kitchenService.save(kitchenUpdate);
            return ResponseEntity.ok(kitchenUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKitchen(@PathVariable Long id){
        try {
            kitchenService.delete(id);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFound e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUse e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
