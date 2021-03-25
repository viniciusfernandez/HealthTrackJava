package br.com.fiap.store.dao;

import java.util.List;

import br.com.fiap.store.bean.Alimento;
import br.com.fiap.store.exception.DBException;

public interface AlimentoDAO {
	
	void cadastrar (Alimento alimento) throws DBException;
	void atualizar (Alimento alimento) throws DBException;
	void remover (int codigo) throws DBException;
	Alimento buscar (int id);
	List<Alimento> listar();
	
}
