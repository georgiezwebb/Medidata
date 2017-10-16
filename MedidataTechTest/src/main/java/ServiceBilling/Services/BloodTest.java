package ServiceBilling.Services;

import ServiceBilling.Patient;

public class BloodTest extends Service {

    private Patient patient;

    public BloodTest(Patient patient) {
        super(78.0);
        this.patient = patient;
    }

    @Override
    public Double getCost() {
        if (!patient.isHasInsurance()) {
            return super.getCost();
        } else {
            return super.getCost() - ((super.getCost()/100) * 15);
        }
    }
}
