package com.pizza.backend.atb.users.fbmodel;

import java.util.List;

public class FbInnerData {

    public FbInnerData() {
    }

    public String app_id;
    public String application;
    public String expires_at;
    public boolean is_valid;
    public List<String> scopes;
    public String user_id;


//		{"app_id":"1022551641176891","application":"atb-pizza-pc","expires_at":1474326000,"is_valid":true,"scopes":["email","public_profile"],"user_id":"10157582987105515"}
}