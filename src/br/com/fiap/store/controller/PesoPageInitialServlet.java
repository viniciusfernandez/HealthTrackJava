package br.com.fiap.store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.store.bean.Peso;
import br.com.fiap.store.dao.PesoDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;

@WebServlet("/dashboard/dash-page-initial/dash-page-initial")
public class PesoPageInitialServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PesoDAO pesoDAO;

	

	@Override
	public void init() throws ServletException {
		super.init();
		pesoDAO = DAOFactory.getPesoDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response);
			break;
		case "abrir-form-edicao":
			abrirFormEdicao(request, response);
			break;
		}
		
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		Peso peso= pesoDAO.buscar(id);
		request.setAttribute("peso", peso);
		request.getRequestDispatcher("../../dashboard/dash-pesos/edicao-peso.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Peso> lista = pesoDAO.listar();
		request.setAttribute("pesos", lista);
		request.getRequestDispatcher("../../dashboard/dash-page-initial/dash-page-initial.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "editar":
			editar(request,response);
			break;
		case "excluir":
			excluir(request, response);
			break;
		}
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		try {
			pesoDAO.remover(codigo);
			request.setAttribute("msg", "Peso removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			double qtd = Double.parseDouble(request.getParameter("qtd"));

			Peso peso1 = new Peso(codigo, qtd);
			pesoDAO.atualizar(peso1);

			request.setAttribute("msg", "Peso atualizado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		listar(request,response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			double qtd = Double.parseDouble(request.getParameter("qtd"));

			Peso peso1 = new Peso(codigo, qtd);
			pesoDAO.cadastrar(peso1);

			request.setAttribute("msg", "Peso cadastrado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		request.getRequestDispatcher("../../dashboard/dash-pesos/cadastro-peso.jsp").forward(request, response);
	}

}