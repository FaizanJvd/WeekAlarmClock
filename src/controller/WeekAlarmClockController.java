package controller;
import alarm.*;
import clock.*;
import counter.*;
import javax.swing.*;
import gui.WeekAlarmClockView;
import time.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import clock.WeekAlarmClock;

public class WeekAlarmClockController {

    private WeekAlarmClock clock;
    private WeekAlarmClockView view;

    public WeekAlarmClockController(WeekAlarmClock clock, WeekAlarmClockView view) {
        this.clock = clock;
        this.view = view;

        // Initialize view with current time
        updateDigitalClock();
        
        // Start clock ticking every second
        Timer clockTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clock.tickTack();
                updateDigitalClock();
                TimeType currentTime = clock.getTime();
                view.updateAnalogClockTime(currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
                view.repaint();
            }
        });
        clockTimer.start();

        // Set up add alarm button
        view.getAddAlarmButton().addActionListener(e -> addAlarm());

        // Set up remove alarms button
        view.getRemoveAlarmButton().addActionListener(e -> removeAlarms());
    }

    // Update digital clock display
    private void updateDigitalClock() {
        view.getDigitalClockLabel().setText(clock.getTime().toString());
    }

    // Add a new alarm
    private void addAlarm() {
        try {
            int hour = Integer.parseInt(view.getHoursField().getText());
            int minute = Integer.parseInt(view.getMinutesField().getText());
            AlarmType alarm = new Alarm(new Time(hour, minute, 0));
            clock.addAlarm(alarm);
            updateAlarmDisplay();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Invalid time format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Remove all alarms
    private void removeAlarms() {
        clock.removeAllAlarms();
        updateAlarmDisplay();
    }
 // Update the alarm display area
    private void updateAlarmDisplay() {
        view.getAlarmArea().setText("");
        if (clock.getAlarms().isEmpty()) {
            view.getAlarmArea().append("No alarms set.\n");
        } else {
            for (AlarmType alarm : clock.getAlarms()) {
                view.getAlarmArea().append("Alarm set for: " + alarm.getTime() + "\n");
            }
        }
    }
}