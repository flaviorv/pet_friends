package com.agendamento.infra;

import com.agendamento.dominio.ServicoAgendado;
import org.springframework.data.repository.CrudRepository;

public interface ServicoAgendadoRepository extends CrudRepository<ServicoAgendado, Integer> {
}
