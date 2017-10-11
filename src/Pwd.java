import java.util.ArrayList;

public class Pwd extends Cmd {

	public ArrayList<String> execute(ArrayList<String> args) {
		ArrayList<String> dummy = new ArrayList<String>();
		dummy.add(CLI.workingDirectory);
		return dummy;
	}
}