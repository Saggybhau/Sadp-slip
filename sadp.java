                                                                     slip1
import java.io.*;
class LowerCaseInputStream extends InputStreamReader {
private Reader reader;
public LowerCaseInputStream(Reader reader) {
super(reader);
this.reader = reader;
}
@Override
public int read() throws IOException {
int data = reader.read();
if (data == -1) {
return -1; // End of stream
}
return Character.toLowerCase((char) data);
}
@Override
public int read(char[] cbuf, int off, int len) throws IOException {
int numCharsRead = reader.read(cbuf, off, len);
for (int i = off; i < off + numCharsRead; i++) {
cbuf[i] = Character.toLowerCase(cbuf[i]);
}
return numCharsRead;
}
}
public class IODecoratorExample {
public static void main(String[] args) {
try {
Reader reader = new LowerCaseInputStream(new BufferedReader(new
InputStreamReader(System.in)));
BufferedReader br = new BufferedReader(reader);
System.out.println("Enter some text (uppercase will be converted to lowercase):");
String line;
while ((line = br.readLine()) != null) {
System.out.println("Converted text: " + line);
}
} catch (IOException e) {
e.printStackTrace();
}
}
}

                                                                    slip 2
public class Singleton {
environment
private static volatile Singleton instance;
private Singleton() {
try {
Thread.sleep(100); 
} catch (InterruptedException e) {
e.printStackTrace();
}
}
public static Singleton getInstance() {
if (instance == null) {
synchronized (Singleton.class) {
if (instance == null) {
instance = new Singleton();
}
}
}
return instance;
}
public void showMessage() {
System.out.println("Hello, Singleton instance: " + this);
}
}
class SingletonTestThread extends Thread {
@Override
public void run() {
Singleton singleton = Singleton.getInstance();
singleton.showMessage();
}
}
public class SingletonTest {
public static void main(String[] args) {
SingletonTestThread thread1 = new SingletonTestThread();
SingletonTestThread thread2 = new SingletonTestThread();
SingletonTestThread thread3 = new SingletonTestThread();
SingletonTestThread thread4 = new SingletonTestThread();
thread1.start();
thread2.start();
thread3.start();
thread4.start();
try {
thread1.join();
thread2.join();
thread3.join();
thread4.join();
} catch (InterruptedException e) {
e.printStackTrace();
}
}
}
                                                                     slip3

1. WeatherStation.java (Observable Class)
import java.util.Observable;

public class WeatherStation extends Observable {
private float temperature;
private float humidity;
private float pressure;
public void measurementsChanged() {
setChanged();
notifyObservers();
}
public void setMeasurements(float temperature, float humidity, float
pressure) {
this.temperature = temperature;
this.humidity = humidity;
this.pressure = pressure;
measurementsChanged(); 
set
}
public float getTemperature() {
return temperature;
}
public float getHumidity() {
return humidity;
}
public float getPressure() {
return pressure;
}
}

2. WeatherDisplay.java (Observer Class)
import java.util.Observable;
import java.util.Observer;
public class WeatherDisplay implements Observer {
private float temperature;
private float humidity;
private float pressure;
@Override
public void update(Observable o, Object arg) {
if (o instanceof WeatherStation) {
WeatherStation weatherStation = (WeatherStation) o;
this.temperature = weatherStation.getTemperature();
this.humidity = weatherStation.getHumidity();

this.pressure = weatherStation.getPressure();
display();
}
}
public void display() {
System.out.println("Weather Data Updated: ");
System.out.println("Temperature: " + temperature + "°C");
System.out.println("Humidity: " + humidity + "%");
System.out.println("Pressure: " + pressure + " hPa");
System.out.println("-----------------------------");
}
}

3. Main.java (Testing the Program)
  
public class Main {
public static void main(String[] args) {
WeatherStation weatherStation = new WeatherStation();
WeatherDisplay weatherDisplay = new WeatherDisplay();
weatherStation.addObserver(weatherDisplay);
weatherStation.setMeasurements(25.0f, 65.0f, 1013.0f); 
weatherStation.setMeasurements(28.0f, 70.0f, 1010.0f); 
weatherStation.setMeasurements(22.0f, 60.0f, 1020.0f);
}
}
