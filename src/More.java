import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class More extends Cmd {
	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		for (String dir : args) {
			ArrayList<String> output = new ArrayList<String>(
					new Cat().execute(new ArrayList<String>(Arrays.asList(dir))));
			boolean flag = false ;
			Scanner sc = new Scanner(System.in);
			for (int i = 0; i < output.size() && flag == false; i++) {
				if (i * 2 >= output.size() && flag == false || i > 10) {
					System.out.println("more");
					while (i + 1 != output.size() && flag == false) {
						String input = sc.nextLine();
						if (!input.trim().equals("")) {
							flag = true;
							break;
						}
						System.out.println(output.get(i));
						i++;
					}
				} else {
					System.out.println(output.get(i));
				}
			}
			sc.close();
		}
		return ret;
	}
}
