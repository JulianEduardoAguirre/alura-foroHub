package com.alura.forohub.repository;

import com.alura.forohub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

//    @Query("SELECT t FROM Topico t ORDER BY t.fecha_creacion DESC LIMIT 10")
//    List<Topico> findTop10ByFechaCreacionOrderByFechaCreacionDesc();

}
