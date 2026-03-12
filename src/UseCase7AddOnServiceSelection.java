import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Service {

    private String serviceName;
    private double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() { return serviceName; }
    public double getCost() { return cost; }
}

class AddOnServiceManager {

    private Map<String, List<Service>> servicesByReservation;

    public AddOnServiceManager() { servicesByReservation = new HashMap<>(); }

    public void addService(String reservationId, Service service) {
        servicesByReservation.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {
        List<Service> services = servicesByReservation.getOrDefault(reservationId, new ArrayList<>());
        double total = 0;
        for (Service s : services) {
            total += s.getCost();
        }
        return total;
    }
}

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection");

        AddOnServiceManager serviceManager = new AddOnServiceManager();

        String reservationId = "Single-1";

        serviceManager.addService(reservationId, new Service("Breakfast", 500.0));
        serviceManager.addService(reservationId, new Service("Airport Pickup", 1000.0));

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + serviceManager.calculateTotalServiceCost(reservationId));
    }
}