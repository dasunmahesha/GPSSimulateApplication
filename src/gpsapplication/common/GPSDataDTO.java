/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpsapplication.common;

/**
 *
 * @author dasun
 */
public class GPSDataDTO {

    private String imei;
    private double latitude;
    private double longitude;

    public GPSDataDTO(String imei, double latitude, double longitude) {
        this.imei = imei;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return imei + "," + latitude + "," + longitude;
    }

}
