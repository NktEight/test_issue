package org.example;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static float getMedianOfNumber(int[] arrayMedian) {
        Arrays.sort(arrayMedian);
        if (arrayMedian.length % 2 == 0) {
            return ((arrayMedian[arrayMedian.length / 2] + arrayMedian[arrayMedian.length / 2 - 1]) / 2f);
        }

        return arrayMedian[arrayMedian.length / 2];
    }

    public static void main(String[] args) throws IOException, ParseException {

        ObjectMapper om = new ObjectMapper();

//        File file = new File("src/main/resources/tickets.json");
        File file = new File("tickets.json");
        JsonFactory jfactory = new JsonFactory();
        JsonParser jParser = jfactory.createParser(file);

        List<FlightDto> flightDtos = null;
        while (jParser.nextToken() != JsonToken.END_OBJECT) {
            if ("tickets".equals(jParser.getCurrentName()) && JsonToken.START_ARRAY == jParser.getCurrentToken()) {
                flightDtos = om.readValue(jParser, om.getTypeFactory().constructCollectionType(List.class, FlightDto.class));
            }

        }
        DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm");
        HashMap<String, Long> carrierMinTime = new HashMap<>();
        List<Integer> priceList = new ArrayList<>();
        for (FlightDto flightDto : flightDtos) {
            if ((flightDto.origin.equals("VVO") && flightDto.destination.equals("TLV")) || (flightDto.origin.equals("TLV") && flightDto.destination.equals("VVO"))) {
                priceList.add(flightDto.price);
                Date arrivalDate = df.parse(flightDto.arrival_date + " " + flightDto.arrival_time);
                Date departureDate = df.parse(flightDto.departure_date + " " + flightDto.departure_time);
                long minTime = arrivalDate.getTime() - departureDate.getTime();
                if (carrierMinTime.get(flightDto.carrier) == null || carrierMinTime.get(flightDto.carrier) > minTime) {
                    carrierMinTime.put(flightDto.carrier, minTime);
                }

            }
        }
        float floatMedian = getMedianOfNumber(priceList.stream().mapToInt(Integer::intValue).toArray());
        float floatAverage = priceList.stream().reduce(0, Integer::sum).floatValue() / priceList.size();
        float averageMedianDiff = floatAverage - floatMedian;
        for (Map.Entry<String, Long> entrySet : carrierMinTime.entrySet()) {
            System.out.println(entrySet.getKey() + " : " + entrySet.getValue() / 60 / 1000);

        }
        System.out.println(carrierMinTime);
        System.out.println(averageMedianDiff);

    }
}