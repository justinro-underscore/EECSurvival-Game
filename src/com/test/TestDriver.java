package com.test;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

/**
 * Adapted from https://junit.org/junit5/docs/current/user-guide/#advanced-topics
 * Runs all integration and unit tests
 */
public class TestDriver {
    /**
     * Creates a launcher for each test and logs results
     * @throws FileNotFoundException throws exception if file not found
     */
    public static void runTests() throws FileNotFoundException {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectPackage("com.test")
                )
                .filters(
                        includeClassNamePatterns(".*Test")
                )
                .build();

        Launcher launcher = LauncherFactory.create();

        // Register a listener of your choice
        TestExecutionListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request);

        PrintWriter postTest = null;
        PrintWriter fail = null;

        postTest = new PrintWriter("testResult.txt");

        ((SummaryGeneratingListener) listener).getSummary().printTo(postTest);
        long testsFailed = ((SummaryGeneratingListener) listener).getSummary().getTestsFailedCount();

        File failure = new File("testFailure.txt");
        failure.delete();

        if (testsFailed != 0) {
            fail = new PrintWriter("testFailure.txt");
            ((SummaryGeneratingListener) listener).getSummary().printFailuresTo(fail);
        }
    }
}
