import java.util.Arrays;
import java.util.List;

public class AssignmentOne {

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("   HEALTH SERVICE APPOINTMENT SYSTEM DEMO   ");
        System.out.println("============================================");
        System.out.println();

        demonstrateProfessionals();

        demonstrateAppointments();
    }

    private static void demonstrateProfessionals() {
        System.out.println("========== PART 3: HEALTH PROFESSIONALS ==========");
        System.out.println();

        GeneralPractitioner gpDou = new GeneralPractitioner(
                "1001", "Dr. Dou", "GP10001");
        GeneralPractitioner gpXie = new GeneralPractitioner(
                "1002", "Dr. Xie", "GP10002");
        GeneralPractitioner gpRunge = new GeneralPractitioner(
                "1003", "Dr. Runge", "GP10003");

        Specialist cardiologistWang = new Specialist(
                "2001", "Dr. Wang",
                Arrays.asList("Monday", "Wednesday", "Friday"), "Cardiology");
        Specialist paediatricianXing = new Specialist(
                "2002", "Dr. Xing", "Paediatrics");

        Surgeon surgeonQiu = new Surgeon(
                "3001", "Dr. Qiu",
                Arrays.asList("Tuesday", "Thursday"), "SURG9001");

        Nurse nurseLiu = new Nurse(
                "4001", "Nurse Liu",
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                "General Ward");
        Nurse nurseSun = new Nurse(
                "4002", "Nurse Sun", "Emergency Ward");

        List<HealthProfessional> professionals = Arrays.asList(
                gpDou, gpXie, gpRunge, cardiologistWang, paediatricianXing,
                surgeonQiu, nurseLiu, nurseSun);

        System.out.println("Full details of all health professionals:");
        System.out.println("------------------------------------------");
        for (HealthProfessional professional : professionals) {
            System.out.println(professional);
            System.out.println();
        }

        System.out.println("Demonstrating type-specific behaviour:");
        System.out.println("--------------------------------------");
        System.out.println(gpDou.prescribeMedication("John", "amoxicillin"));
        System.out.println(cardiologistWang.refer("Lily", "Dr. Qiu"));
        System.out.println(surgeonQiu.performSurgery("Tom", "appendectomy"));
        System.out.println(nurseLiu.takeBloodSample("Mary"));
        System.out.println();
    }

    private static void demonstrateAppointments() {
        System.out.println("========== PART 6: APPOINTMENT MANAGEMENT ==========");
        System.out.println();

        Clinic clinic = new Clinic();

        GeneralPractitioner gpDou = new GeneralPractitioner("1001", "Dr. Dou", "GP10001");
        GeneralPractitioner gpXie = new GeneralPractitioner("1002", "Dr. Xie", "GP10002");
        Specialist cardiologistWang = new Specialist(
                "2001", "Dr. Wang",
                Arrays.asList("Monday", "Wednesday", "Friday"), "Cardiology");
        Surgeon surgeonQiu = new Surgeon(
                "3001", "Dr. Qiu",
                Arrays.asList("Tuesday", "Thursday"), "SURG9001");
        Nurse nurseLiu = new Nurse("4001", "Nurse Liu", "General Ward");

        Patient john = new Patient("John", "0412345678");
        Patient lily = new Patient("Lily", "0498765432");
        Patient tom = new Patient("Tom", "0422334455");
        Patient mary = new Patient("Mary", "0433445566");

        System.out.println("Adding appointments:");
        System.out.println("---------------------");
        System.out.println(clinic.addAppointment(john, gpDou, 480));
        System.out.println(clinic.addAppointment(lily, cardiologistWang, 510));
        System.out.println(clinic.addAppointment(tom, surgeonQiu, 540));
        System.out.println(clinic.addAppointment(mary, nurseLiu, 480));
        System.out.println(clinic.addAppointment(lily, gpXie, 570));
        System.out.println();

        System.out.println("Error case - double-booking a professional:");
        System.out.println("--------------------------------------------");
        System.out.println(clinic.addAppointment(new Patient("Ben", "0455667788"),
                gpDou, 480));
        System.out.println();

        System.out.println("Error case - booking outside clinic hours:");
        System.out.println("--------------------------------------------");
        System.out.println(clinic.addAppointment(john, gpDou, 990));
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

        System.out.println("Cancelling an appointment (Dr. Dou at 08:00):");
        System.out.println("------------------------------------------------");
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
        System.out.println("============================================");
        System.out.println("              END OF DEMONSTRATION          ");
        System.out.println("============================================");
    }

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
