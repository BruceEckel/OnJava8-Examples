// tasks/GreenhouseScheduler.java
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
/* Output: (First and last 10 Lines)
Bing!
Thermostat to night setting
Turning on lights
Turning off lights
Turning greenhouse water on
Turning on lights
Turning greenhouse water off
Thermostat to day setting
Turning on lights
Turning off lights
________...________...________...________...________
Turning on lights
Mon Jun 15 16:00:00 PDT 2015 temperature: 66.4 humidity:
50.05
Mon Jun 15 16:30:00 PDT 2015 temperature: 68.0 humidity:
50.47
Mon Jun 15 17:00:00 PDT 2015 temperature: 69.7 humidity:
51.42
Mon Jun 15 17:30:00 PDT 2015 temperature: 70.8 humidity:
50.87
Mon Jun 15 18:00:00 PDT 2015 temperature: 72.0 humidity:
50.32
Mon Jun 15 18:30:00 PDT 2015 temperature: 73.2 humidity:
49.92
Mon Jun 15 19:00:00 PDT 2015 temperature: 71.9 humidity:
49.81
Mon Jun 15 19:30:00 PDT 2015 temperature: 70.1 humidity:
50.25
Mon Jun 15 20:00:00 PDT 2015 temperature: 68.9 humidity:
51.00
*/
