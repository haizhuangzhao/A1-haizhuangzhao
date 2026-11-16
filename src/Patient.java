/**
 * A patient who books appointments. Kept as its own class because a patient
 * is a meaningful thing with its own data (name and phone number), rather
 * than just a couple of loose strings inside the appointment.
 */
public class Patient {

    private final String name;
    private final String phoneNumber;

    /**
     * @param name        full name
     * @param phoneNumber mobile phone number
     */
    public Patient(String name, String phoneNumber) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Patient name cannot be empty.");
        }
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Patient phone number cannot be empty.");
        }
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return name + " (" + phoneNumber + ")";
    }
}
