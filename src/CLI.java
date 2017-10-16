import java.util.*;

public class CLI {

	static HashMap<String, Cmd> cmd = new HashMap<String, Cmd>();
	protected static String workingDirectory = new String();

	public static void init() {
		workingDirectory = "/home";
		cmd.put("pwd", new Pwd());
		cmd.put("ls", new Ls());
		cmd.put("cd", new Cd());
		cmd.put("cat", new Cat());
		cmd.put("clear", new Clear());
		cmd.put("date", new Date());
		cmd.put("mkdir", new Mkdir());
		cmd.put("mv", new Mv());
		cmd.put("rm", new Rm());
		cmd.put("more", new More());
		cmd.put("help", new Help());
	}

	public static ArrayList<String> parser(String input) {
		String[] cmds = input.split("[|]");
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < cmds.length; i++) {
			String[] commandLine = cmds[i].split(" ");
			String command = commandLine[0]; // the appropriate command to be
												// executed
			commandLine = Arrays
					.copyOfRange(commandLine, 1, commandLine.length);
			if (command == "?-") {
				ArrayList<String> args = new ArrayList<String>(
						new Help().execute(new ArrayList<String>(Arrays
								.asList(commandLine))));
				for (int i = 0; i < args.size; i++) {

				}

			} else if (cmd.containsKey(command)) {
				ret.addAll(cmd.get(command).execute(
						new ArrayList<String>(Arrays.asList(commandLine))));
			} else {
				ret.add("InValid Command");
			}
		}
		return ret;
	}
}