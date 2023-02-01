import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Test {
	public static void main(String[] args) {
		final File outputFile = new File("path/file.txt");
		try {
			Writer writer = new FileWriter(outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
