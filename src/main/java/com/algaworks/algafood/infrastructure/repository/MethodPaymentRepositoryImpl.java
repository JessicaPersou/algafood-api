package com.algaworks.algafood.infrastructure.repository;

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
//        return manager.createQuery("from MethodPayment", MethodPayment.class)
//                .getResultList();
        return null;
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
    public void remove(MethodPayment methodPayment) {
//        methodPayment = findAll(methodPayment.getId());
        manager.remove(methodPayment);
    }

}
