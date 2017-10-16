import java.io.File;
import java.util.ArrayList;

public class Rm extends Cmd {
	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		for (String dir : args) {
			try {
				File f = new File(dir);
				System.out.println(dir);
				if (f.exists()) {
					if (f.isFile()) {
						new File(dir).delete();
					}
				} else {
					dir = CLI.workingDirectory + '/' + dir;
					if (new File(dir).isFile()) {
						new File(dir).delete();
					}
				}
			} catch (Exception e) {
				ret.add("no such file like that : " + dir);
			}
		}
		return ret;
	}
}
