import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AikidoPracticeTracker {
    private List<PracticeSession> sessions = new ArrayList<>();

    public static void main(String[] args) {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Aikido-harjoitusten seuranta =====");
            System.out.println("1. Lisää harjoituskerta");
            System.out.println("2. Näytä kokonaisaika harjoituksille");
            System.out.println("3. Tarkista tutkintokelpoisuus");
            System.out.println("4. Poistu");
            System.out.print("Valitse vaihtoehto: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addPracticeSession(scanner);
                    break;
                case 2:
                    viewTotalPracticeTime();
                    break;
                case 3:
                    checkGraduationEligibility();
                    break;
                case 4:
                    System.out.println("Poistutaan...");
                    return;
                default:
                    System.out.println("Virheellinen vaihtoehto. Yritä uudelleen.");
            }
        }
    }

    public void addPracticeSession(Scanner scanner) {
        System.out.print("Anna päivämäärä (VVVV-KK-PP): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);
        System.out.print("Anna kesto (minuuttia): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // consume newline

        sessions.add(new PracticeSession(date, duration));
        System.out.println("Harjoituskerta lisätty.");
    }

    public void addPracticeSession(LocalDate date, int duration) {
        sessions.add(new PracticeSession(date, duration));
    }

    public List<PracticeSession> getSessions() {
        return sessions;
    }

    public int getTotalPracticeTime() {
        return sessions.stream().mapToInt(PracticeSession::getDuration).sum();
    }

    public boolean isEligibleForGraduation() {
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        long sessionCount = sessions.stream().filter(s -> s.getDate().isAfter(sixMonthsAgo)).count();
        return sessionCount >= 100;
    }

    public void viewTotalPracticeTime() {
        int totalMinutes = getTotalPracticeTime();
        System.out.println("Kokonaisaika harjoituksille: " + totalMinutes + " minuuttia");
    }

    public void checkGraduationEligibility() {
        if (isEligibleForGraduation()) {
            System.out.println("Kelpoinen Kyu-tutkintoon.");
        } else {
            System.out.println("Ei kelpoinen Kyu-tutkintoon.");
        }
    }
}