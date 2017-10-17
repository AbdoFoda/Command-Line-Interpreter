import java.io.File;
import java.util.ArrayList;

public class Cd extends Cmd {
	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		if (args.size() > 0 && !args.get(0).equals(".")) {
			if (args.get(0).equals("..")) {
				try {

					CLI.workingDirectory = CLI.workingDirectory.substring(0,
							CLI.workingDirectory.lastIndexOf("/"));
					if(CLI.workingDirectory.length()==0){
						CLI.workingDirectory="/";
					}
				} catch (Exception e) {
					ret.add("No such file or directory");
				}

			} else {
				Integer index = args.get(0).indexOf("/home");
				if (index == -1) {
					args.set(0, CLI.workingDirectory + '/' + args.get(0));
				}
				File f = new File(args.get(0));
				if (f.isDirectory()) {
					CLI.workingDirectory = args.get(0);

				} else {
					ret.add("No such file or directory");
				}
			}
		}
		return ret;
	}
}
