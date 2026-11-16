import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class HealthProfessional {

    private final String id;
    private final String name;
    private final List<String> workingDays;

    public HealthProfessional(String id, String name, List<String> workingDays) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Professional ID cannot be empty.");
        }
        if (!id.matches("\\d+")) {
            throw new IllegalArgumentException("Professional ID must be numbers only: " + id);
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Professional name cannot be empty.");
        }
        if (workingDays == null || workingDays.isEmpty()) {
            throw new IllegalArgumentException("A professional must work on at least one day.");
        }

        this.id = id;
        this.name = name;
        this.workingDays = Collections.unmodifiableList(new ArrayList<>(workingDays));
    }

    public HealthProfessional(String id, String name) {
        this(id, name, Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getWorkingDays() {
        return workingDays;
    }

    public abstract String getProfessionType();

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional that = (HealthProfessional) other;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getProfessionType())
                .append(": ")
                .append(name)
                .append(" (ID: ")
                .append(id)
                .append(")")
                .append(System.lineSeparator())
                .append("    Works on: ")
                .append(String.join(", ", workingDays));
        return result.toString();
    }
}
