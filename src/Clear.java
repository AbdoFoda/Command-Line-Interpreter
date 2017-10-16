import java.util.ArrayList;

public class Clear extends Cmd {

	@Override
	public ArrayList<String> execute(ArrayList<String> args) {
		// TODO Auto-generated method stub
		ArrayList<String> ret = new ArrayList<String> ();
		for(int i=0;i<100;++i)
			ret.add("\n");
		return ret;
	}

}
