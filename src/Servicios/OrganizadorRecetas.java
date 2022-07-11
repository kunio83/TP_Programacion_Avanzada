package Servicios;

import Models.DataSource;
import java.util.ArrayList;

import Models.Ingrediente;
import Models.Receta;

public class OrganizadorRecetas {

	private String _pathFileIngredientes;
	private String _pathFolderRecetas;
	private ArrayList<Receta> recetasFactibles = new ArrayList<Receta>();
	private ArrayList<Receta> recetasNoFactibles = new ArrayList<Receta>();
	private ArrayList<Ingrediente> ingredientes;
	
	public OrganizadorRecetas(String pathFileIngredientes, String pathFolderRecetas) {
		this._pathFileIngredientes = pathFileIngredientes;
		this._pathFolderRecetas = pathFolderRecetas;
	}
	
	public ResulRecetas DeterminarFactibilidad(DataSource dataSource) {

		try {
			ServicioRecetas servicioRecetas = new ServicioRecetas(_pathFileIngredientes, _pathFolderRecetas, dataSource);
			ingredientes = servicioRecetas.ObtenerIngredientesEnStock();
			ArrayList<Receta> recetas = servicioRecetas.ObtenerRecetas();
			boolean recetaActualFactible;
			
			for(Receta receta : recetas) {
				recetaActualFactible = true;
				
				for(Ingrediente ingrediente : receta.getIngredientes()) {
					if (!ContineneIngredienteDisponible(ingredientes, ingrediente)) {
						recetaActualFactible = false;
						break;
					} 
				}
				
				if (recetaActualFactible) {
					recetasFactibles.add(receta);
				} else {
					recetasNoFactibles.add(receta);
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		return new ResulRecetas(recetasFactibles, recetasNoFactibles, ingredientes);
		
	}
	
	private boolean ContineneIngredienteDisponible(ArrayList<Ingrediente> ingredientes, Ingrediente ingredienteAChequear) {
		for(Ingrediente ingrediente : ingredientes) {
			if (
				ingrediente.getId() == ingredienteAChequear.getId() &&
				ingrediente.getCantidad() >= ingredienteAChequear.getCantidad()
				) {
				return true;
			}
		}
		
		return false;
	}
}
