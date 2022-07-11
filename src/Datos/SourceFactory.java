
package Datos;

import Models.DataSource;
import java.util.HashMap;
import java.util.Map;

public class SourceFactory {
    private final Map<DataSource, ConnectionBase> _connectionMap = new HashMap<>();
    
    public SourceFactory(
        String _pathArchivoIngredientes,
        String _pathFolderRecetas
    ){
        _connectionMap.put(DataSource.File, new ConnectionFile(_pathArchivoIngredientes, _pathFolderRecetas));
        _connectionMap.put(DataSource.Sqlite, new ConnectionSqlite());
    }
    
    public ConnectionBase getConnection(DataSource dataSource){
        var result = this._connectionMap.get(dataSource);
        
        return result;
    }
}
