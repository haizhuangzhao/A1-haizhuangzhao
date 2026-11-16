import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Base class for every health professional in the system.
 * Holds the details all professionals share - an id, a name, and the days
 * of the week they work. The specific types (GP, specialist, etc.) extend
 * this class so the common stuff is only written once.
 *
 * It's abstract because there's no such thing as a "generic" professional
 * with no speciality - everyone has a specific role.
 */
public abstract class HealthProfessional {

    private final String id;
    private final String name;
    private final List<String> workingDays;

    /**
     * Main constructor. Validates everything so an object can't be made with
     * missing or bad data.
     *
     * @param id          numeric id, numbers only
     * @param name        the professional's full name
     * @param workingDays the days they work, e.g. "Monday"
     */
    public HealthProfessional(String id, String name, List<String> workingDays) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Professional ID cannot be empty.");
        }
        if (!id.matches("\\d+")) {
            throw new IllegalArgumentException("Professional ID must be numbers only: " + id);
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Professional name cannot be empty.");
        }
        if (workingDays == null || workingDays.isEmpty()) {
            throw new IllegalArgumentException("A professional must work on at least one day.");
        }

        this.id = id;
        this.name = name;
        // copy the list so callers can't change the schedule behind our back
        this.workingDays = Collections.unmodifiableList(new ArrayList<>(workingDays));
    }

    /** Convenience constructor - defaults to a standard Mon-Fri week. */
    public HealthProfessional(String id, String name) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /** Returns the working days as a read-only list. */
    public List<String> getWorkingDays() {
        return workingDays;
    }

    /** Each subclass reports what kind of professional it actually is. */
    public abstract String getProfessionType();

    /**
     * Two professionals are the same person if their id matches. This is how
     * the clinic stops the same person being double-booked.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional that = (HealthProfessional) other;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Readable summary shown when the object is printed - the type, name, id
     * and working days.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getProfessionType())
                .append(": ")
                .append(name)
                .append(" (ID: ")
                .append(id)
                .append(")")
                .append(System.lineSeparator())
                .append("    Works on: ")
                .append(String.join(", ", workingDays));
        return result.toString();
    }
}
