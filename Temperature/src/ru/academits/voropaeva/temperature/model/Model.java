package ru.academits.voropaeva.temperature.model;

import java.util.List;

public interface Model {
    List<Scale> scales();

    double convert(Scale scaleFrom, Scale scaleTo, double temperature);
}
