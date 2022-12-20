package com.guney.jackson.json.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;

public class Driver {

    public static void main(String[] args) {

        try {
            // create object mapper
            ObjectMapper mapper = new ObjectMapper();

            // read Json file and map/convert to POJO: data/sample-lite.json
            Student theStudent = mapper.readValue(new File("data/sample-full.json"), Student.class);

            // print firstName and lastName
            System.out.println("Student: " + theStudent.getFirstName() + " " + theStudent.getLastName());

            // print address
            Address tempAddress = theStudent.getAddress();
            System.out.println("Address: " + tempAddress.getStreet() + ", " + tempAddress.getCity() + ", " + tempAddress.getState() + ", " + tempAddress.getZip() + ", " + tempAddress.getCountry());

            // print languages
            for (String tempLang : theStudent.getLanguages()) {
                System.out.println(tempLang);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
