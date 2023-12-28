package com.example.demo2608.util;

import com.example.demo2608.model.dto.FormSearchDTO;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchError {
    public static int hasErrorPeopleLimit(FormSearchDTO formSearchDTO){

            int totalAdults = Integer.parseInt(formSearchDTO.getTotal_adults());
            int totalChildren = Integer.parseInt(formSearchDTO.getTotal_children());
//            2 children == 1 adult
            totalChildren=totalChildren/2 + totalChildren%2;
//          max_people in room is maximum 4:
            return totalAdults + totalChildren;
    }

    public static int totalDateBooking(HttpSession session) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date check_inDate=format.parse((String) session.getAttribute("check_in"));

        Date check_outDate=format.parse((String) session.getAttribute("check_out"));

      return (int) Math.abs(check_outDate.getTime()-check_inDate.getTime())/(1000*60*60*24);
    }
}
