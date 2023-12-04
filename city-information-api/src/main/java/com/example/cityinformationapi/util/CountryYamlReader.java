package com.example.cityinformationapi.util;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

@Component
public class CountryYamlReader {

    public Map<String, Object> readCountries() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("countries.yml")) {
            if(inputStream != null) {
                return yaml.load(inputStream);
            } else {
                throw new FileNotFoundException("The countries.yml file was not found.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected Exception: " + e.getMessage());
        }
        return null;
    }

}
