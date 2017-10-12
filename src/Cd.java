import java.io.File;
import java.util.ArrayList;

public class Cd extends Cmd {
	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		if (args.size() > 0 && !args.get(0).equals(".")) {
			if (args.get(0).equals("..")) {
				args.set(0,
						args.get(0).substring(args.get(0).lastIndexOf("/") + 1));
			} else {
				Integer index = args.get(0).indexOf("/home");
				if (index == -1) {
					args.set(0, CLI.workingDirectory + '/' + args.get(0));
				}
				File f = new File(args.get(0));
				if (f.isDirectory()) {
					CLI.workingDirectory = args.get(0);

				} else {
					ret.add(0, "No such file or directory");
				}
			}
		}
		return ret;
	}
}
