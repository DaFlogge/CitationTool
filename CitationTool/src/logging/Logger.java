package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {

	private static File f = new File("debug.log");
	private static FileWriter fw;
	private static boolean inited = false;

	public static void init() throws IOException {
		fw = new FileWriter(f, true);
		fw.write("STARTING AT " + new Date().toString());
		fw.flush();
		inited = true;
	}

	public static void logInfo(Object o) {
		Logger.log("INFO: " + o.toString());
	}
	public static void logErr(Object o) {
		Logger.log("ERROR: " + o.toString());
	}

	public static void logWarn(Object o) {
		Logger.log("WARNING: " + o.toString());
	}

	@Override
	protected void finalize() throws Throwable {	
		if (inited && fw != null) {
			fw.write("STOPPING AT " + new Date().toString());
			fw.flush();
			fw.close();
		}
		super.finalize();
	}
	
	private static void log(Object o) {
		if (inited && fw != null) {
			try {
				fw.write(new Date().toString() + " " + o.toString() + "\n");
				fw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}