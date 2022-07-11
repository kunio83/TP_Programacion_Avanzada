package Datos;

import Models.Ingrediente;
import Models.Receta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionSqlite implements ConnectionBase{
    private Statement _statement;
    private ResultSet _resultSet;
    private Connection _connection;
            
    @Override
    public ArrayList<Ingrediente>  getAllIngredientes() {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        
        try {
            String query = "select * from ingredientes;";
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:./src/Datos/recetas.db";
            
            // create a connection to the database
            _connection = DriverManager.getConnection(url);
            _statement = _connection.createStatement();
            _resultSet = _statement.executeQuery(query);
            Ingrediente ingrediente;

            while(_resultSet.next()){
                //MAP HERE
                int idIngrediente = _resultSet.getInt("id");
                String nombreIngrediente = _resultSet.getString("ingrediente");
                float cantidad = _resultSet.getFloat("cantidad");
                String magnitud = _resultSet.getString("magnitud");
                ingrediente = new Ingrediente(idIngrediente, nombreIngrediente, cantidad, magnitud);
                
                ingredientes.add(ingrediente);
            }
        } catch (SQLException | ClassNotFoundException ex) {
                System.err.println(ex.getMessage());
        } finally {
            if (_connection != null)
            {
                try
                {
                    _connection.close();
                } catch (SQLException ex)
                {
                    Logger.getLogger(ConnectionSqlite.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return ingredientes;
    }
    
    @Override
    public Ingrediente  getIngrediente(int id) {
        try {
            String query = "select * from ingredientes where id =" + id + ";";
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:./src/Datos/recetas.db";
            
            // create a connection to the database
            _connection = DriverManager.getConnection(url);
            _statement = _connection.createStatement();
            _resultSet = _statement.executeQuery(query);
            
            while(_resultSet.next()){
                //MAP HERE
            }
        } catch (SQLException | ClassNotFoundException ex) {
                System.err.println(ex.getMessage());
        } finally {
            if (_connection != null)
            {
                try
                {
                    _connection.close();
                } catch (SQLException ex)
                {
                    Logger.getLogger(ConnectionSqlite.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return null;
    }

    @Override
    public ArrayList<Receta> getAllRecetas() {
        ArrayList<Receta> recetas = new ArrayList<>();
        
        try {
            String query = """
                           select 
                               r.receta,
                               i.id ingredienteId,
                               i.ingrediente,
                               r2r.cantidad 
                           from recetas r
                           join ingredientes2recetas r2r on r.id = r2r.recetaId
                           join ingredientes i on i.id = r2r.ingredienteId;
                           """;
            
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:./src/Datos/recetas.db";
            
            // create a connection to the database
            _connection = DriverManager.getConnection(url);
            _statement = _connection.createStatement();
            _resultSet = _statement.executeQuery(query);
            
            ArrayList<Ingrediente> recetaIngredientes = new ArrayList<>();
            Ingrediente recetaIngrediente;
            Receta receta;
            String recetaNombre = "";
            String recetaNombreActual = "";
            
            while(_resultSet.next()) {
                //MAP HERE
                recetaNombre = _resultSet.getString("receta");
                if (recetaNombreActual == "")
                {
                    recetaNombreActual = recetaNombre;
                }
                
                recetaIngrediente = getRecetaIngrediente(_resultSet);
                    
                if (!recetaNombreActual.equals(recetaNombre))
                {   
                    if (recetaIngredientes != null && !recetaIngredientes.isEmpty())
                    {
                        receta = new Receta(recetaNombreActual, recetaIngredientes);
                        recetas.add(receta);
                        recetaNombreActual = recetaNombre;
                    }
                    
                    recetaIngredientes = new ArrayList<>();
                    recetaIngredientes.add(recetaIngrediente);
                } else{
                    recetaIngredientes.add(recetaIngrediente);
                }
            }
            
            receta = new Receta(recetaNombreActual, recetaIngredientes);
            recetas.add(receta);
            
            
            /*
                    Risotto	8	queso parmesano	30
                    Risotto	9	caldo	2
                    Risotto	3	cebollas	1
                    Risotto	58	Arroz	150
                    Tortilla de papa	3	cebollas	0.5
                    Tortilla de papa	2	papas	4
                    Tortilla de papa	1	huevos	4
            */
            
        } catch (SQLException | ClassNotFoundException ex) {
                System.err.println(ex.getMessage());
        } finally {
            if (_connection != null)
            {
                try
                {
                    _connection.close();
                } catch (SQLException ex)
                {
                    Logger.getLogger(ConnectionSqlite.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return recetas;
    }

    @Override
    public Receta getReceta(int id) {
         try {
            String query = "select * from recetas where id =" + id + ";";
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:./src/Datos/recetas.db";
            
            // create a connection to the database
            _connection = DriverManager.getConnection(url);
            _statement = _connection.createStatement();
            _resultSet = _statement.executeQuery(query);
            
            while(_resultSet.next()){
                //MAP HERE
            }
        } catch (SQLException | ClassNotFoundException ex) {
                System.err.println(ex.getMessage());
        } finally {
            if (_connection != null)
            {
                try
                {
                    _connection.close();
                } catch (SQLException ex)
                {
                    Logger.getLogger(ConnectionSqlite.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return null;
    }
    
    private Ingrediente getRecetaIngrediente(ResultSet resultSet){
        Ingrediente ingrediente = null;
        
        try{
            int Id = resultSet.getInt("ingredienteId");
            String Ingrediente = resultSet.getString("ingrediente");
            float Cantidad = resultSet.getFloat("cantidad");
            
            ingrediente = new Ingrediente(Id, Ingrediente, Cantidad);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return ingrediente;
    }
}