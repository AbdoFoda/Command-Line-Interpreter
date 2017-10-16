import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileOperations {

	public static void writeToTextFile(String fileName, ArrayList<String> result) throws IOException {
		String content = "";
		for (String s : result) {
			content += s + "\n";
		}
		FileWriter fw= new FileWriter(new File(fileName),true);
		fw.write(content);
		fw.close();
	}

	public static void overWriteToTextFile(String fileName, ArrayList<String> result) throws IOException {
		String content = "";
		for (String s : result) {
			content += s + "\n";
		}
		File f = new File(fileName);
		f.delete();
		f = new File(fileName);
		Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
	}
	  public static String readTextFile(String fileName) throws IOException {
	        String content = new String(Files.readAllBytes(Paths.get(fileName)));
	        return content;
	    }
	    public static void writeToTextFile(String fileName, String content) throws IOException {
	        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
	    }
}
