package session;

import java.io.File;
import java.nio.file.Paths;

import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.LauncherSessionListener;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;
import storage.Storage;


//@@author xenthm
/**
 * This class adapts the example code found in the
 * <a href="https://junit.org/junit5/docs/current/user-guide/#launcher-api-launcher-session-listeners-custom">JUnit 5
 * Docs</a> to set the data file location to within the <code>test</code> folder when JUnit tests are being run. This
 * removes the need to perform this setup with <code>@BeforeAll</code> in every JUnit test class. After all the tests
 * are run, the output file is deleted as it is not needed any more. The result is that tests can perform updates to a
 * dummy data file without keeping it afterwards.
 */
public class GlobalSetupTearDownListener implements LauncherSessionListener {
    private static final File TEST_DATA_FILE = Paths.get("src/test/resources/testCatalog.json").toFile();

    private Fixture fixture;

    @Override
    public void launcherSessionOpened(LauncherSession session) {
        session.getLauncher().registerTestExecutionListeners(new TestExecutionListener() {
            @Override
            public void testPlanExecutionStarted(TestPlan testPlan) {
                if (fixture == null) {
                    fixture = new Fixture();
                    fixture.setup();
                }
            }
        });
    }

    @Override
    public void launcherSessionClosed(LauncherSession session) {
        if (fixture != null) {
            fixture.tearDown();
            fixture = null;
        }
    }

    static class Fixture {
        void setup() {
            Storage.getInstance().setDataFile(TEST_DATA_FILE);
        }

        void tearDown() {
            TEST_DATA_FILE.delete();
        }
    }

}
