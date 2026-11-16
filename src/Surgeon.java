import java.util.Arrays;
import java.util.List;

public class Surgeon extends HealthProfessional {

    private final String registrationNumber;

    public Surgeon(String id, String name, List<String> workingDays,
            String registrationNumber) {
        super(id, name, workingDays);
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException("Registration number cannot be empty.");
        }
        this.registrationNumber = registrationNumber;
    }

    public Surgeon(String id, String name, String registrationNumber) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                registrationNumber);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String performSurgery(String patientName, String procedure) {
        return getName() + " performed a " + procedure + " on " + patientName + ".";
    }

    @Override
    public String getProfessionType() {
        return "Surgeon";
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()
                + "    Registration number: " + registrationNumber;
    }
}
