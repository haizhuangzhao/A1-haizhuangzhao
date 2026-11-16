import java.util.Arrays;
import java.util.List;

public class GeneralPractitioner extends HealthProfessional {

    private final String providerNumber;

    public GeneralPractitioner(String id, String name, List<String> workingDays,
            String providerNumber) {
        super(id, name, workingDays);
        if (providerNumber == null || providerNumber.isBlank()) {
            throw new IllegalArgumentException("Provider number cannot be empty.");
        }
        this.providerNumber = providerNumber;
    }

    public GeneralPractitioner(String id, String name, String providerNumber) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                providerNumber);
    }

    public String getProviderNumber() {
        return providerNumber;
    }

    public String prescribeMedication(String patientName, String medication) {
        return getName() + " prescribed " + medication + " to " + patientName + ".";
    }

    @Override
    public String getProfessionType() {
        return "General Practitioner";
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()
                + "    Provider number: " + providerNumber;
    }
}
