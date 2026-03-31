import java.util.*;

// Custom Exception (VERY IMPORTANT)
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation Class
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
    private double basePrice;

    public Reservation(String reservationId, String guestName, String roomType, double basePrice) {
        this.reservationId = reservationId;
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
        this.basePrice = basePrice;
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

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getBasePrice() {
        return basePrice;
    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room: " + roomType;
    }
}

// Booking System with Validation
class BookingSystem {

    // Available rooms
    private Map<String, Integer> inventory = new HashMap<>();

    public BookingSystem() {
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 1);
        inventory.put("Suite", 1);
    }

    // Validate booking (FAIL-FAST)
    private void validate(String roomType) throws InvalidBookingException {

        // Check if room type exists
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        // Check availability
        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }
    }

    // Book room
    public Reservation bookRoom(String reservationId, String guestName, String roomType)
            throws InvalidBookingException {

        // Step 1: Validate first (IMPORTANT)
        validate(roomType);

        // Step 2: Safe state update
        inventory.put(roomType, inventory.get(roomType) - 1);

        // Step 3: Create reservation
        return new Reservation(reservationId, guestName, roomType);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        try {
            // Valid booking
            Reservation r1 = system.bookRoom("RES101", "Arush", "Deluxe");
            System.out.println("Booking Success: " + r1);

            // Invalid room type
            Reservation r2 = system.bookRoom("RES102", "Rahul", "Luxury");
            System.out.println(r2);

        } catch (InvalidBookingException e) {
            // Graceful failure handling
            System.out.println("Booking Failed: " + e.getMessage());
        }

        try {
            // Exhaust inventory
            system.bookRoom("RES103", "Priya", "Deluxe");

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        // System still running safely
        system.displayInventory();
                ", Room: " + roomType +
                ", Price: ₹" + basePrice;
    }
}

// Booking History (stores confirmed bookings)
class BookingHistory {

    // List preserves insertion order
    private List<Reservation> history = new ArrayList<>();

    // Add confirmed booking
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    // Get all bookings
    public List<Reservation> getAllReservations() {
        return history;
    }

    // Display all bookings
    public void displayAll() {
        if (history.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("=== Booking History ===");
        for (Reservation r : history) {
            System.out.println(r);
        }
    }
}

// Report Service (separate from storage)
class BookingReportService {

    // Total revenue
    public double calculateTotalRevenue(List<Reservation> reservations) {
        double total = 0;
        for (Reservation r : reservations) {
            total += r.getBasePrice();
        }
        return total;
    }

    // Count bookings
    public int getTotalBookings(List<Reservation> reservations) {
        return reservations.size();
    }

    // Room type summary
    public void roomTypeSummary(List<Reservation> reservations) {
        Map<String, Integer> map = new HashMap<>();

        for (Reservation r : reservations) {
            map.put(r.getRoomType(), map.getOrDefault(r.getRoomType(), 0) + 1);
        }

        System.out.println("=== Room Type Summary ===");
        for (String type : map.keySet()) {
            System.out.println(type + ": " + map.get(type));
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

    // Generate full report
    public void generateReport(List<Reservation> reservations) {
        System.out.println("\n=== Booking Report ===");

        System.out.println("Total Bookings: " + getTotalBookings(reservations));
        System.out.println("Total Revenue: ₹" + calculateTotalRevenue(reservations));

        roomTypeSummary(reservations);
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulate confirmed bookings (UC6 output)
        Reservation r1 = new Reservation("RES101", "Arush", "Deluxe", 3000);
        Reservation r2 = new Reservation("RES102", "Rahul", "Suite", 5000);
        Reservation r3 = new Reservation("RES103", "Priya", "Deluxe", 3000);

        // Add to history (IMPORTANT STEP)
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Admin views history
        history.displayAll();

        // Admin generates report
        reportService.generateReport(history.getAllReservations());
    }
}