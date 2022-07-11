package Servicios;

import Datos.IngredienteMapper;
import UI.Vent2;

public class Main {

	public static void main(String[] args) {
            IngredienteMapper a = new IngredienteMapper();
            //a.getDbToIngrediente();

            Vent2 vent = new Vent2();
            vent.setVisible(true);
	}
}
