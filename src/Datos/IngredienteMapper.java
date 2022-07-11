
package Datos;

import Models.Ingrediente;

public class IngredienteMapper {
    public static Ingrediente mapFileLineToIngrediente(String fileLine){
        String[] ingredienteArray = fileLine.split(";");
        int idIngrediente = Integer.parseInt(ingredienteArray[0]);
	String ingrediente = ingredienteArray[1];
	float cantidad = Float.parseFloat(ingredienteArray[2]);
	String magnitud = ingredienteArray[3];
		
	return new Ingrediente(idIngrediente, ingrediente, cantidad, magnitud);
    }
}
