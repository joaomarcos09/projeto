package br.ufc.quinta.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufc.quinta.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("from User where nome = ?1")
	User findByNome(String nome);
}
