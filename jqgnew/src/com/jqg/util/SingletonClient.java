 package com.jqg.util;
 
 import cn.emay.sdk.client.api.Client;
 import java.util.PropertyResourceBundle;
 import java.util.ResourceBundle;
 
 public class SingletonClient
 {
   private static Client client = null;
 
   public static synchronized Client getClient(String softwareSerialNo, String key)
   {
     if (client == null) {
       try {
         client = new Client(softwareSerialNo, key);
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     return client;
   }
   public static synchronized Client getClient() {
     ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
     if (client == null) {
       try {
         client = new Client(bundle.getString("softwareSerialNo"), bundle.getString("key"));
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     return client;
   }
   public static void main(String[] str) {
     getClient();
   }
 }

