import java.util.Arrays;
import java.util.List;

/**
 * Entry point for the appointment system. No UI or menus - just a short
 * scripted simulation in main() that creates objects, exercises them and
 * prints the results.
 */
public class AssignmentOne {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("HEALTH SERVICE APPOINTMENT SYSTEM - DEMONSTRATION");
        System.out.println("==================================================");
        System.out.println();

        demonstrateProfessionals();

        demonstrateAppointments();
    }

    /** Part 3: make a mix of professionals and print their details. */
    private static void demonstrateProfessionals() {
        System.out.println("---- PART 3: HEALTH PROFESSIONALS ----");
        System.out.println();

        // three general practitioners
        GeneralPractitioner gp1 = new GeneralPractitioner(
                "1001", "Dr. Emily Carter", "GP12345");
        GeneralPractitioner gp2 = new GeneralPractitioner(
                "1002", "Dr. James Wilson", "GP67890");
        GeneralPractitioner gp3 = new GeneralPractitioner(
                "1003", "Dr. Sarah Thompson", "GP11223");

        // one or two of each other type
        Specialist cardiologist = new Specialist(
                "2001", "Dr. Michael Chen",
                Arrays.asList("Monday", "Wednesday", "Friday"), "Cardiology");
        Specialist paediatrician = new Specialist(
                "2002", "Dr. Olivia Brown", "Paediatrics");

        Surgeon surgeon = new Surgeon(
                "3001", "Dr. David Miller",
                Arrays.asList("Tuesday", "Thursday"), "SURG9001");

        Nurse nurse1 = new Nurse(
                "4001", "Nurse Anna White",
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                "General Ward");
        Nurse nurse2 = new Nurse(
                "4002", "Nurse Lucy Green", "Emergency Ward");

        // all the different types go in one list of the base type
        List<HealthProfessional> professionals = Arrays.asList(
                gp1, gp2, gp3, cardiologist, paediatrician, surgeon, nurse1, nurse2);

        System.out.println("Full details of all health professionals:");
        System.out.println("------------------------------------------");
        for (HealthProfessional professional : professionals) {
            System.out.println(professional);
            System.out.println();
        }

        // each type does its own thing
        System.out.println("Demonstrating type-specific behaviour:");
        System.out.println("--------------------------------------");
        System.out.println(gp1.prescribeMedication("Patient A", "Amoxicillin"));
        System.out.println(cardiologist.refer("Patient B", "Dr. David Miller"));
        System.out.println(surgeon.performSurgery("Patient C", "appendectomy"));
        System.out.println(nurse1.takeBloodSample("Patient D"));
        System.out.println();
    }

    /** Part 6: appointment management end-to-end, normal and error cases. */
    private static void demonstrateAppointments() {
        System.out.println("---- PART 6: APPOINTMENT MANAGEMENT ----");
        System.out.println();

        Clinic clinic = new Clinic();

        GeneralPractitioner gp1 = new GeneralPractitioner("1001", "Dr. Emily Carter", "GP12345");
        GeneralPractitioner gp2 = new GeneralPractitioner("1002", "Dr. James Wilson", "GP67890");
        Specialist cardiologist = new Specialist(
                "2001", "Dr. Michael Chen",
                Arrays.asList("Monday", "Wednesday", "Friday"), "Cardiology");
        Surgeon surgeon = new Surgeon(
                "3001", "Dr. David Miller",
                Arrays.asList("Tuesday", "Thursday"), "SURG9001");
        Nurse nurse1 = new Nurse("4001", "Nurse Anna White", "General Ward");

        Patient p1 = new Patient("John Smith", "0412345678");
        Patient p2 = new Patient("Jane Doe", "0498765432");
        Patient p3 = new Patient("Alex Lee", "0422334455");
        Patient p4 = new Patient("Maria Garcia", "0433445566");

        System.out.println("Adding appointments:");
        System.out.println("---------------------");
        // 08:00 = 480, 08:30 = 510, 09:00 = 540, 09:30 = 570
        System.out.println(clinic.addAppointment(p1, gp1, 480));
        System.out.println(clinic.addAppointment(p2, cardiologist, 510));
        System.out.println(clinic.addAppointment(p3, surgeon, 540));
        System.out.println(clinic.addAppointment(p4, nurse1, 480));
        System.out.println(clinic.addAppointment(p2, gp2, 570));
        System.out.println();

        System.out.println("Error case - double-booking a professional:");
        System.out.println("--------------------------------------------");
        // gp1 already booked at 08:00 (480)
        System.out.println(clinic.addAppointment(new Patient("Someone Else", "0455667788"),
                gp1, 480));
        System.out.println();

        System.out.println("Error case - booking outside clinic hours:");
        System.out.println("--------------------------------------------");
        // 16:30 (990) is outside 08:00-16:00
        System.out.println(clinic.addAppointment(p1, gp1, 990));
        System.out.println();

        System.out.println("All appointments (insertion order):");
        System.out.println("------------------------------------");
        printAppointments(clinic.getAllAppointments());
        System.out.println();

        System.out.println("All appointments sorted by time:");
        System.out.println("---------------------------------");
        printAppointments(clinic.getAppointmentsSortedByTime());
        System.out.println();

        System.out.println("Filter by professional ID 1001:");
        System.out.println("---------------------------------");
        printAppointments(clinic.getAppointmentsByProfessional("1001"));
        System.out.println();

        System.out.println("Filter by patient phone 0498765432:");
        System.out.println("-------------------------------------");
        printAppointments(clinic.getAppointmentsByPatient("0498765432"));
        System.out.println();

        System.out.println("Cancelling an appointment (professional 1001 at 08:00):");
        System.out.println("----------------------------------------------------------");
        System.out.println(clinic.cancelAppointment("1001", 480));
        System.out.println();

        System.out.println("Error case - cancelling a non-existent appointment:");
        System.out.println("------------------------------------------------------");
        System.out.println(clinic.cancelAppointment("9999", 600));
        System.out.println();

        System.out.println("Appointments after cancellation (sorted by time):");
        System.out.println("---------------------------------------------------");
        printAppointments(clinic.getAppointmentsSortedByTime());
        System.out.println();

        System.out.println("Total appointments remaining: " + clinic.getAppointmentCount());
        System.out.println();
        System.out.println("==================================================");
        System.out.println("END OF DEMONSTRATION");
        System.out.println("==================================================");
    }

    /** Prints a list of appointments, or a note when it's empty. */
    private static void printAppointments(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("(no appointments)");
            return;
        }
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
            System.out.println();
        }
    }
}
