import java.io.File;
import java.io.IOException;
import java.util.*;

public class CLI {

	static HashMap<String, Cmd> cmd = new HashMap<String, Cmd>();
	protected static String workingDirectory = new String();

	public static String getAbsolutePath(String path) {
		if (path.charAt(0) != '/') {
			path = workingDirectory + "/" + path;
		}
		return path;
	}

	public static void init() {
		workingDirectory = "/home";
		cmd.put("pwd", new Pwd());
		cmd.put("ls", new Ls());
		cmd.put("cd", new Cd());
		cmd.put("cat", new Cat());
		cmd.put("clear", new Clear());
		cmd.put("date", new Date());
		cmd.put("mkdir", new Mkdir());
		cmd.put("rm", new Rm());
		cmd.put("cp", new Copy());
		cmd.put("mv", new Mv());
		cmd.put("more", new More());
		cmd.put("help", new Help());
		cmd.put("args", new args());
		Help.init();
		args.init();

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
			if ("?-".equals(command)) {
				ret.addAll(new Help().execute(new ArrayList<String>(Arrays
						.asList(commandLine))));

			} else if (cmd.containsKey(command)) {
				String filename = "", overWrite = "";
				for (int j = 0; j < commandLine.length; ++j) {
					if (commandLine[j].equals(">>")
							|| commandLine[j].equals(">")) {
						if (j != commandLine.length - 2) {
							ret.add("an error has occured with overloading operator,it could have just a file");
							return ret;
						} else {
							if (commandLine[j].equals(">>")) {
								filename = getAbsolutePath(commandLine[j + 1]);

							} else {
								overWrite = commandLine[j + 1];
								if (overWrite.charAt(0) != '/') {
									overWrite = workingDirectory + "/"
											+ overWrite;
								}
							}
							commandLine = Arrays.copyOfRange(commandLine, 0, j);
							break;
						}
					}
				}
				if (filename.length() > 0) {
					File f = new File(filename);
					if (f.exists()) {
						try {
							FileOperations.writeToTextFile(
									filename,
									cmd.get(command).execute(
											new ArrayList<String>(Arrays
													.asList(commandLine))));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						ret.add("No such file or directory");
					}
				} else if (overWrite.length() > 0) {
					File f = new File(overWrite);
					if (f.exists()) {
						try {
							FileOperations.overWriteToTextFile(
									overWrite,
									cmd.get(command).execute(
											new ArrayList<String>(Arrays
													.asList(commandLine))));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						ret.add("No such file or directory");
					}
				} else {
					ret.addAll(cmd.get(command).execute(
							new ArrayList<String>(Arrays.asList(commandLine))));
				}

			} else {
				ret.add("InValid Command");
			}

		}
		return ret;
	}
}