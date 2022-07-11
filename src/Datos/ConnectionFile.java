package Datos;

import Models.Ingrediente;
import Models.Receta;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFile implements ConnectionBase{
            
    private final String _pathArchivoIngredientes;
    private final String _pathFolderRecetas;
	
    public ConnectionFile(String pathFileIngredientes, String pathFolderRecetas) {
            this._pathArchivoIngredientes = pathFileIngredientes;
            this._pathFolderRecetas = pathFolderRecetas;
    }
            
    @Override
    public ArrayList<Ingrediente>  getAllIngredientes() {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        try
        {
            File ingredientesFile = new File(_pathArchivoIngredientes);
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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ingredientes;
    }
    
    @Override
    public Ingrediente  getIngrediente(int id) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Receta> getAllRecetas() {
        String nombreReceta;
        ArrayList<Receta> recetas = new ArrayList<>(); 
        File folder = new File(_pathFolderRecetas);
            
        try{
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
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return recetas;
    }

    @Override
    public Receta getReceta(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private Ingrediente GetRecetaPorLinea(String recetaStr) throws IndexOutOfBoundsException{
            String[] recetaArray = recetaStr.split(";");
            int Id = Integer.parseInt(recetaArray[0]);
            String Ingrediente = recetaArray[1];
            float Cantidad = Float.parseFloat(recetaArray[2]);

            return new Ingrediente(Id, Ingrediente, Cantidad);
    }
    	
    private Ingrediente GetIngredientePorLinea(String ingredienteLinea) throws IndexOutOfBoundsException{
            String[] ingredienteArray = ingredienteLinea.split(";");
            int idIngrediente = Integer.parseInt(ingredienteArray[0]);
            String ingrediente = ingredienteArray[1];
            float cantidad = Float.parseFloat(ingredienteArray[2]);
            String magnitud = ingredienteArray[3];

            return new Ingrediente(idIngrediente, ingrediente, cantidad, magnitud);
    }
}