package com.ing.application;

import com.ing.bean.ATM;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fortesp on 07/10/2016.
 */
@Controller
@SpringBootApplication
public class Application {

    private static final String ING_API_URL = "https://www.ing.nl/api/locator/atms/";
    private static Log log = LogFactory.getLog(Application.class);

    public static void main(String[] args) {

            SpringApplication.run(Application.class, args);
    }

    public static List<ATM> getThirdPartyATMList() {

        List<ATM> list = null;

        try {
            String responseBody = getMessage();

            list = Arrays.asList(new ObjectMapper().readValue(responseBody.split("\\n")[1], ATM[].class));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static String getMessage() throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("ACCEPT", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        log.info("Fetching data....");

        ResponseEntity<String> response = restTemplate.exchange(ING_API_URL, HttpMethod.GET, request, String.class);
        String responseBody = response.getBody();

        log.info("Done.");

        return responseBody;
    }

    @RequestMapping("/")
    public String processAction(){

        return "index";
    }

}
