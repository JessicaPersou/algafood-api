package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUse;
import com.algaworks.algafood.domain.exception.EntityNotFound;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;


    public City save(City city){
        Long stateId = city.getState().getId();

        State state = stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFound(
                        String.format("Não existe cadastro de estado com código %d", stateId)));

        city.setState(state);
        return cityRepository.save(city);
    }

    public void delete(Long id){
        try{
            cityRepository.deleteById(id);

        }catch(EmptyResultDataAccessException e){
            throw new EntityNotFound(
                    String.format("Cidade com código %d não foi encontrado.", id));

        }catch(DataIntegrityViolationException e){
            throw new EntityInUse(
                    String.format("Cidade com código %d não foi encontrado.", id));
        }
    }
}
