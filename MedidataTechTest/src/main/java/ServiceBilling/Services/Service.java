package ServiceBilling.Services;

public class Service {

    private Double cost;

    public Service(Double cost) {
        this.cost = cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getCost() {
        return cost;
    }
}
