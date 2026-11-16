import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Clinic {

    // 08:00 (480) to 16:00 (960), every 30 minutes
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

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

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

    public List<Appointment> getAppointmentsSortedByTime() {
        List<Appointment> sorted = new ArrayList<>(appointments);
        Collections.sort(sorted);
        return sorted;
    }

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
