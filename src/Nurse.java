import java.util.Arrays;
import java.util.List;

/**
 * A nurse who assists with patient care and can take blood samples. A nurse
 * is assigned to a particular ward or unit.
 */
public class Nurse extends HealthProfessional {

    private final String ward;

    /**
     * @param id          numeric id
     * @param name        full name
     * @param workingDays days of the week they work
     * @param ward        the ward or unit they're assigned to
     */
    public Nurse(String id, String name, List<String> workingDays, String ward) {
        super(id, name, workingDays);
        if (ward == null || ward.isBlank()) {
            throw new IllegalArgumentException("Ward cannot be empty.");
        }
        this.ward = ward;
    }

    /** Defaults to a Mon-Fri week. */
    public Nurse(String id, String name, String ward) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"), ward);
    }

    public String getWard() {
        return ward;
    }

    /** Taking blood samples is part of a nurse's job. */
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
