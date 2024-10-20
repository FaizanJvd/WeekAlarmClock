package gui;

import java.util.*;
import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import clock.*;
import alarm.*;
import counter.*;
import time.*;

public class WeekAlarmClockGUI extends JFrame
{
	
	private int width = 800;
	private int height = 800;
	private WeekAlarmClock clock;
	private JLabel digitalClockLabel;
	private JTextArea alarmArea;
	
	public WeekAlarmClockGUI()
	{
		setTitle("MYCLOCK Yeah");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		clock = new WeekAlarmClock();
		
		// Adding tabs
		JTabbedPane tabbedPane = new JTabbedPane();
	    
		tabbedPane.addTab("Alarm", alarmPanel() );
	    
	    tabbedPane.addTab("Digital-Clock", digitalClockPanel());
	    tabbedPane.addTab("Analog-Clock", createAnalogClockPanel());
	    // added tabs inside main panel (JFrame).
	    add(tabbedPane);
	    
	    
	    clock.setTime(new Time(0,0,0));
	    
	    // takes milli seconds, function
	    Timer settingTimer = new Timer(1000, (e) ->
	    {
	    	
	    	clock.tickTack();
	    	digitalClockLabel.setText(clock.getTime().toString());
	    	 repaint();
	    	
	    });
	    
	    settingTimer.start();
	    
	    
	}
	private JPanel createAnalogClockPanel() {
        JPanel panel = new JPanel() {
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

                // Get the current time from the clock
                TimeType currentTime = clock.getTime();
                int hours = currentTime.getHour() % 12;  // Convert to 12-hour format
                int minutes = currentTime.getMinute();
                int seconds = currentTime.getSecond();

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
        return panel;
    }
	private JPanel alarmPanel() 
	{
		//alarm panel == main panel
		JPanel alarmPanel = new JPanel(new BorderLayout());
		
		// display inputs for h and m, flowLayout access things
		JPanel inputPanel = new JPanel(new FlowLayout());
		
		JTextField hoursField = new JTextField(2);
		JTextField minutesField = new JTextField(2);
		
		JLabel hoursLabel = new JLabel("Hours");
		JLabel minutesLabel = new JLabel("Minutes");
		
		inputPanel.add(hoursLabel);
		inputPanel.add(hoursField);
		
		inputPanel.add(minutesLabel);
		
		
		inputPanel.add(minutesField);
		
		//First add hours and minutes inside panel
		
		
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		JButton addAlarmButton = new JButton("add Alarm");
		JButton removeAlarmButton = new JButton("remove alarm");
		
		JPanel addedAlarmsPanel = new JPanel();
		alarmArea = new JTextArea(20,30);
		alarmArea.setEditable(false);
		updateAlramArea();
		addedAlarmsPanel.add(alarmArea);
		addAlarmButton.addActionListener( (e) -> 
		{
			int hour = Integer.parseInt(hoursField.getText());
			int minutes = Integer.parseInt(minutesField.getText());
			
			Time settingTheTime = new Time(hour, minutes, 0); 
			Alarm myAlarm = new Alarm(settingTheTime);
			clock.addAlarm(myAlarm);
			updateAlramArea();
		});
		
		removeAlarmButton.addActionListener( (e) -> 
		{
			clock.removeAllAlarms();
		});
		
		
		buttonsPanel.add(addAlarmButton);
		buttonsPanel.add(removeAlarmButton);
		alarmPanel.add(inputPanel, BorderLayout.NORTH);
		alarmPanel.add(addedAlarmsPanel, BorderLayout.CENTER);
		alarmPanel.add(buttonsPanel, BorderLayout.SOUTH);		
		return alarmPanel;
	}
	private void updateAlramArea() {
		Collection<AlarmType> alarms = clock.getAlarms();
        alarmArea.setText("");
        if (alarms.isEmpty()) {
            alarmArea.append("No alarms set.\n");
        } else {
            for (AlarmType alarm : alarms) {
                alarmArea.append("Alarm at " + alarm.getTime().toString() + "\n");
            }
        }
	}
	private JPanel digitalClockPanel()
	{
		JPanel digitalClockPanel = new JPanel();
		digitalClockLabel = new JLabel("00:00:00");
		digitalClockLabel.setFont(new Font("Arial", Font.PLAIN, 48));
		digitalClockPanel.add(digitalClockLabel);
		
		return digitalClockPanel;
		
	}
	
}
