import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Copy extends Cmd {

	@Override
	public ArrayList<String> execute(ArrayList<String> args) {
		// TODO Auto-generated method stub
		ArrayList<String> ret = new ArrayList<String>();
		if (args.size() > 1) {
			String todo = CLI.getAbsolutePath(args.get(args.size() - 1));
			File f = new File(todo);
			if (f.exists()) {
				if (f.isFile()) {
					ArrayList<String> add = new ArrayList<String>();
					for (int i = 0; i < args.size() - 1; ++i) {
						args.set(i, CLI.getAbsolutePath(args.get(i)));
						File cur = new File(args.get(i));
						if (cur.exists()) {
							if (cur.isFile()) {
								try {
									add.add(FileOperations.readTextFile(args.get(i)));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if (cur.isDirectory()) {
								ret.add("cannot copy a directory into a file");
							}
						} else {
							ret.add("No such file or directory :" + args.get(i));
						}
					}
					try {
						FileOperations.overWriteToTextFile(todo, add);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (f.isDirectory()) {
					for (int i = 0; i < args.size() - 1; ++i) {
						args.set(i, CLI.getAbsolutePath(args.get(i)));
						File cur = new File(args.get(i));
						if (cur.exists()) {
							if (cur.isFile()) {
								try {
									File toCreate= new File(todo+"/"+cur.getName());
									String val=FileOperations.readTextFile(args.get(i));
									FileOperations.writeToTextFile(toCreate.getAbsolutePath(), val);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if (cur.isDirectory()) {
								ret.add("cannot copy a directory into a file");
							}
						} else {
							ret.add("No such file or directory :" + args.get(i));
						}
					}
				}
			} else {
				ret.add("No such file or directory");
			}
		} else {
			ret.add("cp command must hava two or more arguments!");
		}
		return ret;
	}

}
