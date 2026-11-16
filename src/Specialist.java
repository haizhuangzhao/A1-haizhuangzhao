import java.util.Arrays;
import java.util.List;

/**
 * Represents a specialist doctor who works within a particular area of
 * medicine, such as cardiology, paediatrics or radiology. A specialist can
 * refer a patient to another professional for further treatment.
 *
 * <p>This class demonstrates <b>inheritance</b> and <b>polymorphism</b>: it
 * extends {@link HealthProfessional}, reuses the shared state, and overrides
 * the inherited {@code toString()} and {@code getProfessionType()} methods to
 * present its own identity.</p>
 */
public class Specialist extends HealthProfessional {

    /** The area of medicine the specialist works in. */
    private final String specialty;

    /**
     * Creates a specialist with the given details.
     *
     * @param id          the numeric identifier of the specialist
     * @param name        the full name of the specialist
     * @param workingDays the days of the week the specialist works
     * @param specialty   the area of medicine, e.g. "Cardiology"
     */
    public Specialist(String id, String name, List<String> workingDays, String specialty) {
        super(id, name, workingDays);
        if (specialty == null || specialty.isBlank()) {
            throw new IllegalArgumentException("Specialty cannot be empty.");
        }
        this.specialty = specialty;
    }

    /**
     * Creates a specialist with a default Monday-to-Friday schedule.
     *
     * @param id        the numeric identifier of the specialist
     * @param name      the full name of the specialist
     * @param specialty the area of medicine
     */
    public Specialist(String id, String name, String specialty) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                specialty);
    }

    /**
     * Returns the area of medicine the specialist works in.
     *
     * @return the specialty
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Refers a patient to another professional. This behaviour is unique to
     * specialists, who often pass patients on for further care.
     *
     * @param patientName   the name of the patient being referred
     * @param referredTo    the name of the professional being referred to
     * @return a message describing the referral
     */
    public String refer(String patientName, String referredTo) {
        return getName() + " referred " + patientName + " to " + referredTo + ".";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProfessionType() {
        return "Specialist";
    }

    /**
     * Returns a readable description of the specialist, combining the shared
     * professional details with the additional specialty.
     *
     * @return a readable summary of the specialist
     */
    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()
                + "    Specialty: " + specialty;
    }
}
