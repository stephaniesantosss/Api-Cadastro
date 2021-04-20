package com.cadastro.app.repository;

import com.cadastro.app.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByEmail (String email);
    boolean existsByCpf (String cpf);
    Pessoa findById(long id);
}
