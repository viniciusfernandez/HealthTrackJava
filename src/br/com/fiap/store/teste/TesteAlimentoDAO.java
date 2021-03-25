package br.com.fiap.store.teste;

import java.util.List;

import br.com.fiap.store.bean.Alimento;
import br.com.fiap.store.dao.AlimentoDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;

public class TesteAlimentoDAO{

	public static void main(String[] args) {
		AlimentoDAO alimentoDAO = DAOFactory.getAlimentoDAO();
		
		//Cadastrar um alimento
		Alimento alimento = new Alimento(1,"almoco","arroz" ,100);
		try {
			alimentoDAO.cadastrar(alimento);
			System.out.println("Alimento cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		//Buscar um alimento pelo código e atualizar
		alimento = alimentoDAO.buscar(1);
		alimento.setTipo("almoço");
		alimento.setAlimento("arroz");
		alimento.setCalorias(100);
		try {
			alimentoDAO.atualizar(alimento);
			System.out.println("Alimento atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Listar os alimentos
		List<Alimento> lista = alimentoDAO.listar();
		for (Alimento alimento1 : lista) {
			System.out.println(alimento1.getCodigo() + " " + alimento1.getTipo()+ " " + alimento1.getAlimento()+ " " + alimento1.getCalorias());
		}
		
		//Remover um alimento
		try {
			alimentoDAO.remover(1);
			System.out.println("Alimento removido.");
		} catch (DBException e) {
			e.printStackTrace();
		}	
	}	
}