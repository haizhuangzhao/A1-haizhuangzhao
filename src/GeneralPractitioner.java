import java.util.Arrays;
import java.util.List;

/**
 * A general practitioner (GP) - a doctor who gives everyday primary care and
 * can prescribe medication.
 */
public class GeneralPractitioner extends HealthProfessional {

    private final String providerNumber;

    /**
     * @param id             numeric id
     * @param name           full name
     * @param workingDays    days of the week they work
     * @param providerNumber the GP's medical provider number
     */
    public GeneralPractitioner(String id, String name, List<String> workingDays,
            String providerNumber) {
        super(id, name, workingDays);
        if (providerNumber == null || providerNumber.isBlank()) {
            throw new IllegalArgumentException("Provider number cannot be empty.");
        }
        this.providerNumber = providerNumber;
    }

    /** Defaults to a Mon-Fri week. */
    public GeneralPractitioner(String id, String name, String providerNumber) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                providerNumber);
    }

    public String getProviderNumber() {
        return providerNumber;
    }

    /** GPs can write prescriptions, which other professionals can't do. */
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
