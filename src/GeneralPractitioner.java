import java.util.Arrays;
import java.util.List;

/**
 * Represents a general practitioner (GP), a doctor who provides everyday
 * primary care. A GP is authorised to prescribe medication to patients.
 *
 * <p>This class demonstrates <b>inheritance</b>: it extends the abstract
 * {@link HealthProfessional} base class and reuses all of its common data
 * (id, name, working days) and behaviour, adding only what is specific to a
 * general practitioner.</p>
 */
public class GeneralPractitioner extends HealthProfessional {

    /** The medical provider number of the GP (unique to doctors). */
    private final String providerNumber;

    /**
     * Creates a general practitioner with the given details.
     *
     * @param id             the numeric identifier of the GP
     * @param name           the full name of the GP
     * @param workingDays    the days of the week the GP works
     * @param providerNumber the medical provider number of the GP
     */
    public GeneralPractitioner(String id, String name, List<String> workingDays,
            String providerNumber) {
        // super(...) first sets up the shared state, then we set our own.
        super(id, name, workingDays);
        if (providerNumber == null || providerNumber.isBlank()) {
            throw new IllegalArgumentException("Provider number cannot be empty.");
        }
        this.providerNumber = providerNumber;
    }

    /**
     * Creates a general practitioner with a default Monday-to-Friday schedule.
     *
     * @param id             the numeric identifier of the GP
     * @param name           the full name of the GP
     * @param providerNumber the medical provider number of the GP
     */
    public GeneralPractitioner(String id, String name, String providerNumber) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                providerNumber);
    }

    /**
     * Returns the medical provider number of the GP.
     *
     * @return the provider number
     */
    public String getProviderNumber() {
        return providerNumber;
    }

    /**
     * Prescribes a medication to a patient. This behaviour is unique to
     * doctors and is one of the functions a GP can perform.
     *
     * @param patientName the name of the patient receiving the prescription
     * @param medication  the medication being prescribed
     * @return a message describing the prescription
     */
    public String prescribeMedication(String patientName, String medication) {
        return getName() + " prescribed " + medication + " to " + patientName + ".";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProfessionType() {
        return "General Practitioner";
    }

    /**
     * Returns a readable description of the GP, including the shared
     * professional details and the additional provider number.
     *
     * @return a readable summary of the GP
     */
    @Override
    public String toString() {
        // Reuse the base details, then add our own extra information.
        return super.toString() + System.lineSeparator()
                + "    Provider number: " + providerNumber;
    }
}
