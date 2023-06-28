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
        return stateService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> StateById(@PathVariable Long id){
        State state = stateService.findById(id);

        if(state != null){
            return ResponseEntity.ok(state);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public State newState(@RequestBody State state){
        return stateService.save(state);
    }

    @PutMapping("/{id}")
    public ResponseEntity<State> updateState(@PathVariable Long id,
                                             @RequestBody State state){
        State stateUpdate = stateRepository.findById(id);

        if(stateUpdate != null){
            BeanUtils.copyProperties(state,stateUpdate, "id");
            stateService.save(stateUpdate);
            return ResponseEntity.ok(stateUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteState(@PathVariable Long id) {
        try{
            stateService.delete(id);
            return ResponseEntity.noContent().build();

        }catch (EntityNotFound ex){
            return ResponseEntity.notFound().build();

        }catch (EntityInUse ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}
