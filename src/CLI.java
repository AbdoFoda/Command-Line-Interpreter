import java.util.*;

public class CLI {

	static HashMap<String, Cmd> cmd = new HashMap<String, Cmd>();
	protected static String workingDirectory = new String();

	public static void init() {
		workingDirectory = "/home/reem/Documents";///home";
		cmd.put("pwd", new Pwd());
		cmd.put("ls", new Ls());
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
			if (cmd.containsKey(command)) {
				ret.addAll(cmd.get(command).execute(
						new ArrayList<String>(Arrays.asList(commandLine))));
			} else {
				ret.add("InValid Command");
			}
		}
		return ret;
	}
}