package ServiceBilling;

import ServiceBilling.Services.*;

import java.util.Scanner;

public class BillingMain {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        Patient patient = getPatientInfo(keyboard);
        Service service = getService(keyboard, patient);
        Discount discount = new Discount(patient,service);
        System.out.println("£" + (discount.getTotalWithDiscount()));

}

    private static Service getService(Scanner keyboard, Patient patient) {
        boolean isValidService = false;

        Service service = null;
        while (!isValidService) {

            System.out.println("Service name (X-ray/Diagnosis/ECG/Vaccine/Blood Test):");
            String input = keyboard.next();


            if (input.toLowerCase().equals("x-ray")) {
                service = new XRay();
                isValidService = true;
            } else if (input.toLowerCase().equals("diagnosis")) {
                service = new Diagnosis();
                isValidService = true;
            } else if (input.toLowerCase().equals("ecg")) {
                service = new ECG();
                isValidService = true;
            } else if (input.toLowerCase().equals("vaccine")) {
                service = new Vaccine();
                isValidService = true;
            } else if (input.toLowerCase().contains("blood")) {
                service = new BloodTest(patient);
                isValidService = true;
            } else {
                System.out.println("Please enter a valid service");
            }

        }
        return service;
    }

    private static Patient getPatientInfo(Scanner keyboard) {
        boolean isSet = false;

        Patient patient = new Patient();

        System.out.println("Patient age:");
        int age = getNextInt(keyboard);

        patient.setAge(age);

        while (!isSet) {
            System.out.println("Does the patient have Medicare Insurance (yes/no)? :");
            String insurance = keyboard.next();
            if (insurance.toLowerCase().equals("yes")) {
                patient.setHasInsurance(true);
                isSet = true;
            }
            if (insurance.toLowerCase().equals("no")) {
                patient.setHasInsurance(false);
                isSet = true;
            }
        }
        return patient;
    }

    private static int getNextInt(Scanner keyboard) {
        int returnVal = 0;
        boolean isInt = false;
        while (!isInt) {
            if (!keyboard.hasNextInt()) {
                System.out.println("Please enter a number");
                keyboard.next();
            } else {
                returnVal = keyboard.nextInt();
                isInt = true;
            }
        }
        return returnVal;
    }
}
