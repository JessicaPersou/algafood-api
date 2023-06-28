package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUse;
import com.algaworks.algafood.domain.exception.EntityNotFound;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public List<Kitchen> findAll(){
        return kitchenRepository.findAll();
    }

    public Kitchen findById(Long id){
        return kitchenRepository.findById(id);
    }

    public Kitchen save(Kitchen kitchen){
        return kitchenRepository.save(kitchen);
    }

    public void delete(Long id){
        try {
            kitchenRepository.delete(id);
        }catch(EmptyResultDataAccessException e){
            throw new EntityNotFound(
                    String.format("Cozinha com código %d não foi encontrado.", id));

        }catch(DataIntegrityViolationException e){
            throw new EntityInUse(
                    String.format("Cozinha com código %d não pode ser excluído.", id));
        }
    }

}
