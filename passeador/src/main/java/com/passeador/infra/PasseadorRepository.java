package com.passeador.infra;

import com.passeador.domain.Passeador;
import org.springframework.data.repository.CrudRepository;

public interface PasseadorRepository extends CrudRepository<Passeador, Integer> {
}
