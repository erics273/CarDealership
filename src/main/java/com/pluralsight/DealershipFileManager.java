package com.pluralsight;

import java.io.*;
import java.util.*;

public class DealershipFileManager {

    private static final String FILE_PATH = "src/main/resources/inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line = reader.readLine();

            if (line != null) {
                // First line: Dealership info
                String[] parts = line.split("\\|");
                String name = parts[0];
                String address = parts[1];
                String phone = parts[2];
                dealership = new Dealership(name, address, phone);
            }

            // Remaining lines: Vehicle records
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                dealership.addVehicle(vehicle);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading dealership file: " + e.getMessage());
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

            // Write dealership info
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            // Write vehicles
            ArrayList<Vehicle> inventory = dealership.getAllVehicles();
            for (Vehicle vehicle : inventory) {
                String line = vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice();
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing dealership file: " + e.getMessage());
        }
    }

}
