import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import static java.nio.file.StandardCopyOption.*;

public class Mv extends Cmd {
	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		if (args.size() == 1) {
			ret.add("process can not be continued");
			return ret;
		}
		String check0 = checkDir(args.get(0)), check1 = checkDir(args.get(1));
		args.set(0, check0);
		if (check1 != null)
			args.set(1, check1);
		if (args.size() == 2 && check0 != null
				&& new File(args.get(0)).isFile()) {
			if (check1 == null) {
				if (args.get(1).indexOf("/home") == -1) {
					args.set(1, CLI.workingDirectory + '/' + args.get(1));
				}
				File f1 = new File(args.get(1));
				try {
					f1.createNewFile();
				} catch (Exception e) {
				}
				FileToFile(args);
			}

		} else if (args.size() == 2 && check0 != null && check1 != null
				&& new File(args.get(0)).isFile()
				&& new File(args.get(1)).isFile()) {
			FileToFile(args);
		} else if (args.size() == 2 && check0 == null) {
			ret.add("process can not be continued");
		} else {
			args.set(args.size() - 1, checkDir(args.get(args.size() - 1)));
			for (int i = 0; i + 1 < args.size(); i++) {
				args.set(i, checkDir(args.get(i)));
			}
			if (checkDir(args.get(args.size() - 1)) == null)
				new Mkdir().execute(new ArrayList<String>(Arrays.asList(args
						.get(args.size() - 1))));
			RecurseOnDir(args, args.get(args.size() - 1));
		}
		return ret;
	}

	public String checkDir(String args) {
		File f = new File(args);
		try {
			if (!f.exists()) {
				args = CLI.workingDirectory + '/' + args;
				f = new File(args);
				if (!f.exists()) {
					return null;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return args;
	}

	public void RecurseOnDir(ArrayList<String> args, String destination) {
		for (int i = 0; i + 1 < args.size(); i++) {
			String dir = args.get(i);
			if (dir == null)
				continue;
			if (new File(dir).isFile()) {
				try {
					String dest = destination + "/" + (new File(dir).getName());
					Files.copy(new File(dir).toPath(), new File(dest).toPath(),
							REPLACE_EXISTING);
					new Rm().execute(new ArrayList<String>(Arrays.asList(dir)));
				} catch (Exception e) {
					e.printStackTrace();
				}
				continue;
			}
			String direct = destination + "/" + new File(dir).getName();
			new Mkdir().execute(new ArrayList<String>(Arrays.asList(direct)));
			ArrayList<String> newArgs = new ArrayList<String>(
					new Ls().execute(new ArrayList<String>(Arrays.asList(dir))));
			for (int j = 0; j < newArgs.size(); j++) {
				newArgs.set(j, dir + "/" + newArgs.get(j));
			}
			newArgs.add("j");
			RecurseOnDir(newArgs, direct);
			new Rmdir().execute(new ArrayList<String>(Arrays.asList(dir)));
		}
	}

	public String checkFile(ArrayList<String> args) {
		File f = new File(args.get(0));
		if (!f.exists()) {
			return ("mv : cannot stat" + args.get(0) + " : No such directory or file ");
		} else if (f.isFile() == false) {
			return ("mv: cannot overwrite non-directory  " + args.get(1)
					+ " with directory" + args.get(0));
		}
		return " ";
	}

	public ArrayList<String> FileToFile(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		File oldfile = new File(args.get(0));
		File newfile = new File(args.get(1));
		oldfile.renameTo(newfile);
		return ret;
	}
}
