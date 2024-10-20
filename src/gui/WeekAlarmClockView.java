package gui;

import javax.swing.*;

import time.TimeType;

import java.awt.*;

public class WeekAlarmClockView extends JFrame {

    private JLabel digitalClockLabel;
    private JTextArea alarmArea;
    private JTextField hoursField, minutesField;
    private JButton addAlarmButton, removeAlarmButton;
    private int currentHour = 0;
    private int currentMinute = 0;
    private int currentSecond = 0;

    public WeekAlarmClockView() {
        setTitle("Week Alarm Clock");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create tabbed pane for Digital Clock, Analog Clock, and Alarms
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Digital Clock", createDigitalClockPanel());
        tabbedPane.addTab("Analog Clock", createAnalogClockPanel());
        tabbedPane.addTab("Alarm", createAlarmPanel());

        add(tabbedPane);
    }

    // Panel for the digital clock display
    private JPanel createDigitalClockPanel() {
        JPanel panel = new JPanel();
        digitalClockLabel = new JLabel("00:00:00");
        digitalClockLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        panel.add(digitalClockLabel);
        return panel;
    }

    private JPanel createAnalogClockPanel() {
    	JPanel analogClockPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Clock center and radius
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int radius = Math.min(centerX, centerY) - 20; // Leave some padding

                // Draw clock face (circle)
                g.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
                
                // Draw hour marks
                for (int i = 0; i < 12; i++) {
                    double angle = Math.toRadians((i * 360 / 12) - 90);  // 12 marks, start at 12 o'clock (-90 degrees)
                    int x1 = (int) (centerX + radius * 0.9 * Math.cos(angle));
                    int y1 = (int) (centerY + radius * 0.9 * Math.sin(angle));
                    int x2 = (int) (centerX + radius * Math.cos(angle));
                    int y2 = (int) (centerY + radius * Math.sin(angle));
                    g.drawLine(x1, y1, x2, y2);
                }

//                // Get the current time from the clock
//                TimeType currentTime = clock.getTime();
                int hours = currentHour % 12;  // Convert to 12-hour format
                int minutes = currentMinute;
                int seconds = currentSecond;

                // Draw hour hand
                double hourAngle = Math.toRadians((hours * 360 / 12) - 90 + (minutes * 30 / 60));  // Adjust for minutes
                drawHand(g, centerX, centerY, hourAngle, (int) (radius * 0.5), 6); // Shorter and thicker

                // Draw minute hand
                double minuteAngle = Math.toRadians((minutes * 360 / 60) - 90);
                drawHand(g, centerX, centerY, minuteAngle, (int) (radius * 0.75), 4); // Longer, thinner than hour hand

                // Draw second hand
                double secondAngle = Math.toRadians((seconds * 360 / 60) - 90);
                drawHand(g, centerX, centerY, secondAngle, (int) (radius * 0.85), 2, Color.RED); // Longest, thinnest, red
            }

            // Utility method to draw the clock hands
            private void drawHand(Graphics g, int x, int y, double angle, int length, int thickness) {
                drawHand(g, x, y, angle, length, thickness, Color.BLACK);
            }

            // Overloaded method to allow custom color for hands
            private void drawHand(Graphics g, int x, int y, double angle, int length, int thickness, Color color) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(color);
                g2.setStroke(new BasicStroke(thickness));
                int xEnd = (int) (x + length * Math.cos(angle));
                int yEnd = (int) (y + length * Math.sin(angle));
                g2.drawLine(x, y, xEnd, yEnd);
                g2.dispose();
            }
        };
        return analogClockPanel;
    }

    // Panel for managing alarms
    private JPanel createAlarmPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Input fields for hours and minutes
        JPanel inputPanel = new JPanel(new FlowLayout());
        hoursField = new JTextField(2);
        minutesField = new JTextField(2);
        inputPanel.add(new JLabel("Hour:"));
        inputPanel.add(hoursField);
        inputPanel.add(new JLabel("Minute:"));
        inputPanel.add(minutesField);

        // Buttons to add/remove alarms
        addAlarmButton = new JButton("Add Alarm");
        removeAlarmButton = new JButton("Remove All Alarms");

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(addAlarmButton);
        buttonsPanel.add(removeAlarmButton);

        // Area to display the current alarms
        alarmArea = new JTextArea(20, 40);
        alarmArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(alarmArea);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Getters for components (for use by the controller)
    public JLabel getDigitalClockLabel() {
        return digitalClockLabel;
    }

    public JTextArea getAlarmArea() {
        return alarmArea;
    }

    public JTextField getHoursField() {
        return hoursField;
    }

    public JTextField getMinutesField() {
        return minutesField;
    }

    public JButton getAddAlarmButton() {
        return addAlarmButton;
    }

    public JButton getRemoveAlarmButton() {
        return removeAlarmButton;
    }

    
    public void updateAnalogClockTime(int hour, int minute, int second) {
    	this.currentHour = hour;
    	this.currentMinute = minute;
    	this.currentSecond = second;
    }
}
