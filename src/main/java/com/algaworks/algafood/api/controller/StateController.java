package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUse;
import com.algaworks.algafood.domain.exception.EntityNotFound;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import com.algaworks.algafood.domain.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> AllStates(){
        return stateRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> StateById(@PathVariable Long id){
        State state = stateRepository.findById(id).orElse(null);

        if(state != null){
            return ResponseEntity.ok(state);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> newState(@RequestBody State state){
        try{
            state = stateService.save(state);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(state);
        }catch (EntityNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateState(@PathVariable Long id,
                                             @RequestBody State state){
        State stateUpdate = stateRepository.findById(id).orElse(null);

        try{
            if(stateUpdate != null){
                BeanUtils.copyProperties(state,stateUpdate, "id");

                stateService.save(stateUpdate);
                return ResponseEntity.ok(stateUpdate);
            }
                return ResponseEntity.notFound().build();
        }catch(EntityNotFound e){
                return ResponseEntity.badRequest()
                            .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteState(@PathVariable Long id) {
        try{
            stateService.delete(id);
            return ResponseEntity.noContent().build();

        }catch (EntityNotFound e){
            return ResponseEntity.notFound().build();

        }catch (EntityInUse e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
