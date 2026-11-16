import java.util.Arrays;
import java.util.List;

/**
 * A specialist doctor who works in one area of medicine (cardiology,
 * paediatrics, etc.) and can refer patients on to other professionals.
 */
public class Specialist extends HealthProfessional {

    private final String specialty;

    /**
     * @param id          numeric id
     * @param name        full name
     * @param workingDays days of the week they work
     * @param specialty   area of medicine, e.g. "Cardiology"
     */
    public Specialist(String id, String name, List<String> workingDays, String specialty) {
        super(id, name, workingDays);
        if (specialty == null || specialty.isBlank()) {
            throw new IllegalArgumentException("Specialty cannot be empty.");
        }
        this.specialty = specialty;
    }

    /** Defaults to a Mon-Fri week. */
    public Specialist(String id, String name, String specialty) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                specialty);
    }

    public String getSpecialty() {
        return specialty;
    }

    /** Specialists often pass patients on for further care. */
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
