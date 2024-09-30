package com.agendamento.infra;

import com.agendamento.domain.ServicoAgendado;
import com.agendamento.domain.Servicos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ServicoAgendadoRepository extends CrudRepository<ServicoAgendado, Integer> {
    @Query("from ServicoAgendado sa where sa.data = :data and sa.servico = :servico and sa.idPrestador = :idPrestador")
    Optional<ServicoAgendado> buscarPor(String data, Servicos servico, int idPrestador);
}
