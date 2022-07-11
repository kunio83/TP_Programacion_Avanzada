package Models;

import java.util.ArrayList;

public class Receta {
	private String NombreReceta;
	private ArrayList<Ingrediente> Ingredientes;
	
	public Receta(String NombreReceta,  ArrayList<Ingrediente> Ingredientes) {
		this.NombreReceta = NombreReceta;
		this.Ingredientes = Ingredientes;

	}
	
	// NombreReceta
	public String getNombreReceta() {
		return NombreReceta;
	}

	public void setNombreReceta(String nombreReceta) {
		NombreReceta = nombreReceta;
	}

	// Ingredientes
	public ArrayList<Ingrediente> getIngredientes() {
		return Ingredientes;
	}

	public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
		Ingredientes = ingredientes;
	}
}
