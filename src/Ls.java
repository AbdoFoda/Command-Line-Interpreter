import java.io.File;
import java.util.ArrayList;

public class Ls extends Cmd {
	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		if (args.size() > 0) {
			Integer index = args.get(0).indexOf('/');
			if (index != 0)
				args.set(0, CLI.workingDirectory + args);
		} else {
			args.add(0, CLI.workingDirectory);
		}
		File f = new File(args.get(0));
		if (f.isDirectory()) {
			File[] filesList = f.listFiles();
			// /System.out.println(filesList.length);
			Integer cnt = 0;
			for (File file : filesList) {
				if (file.getName().charAt(0) != '.') {
					ret.add(cnt++, file.getName());
				}
			}
		} else
			ret.add(0, "No such file or directory");
		return ret;
	}
}
