package ru.academits.voropaeva.model.converter;

import javax.swing.*;
import java.util.Enumeration;

public class ConvertTemperature {
    public double getConversion(String temperature, Enumeration<AbstractButton> buttonsFrom, Enumeration<AbstractButton> buttonsIn) {
        String degreesNameFrom = "";
        String degreesNameIn = "";
        double temperatureDouble = getDigit(temperature);

        while (buttonsFrom.hasMoreElements()) {
            AbstractButton current = buttonsFrom.nextElement();

            if (current.isSelected()) {
                degreesNameFrom = current.getText();
                break;
            }
        }

        while (buttonsIn.hasMoreElements()) {
            AbstractButton current = buttonsIn.nextElement();

            if (current.isSelected()) {
                degreesNameIn = current.getText();
                break;
            }
        }

        if (degreesNameFrom.equals(degreesNameIn)) {
            return temperatureDouble;
        } else if (degreesNameFrom.equals("°C") && degreesNameIn.equals("°F")) {
            return temperatureDouble * 1.8 + 32;
        } else if (degreesNameFrom.equals("°F") && degreesNameIn.equals("°C")) {
            return (temperatureDouble - 32) / 1.8;
        } else if (degreesNameFrom.equals("°C") && degreesNameIn.equals("°К")) {
            return temperatureDouble + 237.15;
        } else if (degreesNameFrom.equals("°К") && degreesNameIn.equals("°C")) {
            return temperatureDouble - 273.15;
        } else if (degreesNameFrom.equals("°F") && degreesNameIn.equals("°К")) {
            return (temperatureDouble - 32) * 5 / 9 + 273.15;
        } else {
            return (temperatureDouble - 273.15) * 9 / 5 + 32;
        }
    }

    private double getDigit(String line) {
        try {
            return Double.parseDouble(line);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
}