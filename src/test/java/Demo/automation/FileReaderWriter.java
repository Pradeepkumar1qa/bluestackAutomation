package Demo.automation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriter {

	private File f = null;
	private FileWriter fw = null;
	private String tabSpacing = "\t\t\t\t";

	public FileReaderWriter(String path_of_file, boolean mode) {
		this.f = new File(path_of_file);

		if (f.exists()) {
			log("file allready exist at specified location", path_of_file);
		} else {
			try {
				f.createNewFile();
				log("created new file", path_of_file);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		try {
			this.fw = new FileWriter(this.f, mode);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void log(String... message) {
		String Final_message = "";
		for (String msg : message) {
			Final_message += msg;
		}
		System.out.println(Final_message);
	}

	public void writeContent(String content) {

		try {
			if (this.fw == null) {
				throw new Exception("File not found");
			}
			this.fw.write(content);
			this.fw.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void writeResult(int index,String gameName,String pageUrl,int statusCode,String tournamentCount) {
		writeContent( index+this.tabSpacing+ gameName+this.tabSpacing+ pageUrl+this.tabSpacing+ statusCode+this.tabSpacing+ tournamentCount+"\n");
	}
	public void writeResult(String index,String gameName,String pageUrl,String statusCode,String tournamentCount) {
		writeContent( index+this.tabSpacing+ gameName+this.tabSpacing+ pageUrl+this.tabSpacing+ statusCode+this.tabSpacing+ tournamentCount+"\n");
	}

}
