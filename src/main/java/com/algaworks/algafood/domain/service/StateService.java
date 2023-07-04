package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUse;
import com.algaworks.algafood.domain.exception.EntityNotFound;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service // responsável pelas regras de negócio do Objeto State
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public State save(State state){
        return stateRepository.save(state);
    }

    public void delete(Long id){
        try {
            stateRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new EntityNotFound(
                    String.format("Estado com código %d não foi encontrado.", id));

        }catch(DataIntegrityViolationException e){
            throw new EntityInUse(
                    String.format("Estado com código %d não pode ser excluído.", id));
        }
    }
}
