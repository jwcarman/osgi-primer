package com.savoirtech.osgi.primer.testing;

import org.apache.felix.service.command.CommandProcessor;
import org.apache.felix.service.command.CommandSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.ProbeBuilder;
import org.ops4j.pax.exam.TestProbeBuilder;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.KarafDistributionConfigurationFilePutOption;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import static org.ops4j.pax.exam.CoreOptions.*;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.*;

@RunWith(PaxExam.class)
public class PrimerFeaturesIT extends Assert {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final Long COMMAND_TIMEOUT = 600000L;

    private static final String FEATURES_CONFIG = "etc/org.apache.karaf.features.cfg";
    private static final String PAX_URL_MVN_CONFIG = "etc/org.ops4j.pax.url.mvn.cfg";
    private static final String KARAF_MANAGEMENT_CONFIG = "etc/org.apache.karaf.management.cfg";
    private static final String KARAF_SHELL_CONFIG = "etc/org.apache.karaf.shell.cfg";
    private static final String PAX_EXAM_RBC_CONFIG = "etc/org.ops4j.pax.exam.rbc.cfg";

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Inject
    private CommandProcessor commandProcessor;

    private final Logger logger = LoggerFactory.getLogger(getClass());

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected void assertBundleActive(String symbolicName) {
        String output = executeCommand(String.format("bundle:list -t 10 | grep '%s' | grep 'Active'", symbolicName));
        assertFalse("Bundle " + symbolicName + " is not active!", isEmpty(output));
    }

    private boolean isEmpty(String output) {
        return output == null || output.isEmpty();
    }

    @Configuration
    public Option[] config() {
        return options(

                karafDistributionConfiguration()
                        .frameworkUrl(maven().groupId("com.savoirtech.aetos").artifactId("aetos").type("tar.gz").version("3.0.2"))
                        .karafVersion("3.0.2")
                        .name("Aetos")
                        .unpackDirectory(new File("target/aetos")),

                new KarafDistributionConfigurationFilePutOption(KARAF_MANAGEMENT_CONFIG, "rmiRegistryHost", "127.0.0.1"),
                new KarafDistributionConfigurationFilePutOption(KARAF_MANAGEMENT_CONFIG, "rmiServerPort", "44445"),
                new KarafDistributionConfigurationFilePutOption(KARAF_MANAGEMENT_CONFIG, "rmiServerHost", "127.0.0.1"),
                new KarafDistributionConfigurationFilePutOption(KARAF_SHELL_CONFIG, "sshHost", "127.0.0.1"),
                new KarafDistributionConfigurationFilePutOption(PAX_URL_MVN_CONFIG, "org.ops4j.pax.url.mvn.useFallbackRepositories", "false"),
                new KarafDistributionConfigurationFilePutOption(PAX_URL_MVN_CONFIG, "org.ops4j.pax.url.mvn.disableAether", "true"),
                new KarafDistributionConfigurationFilePutOption(PAX_EXAM_RBC_CONFIG, "org.ops4j.pax.exam.rbc.rmi.host", "127.0.0.1"),
                new KarafDistributionConfigurationFilePutOption(FEATURES_CONFIG, "featuresBoot", "config,ssh,management,kar,war"),

                keepRuntimeFolder(),
                configureConsole().ignoreLocalConsole(),
                logLevel(LogLevelOption.LogLevel.WARN),
                features(maven().groupId("com.savoirtech.osgi.primer").artifactId("aetos").version("1.0.0-SNAPSHOT").type("xml").classifier("features"), "primer-service"));
    }

    protected void echoOutput(String command) {
        final String output = executeCommand(command);
        logger.info("Output for command [{}]:\n\n{}", command, output);
    }

    protected String executeCommand(final String command) {
        return executeCommand(command, COMMAND_TIMEOUT);
    }

    protected String executeCommand(final String command, final Long timeout) {
        String response;

        FutureTask<String> commandFuture = new FutureTask<>(new Callable<String>() {
            public String call() {
                try {
                    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    final PrintStream printStream = new PrintStream(byteArrayOutputStream, false, "UTF-8");
                    final CommandSession commandSession = commandProcessor.createSession(System.in, printStream, System.err);
                    commandSession.execute(command);
                    printStream.flush();
                    return byteArrayOutputStream.toString("UTF-8");
                } catch (Exception e) {
                    logger.error("Command threw exception!", e);
                    throw new RuntimeException(e);
                }
            }
        });

        try {
            executor.submit(commandFuture);
            response = commandFuture.get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.error("An exception occurred while executing command {}!", command, e);
            response = "SHELL COMMAND TIMED OUT: ";
        }

        return response;
    }

    @ProbeBuilder
    public TestProbeBuilder probeConfiguration(TestProbeBuilder probe) {
        probe.setHeader(Constants.DYNAMICIMPORT_PACKAGE, "*,org.apache.felix.service.*;status=provisional");
        return probe;
    }

    @Test
    public void test() {
        assertBundleActive("Service Example ::: Implementation");
    }
}
