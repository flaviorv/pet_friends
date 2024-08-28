package com.passeador.infra;

import com.passeador.domain.Avaliacao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Integer> {
    @Query("from Avaliacao a where a.passeador.id = :idPasseador ")
    Iterable<Avaliacao> listar(int idPasseador);
}
