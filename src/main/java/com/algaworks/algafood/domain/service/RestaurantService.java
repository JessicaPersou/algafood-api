package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUse;
import com.algaworks.algafood.domain.exception.EntityNotFound;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;


    public Restaurant save(Restaurant restaurant){
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFound(
                        String.format("Não existe cadastro de cozinha com código %d",kitchenId)));

        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }

    public void delete(Long id){
        try {
            restaurantRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new EntityNotFound(
                    String.format("Restaurante com código %d não foi encontrado.", id));

        }catch(DataIntegrityViolationException e){
            throw new EntityInUse(
                    String.format("Restaurante com código %d não pode ser excluído.", id));
        }
    }

}
