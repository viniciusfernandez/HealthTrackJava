package br.com.fiap.store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.store.bean.Alimento;
import br.com.fiap.store.dao.AlimentoDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.singleton.ConnectionManager;

public class OracleAlimentoDAO implements AlimentoDAO{


	private Connection conexao;

	@Override
	public void cadastrar(Alimento alimento) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_ALIMENTO (CD_ALIMENTO,TIPO_ALIMENTO ,NM_ALIMENTO, KCAL) VALUES ( ?, ?, ?,?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, alimento.getCodigo());
			stmt.setString(2, alimento.getTipo());
			stmt.setString(3, alimento.getAlimento());
			stmt.setDouble(4, alimento.getCalorias());

			
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
	public void atualizar(Alimento alimento) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_ALIMENTO SET CD_ALIMENTO= ?, TIPO_ALIMENTO= ?, NM_ALIMENTO= ? , KCAL= ? WHERE CD_ALIMENTO= ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, alimento.getCodigo());
			stmt.setString(2, alimento.getTipo());
			stmt.setString(3, alimento.getAlimento());
			stmt.setDouble(4, alimento.getCalorias());
			
			stmt.setInt(5, alimento.getCodigo());

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
			String sql = "DELETE FROM TB_ALIMENTO WHERE CD_ALIMENTO= ?";
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
	public Alimento buscar(int id) {
		Alimento alimento1 = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_ALIMENTO WHERE TB_ALIMENTO.CD_ALIMENTO= ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int codigo= rs.getInt("CD_ALIMENTO");
				String tipo = rs.getString("TIPO_ALIMENTO");
				String alimento = rs.getString("NM_ALIMENTO");		
				int calorias= rs.getInt("KCAL");
				
				alimento1 = new Alimento (codigo, tipo, alimento, calorias);

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
		return alimento1;
	}
	@Override
	public List<Alimento> listar() {
		List<Alimento> lista = new ArrayList<Alimento>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_ALIMENTO");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				int codigo= rs.getInt("CD_ALIMENTO");
				String tipo = rs.getString("TIPO_ALIMENTO");
				String alimento = rs.getString("NM_ALIMENTO");		
				int calorias= rs.getInt("KCAL");
				
			
				
				Alimento alimento1= new Alimento (codigo, tipo, alimento, calorias);
				
				lista.add(alimento1);
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
