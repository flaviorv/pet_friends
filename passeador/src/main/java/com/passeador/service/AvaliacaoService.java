package com.passeador.service;

import com.passeador.domain.Avaliacao;
import com.passeador.infra.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private PasseadorService passeadorService;

    public Iterable<Avaliacao> listarPorPasseador(int idPasseador) {
        return avaliacaoRepository.listar(idPasseador);
    }

    public Avaliacao salvar(Avaliacao avaliacao, int idPasseador) throws Exception {
        avaliacao.setPasseador(passeadorService.porId(idPasseador));
        return avaliacaoRepository.save(avaliacao);
    }


}
