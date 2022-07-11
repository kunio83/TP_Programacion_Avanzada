package Servicios;

import Datos.ConnectionBase;
import Datos.SourceFactory;
import Models.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import Models.Ingrediente;
import Models.Receta;

public class ServicioRecetas{

	private final String _pathArchivoIngredientes;
	private final String _patCarpetaRecetas;
	private ConnectionBase _connection;
	
        public ServicioRecetas(
                String pathFileIngredientes, 
                String pathFolderRecetas,
                DataSource dataSource
        ) {
		this._pathArchivoIngredientes = pathFileIngredientes;
		this._patCarpetaRecetas = pathFolderRecetas;
                
                var sourceFactory = new SourceFactory(_pathArchivoIngredientes, _patCarpetaRecetas);
                this._connection = sourceFactory.getConnection(dataSource);
	}
        
        public ArrayList<Receta> ObtenerRecetas(){
            ArrayList<Receta> recetas = this._connection.getAllRecetas();
            
            return recetas;
        }
        
        public ArrayList<Ingrediente> ObtenerIngredientesEnStock(){
            ArrayList<Ingrediente> ingredientes = this._connection.getAllIngredientes();
            
            return ingredientes;
        }
        
	/*
	public ArrayList<Receta> ObtenerRecetas()  throws FileNotFoundException {
		String nombreReceta;
		ArrayList<Receta> recetas = new ArrayList<Receta>(); 
		File folder = new File(_patCarpetaRecetas);
		
		for (File file : folder.listFiles()) {
			nombreReceta = file.getName().substring(0,file.getName().length()-4);
			boolean error = false;
			Scanner scanner = new Scanner(file);
			ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>(); 
			
			while(scanner.hasNextLine()) {	
				try {
					ingredientes.add(GetRecetaPorLinea(scanner.nextLine()));
				}
				catch (Exception e){
					error = true;
					break;
				}
				
			}
			
			if(!error && ingredientes.size()>0) {
				Receta receta = new Receta(nombreReceta, ingredientes);
				recetas.add(receta);
			}

			
			scanner.close();
		}
		
		return recetas;
	}
	
	private Ingrediente GetRecetaPorLinea(String recetaStr) throws IndexOutOfBoundsException{
		String[] recetaArray = recetaStr.split(";");
		int Id = Integer.parseInt(recetaArray[0]);
		String Ingrediente = recetaArray[1];
		float Cantidad = Float.parseFloat(recetaArray[2]);
		
		return new Ingrediente(Id, Ingrediente, Cantidad);
	}

	public ArrayList<Ingrediente> ObtenerIngredientesEnStock() throws FileNotFoundException {
		File ingredientesFile = new File(_pathArchivoIngredientes);
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		Scanner scanner = new Scanner(ingredientesFile);
		 
		while(scanner.hasNextLine()) {	
			try {
				ingredientes.add(GetIngredientePorLinea(scanner.nextLine()));
			}
			catch (Exception e){
				continue;
			}
			
		}
		
		scanner.close();
		
		return ingredientes;
	}
	
	private Ingrediente GetIngredientePorLinea(String ingredienteLinea) throws IndexOutOfBoundsException{
		String[] ingredienteArray = ingredienteLinea.split(";");
		int idIngrediente = Integer.parseInt(ingredienteArray[0]);
		String ingrediente = ingredienteArray[1];
		float cantidad = Float.parseFloat(ingredienteArray[2]);
		String magnitud = ingredienteArray[3];
		
		return new Ingrediente(idIngrediente, ingrediente, cantidad, magnitud);
	}
*/
}