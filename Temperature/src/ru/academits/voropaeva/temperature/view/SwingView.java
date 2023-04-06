package ru.academits.voropaeva.temperature.view;

import ru.academits.voropaeva.temperature.model.Model;
import ru.academits.voropaeva.temperature.model.Scale;

import javax.swing.*;
import java.awt.*;

public class SwingView implements View {
    private final Model model;
    private JFrame frame;
    private JTextField textFieldInput;
    private JTextField textFieldOutput;

    public SwingView(Model model) {
        this.model = model;
    }

    @Override
    public void initialize() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Конвертер температур");
            frame.setSize(500, 350);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(400, 280));
            frame.setVisible(true);

            Scale[] scales = new Scale[model.scales().size()];
            model.scales().toArray(scales);

            JLabel labelConvertFrom = new JLabel("Конвертировать из:");
            textFieldInput = new JTextField(10);
            JComboBox<Scale> comboBoxFrom = new JComboBox<>(scales);

            JLabel labelConvertTo = new JLabel("Конвертировать в:");
            textFieldOutput = new JTextField(10);
            JComboBox<Scale> comboBoxTo = new JComboBox<>(scales);

            JButton calculateButton = new JButton("Рассчитать");

            GridBagLayout gridBagLayout = new GridBagLayout();
            JPanel panel = new JPanel(gridBagLayout);

            // СТРОКА 1
            GridBagConstraints gridBagConstraintsLine1 = new GridBagConstraints();
            gridBagConstraintsLine1.gridx = GridBagConstraints.RELATIVE;
            gridBagConstraintsLine1.gridy = 0;
            gridBagLayout.setConstraints(labelConvertFrom, gridBagConstraintsLine1);

            // СТРОКА 2
            GridBagConstraints gridBagConstraintsLine2 = new GridBagConstraints();
            gridBagConstraintsLine2.gridx = GridBagConstraints.RELATIVE;
            gridBagConstraintsLine2.gridy = 1;
            gridBagConstraintsLine2.insets = new Insets(10, 10, 40, 10);

            textFieldInput.setPreferredSize(new Dimension(15, 30));
            textFieldInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));

            gridBagLayout.setConstraints(textFieldInput, gridBagConstraintsLine2);
            gridBagLayout.setConstraints(comboBoxFrom, gridBagConstraintsLine2);

            //СТРОКА 3
            GridBagConstraints gridBagConstraintsLine3 = new GridBagConstraints();
            gridBagConstraintsLine3.gridx = GridBagConstraints.RELATIVE;
            gridBagConstraintsLine3.gridy = 2;

            gridBagLayout.setConstraints(labelConvertTo, gridBagConstraintsLine3);

            //СТРОКА 4
            GridBagConstraints gridBagConstraintsLine4 = new GridBagConstraints();
            gridBagConstraintsLine4.insets = new Insets(10, 0, 0, 0);
            gridBagConstraintsLine4.gridx = GridBagConstraints.RELATIVE;
            gridBagConstraintsLine4.gridy = 3;

            textFieldOutput.setPreferredSize(new Dimension(15, 30));
            textFieldOutput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
            textFieldOutput.setEditable(false);  // запретили редактирование

            gridBagLayout.setConstraints(textFieldOutput, gridBagConstraintsLine4);
            gridBagLayout.setConstraints(comboBoxTo, gridBagConstraintsLine4);

            // СТРОКА 5
            GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.gridy = 4;
            gridBagConstraints5.gridwidth = 4;
            gridBagConstraints5.insets = new Insets(30, 0, 0, 0);

            gridBagLayout.setConstraints(calculateButton, gridBagConstraints5);

            panel.add(labelConvertFrom);
            panel.add(textFieldInput);
            panel.add(comboBoxFrom);
            panel.add(labelConvertTo);
            panel.add(textFieldOutput);
            panel.add(comboBoxTo);
            panel.add(calculateButton);
            frame.add(panel);

            calculateButton.addActionListener(e -> {
                try {
                    textFieldOutput.setText(String.format(
                            "%.3f",
                            model.convert(
                                    (Scale) comboBoxFrom.getSelectedItem(),
                                    (Scale) comboBoxTo.getSelectedItem(),
                                    Double.parseDouble(textFieldInput.getText())
                            )
                    ));
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(frame, "Введите число!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
    }
}