package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


import java.util.Date;

@Data
@JsonPropertyOrder(alphabetic = true)
public class FlightDto {
    @JsonProperty("origin")
    public String origin;
    @JsonProperty("origin_name")
    String origin_name;
    @JsonProperty("destination")
    String destination;
    @JsonProperty("destination_name")
    String destination_name;
    @JsonProperty("departure_date")
    String departure_date;
    @JsonProperty("departure_time")
    String departure_time;
    @JsonProperty("arrival_date")
    String arrival_date;
    @JsonProperty("arrival_time")
    String arrival_time;
    @JsonProperty("carrier")
    String carrier;
    @JsonProperty("stops")
    Integer stops;
    @JsonProperty("price")
    Integer price;
    @JsonCreator

    public FlightDto( @JsonProperty("origin") String origin,
                      @JsonProperty("origin_name") String origin_name,
                      @JsonProperty("destination") String destination,
                      @JsonProperty("destination_name") String destination_name,
                      @JsonProperty("departure_date") String departure_date,
                      @JsonProperty("departure_time") String departure_time,
                      @JsonProperty("arrival_date") String arrival_date,
                      @JsonProperty("arrival_time") String arrival_time,
                      @JsonProperty("carrier") String carrier,
                      @JsonProperty("stops") Integer stops,
                      @JsonProperty("price") Integer price) {
        this.origin = origin;
        this.origin_name = origin_name;
        this.destination = destination;
        this.destination_name = destination_name;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }
}
