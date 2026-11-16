public class Patient {

    private final String name;
    private final String phoneNumber;

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
