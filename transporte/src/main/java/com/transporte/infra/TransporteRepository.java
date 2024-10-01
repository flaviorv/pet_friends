package com.transporte.infra;

import com.transporte.domain.Transporte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface TransporteRepository extends CrudRepository<Transporte, Integer> {

    @Query("from Transporte t where t.pedido.id =:pedidoId and t.status ='ENTREGUE'")
    Transporte buscarTransporteEntregue(String pedidoId);
}
