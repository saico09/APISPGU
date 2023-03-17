package cl.gob.ips.poc.test_drl_poc_ips;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

public class MyClass extends Thread {
	public int pageInit   = 0;
	public int pageFinish = 0;

//	private KieSession kieSession;
//	private List<Product> listProduct;
	
	public MyClass(int pageInit, int pageFinish) {
		this.pageInit = pageInit;
		this.pageFinish = pageFinish;
	}
	
	private static final String[] drlFiles = { "Rules.drl" };
	
	 public KieContainer kieContainer() {
		 KieServices kieServices = KieServices.Factory.get(); 
		 //Load Rules and Ecosystem Definitions
		 KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		 for (String ruleFile : drlFiles) {
		 kieFileSystem.write(ResourceFactory.newClassPathResource(ruleFile));
		 }
		 //Generate Modules and all internal Structures
		 KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		 kieBuilder.buildAll();
		 KieModule kieModule = kieBuilder.getKieModule();
		 return kieServices.newKieContainer(kieModule.getReleaseId());
		 }
	 
	 
	
	public void run() {
		
		 KieSession kieSession = kieContainer().newKieSession();
		 
		List<Product> listProduct = new ArrayList<Product>();
		retornaListaEvaluarDataBase(listProduct, kieSession);
		
//		try {
//			Connection conn = JdbcSQLServerConnection.getConnection();
//
//
//			ResultSet rs = conn.createStatement().executeQuery("select TOP 11 * from tblPACSElegibilidad ");
//	
//			while (rs.next()) {
//				
//				System.out.println("rs.getFetchDirection(): "+rs.getConcurrency());
//				
//			    Product product = new Product();				
//				product.setType("gold");
//				kieSession.insert(product);
//				listProduct.add(product);
//				System.out.println("product: "+product);
//			}	
//		
//			rs.close();
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		long init = System.currentTimeMillis();
        kieSession.fireAllRules();
        kieSession.dispose();	
        
        
//        System.out.println("listProduct.toString(): "+listProduct.toString());
        System.out.println("TOTAL Lectura de "+listProduct.size()+" registros en: "+ (System.currentTimeMillis() - init) +" ms.");
	}
	
	public List<Product> retornaListaEvaluarDataBase(List<Product> listProduct,  KieSession kieSession){
		long init = System.currentTimeMillis();		
		
		try {
			Connection conn = JdbcSQLServerConnection.getConnection();

			ResultSet rs = conn.createStatement().executeQuery("Select * from \n" + 
					"(\n" + 
					"    Select ROW_NUMBER() OVER ( order by iRUN) as 'Row_Number', * \n" + 
					"    from tblPACSElegibilidad \n" + 
					") as tbl\n" + 
					"Where tbl.Row_Number >="+this.pageInit +" AND tbl.Row_Number <="+this.pageFinish +";");
			while (rs.next()) {
				
			    Product product = new Product();				
				product.setType("gold");
				kieSession.insert(product);
				listProduct.add(product);
//				System.out.println("product: "+product);
			}	
		
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Base Datos Lectura de "+listProduct.size()+" registros en: "+ (System.currentTimeMillis() - init) +" ms.");			
		return listProduct;
	}
	
}