package br.com.fiap.store.bean;

public class Exercicio {

	private int codigo;
	private String tipo;
	private String exercicio;
	private double calorias;
	public Exercicio() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Exercicio(int codigo, String tipo, String exercicio, double calorias) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.exercicio = exercicio;
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
	public String getExercicio() {
		return exercicio;
	}
	public void setExercicio(String exercicio) {
		this.exercicio = exercicio;
	}
	public double getCalorias() {
		return calorias;
	}
	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}
	
	
	
}
