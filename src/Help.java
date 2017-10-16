import java.util.ArrayList;
import java.util.HashMap;

public class Help extends Cmd {

	private static HashMap<String, String> hp = new HashMap<String, String>();

	public static void init() {
		hp.put("cat", "output file on the console");
		hp.put("cd", "change the current directory to the directory you give");
		hp.put("clear", "clear all the commands you wrote");
		hp.put("Date", "Output the date on the console");
		hp.put("ls", "List all folders and files on the directory");
		hp.put("mkdir", "make new directory");
		hp.put("more", "output file but not all of it");
		hp.put("mv", "moves folder to folder , file to folder  or file to file");
		hp.put("pwd", "output the current directory you are standing on ");
		hp.put("rm", "remove file");
		hp.put("rmdir", "remove directory");
		hp.put("exit", "stop all");
	}

	@Override
	public  ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret=new ArrayList<String>();
		if (args.size() == 0) {
			ret.add("cat"+" : "+ "output file on the console");
			ret.add("cd"+" : "+
					"change the current directory to the directory you give");
			ret.add("clear"+" : "+ "clear all the commands you wrote");
			ret.add("Date"+" : "+ "Output the date on the console");
			ret.add("ls"+" : "+ "List all folders and files on the directory");
			ret.add("mkdir"+" : "+ "make new directory");
			ret.add("more"+" : "+ "output file but not all of it");
			ret.add("mv"+" : "+
					"moves folder to folder  :  file to folder  or file to file");
			ret.add("pwd"+" : "+ "output the current directory you are standing on ");
			ret.add("rm"+" : "+ "remove file");
			ret.add("rmdir"+" : "+ "remove directory");
			ret.add("exit"+" : "+ "stop all");
		} else {
			for (int i = 0; i < args.size(); ++i)
				ret.add(args.get(i)+ " : "+hp.get(args.get(i)) );
		}
		return ret;
	}
}
