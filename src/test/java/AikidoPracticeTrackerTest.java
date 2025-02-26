import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Scanner;

public class AikidoPracticeTrackerTest {
    @Test
    public void testAddPracticeSession() {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.addPracticeSession(LocalDate.of(2023, 1, 1), 60);
        assertEquals(1, tracker.getSessions().size());
    }

    @Test
    public void testViewTotalPracticeTime() {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.addPracticeSession(LocalDate.of(2023, 1, 1), 60);
        tracker.addPracticeSession(LocalDate.of(2023, 1, 2), 30);
        assertEquals(90, tracker.getTotalPracticeTime());
    }

    @Test
    public void testCheckGraduationEligibility() {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        for (int i = 0; i < 100; i++) {
            tracker.addPracticeSession(LocalDate.now().minusDays(i), 60);
        }
        assertEquals(true, tracker.isEligibleForGraduation());
    }

    @Test
    public void testRunAddPracticeSession() {
        String input = "1\n2023-01-01\n60\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.run();
        assertEquals(1, tracker.getSessions().size());
        System.setIn(System.in); // Reset System.in
    }

    @Test
    public void testRunViewTotalPracticeTime() {
        String input = "2\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.addPracticeSession(LocalDate.of(2023, 1, 1), 60);
        tracker.run();
        System.setIn(System.in); // Reset System.in
    }

    @Test
    public void testRunCheckGraduationEligibility() {
        String input = "3\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        for (int i = 0; i < 100; i++) {
            tracker.addPracticeSession(LocalDate.now().minusDays(i), 60);
        }
        tracker.run();
        System.setIn(System.in); // Reset System.in
    }

    @Test
    public void testRunInvalidOption() {
        String input = "5\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.run();
        System.setIn(System.in); // Reset System.in
    }

    @Test
    public void testAddPracticeSessionWithScanner() {
        String input = "2023-01-01\n60\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.addPracticeSession(new Scanner(System.in));
        assertEquals(1, tracker.getSessions().size());
        System.setIn(System.in); // Reset System.in
    }

    @Test
    public void testViewTotalPracticeTimeMethod() {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.addPracticeSession(LocalDate.of(2023, 1, 1), 60);
        tracker.addPracticeSession(LocalDate.of(2023, 1, 2), 30);
        tracker.viewTotalPracticeTime();
    }

    @Test
    public void testCheckGraduationEligibilityMethod() {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        for (int i = 0; i < 100; i++) {
            tracker.addPracticeSession(LocalDate.now().minusDays(i), 60);
        }
        tracker.checkGraduationEligibility();
    }

    @Test
    public void testMain() {
        String input = "4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        AikidoPracticeTracker.main(new String[]{});
        System.setIn(System.in); // Reset System.in
    }
}