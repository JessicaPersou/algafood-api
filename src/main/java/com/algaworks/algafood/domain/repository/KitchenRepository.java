package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> findAll();
    Kitchen findById(Long id);
    Kitchen save(Kitchen kitchen);
    void delete(Long id);
}
