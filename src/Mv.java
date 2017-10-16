import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class Mv extends Cmd {
	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		boolean check0 = checkDir(args.get(0)), check1 = checkDir(args.get(1));
		if (args.size() == 2 && check0 == true
				&& new File(args.get(0)).isFile()) {
			if (check1 == false) {
				File f1 = new File(args.get(1));
				try {
					f1.createNewFile();
				} catch (Exception e) {
				}

			}
			FileToFile(args);
		} else if (args.size() == 2 && check0 == true && check1 == true
				&& new File(args.get(0)).isFile()
				&& new File(args.get(1)).isFile()) {
			FileToFile(args);
		} else if (args.size() == 2 && check0 == false) {
			ret.add("process can not be continued");
		} else {
			for (int i = 0; i < args.size(); i++) {
				if (checkDir(args.get(i)) == false)
					args.set(i, null);
			}
			if (args.get(args.size() - 1) == null)
				new Mkdir().execute(new ArrayList<String>(Arrays.asList(args
						.get(args.size() - 1))));
			recurseOnDir(args.get(args.size() - 1), args);
		}
		return ret;
	}

	public Boolean checkDir(String args) {
		File f = new File(args);
		if (!f.exists()) {
			args = CLI.workingDirectory + '/' + args;
			f = new File(args);
			if (!f.exists()) {
				return false;
			}
		}
		return true;
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
			CLI.workingDirectory = tmpWorkingDir;
			new Mkdir().execute(new ArrayList<String>(Arrays.asList(dir)));
			recurseOnDir(
					f + "/" + dir,
					new ArrayList<String>(new Ls()
							.execute(new ArrayList<String>(Arrays.asList(dir)))));

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
		System.out.println("reem d5lt1");
		if (!f.exists()) {
			args.set(0, CLI.workingDirectory + '/' + args.get(0));
		}
		if (!f1.exists()) {
			if (args.get(1).indexOf("/home") == -1)
				args.set(1, CLI.workingDirectory + '/' + args.get(1));
			System.out.println("reem d5lt2");
			f1 = new File(args.get(1));
			if (!f1.exists()) {
				try {
					f1.createNewFile();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
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
