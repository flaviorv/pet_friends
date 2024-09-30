package com.almoxarifado.infra;

import com.almoxarifado.domain.EstoqueProduto;
import org.springframework.data.repository.CrudRepository;


public interface EstoqueRepository extends CrudRepository<EstoqueProduto, Integer> {

}
