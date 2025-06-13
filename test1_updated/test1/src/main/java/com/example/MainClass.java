package com.example;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class MainClass {
    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) {
        // Obtain the Drools KieServices singleton
        KieServices kieServices = KieServices.Factory.get();
        // Get a KieContainer that loads the KieModule (including kmodule.xml) from the classpath
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        // Print available KieBases for debugging
        System.out.println(System.getProperty("java.class.path"));
        System.out.println("KieContainer ReleaseId: " + kieContainer.getReleaseId());
        System.out.println("KieContainer ClassLoader: " + kieContainer.getClassLoader());

        // Verify loaded resources
        ClassLoader classLoader = kieContainer.getClassLoader();
        URL resource = classLoader.getResource("META-INF/kmodule.xml");
        if (resource != null) {
            System.out.println("kmodule.xml found at: " + resource.getPath());
        } else {
            System.out.println("kmodule.xml not found in classpath.");
        }

        // Print available KieBases
        System.out.println("Available KieBases: " + kieContainer.getKieBaseNames());

        // Create a new KieSession using the session name defined in kmodule.xml (e.g., "rulesSession")
        try (KieSession kieSession = kieContainer.newKieSession("rulesSession")) {
            // Insert a sample fact
            kieSession.insert("Hello Drools");

            // Fire all rules
            int fired = kieSession.fireAllRules();
            System.out.println("Fired " + fired + " rules");
        } catch (Exception e) {
            logger.error("An error occurred", e);
        }
    }
}
