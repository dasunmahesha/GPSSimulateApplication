package gpsapplication.server;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GPSDataStorage {

    private static final String LOG_FILE = "logs/server.log";

    public static synchronized void saveData(String data) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            String[] parts = data.split(",");

            String imei = parts[0].trim();
            double latitude = Double.parseDouble(parts[1].trim());
            double longitude = Double.parseDouble(parts[2].trim());
            
            
            writer.write(timestamp + " - " + "imei: " + imei + "   latitude: " + latitude + "   longitude: " + longitude + System.lineSeparator());

            System.out.println("Data received and saved succussfuly to log file:\n " + "imei: " + imei + "   latitude: " + latitude + "   longitude: " + longitude);
            

        } catch (IOException e) {
            System.err.println("Error when save data: " + e.getMessage());
        }
    }

}
