package com.algaworks.algafood.infrastructure_repository;

import com.algaworks.algafood.domain.model.MethodPayment;
import com.algaworks.algafood.domain.model.MethodPayment;
import com.algaworks.algafood.domain.repository.MethodPaymentRepository;
import com.algaworks.algafood.domain.repository.MethodPaymentRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class MethodPaymentRepositoryImpl implements MethodPaymentRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<MethodPayment> findAll() {
        return manager.createQuery("SELECT mp FROM MethodPayment mp", MethodPayment.class)
                .getResultList();
    }

    @Override
    public MethodPayment findById(Long id) {
        return manager.find(MethodPayment.class, id);
    }

    @Transactional
    @Override
    public MethodPayment save(MethodPayment methodPayment) {
        return manager.merge(methodPayment);
    }

    @Transactional
    @Override
    public void delete(MethodPayment methodPayment) {
        methodPayment = findById(methodPayment.getId());
        manager.remove(methodPayment);
    }

}
