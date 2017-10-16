import java.util.ArrayList;

public class Help extends Cmd {
	@Override
	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("cat : output file on the console");
		ret.add("cd : change the current directory to the directory you give");
		ret.add("clear : clear all the commands you wrote");
		ret.add("Date : Output the date on the console");
		ret.add("ls : List all folders and files on the directory");
		ret.add("mkdir : make new directory");
		ret.add("more : output file but not all of it");
		ret.add("mv : moves folder to folder , file to folder  or file to file");
		ret.add("pwd : output the current directory you are standing on ");
		ret.add("rm : remove file");
		ret.add("rmdir : remove directory");
		ret.add("exit : stop all");
		return ret;
	}
}
