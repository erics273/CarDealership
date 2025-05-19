package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// This class handles the console-based interaction with the user, displaying menus,
// capturing input, and performing operations on the dealership.
public class UserInterface {

    private static Dealership dealership;

    // Private constructor to prevent instantiation
    private UserInterface() {}

    // This method launches the menu and keeps looping until the user exits
    public static void display() {
        init();  // Load the dealership from file
        Scanner scanner = new Scanner(System.in);
        boolean menuRunning = true;

        while (menuRunning) {
            // Show menu options
            System.out.println("\n=== Car Dealership Menu ===");
            System.out.println("1 - Find vehicles by price");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find all vehicles by mileage");
            System.out.println("6 - Find vehicles by type");
            System.out.println("7 - List all vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("10 - Sell/Lease a Vehicle");
            System.out.println("99 - Quit");
            System.out.print("Enter option: ");

            // Read user input
            int option;
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                scanner.nextLine(); // discard invalid input
                option = -1;
            }

            // Route to the appropriate method
            switch (option) {
                case 1: processGetByPriceRequest(); break;
                case 2: processGetByMakeModelRequest(); break;
                case 3: processGetByYearRequest(); break;
                case 4: processGetByColorRequest(); break;
                case 5: processGetByMileageRequest(); break;
                case 6: processGetByVehicleTypeRequest(); break;
                case 7: processAllVehiclesRequest(); break;
                case 8: processAddVehicleRequest(); break;
                case 9: processRemoveVehicleRequest(); break;
                case 10: handleSellOrLease(); break;
                case 99:
                    System.out.println("Goodbye!");
                    menuRunning = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Loads the dealership inventory from the file using DealershipFileManager
    private static void init() {
        DealershipFileManager dfm = new DealershipFileManager();
        dealership = dfm.getDealership();
    }

    // Search for vehicles within a price range
    public static void processGetByPriceRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();

        ArrayList<Vehicle> results = dealership.getVehiclesByPrice(min, max);
        displayVehicles(results);
    }

    // Search for vehicles by make and model
    public static void processGetByMakeModelRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        ArrayList<Vehicle> results = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(results);
    }

    // Search for vehicles by year range
    public static void processGetByYearRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();

        ArrayList<Vehicle> results = dealership.getVehiclesByYear(min, max);
        displayVehicles(results);
    }

    // Search for vehicles by color
    public static void processGetByColorRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        ArrayList<Vehicle> results = dealership.getVehiclesByColor(color);
        displayVehicles(results);
    }

    // Search for vehicles by mileage
    public static void processGetByMileageRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int max = scanner.nextInt();

        ArrayList<Vehicle> results = dealership.getVehiclesByMileage(min, max);
        displayVehicles(results);
    }

    // Search for vehicles by type
    public static void processGetByVehicleTypeRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        ArrayList<Vehicle> results = dealership.getVehiclesByType(type);
        displayVehicles(results);
    }

    // Show all vehicles in the dealership
    public static void processAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    // Add a new vehicle to the inventory
    public static void processAddVehicleRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.print("Enter type: ");
        String type = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        System.out.print("Enter mileage: ");
        int mileage = scanner.nextInt();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
        dealership.addVehicle(newVehicle);

        DealershipFileManager dfm = new DealershipFileManager();
        dfm.saveDealership(dealership);

        System.out.println("Vehicle added successfully.");
    }

    // Remove a vehicle by VIN
    public static void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();

        Vehicle vehicleToRemove = null;
        for (Vehicle currentVehicle : dealership.getAllVehicles()) {
            if (currentVehicle.getVin() == vin) {
                vehicleToRemove = currentVehicle;
                break;
            }
        }

        // Example of equivalent long form loop:
        // for (int index = 0; index < dealership.getAllVehicles().size(); index++) {
        //     Vehicle currentVehicle = dealership.getAllVehicles().get(index);
        //     if (currentVehicle.getVin() == vin) {
        //         vehicleToRemove = currentVehicle;
        //         break;
        //     }
        // }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            DealershipFileManager dfm = new DealershipFileManager();
            dfm.saveDealership(dealership);
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle with that VIN not found.");
        }
    }

    // Utility method to display a list of vehicles, or a message if empty
    private static void displayVehicles(ArrayList<Vehicle> vehicleList) {
        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle currentVehicle : vehicleList) {
                System.out.println(currentVehicle);
            }

            // Example of equivalent long form loop:
            // for (int index = 0; index < vehicleList.size(); index++) {
            //     Vehicle currentVehicle = vehicleList.get(index);
            //     System.out.println(currentVehicle);
            // }
        }
    }

    //deal with selling or leasing
    private static void handleSellOrLease() {
        //create the scanner to capture user input
        Scanner scanner = new Scanner(System.in);
        try {

            //load up an instance of the contract file manager so we can save contracts
            ContractFileManager contractFileManager = new ContractFileManager();

            //start capturing user info
            System.out.print("Enter VIN of the vehicle: ");
            int vin = Integer.parseInt(scanner.nextLine());

            //see if we can find the vehicle by vin
            Vehicle vehicle = dealership.getVehicleByVin(vin);
            //if we cant find it, let the user know and go back to the previous menu
            if (vehicle == null) {
                System.out.println("Vehicle not found.");
                return;
            }

            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();

            System.out.print("Enter customer email: ");
            String email = scanner.nextLine();

            System.out.print("Is this a Sale or Lease? (Enter 'sale' or 'lease'): ");
            String type = scanner.nextLine().trim().toLowerCase();

            //get the date for the contract and start building the sale/lease contract
            LocalDate date = LocalDate.now();
            Contract contract = null;

            if (type.equals("sale")) {
                System.out.print("Is this financed? (yes/no): ");
                boolean financed = scanner.nextLine().trim().equalsIgnoreCase("yes");
                contract = new SalesContract(date.toString(), name, email, vehicle, financed);
            } else if (type.equals("lease")) {
                int currentYear = date.getYear();
                if (vehicle.getYear() <= currentYear - 3) {
                    System.out.println("Vehicle is too old for a lease (must be 3 years or newer). Returning to menu.");
                    return;
                }
                contract = new LeaseContract(date.toString(), name, email, vehicle);
            } else {
                System.out.println("Invalid contract type. Returning to menu.");
                return;
            }

            //save the contract
            contractFileManager.saveContract(contract);

            //remove the vehicle from inventory
            dealership.removeVehicle(vehicle);

            //update the dealership inventory
            DealershipFileManager dfm = new DealershipFileManager();
            dfm.saveDealership(dealership);

            System.out.println("Contract recorded successfully and vehicle removed from inventory.");

        } catch (Exception e) {
            System.out.println("Error during contract creation: " + e.getMessage());
        }
    }

}

