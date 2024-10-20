import clock.WeekAlarmClock;
import controller.WeekAlarmClockController;
import gui.WeekAlarmClockView;

public class Main {
    public static void main(String[] args) {
        WeekAlarmClock model = new WeekAlarmClock();
        WeekAlarmClockView view = new WeekAlarmClockView();
        WeekAlarmClockController controller = new WeekAlarmClockController(model, view);

        view.setVisible(true);
    }
}
