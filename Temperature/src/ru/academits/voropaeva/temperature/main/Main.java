package ru.academits.voropaeva.temperature.main;

import ru.academits.voropaeva.temperature.model.CelsiusScale;
import ru.academits.voropaeva.temperature.model.TemperatureScale;
import ru.academits.voropaeva.temperature.veiw.SwingView;
import ru.academits.voropaeva.temperature.veiw.View;

public class Main {
    public static void main(String[] args) {
        TemperatureScale model = new CelsiusScale();
        View view = new SwingView(model);

        view.initialize();
    }
}
