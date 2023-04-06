package ru.academits.voropaeva.temperature.main;

import ru.academits.voropaeva.temperature.model.*;
import ru.academits.voropaeva.temperature.view.SwingView;
import ru.academits.voropaeva.temperature.view.View;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Model model = new Converter(Arrays.asList(
                new CelsiusScale(),
                new KelvinScale(),
                new FahrenheitScale()));

        View view = new SwingView(model);

        view.initialize();
    }
}