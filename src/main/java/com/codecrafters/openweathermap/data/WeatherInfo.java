package com.codecrafters.openweathermap.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Data-Object holding weather information.
 *
 * @author ISchwarz
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {

    @JsonProperty("dt")
    private long date;
    @JsonProperty("main")
    private MainInfo mainInfo;
    @JsonProperty("weather")
    private List<MetaInfo> metaInfo = new ArrayList<>();
    @JsonProperty("rain")
    private PrecipitationInfo rainInfo = new PrecipitationInfo();
    @JsonProperty("snow")
    private PrecipitationInfo snowInfo = new PrecipitationInfo();
    @JsonProperty("clouds")
    private CloudInfo cloudInfo = new CloudInfo();

    public long getDate() {
        return date;
    }

    public MainInfo getMainInfo() {
        return mainInfo;
    }

    public PrecipitationInfo getRainInfo() {
        return rainInfo;
    }

    public PrecipitationInfo getSnowInfo() {
        return snowInfo;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "date=" + date +
                ", mainInfo=" + mainInfo +
                ", metaInfo=" + metaInfo +
                ", rainInfo=" + rainInfo +
                ", snowInfo=" + snowInfo +
                ", cloudInfo=" + cloudInfo +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MainInfo {

        @JsonProperty("temp")
        public Temperature temperature;
        @JsonProperty("temp_min")
        public Temperature minimalTemperature;
        @JsonProperty("temp_max")
        public Temperature maximalTemperature;

        public Temperature getTemperature() {
            return temperature;
        }

        public Temperature getMinimalTemperature() {
            return minimalTemperature;
        }

        public Temperature getMaximalTemperature() {
            return maximalTemperature;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temperature=" + temperature +
                    ", minimalTemperature=" + minimalTemperature +
                    ", maximalTemperature=" + maximalTemperature +
                    '}';
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PrecipitationInfo {

        @JsonProperty("3h")
        private double value;

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "PrecipitationInfo{" +
                    "value=" + value +
                    '}';
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MetaInfo {

        @JsonProperty("all")
        private int id;
        @JsonProperty("main")
        private String name;
        @JsonProperty("description")
        private String description;
        @JsonProperty("icon")
        private String icon;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }

        @Override
        public String toString() {
            return "MetaInfo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CloudInfo {

        @JsonProperty("all")
        private int percentage;

        public int getPercentage() {
            return percentage;
        }

        @Override
        public String toString() {
            return "CloudInfo{" +
                    "percentage=" + percentage +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WindInfo {

        @JsonProperty("speed")
        private double speed;
        @JsonProperty("deg")
        private double direction;

        public double getSpeed() {
            return speed;
        }

        public double getDirection() {
            return direction;
        }

        @Override
        public String toString() {
            return "WindInfo{" +
                    "speed=" + speed +
                    ", direction=" + direction +
                    '}';
        }
    }

}
