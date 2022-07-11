package Servicios;

import java.util.ArrayList;

import Models.Ingrediente;
import Models.Receta;

public class ResulRecetas {
	private  ArrayList<Receta> recetasFactibles;
	private ArrayList<Receta> recetasNoFactibles;
	private ArrayList<Ingrediente> ingredientes;



	public ResulRecetas(ArrayList<Receta> recetasFactibles, ArrayList<Receta> recetasNoFactibles, ArrayList<Ingrediente> ingredientes) {
		this.recetasFactibles = recetasFactibles;
		this.recetasNoFactibles = recetasNoFactibles;
		this.ingredientes = ingredientes;
	}

	public ArrayList<Receta> getRecetasFactibles() {
		return recetasFactibles;
	}

	public void setRecetasFactibles(ArrayList<Receta> recetasFactibles) {
		this.recetasFactibles = recetasFactibles;
	}

	public ArrayList<Receta> getRecetasNoFactibles() {
		return recetasNoFactibles;
	}

	public void setRecetasNoFactibles(ArrayList<Receta> recetasNoFactibles) {
		this.recetasNoFactibles = recetasNoFactibles;
	}
	
	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

}
