public class Appointment implements Comparable<Appointment> {

    private final Patient patient;
    private final HealthProfessional professional;
    private final int time;

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

    public String getFormattedTime() {
        int hours = time / 60;
        int minutes = time % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    @Override
    public int compareTo(Appointment other) {
        // sort by time, earliest first
        return Integer.compare(this.time, other.time);
    }

    @Override
    public String toString() {
        return "Appointment at " + getFormattedTime() + System.lineSeparator()
                + "    Patient: " + patient + System.lineSeparator()
                + "    Professional: " + professional;
    }
}
