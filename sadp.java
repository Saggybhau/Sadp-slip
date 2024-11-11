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
