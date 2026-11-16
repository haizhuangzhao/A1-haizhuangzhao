/**
 * Represents a patient who books appointments with health professionals.
 *
 * <p>The patient is modelled as its own class (rather than storing loose
 * strings inside the appointment) because a patient is a meaningful,
 * reusable entity with its own data: a name and a mobile phone number. Keeping
 * it separate also follows the single-responsibility idea — the appointment
 * links a patient to a professional, while the patient holds the patient's own
 * details.</p>
 *
 * <p><b>Encapsulation</b>: the fields are private and can only be set through
 * the constructor, which validates them so a patient can never be created
 * without a name and phone number.</p>
 */
public class Patient {

    /** The full name of the patient. */
    private final String name;

    /** The mobile phone number of the patient. */
    private final String phoneNumber;

    /**
     * Creates a patient with the given name and phone number.
     *
     * @param name        the full name of the patient; must not be null or blank
     * @param phoneNumber the mobile phone number; must not be null or blank
     * @throws IllegalArgumentException if either argument is invalid
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

    /**
     * Returns the full name of the patient.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the mobile phone number of the patient.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns a readable description of the patient.
     *
     * @return a readable summary of the patient
     */
    @Override
    public String toString() {
        return name + " (" + phoneNumber + ")";
    }
}
