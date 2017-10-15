//import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Mkdir extends Cmd {

	@Override
	public ArrayList<String> execute(ArrayList<String> args) {
		// TODO Auto-generated method stub
		ArrayList<String> ret= new ArrayList<String> ();
		for(String dir : args) {	
				if(dir.charAt(0)!='/') {
					dir=CLI.workingDirectory+"/"+dir;
				}
				if(Files.exists(Paths.get(dir.substring(0,dir.lastIndexOf("/")))) ){
					Path path= Paths.get(dir);
					if(!Files.exists(path)) {
						try {
							Files.createDirectories(path);
						}catch(IOException e) {
							System.out.println("Access denied");
						}
					}else {
						ret.add("Directory : " +dir + " is already exists");
					}
				}else {
					ret.add("No such file or directory :"+dir);
				}
				
		}	
		return ret;
	}
}
