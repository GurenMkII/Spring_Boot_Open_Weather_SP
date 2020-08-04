package com.example.demo666.service;

import java.util.List;

import com.example.demo666.model.Response;
import com.example.demo666.model.ZipCode;
import com.example.demo666.repository.RequestRepository;
import com.example.demo666.repository.ZipCodeRepository;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {
    @Value("${api_key}")
    private String apiKey;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ZipCodeRepository zipCodeRepository;

    // public void save(ZipCode zipCode){
    //     zipCodeRepository.save(zipCode);
    // }

    public List<ZipCode> getRecentSearches(){
        return zipCodeRepository.findAll();
    }

    public Response getForecast(String zipCode) {

        ZipCode zip = new ZipCode(zipCode);

        String url = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + "&units=imperial&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        try{
            if (zipCodeRepository.findByZip(zipCode) == null){
                zipCodeRepository.save(zip);
            }
            return restTemplate.getForObject(url, Response.class);
        } catch (HttpClientErrorException|ConstraintViolationException ex){
            Response response = new Response();
            response.setName("error");
            return response;
        }
    }

}