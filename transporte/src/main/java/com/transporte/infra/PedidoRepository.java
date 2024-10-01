package com.transporte.infra;

import com.transporte.domain.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, String> {
}
