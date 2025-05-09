package com.pluralsight;

import java.io.*;
import java.util.ArrayList;


// This class is responsible for saving and loading dealership data to and from a CSV file.
public class DealershipFileManager {

    // File path where vehicle data is stored
    private static final String FILE_PATH = "src/main/resources/inventory.csv";

    // Reads the dealership information and inventory from the CSV file
    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            // Open the file for reading
            BufferedReader fileReader = new BufferedReader(new FileReader(FILE_PATH));

            // First line contains the dealership info
            String dealershipInfoLine = fileReader.readLine();

            if (dealershipInfoLine != null) {
                // Split the line into parts: name, address, phone
                String[] dealershipParts = dealershipInfoLine.split("\\|");
                String name = dealershipParts[0];
                String address = dealershipParts[1];
                String phone = dealershipParts[2];

                // Create the dealership object
                dealership = new Dealership(name, address, phone);
            }

            // Read each line (one per vehicle)
            String vehicleLine;
            while ((vehicleLine = fileReader.readLine()) != null) {
                String[] parts = vehicleLine.split("\\|");

                // Parse vehicle fields
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                // Add each vehicle to the dealership
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                dealership.addVehicle(vehicle);
            }

            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error reading dealership file: " + e.getMessage());
        }

        return dealership;
    }

    // Writes the dealership and its vehicles to the CSV file
    public void saveDealership(Dealership dealership) {
        try {
            // Open the file for writing (overwrites existing file)
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(FILE_PATH));

            // Write dealership header info (first line)
            fileWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            fileWriter.newLine();

            // Write one line for each vehicle
            ArrayList<Vehicle> inventory = dealership.getAllVehicles();
            for (Vehicle v : inventory) {
                String line = v.getVin() + "|" +
                        v.getYear() + "|" +
                        v.getMake() + "|" +
                        v.getModel() + "|" +
                        v.getVehicleType() + "|" +
                        v.getColor() + "|" +
                        v.getOdometer() + "|" +
                        v.getPrice();
                fileWriter.write(line);
                fileWriter.newLine();
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing dealership file: " + e.getMessage());
        }
    }
}

