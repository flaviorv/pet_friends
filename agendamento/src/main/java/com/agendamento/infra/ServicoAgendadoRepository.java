package com.agendamento.infra;

import com.agendamento.domain.ServicoAgendado;
import org.springframework.data.repository.CrudRepository;

public interface ServicoAgendadoRepository extends CrudRepository<ServicoAgendado, Integer> {
}
