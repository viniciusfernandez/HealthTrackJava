package br.com.fiap.store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.store.bean.Alimento;
import br.com.fiap.store.dao.AlimentoDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;

@WebServlet("/dashboard/dash-food/alimento")
public class AlimentoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AlimentoDAO alimentoDAO;

	

	@Override
	public void init() throws ServletException {
		super.init();
		alimentoDAO = DAOFactory.getAlimentoDAO();
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
		Alimento alimento= alimentoDAO.buscar(id);
		request.setAttribute("alimento", alimento);
		request.getRequestDispatcher("../../dashboard/dash-food/edicao-alimento.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Alimento> lista = alimentoDAO.listar();
		request.setAttribute("alimentos", lista);
		request.getRequestDispatcher("../../dashboard/dash-food/dash-food.jsp").forward(request, response);
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
			alimentoDAO.remover(codigo);
			request.setAttribute("msg", "Alimento removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String tipo = request.getParameter("tipo");
			String alimento = request.getParameter("alimento");
			double calorias = Double.parseDouble(request.getParameter("calorias"));

			Alimento alimento1 = new Alimento(codigo, tipo, alimento, calorias);
			alimentoDAO.atualizar(alimento1);

			request.setAttribute("msg", "Alimento atualizado!");
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
			String tipo = request.getParameter("tipo");
			String alimento = request.getParameter("alimento");
			double calorias = Double.parseDouble(request.getParameter("calorias"));

			Alimento alimento1 = new Alimento(codigo, tipo, alimento, calorias);
			alimentoDAO.cadastrar(alimento1);

			request.setAttribute("msg", "Alimento cadastrado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		request.getRequestDispatcher("../../dashboard/dash-food/cadastro-alimento.jsp").forward(request, response);
	}

}