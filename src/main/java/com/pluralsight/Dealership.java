package com.pluralsight;

import java.util.ArrayList;

// Represents a car dealership and provides methods to manage its inventory of vehicles.
public class Dealership {
    // Basic information about the dealership
    private String name;
    private String address;
    private String phone;

    // The list of vehicles the dealership currently has
    private ArrayList<Vehicle> inventory;

    // Constructor: sets up dealership info and starts with an empty list
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<Vehicle>();
    }

    // Adds a new vehicle to the list
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    // Removes a vehicle based on VIN match
    public void removeVehicle(Vehicle vehicle) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getVin() == vehicle.getVin()) {
                inventory.remove(i); // stop after removing first match
                break;
            }
        }
    }

    // Returns the entire vehicle list
    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    // Returns vehicles between the given price range
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                results.add(vehicle);
            }
        }
        return results;
    }

    // Returns vehicles that match both make and model
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                results.add(vehicle);
            }
        }
        return results;
    }

    // Returns vehicles within the year range provided
    public ArrayList<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear) {
                results.add(vehicle);
            }
        }
        return results;
    }

    // Returns vehicles matching the specified color
    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                results.add(vehicle);
            }
        }
        return results;
    }

    // Returns vehicles within the specified mileage range
    public ArrayList<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= minMileage && vehicle.getOdometer() <= maxMileage) {
                results.add(vehicle);
            }
        }
        return results;
    }

    // Returns vehicle by vin
    public Vehicle getVehicleByVin(int vin) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVin() == vin) {
                return vehicle;
            }
        }
        return null;
    }

    // Returns vehicles that match the vehicle type
    public ArrayList<Vehicle> getVehiclesByType(String type) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(type)) {
                results.add(vehicle);
            }
        }
        return results;
    }

    // Getters for dealership name, address, and phone
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
}

