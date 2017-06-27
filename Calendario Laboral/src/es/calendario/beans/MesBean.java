package es.calendario.beans;

import java.util.ArrayList;

public class MesBean {

	private ArrayList<SemanaBean> listaSemanas;	
	private double minutosTrabajados;
	
	
	public ArrayList<SemanaBean> getListaSemanas() {
		return listaSemanas;
	}
	public void setListaSemanas(ArrayList<SemanaBean> listaSemanas) {
		this.listaSemanas = listaSemanas;
	}
	public double getMinutosTrabajados() {
		return minutosTrabajados;
	}
	public void setMinutosTrabajados(double minutosTrabajados) {
		this.minutosTrabajados = minutosTrabajados;
	}
	
	
}
