package ServiceBilling;

import ServiceBilling.Services.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiscountTest {

    private int AGE_4 = 4;
    private int AGE_5 = 5;
    private int AGE_6 = 6;

    private int AGE_64 = 64;
    private int AGE_65 = 65;
    private int AGE_66 = 66;

    private int AGE_69 = 69;
    private int AGE_70 = 70;
    private int AGE_71 = 71;


    Patient patient = new Patient();
    Service service = new XRay();


    private void testGetPercentage(int age, Double expectedPercentage) {

        patient.setAge(age);
        Discount discount = new Discount(patient, service);
        Double percentage = discount.getPercentage();
        assertTrue(percentage.equals(expectedPercentage));
    }

    @Test
    public void testExpectedPercentagesAreReturned_forLowestAgeCategory() {
        testGetPercentage(AGE_4, 40.0);
        testGetPercentage(AGE_5, 0.0);
        testGetPercentage(AGE_6, 0.0);
    }

    @Test
    public void testExpectedPercentagesAreReturned_forMiddleAgeCategory() {
        testGetPercentage(AGE_64, 0.0);
        testGetPercentage(AGE_65, 60.0);
        testGetPercentage(AGE_66, 60.0);
    }

    @Test
    public void testExpectedPercentagesAreReturned_forHighAgeCategory() {
        testGetPercentage(AGE_69, 60.0);
        testGetPercentage(AGE_70, 90.0);
        testGetPercentage(AGE_71, 90.0);
    }

    void getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(int age, Double expectedTotal, Service service) {

        patient.setAge(age);
        Discount discount = new Discount(patient, service);
        Double total = discount.getTotalWithDiscount();
        assertEquals(total, expectedTotal);
    }

    @Test
    public void getTotalWithDiscount_whenServiceNotVaccineOrBloodTest_forLowAgeRange_XRay() {
        Service service = new XRay();
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_4, 90.00, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_5, 150.00, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_6, 150.00, service);
    }

    @Test
    public void getTotalWithDiscount_whenServiceNotVaccineOrBloodTest_forMidAgeRange_XRay() {
        Service service = new XRay();
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_64, 150.00, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_65, 60.00, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_66, 60.00, service);
    }

    @Test
    public void getTotalWithDiscount_whenServiceNotVaccineOrBloodTest_forHighAgeRange_XRay() {
        Service service = new XRay();
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_69, 60.00, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_70, 15.00, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_71, 15.00, service);
    }

    @Test
    public void getTotalWithDiscount_whenServiceNotVaccineOrBloodTest_forLowAgeRange_ECG() {
        Service service = new ECG();
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_4, 120.24, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_5, 200.40, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_6, 200.40, service);
    }

    @Test
    public void getTotalWithDiscount_whenServiceNotVaccineOrBloodTest_forMidAgeRange_ECG() {
        Service service = new ECG();
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_64, 200.40, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_65, 80.16, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_66, 80.16, service);
    }

    @Test
    public void getTotalWithDiscount_whenServiceNotVaccineOrBloodTest_forHighAgeRange_ECG() {
        Service service = new ECG();
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_69, 80.16, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_70, 20.04, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_71, 20.04, service);
    }


    @Test
    public void getTotalWithDiscount_whenServiceNotVaccineOrBloodTest_forLowAgeRange_Diagnosis() {
        Service service = new Diagnosis();
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_4, 36.00, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_5, 60.00, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_6, 60.00, service);
    }

    @Test
    public void getTotalWithDiscount_whenServiceNotVaccineOrBloodTest_forMidAgeRange_Diagnosis() {
        Service service = new Diagnosis();
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_64, 60.00, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_65, 24.00, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_66, 24.00, service);
    }

    @Test
    public void getTotalWithDiscount_whenServiceNotVaccineOrBloodTest_forHighAgeRange_Diagnosis() {
        Service service = new Diagnosis();
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_69, 24.00, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_70, 6.00, service);
        getTotalWithDiscount_whenServiceNotVaccineOrBloodTest(AGE_71, 6.00, service);
    }


    private void testGetTotalWithDiscountWhenBloodTest(int age, boolean hasInsurance, Double expectedCost) {
        patient.setHasInsurance(hasInsurance);
        patient.setAge(age);
        Service service = new BloodTest(patient);
        assert (service.getCost().equals(expectedCost));

    }

    @Test
    public void testGetTotalWithDiscountWhenBloodTest_whenPatientHasInsurance_isTrue() {
        testGetTotalWithDiscountWhenBloodTest(AGE_4, true, 66.3);
        testGetTotalWithDiscountWhenBloodTest(AGE_5, true, 66.3);
        testGetTotalWithDiscountWhenBloodTest(AGE_6, true, 66.3);

        testGetTotalWithDiscountWhenBloodTest(AGE_64, true, 66.3);
        testGetTotalWithDiscountWhenBloodTest(AGE_65, true, 66.3);
        testGetTotalWithDiscountWhenBloodTest(AGE_66, true, 66.3);

        testGetTotalWithDiscountWhenBloodTest(AGE_69, true, 66.3);
        testGetTotalWithDiscountWhenBloodTest(AGE_70, true, 66.3);
        testGetTotalWithDiscountWhenBloodTest(AGE_71, true, 66.3);
    }

    @Test
    public void testGetTotalWithDiscountWhenBloodTest_whenPatientHasInsurance_isFalse() {
        testGetTotalWithDiscountWhenBloodTest(AGE_4, false, 78.0);
        testGetTotalWithDiscountWhenBloodTest(AGE_5, false, 78.0);
        testGetTotalWithDiscountWhenBloodTest(AGE_6, false, 78.0);

        testGetTotalWithDiscountWhenBloodTest(AGE_64, false, 78.0);
        testGetTotalWithDiscountWhenBloodTest(AGE_65, false, 78.0);
        testGetTotalWithDiscountWhenBloodTest(AGE_66, false, 78.0);

        testGetTotalWithDiscountWhenBloodTest(AGE_69, false, 78.0);
        testGetTotalWithDiscountWhenBloodTest(AGE_70, false, 78.0);
        testGetTotalWithDiscountWhenBloodTest(AGE_71, false, 78.0);
    }

    private void testBloodTestTotal_afterInsuranceAdjustment(int age, Boolean hasInsurance, Double expectedTotal) {
        patient.setHasInsurance(hasInsurance);
        patient.setAge(age);
        Service service = new BloodTest(patient);
        Discount discount = new Discount(patient, service);
        assert (discount.getTotalWithDiscount().equals(expectedTotal));
    }

    @Test
    public void testGetTotalWithDiscount_whenBloodTestInsuranceAdjusted_hasInsuranceisTrue(){
        testBloodTestTotal_afterInsuranceAdjustment(AGE_4,true, 39.78);
        testBloodTestTotal_afterInsuranceAdjustment(AGE_5,true, 66.30);
        testBloodTestTotal_afterInsuranceAdjustment(AGE_6,true, 66.30);

        testBloodTestTotal_afterInsuranceAdjustment(AGE_64,true, 66.30);
        testBloodTestTotal_afterInsuranceAdjustment(AGE_65,true, 26.52);
        testBloodTestTotal_afterInsuranceAdjustment(AGE_66,true, 26.52);

        testBloodTestTotal_afterInsuranceAdjustment(AGE_69,true, 26.52);
        testBloodTestTotal_afterInsuranceAdjustment(AGE_70,true, 6.63);
        testBloodTestTotal_afterInsuranceAdjustment(AGE_71,true, 6.63);
    }

    @Test
    public void testGetTotalWithDiscount_whenBloodTestInsuranceAdjusted_hasInsuranceisFalse(){
        testBloodTestTotal_afterInsuranceAdjustment(AGE_4,false, 46.80);
        testBloodTestTotal_afterInsuranceAdjustment(AGE_5,false, 78.00);
        testBloodTestTotal_afterInsuranceAdjustment(AGE_6,false, 78.00);

        testBloodTestTotal_afterInsuranceAdjustment(AGE_64,false, 78.00);
        testBloodTestTotal_afterInsuranceAdjustment(AGE_65,false, 31.20);
        testBloodTestTotal_afterInsuranceAdjustment(AGE_66,false, 31.20);

        testBloodTestTotal_afterInsuranceAdjustment(AGE_69,false, 31.20);
        testBloodTestTotal_afterInsuranceAdjustment(AGE_70,false, 7.80);
        testBloodTestTotal_afterInsuranceAdjustment(AGE_71,false, 7.80);
    }
}