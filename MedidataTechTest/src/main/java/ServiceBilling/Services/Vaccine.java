package ServiceBilling.Services;

import java.util.Scanner;

import static java.lang.Math.floor;

public class Vaccine extends Service {

    private Integer vaccineQuantity;
    private Double vaccineCost = 15.00;

    public Vaccine() {
        super(27.50);
        setVaccineQuantityByScreenPrompt();
        setCost(calculateVaccineTotal());
    }


    public void setVaccineQuantity(Integer vaccineQuantity) {
        this.vaccineQuantity = vaccineQuantity;
    }


    public void setVaccineQuantityByScreenPrompt(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter number of required vaccines:");
        int qty = (int) floor(keyboard.nextInt());
        setVaccineQuantity(qty);
    }

    public Double calculateVaccineTotal(){

        return getCost() + vaccineCost * vaccineQuantity;
    }

    @Override
    public void setCost(Double cost) {
        super.setCost(calculateVaccineTotal());
    }
}
