package br.com.fiap.store.teste;

import java.util.List;

import br.com.fiap.store.bean.Peso;
import br.com.fiap.store.dao.PesoDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;

public class TestePesoDAO{

	public static void main(String[] args) {
		PesoDAO pesoDAO = DAOFactory.getPesoDAO();
		
		//Cadastrar um peso
		Peso peso = new Peso(32,100.0);
		try {
			pesoDAO.cadastrar(peso);
			System.out.println("Peso cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

//		Buscar um peso pelo código e atualizar
		peso = pesoDAO.buscar(32);
		peso.setQtd(100);
		try {
			pesoDAO.atualizar(peso);
			System.out.println("Peso atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Listar os pesos
		List<Peso> lista = pesoDAO.listar();
		for (Peso peso1 : lista) {
			System.out.println(peso1.getCodigo() + " " + peso1.getQtd());
		}
		
		//Remover um peso
		try {
			pesoDAO.remover(32);
			System.out.println("Peso removido.");
		} catch (DBException e) {
			e.printStackTrace();
		}	
	}	
}