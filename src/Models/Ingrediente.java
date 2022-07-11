package Models;

public class Ingrediente {

	private int Id;
	private String Ingrediente;
	private float Cantidad;
	private String Magnitud;
	
	public Ingrediente(int Id, String Ingrediente, float Cantidad, String Magnitud) {
		this.Id = Id;
		this.Ingrediente = Ingrediente;
		this.Cantidad = Cantidad;
		this.Magnitud = Magnitud;
	}
	
	public Ingrediente(int Id, String Ingrediente, float Cantidad) {
		this.Id = Id;
		this.Ingrediente = Ingrediente;
		this.Cantidad = Cantidad;
		this.Magnitud = null;
	}
	
	// Id
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	// Ingrediente
	public String getIngrediente() {
		return Ingrediente;
	}
	public void setIngrediente(String ingrediente) {
		Ingrediente = ingrediente;
	}
	
	// Cantidad
	public float getCantidad() {
		return Cantidad;
	}
	public void setCantidad(float cantidad) {
		Cantidad = cantidad;
	}
	
	// Magnitud
	public String getMagnitud() {
		return Magnitud;
	}
	public void setMagnitud(String magnitud) {
		Magnitud = magnitud;
	}
}
