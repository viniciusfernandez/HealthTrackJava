package br.com.fiap.store.bean;

public class Alimento {

	private int codigo;
	private String tipo;
	private String alimento;
	private double calorias;
	
	
	public Alimento() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Alimento(int codigo, String tipo, String alimento, double calorias) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.alimento = alimento;
		this.calorias = calorias;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getAlimento() {
		return alimento;
	}


	public void setAlimento(String alimento) {
		this.alimento = alimento;
	}


	public double getCalorias() {
		return calorias;
	}


	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}
	
	
	
}
