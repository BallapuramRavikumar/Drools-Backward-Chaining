package com.example;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Applicant;

public class RuleRunner {
    private static final Logger logger = LoggerFactory.getLogger(RuleRunner.class);

    public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        System.out.println("Available KieBases: " + kieContainer.getKieBaseNames());

        try (KieSession kSession = kieContainer.newKieSession("rulesApplicant")) {
            // Create some Applicant facts
            Applicant john = new Applicant("John", 15, true);
            Applicant jane = new Applicant("Jane", 20, false);

            // Insert the facts into the KieSession
            kSession.insert(john);
            kSession.insert(jane);

            // Fire the rules
            int fired = kSession.fireAllRules();
            System.out.println("Fired " + fired + " rules");

            // Check the results
            System.out.println(john.getName() + " is valid: " + john.isValid());
            System.out.println(jane.getName() + " is valid: " + jane.isValid());

        } catch (Exception e) {
            logger.error("An error occurred", e);
        }
    }
}
