package ru.academits.voropaeva.temperature.main;

import ru.academits.voropaeva.temperature.model.*;
import ru.academits.voropaeva.temperature.view.SwingView;
import ru.academits.voropaeva.temperature.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Scale> scales = new ArrayList<>(Arrays.asList(
                new CelsiusScale(),
                new KelvinScale(),
                new FahrenheitScale())
        );

        Model model = new Converter(scales);
        View view = new SwingView(model);

        view.initialize();
    }
}