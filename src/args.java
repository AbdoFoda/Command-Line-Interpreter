import java.util.ArrayList;
import java.util.HashMap;

public class args extends Cmd {

	private static HashMap<String, String> arguments = new HashMap<String, String>();

	public static void init() {
		arguments.put("cat", "takes one or more file, directories");
		arguments.put("cd", "takes one directory");
		arguments.put("clear", "no arguments is accepted");
		arguments.put("Date", "no arguments is accepted");
		arguments.put("ls", "take one directory");
		arguments.put("mkdir", "takes one or more directories");
		arguments.put("more", "takes one or more directories");
		arguments.put("mv", "takes two or more directories");
		arguments.put("pwd", "no arguments is accepted ");
		arguments.put("rm", "takes one or more directories");
		arguments.put("rmdir", "takes one or more directories");
		arguments.put("exit", "no arguments is accepted");
	}

	@Override
	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < args.size(); ++i)
			ret.add(args.get(i) + " : " + arguments.get(args.get(i)));
		return ret;
	}

}
