import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Date extends Cmd {

	@Override
	public ArrayList<String> execute(ArrayList<String> args) {
		// TODO Auto-generated method stub
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ArrayList <String> ret = new ArrayList<String>();
		ret.add(dtf.format(now));
		return ret;
	}
}
