package com.ing.controller;

import com.ing.application.Application;
import com.ing.bean.ATM;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fortesp on 08/10/2016.
 */
@RestController
public class RestATM {

    private static List<ATM> listATM = Application.getThirdPartyATMList();

    @RequestMapping("/atms")
    public List<ATM> process(@RequestParam(value="city", defaultValue="AMSTERDAM") String city) {

        if(!city.isEmpty()) {

            List<ATM> filteredList = new ArrayList<ATM>();

            for (ATM atm : listATM) {
                if (atm.getAddress().getCity().toLowerCase().contains(city.toLowerCase()))
                    filteredList.add(atm);
            }

            return filteredList;

        }

        return listATM;
    }

}
