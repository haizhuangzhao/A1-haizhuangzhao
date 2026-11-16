import java.util.Arrays;
import java.util.List;

public class Specialist extends HealthProfessional {

    private final String specialty;

    public Specialist(String id, String name, List<String> workingDays, String specialty) {
        super(id, name, workingDays);
        if (specialty == null || specialty.isBlank()) {
            throw new IllegalArgumentException("Specialty cannot be empty.");
        }
        this.specialty = specialty;
    }

    public Specialist(String id, String name, String specialty) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                specialty);
    }

    public String getSpecialty() {
        return specialty;
    }

    public String refer(String patientName, String referredTo) {
        return getName() + " referred " + patientName + " to " + referredTo + ".";
    }

    @Override
    public String getProfessionType() {
        return "Specialist";
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()
                + "    Specialty: " + specialty;
    }
}
