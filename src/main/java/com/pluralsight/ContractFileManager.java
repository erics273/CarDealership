package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private static final String FILE_NAME = "src/main/resources/contracts.csv";

    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            if (contract instanceof SalesContract) {
                SalesContract sc = (SalesContract) contract;
                writer.write("SALE|" + sc.getDate() + "|" + sc.getCustomerName() + "|" + sc.getCustomerEmail() + "|"
                        + sc.getVehicle().getVin() + "|" + sc.getVehicle().getYear() + "|"
                        + sc.getVehicle().getMake() + "|" + sc.getVehicle().getModel() + "|"
                        + sc.getVehicle().getVehicleType() + "|" + sc.getVehicle().getColor() + "|"
                        + sc.getVehicle().getOdometer() + "|" + sc.getVehicle().getPrice() + "|"
                        + sc.getSalesTaxAmount() + "|" + sc.getRecordingFee() + "|" + sc.getProcessingFee() + "|"
                        + sc.getTotalPrice() + "|" + (sc.isFinanced() ? "YES" : "NO") + "|" + sc.getMonthlyPayment());
                writer.newLine();
            } else if (contract instanceof LeaseContract) {
                LeaseContract lc = (LeaseContract) contract;
                writer.write("LEASE|" + lc.getDate() + "|" + lc.getCustomerName() + "|" + lc.getCustomerEmail() + "|"
                        + lc.getVehicle().getVin() + "|" + lc.getVehicle().getYear() + "|"
                        + lc.getVehicle().getMake() + "|" + lc.getVehicle().getModel() + "|"
                        + lc.getVehicle().getVehicleType() + "|" + lc.getVehicle().getColor() + "|"
                        + lc.getVehicle().getOdometer() + "|" + lc.getVehicle().getPrice() + "|"
                        + lc.getExpectedEndingValue() + "|" + lc.getLeaseFee() + "|" + lc.getMonthlyPayment());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to write contract: " + e.getMessage());
        }
    }
}