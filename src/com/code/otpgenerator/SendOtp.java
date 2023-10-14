package com.code.otpgenerator;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class SendOtp {
    public static final String sender_id = "FSTSMS";
    public static final String language = "english";
    public static final String route = "otp";
    public static final String API_KEY = "cKMqOBnVepw6E1WYfaPQimZ4F2kRSgs3GNDUjz7hIvJX8LTAHusPMwl7ITL8Gq6UxNBvkyWYZb1HtiCr";

    public static void sendOtp(String otp, String number) {

        try {
            //	msg = URLEncoder.encode(msg, "UTF-8");
            String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization="+API_KEY+"&variables_values="+otp+"&route=otp&numbers="+number;
            URL url = new URL(myUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("cache-control", "no-cache");

            System.out.println("Wait............................................");
            int responseCode = con.getResponseCode();
            String resmsg = con.getResponseMessage();
            System.out.println("Response Code : "+ responseCode);
            System.out.println("Response Code : "+ resmsg);
            StringBuffer response = new StringBuffer();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            while(true) {
                String line = br.readLine();
                if(line==null) {
                    break;
                }
                response.append(line);
            }
            System.out.println(response);

        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

        String otp = GenerateOtp.generateOtp();
        String number = "9651399078";
        sendOtp(otp, number);
    }
}
