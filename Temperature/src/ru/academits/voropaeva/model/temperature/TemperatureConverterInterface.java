package ru.academits.voropaeva.model.temperature;

public interface TemperatureConverterInterface {
    Kelvin kelvin = new Kelvin();
    Fahrenheit fahrenheit = new Fahrenheit();
    Celsius celsius = new Celsius();

    Kelvin getKelvin();

    Fahrenheit getFahrenheit();

    Celsius getCelsius();

    void setScaleNameFrom(TemperatureScaleInterface temperatureScaleInterface);
    void setTemperature(double temperature);

    double convert(Kelvin kelvin);

    double convert(Fahrenheit fahrenheit);

    double convert(Celsius celsius);
}