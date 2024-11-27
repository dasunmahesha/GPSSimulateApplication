/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpsapplication.client;

import gpsapplication.common.GPSDataDTO;
import gpsapplication.common.Config;
import java.io.*;
import java.net.*;
import java.util.Random;

/**
 *
 * @author dasun
 */
public class GPSClient {

    public static void main(String[] args) {
        String imei = generateIMEI();
        System.out.println("Starting GPS Client with IMEI: " + imei);

        try (Socket socket = new Socket(Config.SERVER_ADDRESS, Config.SERVER_PORT); PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            Random random = new Random();

            while (true) {
                
                //randome location 
                double latitude = -90 + (90 - (-90)) * random.nextDouble();
                double longitude = -180 + (180 - (-180)) * random.nextDouble();

                GPSDataDTO gpsData = new GPSDataDTO(imei, latitude, longitude);
                writer.println(gpsData);
                System.out.println("Sent data: " + gpsData);

                // Sleep before sending the next location
                Thread.sleep(Config.CLIENT_SIMULATION_INTERVAL);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }

    private static String generateIMEI() {
        Random random = new Random();
        StringBuilder imei = new StringBuilder();

        //random 15 digit number for imei
        for (int i = 0; i < 15; i++) {
            imei.append(random.nextInt(10));
        }
        return imei.toString();
    }
}
