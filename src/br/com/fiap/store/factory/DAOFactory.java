package br.com.fiap.store.factory;

import br.com.fiap.store.dao.AlimentoDAO;
import br.com.fiap.store.dao.ExercicioDAO;
import br.com.fiap.store.dao.PesoDAO;
import br.com.fiap.store.dao.UsuarioDAO;
import br.com.fiap.store.dao.impl.OracleAlimentoDAO;
import br.com.fiap.store.dao.impl.OraclePesoDAO;
import br.com.fiap.store.dao.impl.OracleUsuarioDAO;
import br.com.fiap.store.dao.impl.OracleExercicioDAO;

public class DAOFactory {

	public static PesoDAO getPesoDAO() {
		return new OraclePesoDAO();
	}
	
	public static AlimentoDAO getAlimentoDAO() {
		return new OracleAlimentoDAO();
	}

	public static ExercicioDAO getExercicioDAO() {
		// TODO Auto-generated method stub
		return new OracleExercicioDAO();
	}

	public static UsuarioDAO getUsuarioDAO() {
		// TODO Auto-generated method stub
		return new OracleUsuarioDAO();
	}
	
	
}