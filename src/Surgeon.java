import java.util.Arrays;
import java.util.List;

/**
 * Represents a surgeon, a doctor who performs operations. A surgeon keeps
 * track of a registration number for their surgical licence and can perform a
 * surgery.
 *
 * <p>This class demonstrates <b>inheritance</b>: it extends
 * {@link HealthProfessional} and adds the data and behaviour that are unique
 * to surgeons.</p>
 */
public class Surgeon extends HealthProfessional {

    /** The surgical registration number of the surgeon. */
    private final String registrationNumber;

    /**
     * Creates a surgeon with the given details.
     *
     * @param id                 the numeric identifier of the surgeon
     * @param name               the full name of the surgeon
     * @param workingDays        the days of the week the surgeon works
     * @param registrationNumber the surgical registration number
     */
    public Surgeon(String id, String name, List<String> workingDays,
            String registrationNumber) {
        super(id, name, workingDays);
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException("Registration number cannot be empty.");
        }
        this.registrationNumber = registrationNumber;
    }

    /**
     * Creates a surgeon with a default Monday-to-Friday schedule.
     *
     * @param id                 the numeric identifier of the surgeon
     * @param name               the full name of the surgeon
     * @param registrationNumber the surgical registration number
     */
    public Surgeon(String id, String name, String registrationNumber) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                registrationNumber);
    }

    /**
     * Returns the surgical registration number of the surgeon.
     *
     * @return the registration number
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Performs a surgery on a patient. This behaviour is unique to surgeons.
     *
     * @param patientName the name of the patient
     * @param procedure   the name of the surgical procedure
     * @return a message describing the surgery
     */
    public String performSurgery(String patientName, String procedure) {
        return getName() + " performed a " + procedure + " on " + patientName + ".";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProfessionType() {
        return "Surgeon";
    }

    /**
     * Returns a readable description of the surgeon, combining the shared
     * professional details with the additional registration number.
     *
     * @return a readable summary of the surgeon
     */
    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()
                + "    Registration number: " + registrationNumber;
    }
}
