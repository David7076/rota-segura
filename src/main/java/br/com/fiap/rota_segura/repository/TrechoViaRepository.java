package br.com.fiap.rota_segura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.rota_segura.model.TrechoVia;

@Repository
public interface TrechoViaRepository extends JpaRepository<TrechoVia, Long> {

}
