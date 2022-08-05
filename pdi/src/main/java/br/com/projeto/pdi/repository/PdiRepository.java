package br.com.projeto.pdi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.pdi.model.Funcionario;
import br.com.projeto.pdi.model.Pdi;

@Repository
public interface PdiRepository extends JpaRepository<Pdi, Long> {

	public List<Pdi> findByFuncionario(Funcionario funcionario);

}
