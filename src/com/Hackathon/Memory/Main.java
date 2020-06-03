package com.Hackathon.Memory;

import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class Main {
    public static void main(String args[]) {
        try{
            JSONObject json = new JSONObject();
            JSONObject values = new JSONObject();
            double maxMemory = Double.MIN_VALUE, avgMemory = 0.0, total = 0.0;

            BufferedReader br = new BufferedReader(new FileReader(new File("./Memory.txt")));

            String s;
            int count=0;
            boolean flag = false;
            while((s = br.readLine()) != null) {
                if(!flag){
                    flag = !flag;
                    continue;
                }
                String[] arr = split(s);
                double current = Double.parseDouble (  String.format (  "%.2f", Double.parseDouble ( arr[1] )));
                if(current > maxMemory ) maxMemory = current;
                total+=current;
                values.put ( (++count)+"s",arr[1] );
                flag = !flag;
            }
            json.put("values",values);
            json.put ( "MaxMemory(MB)", ( maxMemory ) );
            json.put ( "AverageMemory(MB)", (maxMemory/count)  );
            json.put("Usecasename","HomePage");
            System.out.println(json);
            writeIntoFile ( json.toString () );

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] split(String s){
        ArrayList<String> values = new ArrayList<String>();
        String temp = "";
        for(char c:s.toCharArray()) {
            if(c==' ' && temp.length()>0) {
                values.add(temp);
//                System.out.println(temp);
                temp = "";
            }
            if(c!=' ') temp += c;
        }
        String[] result = new String[values.size()];
        for(int i=0; i<values.size(); i++) {
            result[i] = values.get(i);
        }
        return result;
    }

    public static void writeIntoFile(String s) {
        try {
            FileWriter writer = new FileWriter("CPU.json");
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
