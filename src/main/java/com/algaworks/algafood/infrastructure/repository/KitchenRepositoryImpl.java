package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Kitchen> findAll() {
        return manager.createQuery("from Kitchen", Kitchen.class)
                .getResultList();
    }

    @Override
    public Kitchen findById(Long id) {
        return manager.find(Kitchen.class, id);
    }

    @Transactional
    @Override
    public Kitchen save(Kitchen kitchen) {
        return manager.merge(kitchen);
    }

    @Transactional
    @Override
    public void remove(Kitchen kitchen) {
        kitchen = findAll(kitchen.getId());
        manager.remove(kitchen);
    }

}