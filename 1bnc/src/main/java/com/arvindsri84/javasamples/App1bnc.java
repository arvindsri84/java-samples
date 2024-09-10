package com.arvindsri84.javasamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class App1bnc {

    record Weather(String location, double weather) {
    }

    private static class WeatherDetails extends ArrayList<Double> {

        public Double mean() {
            return this.stream().collect(Collectors.averagingDouble(x -> x));
        }

        public Double min() {
            return this.stream().mapToDouble(x -> x).min().orElse(0);
        }

        public Double max() {
            return this.stream().mapToDouble(x -> x).max().orElse(0);
        }

        public String toString() {
            return min() + "/" + mean() + "/" + max();
        }

    }

    public static void main(String[] args) {
        var path = Paths.get("src\\main\\resources\\weather_stations.csv");
        try (var lines = Files.lines(path)) {
            var groupedResult =
                    lines.map(App1bnc::map2Weather)
                            .collect(Collectors.groupingBy(Weather::location,
                                    Collectors.mapping(Weather::weather, Collectors.toCollection(WeatherDetails::new))));

            groupedResult.entrySet().forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private static Weather map2Weather(String line) {
        var tokens = line.split(";");
        return new Weather(tokens[0], Double.parseDouble(tokens[1]));
    }
}

