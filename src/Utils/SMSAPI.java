/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author 21626
 */
public class SMSAPI {
        public static final String ACCOUNT_SID = "AC5bf1069e34a096d16cebd4a1b4481bba";
    public static final String AUTH_TOKEN = "da347215ba823267a67e994fcf2b15bb";

    public static void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(num),new PhoneNumber("+17622474309"), msg).create();

        System.out.println(message.getSid());

    }
}
