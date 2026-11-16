/**
 * Represents a single appointment, linking a patient to a chosen health
 * professional at a specific time on the current day.
 *
 * <p>An appointment must be able to refer to <em>any</em> type of health
 * professional. This is achieved through <b>polymorphism</b>: the appointment
 * holds a reference of the base type {@link HealthProfessional}, so it can
 * store a {@code GeneralPractitioner}, {@code Specialist}, {@code Surgeon},
 * {@code Nurse}, or any future professional type without any change to this
 * class.</p>
 *
 * <p>The time is stored as the number of minutes since midnight (for example
 * 08:00 becomes 480 and 16:00 becomes 960). Storing time as a single integer
 * makes ordering appointments from earliest to latest trivial and avoids the
 * fiddly string comparison that would result from storing a time like
 * "08:00".</p>
 *
 * <p>The class implements {@link Comparable} so that a collection of
 * appointments can be sorted into time order using
 * {@code Collections.sort(...)}.</p>
 */
public class Appointment implements Comparable<Appointment> {

    /** The patient the appointment is for. */
    private final Patient patient;

    /** The health professional the appointment is with. */
    private final HealthProfessional professional;

    /** The time of the appointment, as minutes since midnight. */
    private final int time;

    /**
     * Creates an appointment linking a patient, a professional and a time.
     *
     * <p>The constructor enforces that an appointment can never be created in
     * an incomplete or invalid state: every argument must be present, and the
     * time must be a valid number of minutes in a day.</p>
     *
     * @param patient      the patient; must not be null
     * @param professional the health professional; must not be null
     * @param time         the time in minutes since midnight (0 to 1439)
     * @throws IllegalArgumentException if any argument is invalid
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

    /**
     * Returns the patient of this appointment.
     *
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the health professional of this appointment.
     *
     * @return the health professional
     */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /**
     * Returns the time of this appointment in minutes since midnight.
     *
     * @return the time in minutes
     */
    public int getTime() {
        return time;
    }

    /**
     * Returns the time formatted as {@code HH:MM} (for example "08:30").
     *
     * @return the formatted time
     */
    public String getFormattedTime() {
        int hours = time / 60;
        int minutes = time % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    /**
     * Compares this appointment to another by time, so appointments can be
     * sorted from earliest to latest.
     *
     * @param other the appointment to compare with
     * @return a negative number, zero, or a positive number if this
     *         appointment is earlier than, the same as, or later than the
     *         other appointment
     */
    @Override
    public int compareTo(Appointment other) {
        return Integer.compare(this.time, other.time);
    }

    /**
     * Returns a readable description of the appointment, including the time,
     * the patient and the full details of the chosen health professional.
     *
     * @return a readable summary of the appointment
     */
    @Override
    public String toString() {
        return "Appointment at " + getFormattedTime() + System.lineSeparator()
                + "    Patient: " + patient + System.lineSeparator()
                + "    Professional: " + professional;
    }
}
