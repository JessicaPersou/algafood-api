package com.algaworks.algafood.domain.repository;


import com.algaworks.algafood.domain.model.MethodPayment;

import java.util.List;

public interface MethodPaymentRepository {

    List<MethodPayment> findAll();
    MethodPayment findById(Long id);
    MethodPayment save(MethodPayment methodPayment);
    void delete(MethodPayment methodPayment);

}

