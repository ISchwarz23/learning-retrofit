# learning-retrofit
A small project to learn how to use the retrofit library. As example an API wrapper for openweathermap.org in Java was created.

## Functions
The `OpenWeatherMap` class provides access to the 'current weather' and 'weather forecast' feature providde by openweathermap.org.
The API wrapper was designed for optimal use with Lambdas and method-references.

### Initialization
To set up the `OpenWeatherMap` API wrapper you need to give your API-Key.
```java
OpenWeatherMap openWeatherMap = new OpenWeatherMap("your-api-key");
```

### Current Weather
There are two ways to get the current weather: synchronous and asynchronous.
```java
// sync
CurrentWeatherInfo currentWeather = openWeatherMap.getCurrentWeather("London,GB");
// async (ignoring errors)
openWeatherMap.getCurrentWeather("London,GB", System.out::println);
// async (printing errors)
openWeatherMap.getCurrentWeather("London,GB", System.out::println, System.err::println);
```

### WeatherForecast
There are two ways to get the weather forecast: synchronous and asynchronous.
```java
// sync
ForecastWeatherInfo weatherForecast = openWeatherMap.getForecastWeather("London,GB");
// async (ignoring errors)
openWeatherMap.getForecastWeather("London,GB", System.out::println);
// async (printing errors)
openWeatherMap.getForecastWeather("London,GB", System.out::println, System.err::println);
```

## Data Structure
Unfortunately the data structure of the results for the current weather and the weather forecast are not given the same way by openweathermap.org. Therefor the data structure was harmonized. The advantage is, that the API is now more intuitive to use.

```
CurrentWeatherInfo
  -> city: City
  -> weatherInfo: WeatherInfo

ForecastWeatherInfo
  -> city: City
  -> weatherInfos: List<WeatherInfo>

City
  -> id: int
  -> name: String
  -> coordinates: Coordinates
  -> country: String
  -> population: int

Coordinates
  -> latitude: double
  -> longitude: double

WeatherInfo
  -> dateTime: ZonedDateTime
  -> temperatureInfo: TemperatureInfo
  -> rainInfo: PercipitationInfo
  -> snowInfo: PercipitationInfo
  -> cloudInfo: CloudInfo
  -> metaInfo: List<MetaInfo>

TemperatureInfo
  -> temperature: Temperature
  -> minimalTemperature: Temperature
  -> maximalTemperature: Temperature

Temperature
  -> temperature: double
  -> unit: TemperatureUnit

TemperatureUnit
   -> KELVIN
   -> CELSIUS
   -> FAHRENHEIT

PercipitationInfo
   -> value: double

CloudInfo
   -> percentage: double

MetaInfo
   -> id: int
   -> name: String
   -> description: String
   -> icon: String
```

## Specialities
As the focus of this API wrapper was on providing the temperature. It has a very comfortable way of getting and transforming it to your requested unit.
Key component for this is the `TemperatureUnit` enum, which was inspired by the `TimeUnit` enum. This enum is able to transform temperatures to different units.
```java
double temperatureInCelsius = TemperatureUnit.KELVIN.toCelsius(temperatureInKelvin);
double temperatureInFahrenheit = TemperatureUnit.CELSIUS.toFahrenheit(temperatureInCelsius);
```
You are able to achieve this more easy by using the `Temperature` class.
```java
Temperature temperature = new Temperature(290.3, TemperatureUnit.KELVIN);
double temperatureInCelsius = temperature.inCelsius();
double temperatureInFahrenheit = temperature.inFahrenheit();
```
And this is exactly how the temperature is given by the API wrapper. Just get the Temperature in the unit you need.