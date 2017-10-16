import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class More extends Cmd {
	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		for (String dir : args) {
			File f = new File(dir);
			if (!f.exists()) {
				dir = CLI.workingDirectory + '/' + dir;
			}
			if (new File(dir).exists()) {
				System.out.println(dir);
				ArrayList<String> output = new ArrayList<String>(
						new Cat().execute(new ArrayList<String>(Arrays
								.asList(dir))));
				System.err.println("heyyyy" + output.size());
				boolean flag = false;
				for (int i = 0; i < output.size() && flag == false; i++) {
					if (i * 2 >= output.size() && flag == false) {
						System.out.println("more");
						while (i + 1 != output.size()) {
							String input = new Scanner(System.in).nextLine();
							if (input != "\n")
								flag = true;
							System.out.println(output.get(i));
						}
					} else {
						System.out.println(output.get(i));
					}
				}
			} else {
				System.out.println("NO Such file like that " + dir);
			}
		}
		return ret;
	}
}
