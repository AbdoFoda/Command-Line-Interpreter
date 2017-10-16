import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class Mv extends Cmd {
	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		if (new File(args.get(args.size() - 1)).isFile()) {
			if (args.size() == 2) {
				FileToFile(args);
			} else {
				ret.add("process can not be continued");
			}
		} else {
			ret.addAll(checkDir(args));
			recurseOnDir(args.get(args.size() - 1), args);
		}
		return ret;
	}

	public ArrayList<String> checkDir(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < args.size(); i++) {
			File f = new File(args.get(i));
			if (!f.exists()) {
				args.set(i, CLI.workingDirectory + '/' + args.get(i));
				f = new File(args.get(i));
				if (!f.exists()) {
					ret.add("there is no such directory :" + args.get(i));
					args.set(i, null);
				}
			}
		}
		return ret;
	}

	public void recurseOnDir(String f, ArrayList<String> args) {
		for (int i = 0; i + 1 < args.size(); i++) {
			String dir = args.get(i);
			if (dir == null)
				continue;
			if (new File(dir).isFile()) {
				File file = new File(dir);
				continue;
			}
			String tmpWorkingDir = CLI.workingDirectory;
			CLI.workingDirectory = f;
			new Mkdir().execute(new ArrayList<String>(Arrays.asList(dir)));
			recurseOnDir(
					f + "/" + dir,
					new ArrayList<String>(new Ls()
							.execute(new ArrayList<String>(Arrays.asList(dir)))));

			CLI.workingDirectory = tmpWorkingDir;
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
		File f = new File(args.get(0)), f1 = new File(args.get(1));
		if (!f.exists()) {
			args.set(0, CLI.workingDirectory + '/' + args.get(0));
		}
		if (!f1.exists()) {
			args.set(1, CLI.workingDirectory + '/' + args.get(1));
		}
		ArrayList<String> ret = new ArrayList<String>();
		String checker = checkFile(args);
		if (!" ".equals(checker)) {
			ret.add(checker);
			return ret;
		}
		File oldfile = new File(args.get(0));
		File newfile = new File(args.get(1));
		oldfile.renameTo(newfile);
		return ret;
	}
}
