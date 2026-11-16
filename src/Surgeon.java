import java.util.Arrays;
import java.util.List;

/**
 * A surgeon - a doctor who performs operations. Keeps a registration number
 * for their surgical licence.
 */
public class Surgeon extends HealthProfessional {

    private final String registrationNumber;

    /**
     * @param id                 numeric id
     * @param name               full name
     * @param workingDays        days of the week they work
     * @param registrationNumber surgical registration number
     */
    public Surgeon(String id, String name, List<String> workingDays,
            String registrationNumber) {
        super(id, name, workingDays);
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException("Registration number cannot be empty.");
        }
        this.registrationNumber = registrationNumber;
    }

    /** Defaults to a Mon-Fri week. */
    public Surgeon(String id, String name, String registrationNumber) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                registrationNumber);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /** Only surgeons perform operations. */
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
