package br.com.fiap.store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.store.bean.Exercicio;
import br.com.fiap.store.dao.ExercicioDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;

@WebServlet("/dashboard/dash-exercise/exercicio")
public class ExercicioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ExercicioDAO exercicioDAO;

	

	@Override
	public void init() throws ServletException {
		super.init();
		exercicioDAO = DAOFactory.getExercicioDAO();
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
		Exercicio exercicio= exercicioDAO.buscar(id);
		request.setAttribute("exercicio", exercicio);
		request.getRequestDispatcher("../../dashboard/dash-exercise/edicao-exercicio.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Exercicio> lista = exercicioDAO.listar();
		request.setAttribute("exercicios", lista);
		request.getRequestDispatcher("../../dashboard/dash-exercise/dash-exercise.jsp").forward(request, response);
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
			exercicioDAO.remover(codigo);
			request.setAttribute("msg", "Exercicio removido!");
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
			String exercicio = request.getParameter("exercicio");
			double calorias = Double.parseDouble(request.getParameter("calorias"));

			Exercicio exercicio1 = new Exercicio(codigo, tipo, exercicio, calorias);
			exercicioDAO.atualizar(exercicio1);

			request.setAttribute("msg", "Exercicio atualizado!");
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
			String exercicio = request.getParameter("exercicio");
			double calorias = Double.parseDouble(request.getParameter("calorias"));

			Exercicio exercicio1 = new Exercicio(codigo, tipo, exercicio, calorias);
			exercicioDAO.cadastrar(exercicio1);

			request.setAttribute("msg", "Exercicio cadastrado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		request.getRequestDispatcher("../../dashboard/dash-exercise/cadastro-exercicio.jsp").forward(request, response);
	}

}