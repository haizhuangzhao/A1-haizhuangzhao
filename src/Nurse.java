import java.util.Arrays;
import java.util.List;

public class Nurse extends HealthProfessional {

    private final String ward;

    public Nurse(String id, String name, List<String> workingDays, String ward) {
        super(id, name, workingDays);
        if (ward == null || ward.isBlank()) {
            throw new IllegalArgumentException("Ward cannot be empty.");
        }
        this.ward = ward;
    }

    public Nurse(String id, String name, String ward) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"), ward);
    }

    public String getWard() {
        return ward;
    }

    public String takeBloodSample(String patientName) {
        return getName() + " took a blood sample from " + patientName + ".";
    }

    @Override
    public String getProfessionType() {
        return "Nurse";
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()
                + "    Ward: " + ward;
    }
}
