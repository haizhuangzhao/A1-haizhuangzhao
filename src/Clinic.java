import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the collection of all appointments for the health service. It
 * provides operations to add, view, filter, sort and cancel appointments,
 * and guards against double-booking a health professional.
 *
 * <p>The clinic runs to a fixed daily schedule of time slots from 08:00 to
 * 16:00 in 30-minute intervals. These slots are stored as a public constant
 * array so the schedule is defined in exactly one place — if the clinic ever
 * changes its hours, only this array needs to be edited and the rest of the
 * system carries on working with the new schedule.</p>
 *
 * <p><b>Data structure choice:</b> the appointments are stored in an
 * {@link ArrayList}. A list preserves insertion order, allows duplicate
 * elements (two different patients may book different professionals at the
 * same time), supports random access, and pairs naturally with
 * {@code Collections.sort(...)} for ordering. This makes it the right choice
 * for a small, single-day collection of appointments.</p>
 *
 * <p><b>Four pillars of OOP used here:</b></p>
 * <ul>
 *   <li><b>Encapsulation</b> – the list is private; the only mutations go
 *       through the public {@code add} and {@code cancel} methods, which
 *       validate their input and return a warning message when an operation
 *       cannot be performed.</li>
 *   <li><b>Polymorphism</b> – every operation works through the base type
 *       {@link HealthProfessional}, so any specific professional type can be
 *       booked, filtered or cancelled in the same way.</li>
 * </ul>
 */
public class Clinic {

    /**
     * The fixed daily schedule of time slots, in minutes since midnight.
     * 08:00 (480) through 16:00 (960) in 30-minute steps.
     */
    public static final int[] TIME_SLOTS = generateTimeSlots(480, 960, 30);

    /** The appointments managed by this clinic. */
    private final List<Appointment> appointments;

    /**
     * Creates an empty clinic with no appointments.
     */
    public Clinic() {
        this.appointments = new ArrayList<>();
    }

    /**
     * Generates a time-slot schedule between a start and end time.
     *
     * @param start   the first slot in minutes since midnight (inclusive)
     * @param end     the last slot in minutes since midnight (inclusive)
     * @param step    the interval between slots in minutes
     * @return an array of slot times in minutes since midnight
     */
    private static int[] generateTimeSlots(int start, int end, int step) {
        int count = ((end - start) / step) + 1;
        int[] slots = new int[count];
        for (int i = 0; i < count; i++) {
            slots[i] = start + i * step;
        }
        return slots;
    }

    /**
     * Adds a new appointment, after checking that all required information is
     * present and that the chosen health professional is not already booked
     * into the same time slot.
     *
     * @param patient      the patient; must not be null
     * @param professional the health professional; must not be null
     * @param time         the time in minutes since midnight
     * @return a message describing the result of the operation (success or a
     *         warning if it could not be performed)
     */
    public String addAppointment(Patient patient, HealthProfessional professional, int time) {
        // Validate the required information first. A missing patient,
        // professional or an out-of-hours time is rejected with a warning.
        if (patient == null || patient.getName() == null || patient.getName().isBlank()) {
            return "Warning: cannot add appointment - a patient name is required.";
        }
        if (professional == null) {
            return "Warning: cannot add appointment - a health professional is required.";
        }
        if (!isValidTimeSlot(time)) {
            return "Warning: cannot add appointment - " + formatTime(time)
                    + " is outside the clinic's opening hours.";
        }

        // Reject any booking that would double-book a professional.
        for (Appointment existing : appointments) {
            if (existing.getProfessional().equals(professional)
                    && existing.getTime() == time) {
                return "Warning: cannot add appointment - " + professional.getName()
                        + " is already booked at " + formatTime(time) + ".";
            }
        }

        appointments.add(new Appointment(patient, professional, time));
        return "Appointment added: " + patient.getName() + " with " + professional.getName()
                + " at " + formatTime(time) + ".";
    }

    /**
     * Returns a list of all appointments currently in the clinic, in the order
     * they were added. The returned list is a defensive copy so callers cannot
     * change the clinic's internal state.
     *
     * @return a copy of the appointments
     */
    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    /**
     * Returns the appointments for a particular health professional, identified
     * by their id, in time order.
     *
     * @param professionalId the id of the health professional
     * @return a list of matching appointments, sorted by time
     */
    public List<Appointment> getAppointmentsByProfessional(String professionalId) {
        List<Appointment> matches = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)) {
                matches.add(appointment);
            }
        }
        Collections.sort(matches);
        return matches;
    }

    /**
     * Returns the appointments for a particular patient, identified by their
     * phone number, in time order.
     *
     * @param phoneNumber the mobile phone number of the patient
     * @return a list of matching appointments, sorted by time
     */
    public List<Appointment> getAppointmentsByPatient(String phoneNumber) {
        List<Appointment> matches = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getPhoneNumber().equals(phoneNumber)) {
                matches.add(appointment);
            }
        }
        Collections.sort(matches);
        return matches;
    }

    /**
     * Returns all appointments sorted from earliest to latest. Appointments at
     * the same time (for different professionals) may appear in any order.
     *
     * @return all appointments in time order
     */
    public List<Appointment> getAppointmentsSortedByTime() {
        List<Appointment> sorted = new ArrayList<>(appointments);
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * Cancels an appointment identified by a health professional id and a
     * time. If no matching appointment exists, a warning message is returned.
     *
     * @param professionalId the id of the health professional
     * @param time           the appointment time in minutes since midnight
     * @return a message describing the result of the operation
     */
    public String cancelAppointment(String professionalId, int time) {
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)
                    && appointment.getTime() == time) {
                appointments.remove(appointment);
                return "Appointment cancelled: " + appointment.getPatient().getName()
                        + " with " + appointment.getProfessional().getName()
                        + " at " + formatTime(time) + ".";
            }
        }
        return "Warning: cannot cancel - no appointment found for professional ID "
                + professionalId + " at " + formatTime(time) + ".";
    }

    /**
     * Cancels all appointments for a patient identified by their phone number.
     * If no matching appointment exists, a warning message is returned.
     *
     * @param phoneNumber the mobile phone number of the patient
     * @return a message describing the result of the operation
     */
    public String cancelAppointmentsByPatient(String phoneNumber) {
        int removed = 0;
        for (int i = appointments.size() - 1; i >= 0; i--) {
            if (appointments.get(i).getPatient().getPhoneNumber().equals(phoneNumber)) {
                appointments.remove(i);
                removed++;
            }
        }
        if (removed == 0) {
            return "Warning: cannot cancel - no appointments found for patient phone "
                    + phoneNumber + ".";
        }
        return "Cancelled " + removed + " appointment(s) for patient phone " + phoneNumber + ".";
    }

    /**
     * Returns the total number of appointments currently held by the clinic.
     *
     * @return the number of appointments
     */
    public int getAppointmentCount() {
        return appointments.size();
    }

    /**
     * Checks whether a time (in minutes since midnight) falls on one of the
     * clinic's fixed time slots.
     *
     * @param time the time to check
     * @return {@code true} if the time is a valid slot
     */
    private boolean isValidTimeSlot(int time) {
        for (int slot : TIME_SLOTS) {
            if (slot == time) {
                return true;
            }
        }
        return false;
    }

    /**
     * Formats a time in minutes since midnight as {@code HH:MM}.
     *
     * @param time the time in minutes
     * @return the formatted time
     */
    private static String formatTime(int time) {
        int hours = time / 60;
        int minutes = time % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
