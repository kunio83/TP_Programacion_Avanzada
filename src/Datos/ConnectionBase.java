package Datos;

import Models.Ingrediente;
import Models.Receta;
import java.util.ArrayList;

public interface ConnectionBase {
    
    public ArrayList<Ingrediente>  getAllIngredientes();
    
    public Ingrediente  getIngrediente(int id);
    
    public ArrayList<Receta> getAllRecetas();
    
    public Receta getReceta(int id);
}
