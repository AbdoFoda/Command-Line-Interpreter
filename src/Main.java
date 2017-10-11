import java.util.*;

public class Main {

	public static void main(String args[]) {
		CLI.init();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			Pwd path = new Pwd();
			System.out.print(path.execute(new ArrayList<String>()).get(0)
					+ "$: ");
			String input = scanner.nextLine();
			if ("exit".equals(input))
				break;
			ArrayList<String> output = CLI.parser(input);
			for (String e : output) {
				if (e.length() > 0) {
					System.out.println(e);
				}
			}
		}
		scanner.close();
	}
}