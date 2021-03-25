package br.com.fiap.store.bean;


public class Peso {

	private int codigo;
	private double qtd;
	
	
	public Peso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Peso(int codigo, double qtd) {
		this.codigo = codigo;
		this.qtd = qtd;
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getQtd() {
		return qtd;
	}

	public void setQtd(double qtd) {
		this.qtd = qtd;
	}

	

}