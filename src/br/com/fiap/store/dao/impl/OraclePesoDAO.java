package br.com.fiap.store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.store.bean.Peso;
import br.com.fiap.store.dao.PesoDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.singleton.ConnectionManager;

public class OraclePesoDAO implements PesoDAO{


	private Connection conexao;

	@Override
	public void cadastrar(Peso peso) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_PESO (CD_PESO, QTD_PESO) VALUES ( ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, peso.getCodigo());
			stmt.setDouble(2, peso.getQtd());

			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void atualizar(Peso peso) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_PESO SET CD_PESO= ?, QTD_PESO= ? WHERE CD_PESO= ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, peso.getCodigo());
			stmt.setDouble(2, peso.getQtd());
			
			stmt.setInt(3, peso.getCodigo());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void remover(int codigo) throws DBException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM TB_PESO WHERE CD_PESO= ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao remover.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Peso buscar(int id) {
		Peso peso1 = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_PESO WHERE TB_PESO.CD_PESO= ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int codigo= rs.getInt("CD_PESO");
				int qtd= rs.getInt("QTD_PESO");
				
				peso1 = new Peso(codigo, qtd);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return peso1;
	}
	@Override
	public List<Peso> listar() {
		List<Peso> lista = new ArrayList<Peso>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_PESO");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				int codigo= rs.getInt("CD_PESO");
				int qtd= rs.getInt("QTD_PESO");
				
			
				
				Peso peso1= new Peso (codigo, qtd);
				
				lista.add(peso1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

}
