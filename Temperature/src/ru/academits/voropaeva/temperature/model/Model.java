package ru.academits.voropaeva.temperature.model;

import java.util.ArrayList;

public interface Model {
    ArrayList<Scale> getScales();

    double convert(Scale scaleFrom, Scale scaleTo, double temperature);
}
