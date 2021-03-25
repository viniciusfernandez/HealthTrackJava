package br.com.fiap.store.dao;

import java.util.List;

import br.com.fiap.store.bean.Exercicio;
import br.com.fiap.store.exception.DBException;

public interface ExercicioDAO {

	void cadastrar (Exercicio exercicio) throws DBException;
	void atualizar (Exercicio exercicio) throws DBException;
	void remover (int codigo) throws DBException;
	Exercicio buscar (int id);
	List<Exercicio> listar();
	
}


