import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages all the appointments for the health service: adding, viewing,
 * filtering, sorting and cancelling. Also stops a professional being
 * double-booked.
 *
 * The clinic runs on a fixed daily schedule of 08:00-16:00 in 30-minute
 * steps. The slots are a single constant array so if the hours ever change,
 * that's the only place to edit.
 *
 * Appointments are stored in an ArrayList - it keeps insertion order, allows
 * two patients to book different professionals at the same time, and works
 * with Collections.sort() for ordering.
 */
public class Clinic {

    /** Time slots in minutes since midnight: 08:00 (480) to 16:00 (960). */
    public static final int[] TIME_SLOTS = generateTimeSlots(480, 960, 30);

    private final List<Appointment> appointments;

    public Clinic() {
        this.appointments = new ArrayList<>();
    }

    private static int[] generateTimeSlots(int start, int end, int step) {
        int count = ((end - start) / step) + 1;
        int[] slots = new int[count];
        for (int i = 0; i < count; i++) {
            slots[i] = start + i * step;
        }
        return slots;
    }

    /**
     * Adds an appointment if the info is complete, the time is within opening
     * hours, and the professional isn't already booked at that slot. Returns
     * a message saying what happened.
     */
    public String addAppointment(Patient patient, HealthProfessional professional, int time) {
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

    /** All appointments in insertion order (a copy, so it can't be tampered with). */
    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    /** Appointments for one professional, by id, sorted by time. */
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

    /** Appointments for one patient, by phone number, sorted by time. */
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

    /** All appointments sorted earliest to latest. */
    public List<Appointment> getAppointmentsSortedByTime() {
        List<Appointment> sorted = new ArrayList<>(appointments);
        Collections.sort(sorted);
        return sorted;
    }

    /** Cancels by professional id + time. Warns if nothing matches. */
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

    /** Cancels every appointment for a patient, by phone number. */
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

    public int getAppointmentCount() {
        return appointments.size();
    }

    private boolean isValidTimeSlot(int time) {
        for (int slot : TIME_SLOTS) {
            if (slot == time) {
                return true;
            }
        }
        return false;
    }

    private static String formatTime(int time) {
        int hours = time / 60;
        int minutes = time % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
