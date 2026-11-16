import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a generic health professional working for the health service.
 *
 * <p>This is the abstract base class for every specific type of health
 * professional in the system (general practitioners, specialists, surgeons,
 * nurses, and so on). It captures the data and behaviour that is common to
 * <em>all</em> professionals, so that the more specific subclasses do not have
 * to repeat it.</p>
 *
 * <p>A professional is always identifiable by a numeric ID and has a name.
 * Every professional also works on a fixed set of days each week (for example
 * Monday, Wednesday and Friday). Those days are stored as a read-only
 * collection so that external code can inspect them but can never modify them
 * and put an object into an invalid state.</p>
 *
 * <p>The class is {@code abstract} because it never makes sense to create a
 * "plain" health professional with no speciality: every real worker has a
 * specific role. Subclasses are forced to provide their own description of
 * what kind of professional they are by implementing
 * {@link #getProfessionType()}.</p>
 *
 * <p><b>Four pillars of OOP used here:</b></p>
 * <ul>
 *   <li><b>Encapsulation</b> – all fields are {@code private}; the only way to
 *       read them is through getters, and the only way to write them is
 *       through the constructors, which validate the arguments.</li>
 *   <li><b>Abstraction</b> – the class is {@code abstract} and declares an
 *       abstract method {@link #getProfessionType()}, hiding the concrete
 *       "kind of worker" detail from the rest of the program.</li>
 * </ul>
 */
public abstract class HealthProfessional {

    /** The minimum number of working days a professional must have. */
    private static final int MIN_WORKING_DAYS = 1;

    /** The numeric identifier of the professional (numbers only). */
    private final String id;

    /** The full name of the professional. */
    private final String name;

    /**
     * The days of the week the professional works. Stored as a read-only list
     * so callers cannot alter the schedule after the object is created.
     */
    private final List<String> workingDays;

    /**
     * Creates a health professional with the given id, name and working days.
     *
     * @param id          the numeric identifier of the professional; must not
     *                    be null or blank and must consist of digits only
     * @param name        the full name of the professional; must not be null
     *                    or blank
     * @param workingDays the days of the week the professional works; must not
     *                    be null and must contain at least one day
     * @throws IllegalArgumentException if any argument is invalid
     */
    public HealthProfessional(String id, String name, List<String> workingDays) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Professional ID cannot be empty.");
        }
        if (!id.matches("\\d+")) {
            throw new IllegalArgumentException("Professional ID must contain numbers only: " + id);
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Professional name cannot be empty.");
        }
        if (workingDays == null || workingDays.isEmpty()) {
            throw new IllegalArgumentException(
                    "A professional must work on at least " + MIN_WORKING_DAYS + " day.");
        }

        this.id = id;
        this.name = name;
        // Defensive copy: the caller's list cannot affect our internal state.
        this.workingDays = Collections.unmodifiableList(new ArrayList<>(workingDays));
    }

    /**
     * Creates a health professional with a default schedule of Monday to
     * Friday. This convenience constructor is useful because many
     * professionals work a standard weekday schedule.
     *
     * @param id   the numeric identifier of the professional
     * @param name the full name of the professional
     */
    public HealthProfessional(String id, String name) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
    }

    /**
     * Returns the numeric identifier of the professional.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the full name of the professional.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an unmodifiable view of the days of the week this professional
     * works. Callers can read the schedule but can never change it.
     *
     * @return the working days
     */
    public List<String> getWorkingDays() {
        return workingDays;
    }

    /**
     * Returns a short label describing what kind of health professional this
     * is (for example "General Practitioner" or "Nurse"). Each concrete
     * subclass must provide its own implementation.
     *
     * @return the type name of the professional
     */
    public abstract String getProfessionType();

    /**
     * Two health professionals are considered equal when they have the same
     * id. This is how the system decides whether two references point to "the
     * same person", which is essential for preventing a professional from
     * being double-booked.
     *
     * @param other the object to compare with
     * @return {@code true} if the other object is a HealthProfessional with
     *         the same id
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

    /**
     * Returns a hash code consistent with {@link #equals(Object)}. Objects
     * with equal ids have equal hash codes.
     *
     * @return the hash code based on the id
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Returns a human-readable description of the professional, including the
     * kind of professional, the name, the id and the working days. This is
     * what is shown when the object is printed, for example with
     * {@code System.out.println(doctorA)}.
     *
     * @return a readable summary of the professional
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
