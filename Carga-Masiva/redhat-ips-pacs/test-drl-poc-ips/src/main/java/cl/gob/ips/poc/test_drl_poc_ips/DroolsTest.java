package cl.gob.ips.poc.test_drl_poc_ips;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.compiler.compiler.DroolsParserException;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import com.opencsv.CSVReader;


public class DroolsTest {

	public static void main(String[] args) throws DroolsParserException,
			IOException {
		DroolsTest droolsTest = new DroolsTest();
		droolsTest.executeDrools();
	}

	private static final String[] drlFiles = { "Rules.drl" };
	
//	private KieContainer kieContainer;
	
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
	 
	
	public void executeDrools() throws DroolsParserException, IOException {

//		
//		for (int i=0; i<10;i++) {
//			MyClass thread = new MyClass(i+1,(i+1)*10);
//			thread.start();			
//		}
		
//		MyClass thread1 = new MyClass(1, 350000);
//		thread1.start();			
//
//		
//		MyClass thread2 = new MyClass(350001, 700000);
//		thread2.start();			
//
//		MyClass thread3 = new MyClass(700001, 1000000);
//		thread3.start();		
//
//		
//		MyClass thread4 = new MyClass(1000001, 1400000);
//		thread4.start();		
		

//		

		KieSession kieSession = kieContainer().newKieSession();		 
		List<DatosBeneficiarioPacsFijoVector> listProduct = new ArrayList<DatosBeneficiarioPacsFijoVector>();
		retornaListaEvaluarDataBase(listProduct, kieSession);

		
//		List cmds = new ArrayList();
//		cmds.add(CommandFactory.newSetGlobal("list1", new ArrayList(), true));
//		cmds.add(CommandFactory.newInsert(new Person("jon", 102), "person"));
//		cmds.add(CommandFactory.newQuery("Get People" "getPeople"));

		// Execute the list.
//		ExecutionResults results = kieSession.execute(CommandFactory.newBatchExecution(cmds));

		
		

		long init = System.currentTimeMillis();	
        kieSession.fireAllRules();
        kieSession.dispose();			 
		System.out.println("Ejecución Reglas en: "+ (System.currentTimeMillis() - init) +" ms.");	
		        
		
		Collection<?> col = kieSession.getObjects();
//		Iterator<?> ite = col.iterator();
		
//		for (Iterator<?> ite = col.iterator(); ite.hasNext(); ) 
//		       System.out.println((DatosBeneficiarioPacsFijoVector) ite.next());
		        
//		ExecutionResults results = kieSession.execute(CommandFactory.newBatchExecution(cmds));
//	    Collection<?> validations = (Collection<?>) results.getValue("validations");
		
//		actualizaListaEvaluarDataBase(listProduct);     

		        
		
//		System.out.println("product: "+listProduct.toString());
		
		
//		String decisionTableXml = new String ( 
//				  Files.readAllBytes( 
//				    Paths.get("./someDecisionTable.gdst") ) );
//				GuidedDecisionTable52 model = GuidedDTXMLPersistence.getInstance().unmarshal( decisionTableXml );
//				String droolsRules = GuidedDTDRLPersistence.getInstance().marshal( model );
				
				

//		PackageBuilder packageBuilder = new PackageBuilder();
//		String ruleFile = "Rules.drl";
//		InputStream resourceAsStream = getClass().getResourceAsStream(ruleFile);
//
//		Reader reader = new InputStreamReader(resourceAsStream);
//		packageBuilder.addPackageFromDrl(reader);
//		org.drools.core.rule.Package rulesPackage = packageBuilder.getPackage();
//		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
//		ruleBase.addPackage(rulesPackage);
//
//		WorkingMemory workingMemory = ruleBase.newStatefulSession();
//
//		List<Product> listProduct = new ArrayList<Product>();
//				
//		retornaListaEvaluar(listProduct, workingMemory);
	
		
		//10KK -> Reglas ejecutadas en 3519 ms.
		//5KK  -> Reglas ejecutadas en 1754 ms.
		//1.5KK  -> Reglas ejecutadas en 575 ms.


//		workingMemory.fireAllRules();		
//		System.out.println("Reglas ejecutadas en "+ (System.currentTimeMillis() - init) +" ms.");
	}
	

//	Lectura de 1316104 registros en: 235953 ms.
//	Reglas ejecutadas en 603 ms.
	public List<DatosBeneficiarioPacsFijoVector> retornaListaEvaluarDataBase(List<DatosBeneficiarioPacsFijoVector> listProduct,  KieSession kieSession){
		long init = System.currentTimeMillis();		
		
		
		try {
			Connection conn = JdbcSQLServerConnection.getConnection();


			ResultSet rs = conn.createStatement().executeQuery("select TOP 100 * from tblPACSElegibilidad ORDER BY iRUN DESC");
	
			while (rs.next()) {
				DatosBeneficiarioPacsFijoVector datosBeneficiarioPacsFijoVector = new DatosBeneficiarioPacsFijoVector();
				datosBeneficiarioPacsFijoVector.setFechaDefuncion("1900-01-01");
				
				kieSession.insert(datosBeneficiarioPacsFijoVector);
				listProduct.add(datosBeneficiarioPacsFijoVector);
			}	
		
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
//		System.out.println("Lectura de "+listProduct.size()+" registros en: "+ (System.currentTimeMillis() - init) +" ms.");			
		return listProduct;
	}
	
	
	public List<Product> actualizaListaEvaluarDataBase(List<Product> listProduct){
		long init = System.currentTimeMillis();		
		
		
		try {
			Connection conn = JdbcSQLServerConnection.getConnection();
	
			PreparedStatement prepStmt = conn.prepareStatement(    
				    "UPDATE tblPACSElegibilidad SET vcApellidoPaterno=? WHERE iRUN=?");           

			
			for(Product product:listProduct) {
				prepStmt.setString(1,product.getvApellidoPaterno());                         
				  prepStmt.setInt(2,product.getiRUN());
				  prepStmt.addBatch();                                   				
			}



			prepStmt.executeBatch();  
		
			
		
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Actualiza de "+listProduct.size()+" registros en: "+ (System.currentTimeMillis() - init) +" ms.");			
		return listProduct;
	}
	
	
	
	public List<Product> retornaListaEvaluar(List<Product> listProduct,  KieSession kieSession){
		long init = System.currentTimeMillis();
		CSVReader reader2;
		try {

			reader2 = new CSVReader(new FileReader("/home/cquezada/git/ips/poc/test-drl-poc-ips/src/main/java/cl/gob/ips/poc/test_drl_poc_ips/RUN_4000000_hasta_5000000.csv"),';');			
			String [] nextLine;
			while ((nextLine = reader2.readNext()) != null) {
			    Product product = new Product();				
				product.setType("gold");
				kieSession.insert(product);
				listProduct.add(product);
			}	

			reader2 = new CSVReader(new FileReader("/home/cquezada/git/ips/poc/test-drl-poc-ips/src/main/java/cl/gob/ips/poc/test_drl_poc_ips/RUN_5000000_hasta_5500000.csv"),';');			
			while ((nextLine = reader2.readNext()) != null) {
			    Product product = new Product();				
				product.setType("gold");
				kieSession.insert(product);
				listProduct.add(product);
			}	
			
			reader2 = new CSVReader(new FileReader("/home/cquezada/git/ips/poc/test-drl-poc-ips/src/main/java/cl/gob/ips/poc/test_drl_poc_ips/RUN_5500000_hasta_6000000.csv"),';');			
			while ((nextLine = reader2.readNext()) != null) {
			    Product product = new Product();				
				product.setType("gold");
				kieSession.insert(product);
				listProduct.add(product);
			}	
			
			reader2 = new CSVReader(new FileReader("/home/cquezada/git/ips/poc/test-drl-poc-ips/src/main/java/cl/gob/ips/poc/test_drl_poc_ips/RUN_6000000_hasta_6500000.csv"),';');			
			while ((nextLine = reader2.readNext()) != null) {
			    Product product = new Product();				
				product.setType("gold");
				kieSession.insert(product);
				listProduct.add(product);
			}	
			
			reader2 = new CSVReader(new FileReader("/home/cquezada/git/ips/poc/test-drl-poc-ips/src/main/java/cl/gob/ips/poc/test_drl_poc_ips/RUN_6500000_hasta_7000000.csv"),';');			
			while ((nextLine = reader2.readNext()) != null) {
			    Product product = new Product();				
				product.setType("gold");
				kieSession.insert(product);
				listProduct.add(product);
			}	
			
			reader2 = new CSVReader(new FileReader("/home/cquezada/git/ips/poc/test-drl-poc-ips/src/main/java/cl/gob/ips/poc/test_drl_poc_ips/RUN_7000000_hasta_8000000.csv"),';');			
			while ((nextLine = reader2.readNext()) != null) {
			    Product product = new Product();				
				product.setType("gold");
				kieSession.insert(product);
				listProduct.add(product);
			}				
	
			
			reader2 = new CSVReader(new FileReader("/home/cquezada/git/ips/poc/test-drl-poc-ips/src/main/java/cl/gob/ips/poc/test_drl_poc_ips/RUN_hasta_4000000.csv"),';');			
			while ((nextLine = reader2.readNext()) != null) {
			    Product product = new Product();				
				product.setType("gold");
				kieSession.insert(product);
				listProduct.add(product);
			}	
			
			
			reader2 = new CSVReader(new FileReader("/home/cquezada/git/ips/poc/test-drl-poc-ips/src/main/java/cl/gob/ips/poc/test_drl_poc_ips/RUN_sobre_8000000.csv"),';');			
			while ((nextLine = reader2.readNext()) != null) {
			    Product product = new Product();				
				product.setType("gold");
				kieSession.insert(product);
				listProduct.add(product);
			}	

			System.out.println("Lectura de "+listProduct.size()+" registros en: "+ (System.currentTimeMillis() - init) +" ms.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return listProduct;
	}

}