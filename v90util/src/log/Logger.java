package log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

import util.TimeUtil;

public class Logger {
	private static final String FILE_SUFFIX = ".log";
	private static final String FILE_NAME_CURRENT = "current.log";
	private static final String FILE_NAME_ERROR = "error.log";

	protected File logDir;
	protected PrintWriter out;

	protected int logFileLevel;
	protected int logStdoutLevel;
	protected int logFileSizeLimit;

	protected int counter = 100;

	public Logger(File logDir, int fileLevel, int stdoutLevel, int fileSizeLimit) {
		this.logDir = logDir;
		this.logFileLevel = fileLevel;
		this.logStdoutLevel = fileLevel;
		this.logFileSizeLimit = fileSizeLimit;
	}

	private static final String MSG_ERROR_LOG = "{0} \t {1} \t Error during logging for message: {2}";

	public void logMessage(LogLevel logLevel, Throwable throwable, String message) {
		try {
			doLogMessage(logLevel, throwable, message);
		} catch (Throwable t) {
			Object[] params = { TimeUtil.getDateTimeString(), logLevel.getName(), message };
			String stdoutMessage = MessageFormat.format(MSG_ERROR_LOG, params);
			System.out.println(stdoutMessage + ": \n");
			throwable.printStackTrace();
			System.out.println("Exception caught from the logger code:");
			t.printStackTrace();
		}
	}

	private static final String MSG_STANDARD = "{0} \t {1} \t {2}";

	public void doLogMessage(LogLevel logLevel, Throwable throwable, String message) {
		boolean okToFile = logLevel.getId() <= logFileLevel;
		boolean okToStdout = logLevel.getId() <= logStdoutLevel;
		if (!okToFile && !okToStdout) {
			return;
		}

		Object[] params = { TimeUtil.getDateTimeString(), logLevel.getName(), message };
		String msg = MessageFormat.format(MSG_STANDARD, params);
		if (okToStdout) {
			System.out.println(msg);
			if (throwable != null) {
				throwable.printStackTrace();
			}
		}

		if (okToFile) {
			try {
				checkLogFile();
				out.println(msg);
				if (throwable != null) {
					throwable.printStackTrace(out);
					out.println("");
					counter += 9;
				}
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			counter++;
		}

		if (logLevel == LogLevel.ERROR) {
			File errorLogFile = new File(logDir, FILE_NAME_ERROR);
			try {
				PrintWriter pw = new PrintWriter(new FileOutputStream(errorLogFile, true), false);
				pw.println(msg);
				if (throwable != null) {
					throwable.printStackTrace(pw);
				}
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	protected void checkLogFile() {
		if (counter < 30) {
			return;
		}

		synchronized (this) {
			File thisFile = new File(logDir, FILE_NAME_CURRENT);
			if (thisFile != null && thisFile.length() > (1024 * logFileSizeLimit)) {
				if (out != null) {
					out.flush();
					out.close();
					out = null;
				}
				String ts = TimeUtil.getDateTimeStringAsID();
				String fileName = "log_" + ts + FILE_SUFFIX;
				File newFile = new File(logDir, fileName);
				File oldFile = new File(logDir, FILE_NAME_CURRENT);
				oldFile.renameTo(newFile);
				open();
			} else {
				open();
			}
			counter = 0;
		}
	}

	private static final String MSG_CANNOT_OPEN_FILE = "Cannot open a log file:{0} in directory: {1}";

	protected void open() {
		try {
			File logFile = new File(logDir, FILE_NAME_CURRENT);
			out = new PrintWriter(new FileOutputStream(logFile, true), false);
		} catch (IOException ioe) {
			Object[] params = { FILE_NAME_CURRENT, logDir.getAbsolutePath() };
			System.out.println(MessageFormat.format(MSG_CANNOT_OPEN_FILE, params));
			ioe.printStackTrace();
		}
	}
}
