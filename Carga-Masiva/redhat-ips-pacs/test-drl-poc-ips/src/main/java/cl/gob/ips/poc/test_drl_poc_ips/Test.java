package cl.gob.ips.poc.test_drl_poc_ips;

import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class Test {

	public static void main(String[] args) {
		
//        String url = "http://localhost:8080/kie-drools-wb/maven2wb/test/Testing/1.0/Testing-1.0.jar";

//        ReleaseIdImpl releaseId = new ReleaseIdImpl( "cl.ips.pacs", "Testing", "1.0" );

//        KieServices ks = KieServices.Factory.get();
//        ks.getResources().newUrlResource(url);

        
//        System.out.println("ks: "+ks);
//        KieContainer kieContainer = ks.newKieContainer(releaseId);
//        KieScanner kieScanner = ks.newKieScanner(kieContainer);
//        kieScanner.start(5000L);
       
//		ClassLoader cl = addkjarToClasspath("");
		KieContainer kieContainer = KieServices.Factory.get().newKieClasspathContainer();
		
        Scanner scanner = new Scanner(System.in);
        runRule(kieContainer);
    }

    private static void runRule(KieContainer kieKontainer) {
//        KieSession kSession = kieKontainer.newKieSession("default-stateless-ksession");
    	StatelessKieSession kSession = kieKontainer.newStatelessKieSession();

        
        
//        KieSessionModel ksession1 = kBase1
//                .newKieSessionModel(id + ".KSession1")
//                .setType(KieSessionModel.KieSessionType.STATEFUL)
//                .setClockType(ClockTypeOption.get("pseudo"))
//                .setDefault(true);
        
        Product details = new Product();   
        details.setDiscount(20);
//        kSession.insert(details);
//        System.out.println("Fired rules: " + kSession.fireAllRules());
    } 
	

}