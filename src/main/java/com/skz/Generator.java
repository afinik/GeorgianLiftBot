package com.skz;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;

public class Generator {

    protected static String getCode(){
        String cookie = "";
        Unirest.setTimeouts(0, 0);
        String myCode = "No code. Have a nice day";
        try {
            HttpResponse<String> response = Unirest.post("https://myapi.skyz.ge/public/authenticate")
                    .header("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"102\", \"Google Chrome\";v=\"102\"")
                    .header("sec-ch-ua-mobile", "?0")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36")
                    .header("sec-ch-ua-platform", "\"Windows\"")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("Accept", "*/*")
                    .body("{\"username\":\"195385\",\"password\":\"19001030112\"}")
                    .asString();

            cookie = response.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        if (!cookie.equals("")){
            try {
                HttpResponse<String> response2 = Unirest.post("https://myapi.skyz.ge/private/get-temporary-codes")
                        .header("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"102\", \"Google Chrome\";v=\"102\"")
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .header("sec-ch-ua-mobile", "?0")
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36")
                        .header("sec-ch-ua-platform", "\"Windows\"")
                        .header("Cookie", cookie)
                        .body("{\"page\":1,\"pageSize\":10}")
                        .asString();
                String jsonResponce = response2.getBody();
                if (jsonResponce.equals("{\"count\":0,\"rows\":[]}")){
                    HttpResponse<String> response3 = Unirest.post("https://myapi.skyz.ge/private/create-temporary-codes")
                            .header("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"102\", \"Google Chrome\";v=\"102\"")
                            .header("Accept", "application/json")
                            .header("Content-Type", "application/json")
                            .header("sec-ch-ua-mobile", "?0")
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36")
                            .header("sec-ch-ua-platform", "\"Windows\"")
                            .header("Cookie", cookie)
                            .body("{\"multiUse\":1,\"count\":1}")
                            .asString();
                    HttpResponse<String> response4 = Unirest.post("https://myapi.skyz.ge/private/get-temporary-codes")
                            .header("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"102\", \"Google Chrome\";v=\"102\"")
                            .header("Accept", "application/json")
                            .header("Content-Type", "application/json")
                            .header("sec-ch-ua-mobile", "?0")
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36")
                            .header("sec-ch-ua-platform", "\"Windows\"")
                            .header("Cookie", cookie)
                            .body("{\"page\":1,\"pageSize\":10}")
                            .asString();
                    myCode = response4.getBody();
                }else {
                    myCode = response2.getBody();
                }
                JsonObject jsonObject = new JsonParser().parse(myCode).getAsJsonObject();
                String[] s = new String[jsonObject.get("rows").toString().substring(3, jsonObject.get("rows")
                        .toString().length() - 3).replaceAll("\"","").split(",").length];

                HashMap map = new HashMap<>();
                for (int i = 0; i < jsonObject.get("rows").toString()
                        .substring(3, jsonObject.get("rows").toString().length() - 3).replaceAll("\"","").split(",").length; i++) {
                    s[i] = jsonObject.get("rows").toString().substring(3, jsonObject.get("rows").toString().length() - 3).replaceAll("\"","").split(",")[i];
                    map.put(s[i].split(":")[0],s[i].split(":")[1]);
                }
                myCode = map.get("code").toString();
            } catch (UnirestException e) {
                e.printStackTrace();
            }

        }
        return myCode;
    }

}
