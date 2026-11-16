import java.util.Arrays;
import java.util.List;

/**
 * Represents a nurse, a health professional who assists with patient care and
 * can take blood samples.
 *
 * <p>This class demonstrates <b>inheritance</b>: it extends
 * {@link HealthProfessional} and adds data and behaviour unique to nurses. A
 * nurse is registered in a particular ward or unit.</p>
 */
public class Nurse extends HealthProfessional {

    /** The ward or unit the nurse is assigned to. */
    private final String ward;

    /**
     * Creates a nurse with the given details.
     *
     * @param id          the numeric identifier of the nurse
     * @param name        the full name of the nurse
     * @param workingDays the days of the week the nurse works
     * @param ward        the ward or unit the nurse is assigned to
     */
    public Nurse(String id, String name, List<String> workingDays, String ward) {
        super(id, name, workingDays);
        if (ward == null || ward.isBlank()) {
            throw new IllegalArgumentException("Ward cannot be empty.");
        }
        this.ward = ward;
    }

    /**
     * Creates a nurse with a default Monday-to-Friday schedule.
     *
     * @param id   the numeric identifier of the nurse
     * @param name the full name of the nurse
     * @param ward the ward or unit the nurse is assigned to
     */
    public Nurse(String id, String name, String ward) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"), ward);
    }

    /**
     * Returns the ward or unit the nurse is assigned to.
     *
     * @return the ward
     */
    public String getWard() {
        return ward;
    }

    /**
     * Takes a blood sample from a patient. This behaviour is unique to nurses.
     *
     * @param patientName the name of the patient
     * @return a message describing the blood sample being taken
     */
    public String takeBloodSample(String patientName) {
        return getName() + " took a blood sample from " + patientName + ".";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProfessionType() {
        return "Nurse";
    }

    /**
     * Returns a readable description of the nurse, combining the shared
     * professional details with the additional ward.
     *
     * @return a readable summary of the nurse
     */
    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()
                + "    Ward: " + ward;
    }
}
