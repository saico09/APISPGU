package cl.gob.ips.poc.test_drl_poc_ips;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.drools.compiler.compiler.DroolsParserException;
import org.kie.api.runtime.KieSession;


/**
 * Esta clase representa el manejo de excepciones particulares del Request que
 * genera el Bad Request
 * 
 * @author Red Hat
 * @version 1.0
 * @since 1.0
 */
public class MetaDataConnectEval{


	public static void main(String[] args) throws DroolsParserException, IOException {
		MetaDataConnectEval metaDataConnectEval = new MetaDataConnectEval();
		metaDataConnectEval.listaMetadataResult();
	}
	
	
	public void listaMetadataResult() {
		
		String valorBusqueda = "VASQUEZ";

		String   catalog          = null;
		String   schemaPattern    = null;
		String   tableNamePattern = null;
		String[] types            = null;
		String columnNamePattern = null;
		
		try {
			Connection conn = JdbcSQLServerConnection.getConnection();
		
			DatabaseMetaData dbmd = conn.getMetaData();
			
			ResultSet rs = dbmd.getTables(null, null, "%", null);
			while (rs.next()) {
//			  System.out.println(rs.getString(3));
			  
			  String tableName = rs.getString(3);
			  
//			  tableName="tblPACSElegibilidad";
			  
			  System.out.println("tableName: "+tableName);


			    ResultSet resultColumns = dbmd.getColumns(catalog, schemaPattern,  tableName, columnNamePattern);

			    while(resultColumns.next()){
			        String columnName = resultColumns.getString(4);
			        int    columnType = resultColumns.getInt(5);
			        
//			        System.out.println("columnName: "+columnName +" tipo: " + columnType);
			        
			        
			        if (columnType != 4 && columnType != -4){
						String queryExecute = "select 1 from "+tableName+"  WHERE "+columnName+" like '%"+valorBusqueda+"%'";
						
//						System.out.println("queryExecute: "+queryExecute);
						ResultSet rsTable;
						try {
							rsTable = conn.createStatement().executeQuery(queryExecute);
							
							if (rsTable.next()){
								System.out.println("----> Valor encontrado en tabla: "+ tableName+" y columna: "+columnName);
//								Thread.sleep(1*1000);
							}
						} catch (SQLException e) {
//							System.out.println("ERROR: "+e.getMessage());
						}
			        }
			    }
			  
			 
			}
			
			
			
			
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
}
