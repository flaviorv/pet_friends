package com.agendamento.service;

import com.agendamento.dominio.ServicoAgendado;
import com.agendamento.infra.ServicoAgendadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoAgendadoService {
    @Autowired
    private ServicoAgendadoRepository servicoAgendadoRepository;

    public Iterable<ServicoAgendado> listar() {
        return servicoAgendadoRepository.findAll();
    }

    public ServicoAgendado salvar(ServicoAgendado servicoAgendado) {
        return servicoAgendadoRepository.save(servicoAgendado);
    }


}
