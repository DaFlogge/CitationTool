package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {

	private static File f = new File("debug.log");
	private static FileWriter fw;
	private static boolean inited = false;
	private static boolean printToConsole = false;

	public static void init(boolean print) throws IOException {
		printToConsole = print;
		fw = new FileWriter(f, true);
		fw.write("STARTING AT " + new Date().toString() + "\n");
		fw.flush();
		inited = true;
	}

	public static synchronized void logInfo(Object o) {
		Logger.log("INFO: " + o.toString());
	}
	public static synchronized void logErr(Object o) {
		Logger.log("ERROR: " + o.toString());
	}

	public static synchronized void logWarn(Object o) {
		Logger.log("WARNING: " + o.toString());
	}

	private static synchronized void log(Object o) {
		if (inited && fw != null) {
			if (printToConsole) {
				System.out.println(o.toString());
			}
//			try {
//				fw.write("[" + new Date().toString() + "] " + o.toString() + "\n");
//				fw.flush();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}	
	}

	public static void finish() throws IOException {
		System.out.println(inited);
		System.out.println(fw == null);
		if (inited && fw != null) {
			fw.write("STOPPING AT " + new Date().toString()  + "\n");
			fw.flush();
			fw.close();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (inited && fw != null) {
			fw.close();
		}
		super.finalize();
	}
	
}