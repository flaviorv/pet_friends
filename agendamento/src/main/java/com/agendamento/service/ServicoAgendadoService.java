package com.agendamento.service;

import com.agendamento.client.Passeador;
import com.agendamento.client.PasseadorClient;
import com.agendamento.dominio.ServicoAgendado;
import com.agendamento.dominio.Servicos;
import com.agendamento.infra.ServicoAgendadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoAgendadoService {
    @Autowired
    private ServicoAgendadoRepository servicoAgendadoRepository;

    @Autowired
    private PasseadorClient passeadorClient;

    public Iterable<ServicoAgendado> listar() {
        return servicoAgendadoRepository.findAll();
    }

    public ServicoAgendado salvar(ServicoAgendado servicoAgendado) throws Exception {
        if(servicoAgendado.getServico() == Servicos.PASSEIO) {
            try {
                Passeador p = passeadorClient.porId(servicoAgendado.getIdPrestador());
                System.out.println(p.getNome() + p.getAvaliacaoMedia());

            }catch (Exception e) {
                throw new Exception("Passeador não encontrado.");
            }
            return servicoAgendadoRepository.save(servicoAgendado);
        }else{
            throw new Exception("Prestador de serviços ainda não cadastrado. Escolha outro serviço");
        }
    }


}
