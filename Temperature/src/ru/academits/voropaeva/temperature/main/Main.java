package ru.academits.voropaeva.temperature.main;

import ru.academits.voropaeva.temperature.model.CelsiusScale;
import ru.academits.voropaeva.temperature.view.SwingView;

public class Main {
    public static void main(String[] args) {
        CelsiusScale model = new CelsiusScale();
        SwingView view = new SwingView(model);
        view.initialize();
    }
}