import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

public class Mv extends Cmd {
	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		File f = new File(args.get(args.size() - 1));
		if (f.isFile()) {
			if (args.size() == 2) {
				FileToFile(args);
			} else {
				ret.add("process can not be continued");
			}
		}
		String last = args.get(args.size() - 1);
		for (int i = 0; i + 1 < args.size(); i++) {
			try {

			} catch (Exception e) {
				ret.add("there is no such reposirty like that : " + args.get(0));
			}
		}
		return ret;
	}

	public String check(ArrayList<String> args) {
		File f = new File(args.get(0));
		if (!f.exists()) {
			return ("mv : cannot stat" + args.get(0) + " : No such directory or file ");
		} else if (f.isFile() == false) {
			return ("mv: cannot overwrite non-directory  " + args.get(1)
					+ " with directory" + args.get(0));
		}
		return "";
	}

	public ArrayList<String> FileToFile(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		String checkIfThereIsFolder = check(args);
		if (checkIfThereIsFolder.equals("")) {
			ret.add(checkIfThereIsFolder);
			return ret;
		}
		File f1 = new File(args.get(0)), f2 = new File(args.get(1));
		try {
			Files.move(f1.toPath(), f2.toPath());
		} catch (Exception e) {
		}
		return ret;
	}
}
