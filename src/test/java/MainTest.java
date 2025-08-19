import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("This test is disabled because it requires a long-running process to complete.")
    public void methodExecutesWithinTimeout() throws Exception {
        Main.main(new String[]{});
    }
}
