/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIs;

/**
 *
 * @author louay
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSManager {


    public void sendSMS(String receiverPhoneNumber, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(
                new PhoneNumber(receiverPhoneNumber),
                new PhoneNumber(TWILIO_PHONE_NUMBER),
                message
        ).create();

        System.out.println("SMS sent successfully."); // Print a success message
    }
}
