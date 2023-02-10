package ru.academits.voropaeva.view.temperature;

import ru.academits.voropaeva.model.temperature.*;

import javax.swing.*;
import java.awt.*;

public class View {
    TemperatureConverterInterface model;
    JFrame frame;
    JPanel panel;
    JLabel labelOne;
    JTextField textFieldInput;
    ButtonGroup buttonGroupFrom;
    JRadioButton celsiusButtonFrom;
    JRadioButton fahrenheitButtonFrom;
    JRadioButton kelvinButtonFrom;
    JLabel labelTwo;
    ButtonGroup buttonGroupIn;
    JRadioButton celsiusButtonIn;
    JRadioButton fahrenheitButtonIn;
    JRadioButton kelvinButtonIn;
    JTextField textFieldOutput;
    JButton calculateButton;

    public View(TemperatureConverterInterface model) {
        this.model = model;
    }

    public void initialize() {
        frame = new JFrame("Конвертер температур");
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 280));
        frame.setVisible(true);

        labelOne = new JLabel("Конвертировать из:");
        textFieldInput = new JTextField(10);
        buttonGroupFrom = new ButtonGroup();
        celsiusButtonFrom = new JRadioButton("°C", true);
        fahrenheitButtonFrom = new JRadioButton("°F");
        kelvinButtonFrom = new JRadioButton("К");
        labelTwo = new JLabel("Конвертировать в:");
        buttonGroupIn = new ButtonGroup();
        celsiusButtonIn = new JRadioButton("°C", true);
        fahrenheitButtonIn = new JRadioButton("°F");
        kelvinButtonIn = new JRadioButton("К");
        textFieldOutput = new JTextField(10);
        calculateButton = new JButton("Рассчитать");

        GridBagLayout gridBagLayout = new GridBagLayout();
        panel = new JPanel(gridBagLayout);

        // СТРОКА 1
        GridBagConstraints gridBagConstraintsLineOne = new GridBagConstraints();
        gridBagConstraintsLineOne.gridx = GridBagConstraints.RELATIVE;
        gridBagConstraintsLineOne.gridy = 0;
        gridBagLayout.setConstraints(labelOne, gridBagConstraintsLineOne);

        // СТРОКА 2
        GridBagConstraints gridBagConstraintsLineTwo = new GridBagConstraints();
        gridBagConstraintsLineTwo.gridx = GridBagConstraints.RELATIVE;
        gridBagConstraintsLineTwo.gridy = 1;
        gridBagConstraintsLineTwo.insets = new Insets(10, 10, 40, 10);

        textFieldInput.setPreferredSize(new Dimension(15, 30));
        textFieldInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));

        buttonGroupFrom.add(celsiusButtonFrom);
        buttonGroupFrom.add(fahrenheitButtonFrom);
        buttonGroupFrom.add(kelvinButtonFrom);

        gridBagLayout.setConstraints(textFieldInput, gridBagConstraintsLineTwo);
        gridBagLayout.setConstraints(celsiusButtonFrom, gridBagConstraintsLineTwo);
        gridBagLayout.setConstraints(fahrenheitButtonFrom, gridBagConstraintsLineTwo);
        gridBagLayout.setConstraints(kelvinButtonFrom, gridBagConstraintsLineTwo);

        //СТРОКА 3
        GridBagConstraints gridBagConstraintsLineThree = new GridBagConstraints();
        gridBagConstraintsLineThree.gridx = GridBagConstraints.RELATIVE;
        gridBagConstraintsLineThree.gridy = 2;

        gridBagLayout.setConstraints(labelTwo, gridBagConstraintsLineThree);

        //СТРОКА 4
        GridBagConstraints gridBagConstraintsLineFour = new GridBagConstraints();
        gridBagConstraintsLineFour.insets = new Insets(10, 0, 0, 0);
        gridBagConstraintsLineFour.gridx = GridBagConstraints.RELATIVE;
        gridBagConstraintsLineFour.gridy = 3;

        buttonGroupIn.add(celsiusButtonIn);
        buttonGroupIn.add(fahrenheitButtonIn);
        buttonGroupIn.add(kelvinButtonIn);

        textFieldOutput.setPreferredSize(new Dimension(15, 30));
        textFieldOutput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
        textFieldOutput.setEditable(false);  // запретили редактирование

        gridBagLayout.setConstraints(textFieldOutput, gridBagConstraintsLineFour);
        gridBagLayout.setConstraints(celsiusButtonIn, gridBagConstraintsLineFour);
        gridBagLayout.setConstraints(fahrenheitButtonIn, gridBagConstraintsLineFour);
        gridBagLayout.setConstraints(kelvinButtonIn, gridBagConstraintsLineFour);

        // СТРОКА 5
        GridBagConstraints gridBagConstraintsFive = new GridBagConstraints();
        gridBagConstraintsFive.gridy = 4;
        gridBagConstraintsFive.gridwidth = 4;
        gridBagConstraintsFive.insets = new Insets(30, 0, 0, 0);

        gridBagLayout.setConstraints(calculateButton, gridBagConstraintsFive);

        panel.add(labelOne);
        panel.add(textFieldInput);
        panel.add(celsiusButtonFrom);
        panel.add(fahrenheitButtonFrom);
        panel.add(kelvinButtonFrom);
        panel.add(labelTwo);
        panel.add(textFieldOutput);
        panel.add(celsiusButtonIn);
        panel.add(fahrenheitButtonIn);
        panel.add(kelvinButtonIn);
        panel.add(calculateButton);
        frame.add(panel);

        calculateButton.addActionListener(e -> {
            if (celsiusButtonFrom.isSelected()) {
                model.setScaleNameFrom(model.getCelsius());
            } else if (fahrenheitButtonFrom.isSelected()) {
                model.setScaleNameFrom(model.getFahrenheit());
            } else {
                model.setScaleNameFrom(model.getKelvin());
            }

            try {
                model.setTemperature(Double.parseDouble(textFieldInput.getText()));
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(frame, "Введите число!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double result;

            if (celsiusButtonIn.isSelected()) {
                result = model.convert(model.getCelsius());
            } else if (fahrenheitButtonIn.isSelected()) {
                result = model.convert(model.getFahrenheit());
            } else {
                result = model.convert(model.getKelvin());
            }

            textFieldOutput.setText(String.format("%.3f", result));
        });
    }
}