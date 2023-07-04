package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
