package com.nonobank.apps.utils.variable;

import java.io.IOException;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;

public class CodisTool {
	private static Jedis jedis;

    public static void init(String host,int port){

        jedis = new Jedis(host,port);
    }


    public static boolean isKeyExit(String key){

        return jedis.exists(key);
    }

    public static boolean setValue(String key,String value){
        jedis.set(key,value);
        if(jedis.get(key)!=null&&jedis.get(key).equals(value)){
            return true;
        }else{
            return false;
        }
    }

    public static Set<String> getkey(String key){

        return jedis.keys(key);

    }

    public static String getValue(String key){

        return jedis.get(key);
    }




    public static String getHttpResult(String url) {
        HttpGet request = new HttpGet(url);
        String returnStr = "";
        HttpResponse response = null;
        try {
            response = HttpClients.createDefault().execute(request);
            returnStr = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return returnStr;
        }
    }


    public static String getStr(JSONObject obj,String key){
        return obj.get(key) instanceof JSONObject?obj.getJSONObject(key).toString():obj.getString(key).toString();
    }


    public static String getJssonKey(String jsonStr,String key){
        String returnStr = "";
        JSONObject obj = new JSONObject(jsonStr);
        String[] keySet = key.split(";");
        if(keySet.length==1){

            returnStr = CodisTool.getStr(obj,key);
        }else{

            for(int i=0;i<keySet.length;i++) {

                obj = obj.getJSONObject(keySet[i]);

                if (i == keySet.length - 2) {
                    returnStr =  CodisTool.getStr(obj,keySet[keySet.length - 1]);
                    //obj.getJSONObject(keySet[keySet.length - 1]).toString();
                    break;
                }

            }
        }
        return returnStr;
    }

    public static boolean checkCodisKey(String host,int port,String url,String key){

        String sessionId = CodisTool.getJssonKey(CodisTool.getHttpResult(url),key);
        System.out.println(sessionId);
        CodisTool.init(host,port);
        return CodisTool.isKeyExit(sessionId);

    }

    public static void main(String[] args) throws IOException {

        String url = "http://www.sit.nonobank.com/MicroSiteApi/getSessionId";
        String key = "data;session_id";
        int i=1;
        String host = "";
        int port = 0;
        if( i==0){
            host = "192.168.3.130";
            port = 6379;
        }else{
            host = "192.168.4.53";
            port = 19000;
        }


        String jssonStr = CodisTool.getHttpResult(url);
        String sessionId = CodisTool.getJssonKey(jssonStr,"data;session_id");
        System.out.println(sessionId);
        CodisTool.init(host,port);
        CodisTool.setValue("mykey","mykey");
        boolean f = CodisTool.isKeyExit("mykey");
        boolean f2 = CodisTool.isKeyExit(sessionId+"1212");
        System.out.println(f);
        System.out.println(f2);


        System.out.print(CodisTool.checkCodisKey(host,port,url,key));
        //System.out.println(CodisTool.getkey("*"));
    }

}
