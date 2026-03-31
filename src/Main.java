import java.util.*;

// Add-On Service Class
class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
// Represents a guest's booking request
class Reservation6 {
    String guestName;
    String roomType;

    Reservation6(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Manages room inventory and allocation
class RoomInventory6 {
    HashMap<String, Integer> inventory = new HashMap<>();
    HashMap<String, Set<String>> allocatedRooms = new HashMap<>();

    RoomInventory6() {
        // Initial inventory counts
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);

        // Initialize allocated room sets
        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());
    }

    // Allocate a room if available
    String allocateRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available <= 0) {
            return null; // No rooms available
        }

        Set<String> allocated = allocatedRooms.get(roomType);
        int roomNumber = 1;

        // Generate next unique room ID
        while (allocated.contains(roomType + "-" + roomNumber)) {
            roomNumber++;
        }

        String roomID = roomType + "-" + roomNumber;
        allocated.add(roomID);

        // Decrement inventory
        inventory.put(roomType, available - 1);

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return serviceName + " (₹" + cost + ")";
        return roomID;
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map<ReservationID, List of Services>
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Get services
    public List<AddOnService> getServices(String reservationId) {
        return serviceMap.getOrDefault(reservationId, new ArrayList<>());
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {
        double total = 0;
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }

        return total;
    }

    // Display services
    public void displayServices(String reservationId) {
        List<AddOnService> services = getServices(reservationId);

        if (services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Services for Reservation " + reservationId + ":");
        for (AddOnService s : services) {
            System.out.println("- " + s);
        System.out.println("Room Allocation Processing\n");

        // Booking request queue (FIFO)
        Queue<Reservation6> bookingQueue = new LinkedList<>();
        bookingQueue.add(new Reservation6("Abhi", "Single"));
        bookingQueue.add(new Reservation6("Subha", "Single"));
        bookingQueue.add(new Reservation6("Vanmathi", "Suite"));

        RoomInventory6 inventory = new RoomInventory6();

        // Process each booking request
        while (!bookingQueue.isEmpty()) {
            Reservation6 request = bookingQueue.poll();
            String allocatedRoom = inventory.allocateRoom(request.roomType);
            if (allocatedRoom != null) {
                System.out.println("Booking confirmed for Guest: " + request.guestName
                        + ", Room ID: " + allocatedRoom);
            } else {
                System.out.println("No available room for Guest: " + request.guestName
                        + ", Room Type: " + request.roomType);
            }
        }

        System.out.println("Total Add-on Cost: ₹" + calculateTotalCost(reservationId));
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        // Existing reservation (from UC6)
        String reservationId = "RES101";

        AddOnServiceManager manager = new AddOnServiceManager();

        // Create add-on services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService spa = new AddOnService("Spa", 1500);
        AddOnService pickup = new AddOnService("Airport Pickup", 800);

        // Guest selects services
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);
        manager.addService(reservationId, pickup);

        // Display result
        manager.displayServices(reservationId);
    }
}