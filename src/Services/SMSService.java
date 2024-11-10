/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author louay
 */
public class SMSService {
     private String apiKey;
    private String apiEndpoint;

    public SMSService(String apiKey, String apiEndpoint) {
        this.apiKey = apiKey;
        this.apiEndpoint = apiEndpoint;
    }

    public void sendSMS(String phoneNumber, String message) {
        // Code to send SMS using the provided API endpoint and API key
    }}