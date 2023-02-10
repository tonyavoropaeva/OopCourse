package ru.academits.voropaeva.temperature_main;

import ru.academits.voropaeva.model.temperature.TemperatureConverter;
import ru.academits.voropaeva.model.temperature.TemperatureConverterInterface;
import ru.academits.voropaeva.view.temperature.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperatureConverterInterface model = new TemperatureConverter();
            View view = new View(model);
            view.initialize();
        });
    }
}