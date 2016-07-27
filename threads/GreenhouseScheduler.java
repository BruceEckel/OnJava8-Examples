// threads/GreenhouseScheduler.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Rewriting innerclasses/GreenhouseController.java
// to use a ScheduledThreadPoolExecutor
// {Args: 5000}
import java.util.concurrent.*;
import java.util.*;

public class GreenhouseScheduler {
  private volatile boolean light = false;
  private volatile boolean water = false;
  private String thermostat = "Day";
  public synchronized String getThermostat() {
    return thermostat;
  }
  public synchronized void setThermostat(String value) {
    thermostat = value;
  }
  ScheduledThreadPoolExecutor scheduler =
    new ScheduledThreadPoolExecutor(10);
  public void schedule(Runnable event, long delay) {
    scheduler.schedule(event,delay,TimeUnit.MILLISECONDS);
  }
  public void
  repeat(Runnable event, long initialDelay, long period) {
    scheduler.scheduleAtFixedRate(
      event, initialDelay, period, TimeUnit.MILLISECONDS);
  }
  class LightOn implements Runnable {
    @Override
    public void run() {
      // Put hardware control code here to
      // physically turn on the light.
      System.out.println("Turning on lights");
      light = true;
    }
  }
  class LightOff implements Runnable {
    @Override
    public void run() {
      // Put hardware control code here to
      // physically turn off the light.
      System.out.println("Turning off lights");
      light = false;
    }
  }
  class WaterOn implements Runnable {
    @Override
    public void run() {
      // Put hardware control code here.
      System.out.println("Turning greenhouse water on");
      water = true;
    }
  }
  class WaterOff implements Runnable {
    @Override
    public void run() {
      // Put hardware control code here.
      System.out.println("Turning greenhouse water off");
      water = false;
    }
  }
  class ThermostatNight implements Runnable {
    @Override
    public void run() {
      // Put hardware control code here.
      System.out.println("Thermostat to night setting");
      setThermostat("Night");
    }
  }
  class ThermostatDay implements Runnable {
    @Override
    public void run() {
      // Put hardware control code here.
      System.out.println("Thermostat to day setting");
      setThermostat("Day");
    }
  }
  class Bell implements Runnable {
    @Override
    public void run() { System.out.println("Bing!"); }
  }
  class Terminate implements Runnable {
    @Override
    public void run() {
      System.out.println("Terminating");
      scheduler.shutdownNow();
      // Must start a separate task to do this job,
      // since the scheduler was shut down:
      new Thread() {
        @Override
        public void run() {
          for(DataPoint d : data)
            System.out.println(d);
        }
      }.start();
    }
  }
  // New feature: data collection
  static class DataPoint {
    final Calendar time;
    final double temperature;
    final double humidity;
    public DataPoint(Calendar d, double temp, double hum) {
      time = d;
      temperature = temp;
      humidity = hum;
    }
    @Override
    public String toString() {
      return time.getTime() +
        String.format(
          " temperature: %1$.1f humidity: %2$.2f",
          temperature, humidity);
    }
  }
  private Calendar lastTime = Calendar.getInstance();
  { // Adjust date to the half hour
    lastTime.set(Calendar.MINUTE, 30);
    lastTime.set(Calendar.SECOND, 00);
  }
  private double lastTemp = 65.0f;
  private int tempDirection = +1;
  private double lastHumidity = 50.0f;
  private int humidityDirection = +1;
  private SplittableRandom rand = new SplittableRandom(47);
  List<DataPoint> data = Collections.synchronizedList(
    new ArrayList<>());
  class CollectData implements Runnable {
    @Override
    public void run() {
      System.out.println("Collecting data");
      synchronized(GreenhouseScheduler.this) {
        // Pretend the interval is longer than it is:
        lastTime.set(Calendar.MINUTE,
          lastTime.get(Calendar.MINUTE) + 30);
        // One in 5 chances of reversing the direction:
        if(rand.nextInt(5) == 4)
          tempDirection = -tempDirection;
        // Store previous value:
        lastTemp +=
          tempDirection * (1.0f + rand.nextDouble());
        if(rand.nextInt(5) == 4)
          humidityDirection = -humidityDirection;
        lastHumidity +=
          humidityDirection * rand.nextDouble();
        // Calendar must be cloned, otherwise all
        // DataPoints hold references to same lastTime.
        // For basic object like Calendar, clone() is OK.
        data.add(new DataPoint((Calendar)lastTime.clone(),
          lastTemp, lastHumidity));
      }
    }
  }
  public static void main(String[] args) {
    GreenhouseScheduler gh = new GreenhouseScheduler();
    gh.schedule(gh.new Terminate(), 5000);
    // Former "Restart" class not necessary:
    gh.repeat(gh.new Bell(), 0, 1000);
    gh.repeat(gh.new ThermostatNight(), 0, 2000);
    gh.repeat(gh.new LightOn(), 0, 200);
    gh.repeat(gh.new LightOff(), 0, 400);
    gh.repeat(gh.new WaterOn(), 0, 600);
    gh.repeat(gh.new WaterOff(), 0, 800);
    gh.repeat(gh.new ThermostatDay(), 0, 1400);
    gh.repeat(gh.new CollectData(), 500, 500);
  }
}
/* Output: (First and Last 10 Lines)
Bing!
Thermostat to night setting
Turning on lights
Turning off lights
Turning greenhouse water on
Turning greenhouse water off
Thermostat to day setting
Turning on lights
Turning on lights
Turning off lights
...________...________...________...________...
Terminating
Wed Jul 27 11:00:00 MDT 2016 temperature: 66.3 humidity:
50.20
Wed Jul 27 11:30:00 MDT 2016 temperature: 67.3 humidity:
51.02
Wed Jul 27 12:00:00 MDT 2016 temperature: 68.8 humidity:
51.82
Wed Jul 27 12:30:00 MDT 2016 temperature: 70.0 humidity:
52.19
Wed Jul 27 13:00:00 MDT 2016 temperature: 71.5 humidity:
52.99
Wed Jul 27 13:30:00 MDT 2016 temperature: 73.0 humidity:
53.32
Wed Jul 27 14:00:00 MDT 2016 temperature: 74.4 humidity:
53.99
Wed Jul 27 14:30:00 MDT 2016 temperature: 75.5 humidity:
54.40
Wed Jul 27 15:00:00 MDT 2016 temperature: 76.8 humidity:
53.96
*/
