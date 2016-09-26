package com.pizza.backend.atb.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizza.backend.atb.users.fbmodel.FbData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean userExists(String userId) {

        User user = userRepository.findByUserEmail(userId);

        return user != null;
    }

    public User getUser(String authToken) throws IOException {

        String userId = isAuthTokenValid(authToken);

        System.out.println("FB USER: " + userId);

        if (userId != null) {

            //login user ...
            User user = userRepository.findByUserEmail(userId);

            if (user == null) {
                // add user
                User newUser = new User();
                newUser.setUserName(userId);
                newUser.setUserEmail(userId);

                userRepository.save(newUser);

                return newUser;
            } else {
                return user;
            }
        }

        return null;
    }

    //DEV
//    private final String APP_ID = "1309813052376049";
//    private final String APP_SECRECT = "55b333bcb85c38d360e47b648d315ac0";

    //PRODUCTION
    private final String APP_ID = "1022551641176891";
    private final String APP_SECRECT = "17e5382d1995451a526cfc1ce9f9d9a8";

    public String isAuthTokenValid(String authToken) throws IOException {

        try {
            String url = "https://graph.facebook.com/debug_token?input_token=" + authToken + "&access_token=" + APP_ID + "|" + APP_SECRECT;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());

            ObjectMapper objectMapper = new ObjectMapper();
            FbData fbData = objectMapper.readValue(response.toString(), FbData.class);

            if (fbData.data.is_valid && APP_ID.equalsIgnoreCase(fbData.data.app_id) && !fbData.data.user_id.isEmpty()) {
                return fbData.data.user_id;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        throw new RuntimeException("Not Authorized");
    }
}
