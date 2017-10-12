import java.util.ArrayList;

public class Pwd extends Cmd {

	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(CLI.workingDirectory);
		return ret;
	}
}