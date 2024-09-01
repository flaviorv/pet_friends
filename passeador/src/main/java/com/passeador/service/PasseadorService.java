package com.passeador.service;

import com.passeador.domain.Passeador;
import com.passeador.infra.PasseadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasseadorService {
    @Autowired
    private PasseadorRepository passeadorRepository;

    public Iterable<Passeador> listar(){
        return passeadorRepository.findAll();
    }



    public Passeador porId(int id) throws Exception {
        Optional<Passeador> passeador = passeadorRepository.findById(id);
        if(passeador.isPresent()) {
            return passeador.get();
        }
        throw new Exception("Passeador não encontrado.");
    }

    public Passeador salvar(Passeador passeador){
        return passeadorRepository.save(passeador);
    }
}
