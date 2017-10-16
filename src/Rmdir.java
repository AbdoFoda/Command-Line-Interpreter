import java.io.File;
import java.util.ArrayList;

public class Rmdir extends Cmd {

	@Override
	public ArrayList<String> execute(ArrayList<String> args) {
		// TODO Auto-generated method stub
		for(String dir :args) {
			ArrayList <String> tmp= new ArrayList<String>();
			tmp.add(dir);
			Ls l = new Ls();
			if( l.execute(tmp).size()>0 ) {
				System.out.println("rmdir cannot remove such directory : "+dir);
			}else {
				if(dir.charAt(0)!='/') {
					dir=CLI.workingDirectory+"/"+dir;
				}
				new File(dir).delete();
			}
		}
		return null;
	}

}
