
package Datos;

import Models.Ingrediente;
import Models.Receta;

public class Mapper {
    public static Ingrediente mapFileLineToIngrediente(String fileLine){
        String[] ingredienteArray = fileLine.split(";");
        int idIngrediente = Integer.parseInt(ingredienteArray[0]);
	String ingrediente = ingredienteArray[1];
	float cantidad = Float.parseFloat(ingredienteArray[2]);
	String magnitud = ingredienteArray[3];
		
	return new Ingrediente(idIngrediente, ingrediente, cantidad, magnitud);
    }
    
    public static Receta mapFileLineToReceta(String fileLine){
        String[] recetaArray = fileLine.split(";");
        int Id = Integer.parseInt(recetaArray[0]);
        String Ingrediente = recetaArray[1];
        float Cantidad = Float.parseFloat(recetaArray[2]);

        return new Ingrediente(Id, Ingrediente, Cantidad);
    }
}
