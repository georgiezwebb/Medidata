package ServiceBilling;

import ServiceBilling.Services.Service;

import java.text.DecimalFormat;

public class Discount {

    private Double percentage;
    private Patient patient;
    private Service service;

    public Discount(Patient patient, Service service) {
        this.patient = patient;
        this.service = service;
        calculatePercentage();
    }

    public Double getPercentage() {
        return percentage;
    }

    private void calculatePercentage(){

        int age = patient.getAge();
        if (age >= 70){
            this.percentage = 90.0;
        } else if (age >= 65 && age <= 70){
            this.percentage = 60.0;
        } else if (age < 5){
            this.percentage = 40.0;
        }else
            this.percentage = 0.0;
    }

    public Double getTotalWithDiscount(){
        DecimalFormat f = new DecimalFormat("##.00");
        return Double.valueOf(f.format(service.getCost() - (service.getCost()/100 * getPercentage())));
    }
}
