/**
 * A single appointment, linking a patient to a chosen health professional at
 * a specific time on the current day.
 *
 * The professional is stored as the base type so the same appointment can
 * hold any kind of professional (GP, specialist, surgeon, nurse, or anything
 * added later).
 *
 * The time is kept as minutes since midnight (08:00 -> 480, 16:00 -> 960).
 * A single integer is easy to sort, unlike comparing "08:00" strings.
 *
 * Implements Comparable so a list of appointments can be sorted by time with
 * Collections.sort().
 */
public class Appointment implements Comparable<Appointment> {

    private final Patient patient;
    private final HealthProfessional professional;
    private final int time;

    /**
     * @param patient      the patient
     * @param professional the health professional
     * @param time         time in minutes since midnight (0-1439)
     */
    public Appointment(Patient patient, HealthProfessional professional, int time) {
        if (patient == null) {
            throw new IllegalArgumentException("An appointment must have a patient.");
        }
        if (professional == null) {
            throw new IllegalArgumentException("An appointment must have a health professional.");
        }
        if (time < 0 || time > 1439) {
            throw new IllegalArgumentException("Time must be between 0 and 1439 minutes: " + time);
        }
        this.patient = patient;
        this.professional = professional;
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public HealthProfessional getProfessional() {
        return professional;
    }

    public int getTime() {
        return time;
    }

    /** Returns the time as "HH:MM", e.g. "08:30". */
    public String getFormattedTime() {
        int hours = time / 60;
        int minutes = time % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    /** Sorts earliest first. */
    @Override
    public int compareTo(Appointment other) {
        return Integer.compare(this.time, other.time);
    }

    @Override
    public String toString() {
        return "Appointment at " + getFormattedTime() + System.lineSeparator()
                + "    Patient: " + patient + System.lineSeparator()
                + "    Professional: " + professional;
    }
}
