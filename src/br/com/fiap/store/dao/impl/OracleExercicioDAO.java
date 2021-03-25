package br.com.fiap.store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.store.bean.Exercicio;
import br.com.fiap.store.dao.ExercicioDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.singleton.ConnectionManager;

public class OracleExercicioDAO implements ExercicioDAO{


	private Connection conexao;

	@Override
	public void cadastrar(Exercicio exercicio) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_EXERCICIO (CD_EXERCICIO,TIPO_EXERCICIO ,NM_EXERCICIO, KCAL) VALUES ( ?, ?, ?,?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, exercicio.getCodigo());
			stmt.setString(2, exercicio.getTipo());
			stmt.setString(3, exercicio.getExercicio());
			stmt.setDouble(4, exercicio.getCalorias());

			
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
	public void atualizar(Exercicio exercicio) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_EXERCICIO SET CD_EXERCICIO= ?, TIPO_EXERCICIO= ?, NM_EXERCICIO= ? , KCAL= ? WHERE CD_EXERCICIO= ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, exercicio.getCodigo());
			stmt.setString(2, exercicio.getTipo());
			stmt.setString(3, exercicio.getExercicio());
			stmt.setDouble(4, exercicio.getCalorias());
			
			stmt.setInt(5, exercicio.getCodigo());

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
			String sql = "DELETE FROM TB_EXERCICIO WHERE CD_EXERCICIO= ?";
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
	public Exercicio buscar(int id) {
		Exercicio exercicio1 = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_EXERCICIO WHERE TB_EXERCICIO.CD_EXERCICIO= ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int codigo= rs.getInt("CD_EXERCICIO");
				String tipo = rs.getString("TIPO_EXERCICIO");
				String exercicio = rs.getString("NM_EXERCICIO");		
				int calorias= rs.getInt("KCAL");
				
				exercicio1 = new Exercicio (codigo, tipo, exercicio, calorias);

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
		return exercicio1;
	}
	@Override
	public List<Exercicio> listar() {
		List<Exercicio> lista = new ArrayList<Exercicio>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_EXERCICIO");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				int codigo= rs.getInt("CD_EXERCICIO");
				String tipo = rs.getString("TIPO_EXERCICIO");
				String exercicio = rs.getString("NM_EXERCICIO");		
				int calorias= rs.getInt("KCAL");
				
			
				
				Exercicio exercicio1= new Exercicio (codigo, tipo, exercicio, calorias);
				
				lista.add(exercicio1);
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
