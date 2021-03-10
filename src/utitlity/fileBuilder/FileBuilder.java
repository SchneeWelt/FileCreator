package utitlity.fileBuilder;

import java.io.File;
import java.io.IOException;

import utitlity.FileOpener;
import utitlity.Printer;

public class FileBuilder
{
	/* protocol output vars */
	private boolean buildFolder = false;
	private boolean buildTextFile = false;
	private boolean buildWorldFile = false;

	private Wrapper wrapper = null;
	private FileOpener fileOpener = new FileOpener();

	public FileBuilder(Wrapper wrapper)
	{
		this.wrapper = wrapper;
	}

	/**
	 * this method will build your files. In a nutshell this class is only written
	 * for this method.
	 * 
	 */

	public final void buildFiles()
	{
		if (isCreateFolder())
		{
			buildFolder();

			if (isCreateWordFile())
				buildInnerWordFile();

			if (isCreateTextFile())
				buildInnerTextFile();

			if (isCreateNoFile())
				Printer.doDebugPrint("create no file");

			return;
		}

		if (isCreateTextFile())
			buildTextFile();

		if (isCreateWordFile())
			buildWordFile();
	}

	/**
	 * This will print information about the created files.
	 *
	 */

	public final void printProtocol()
	{
		String message = "";

		String folderInformation = "Build Folder: " + isBuildFolder() + "\n";
		String textFileInformation = "Build Text File: " + isBuildTextFile() + "\n";
		String wordFileInformation = "Build Word File: " + isBuildWorldFile() + "\n";

		message += folderInformation + textFileInformation + wordFileInformation;

		System.out.println(message);
	}

	private final void createFile(File file)
	{
		try
		{
			file.createNewFile();
		} catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("[error] could not create the file");
		}
	}

	private final void buildFolder()
	{
		File folder = new File("./" + wrapper.stringBuilder().getOutput());

		if (!folder.exists() && wrapper.debuggerSwitch().isAllowFileOutput())
			folder.mkdir();

		fileOpener.parseDir(folder);

		setBuildFolder(true);
	}

	private final void buildInnerWordFile()
	{
		File wordFile = new File(
				"./" + wrapper.stringBuilder().getOutput() + "/" + wrapper.stringBuilder().getOutput() + ".docx");

		if (!wordFile.exists() && wrapper.debuggerSwitch().isAllowFileOutput())
			createFile(wordFile);

		/* parse file to fileOpener */
		fileOpener.parseFile(wordFile);

		setBuildWorldFile(true);
	}

	private final void buildWordFile()
	{
		File wordFile = new File("./" + wrapper.stringBuilder().getOutput() + ".docx");

		if (!wordFile.exists() && wrapper.debuggerSwitch().isAllowFileOutput())
			createFile(wordFile);

		/* parse file to file opener */
		fileOpener.parseFile(wordFile);

		setBuildWorldFile(true);
	}

	private final void buildInnerTextFile()
	{
		File textFile = new File(
				"./" + wrapper.stringBuilder().getOutput() + "/" + wrapper.stringBuilder().getOutput() + ".txt");

		if (!textFile.exists() && wrapper.debuggerSwitch().isAllowFileOutput())
			createFile(textFile);

		/* parse file to file opener */
		fileOpener.parseFile(textFile);

		setBuildTextFile(true);
	}

	private final void buildTextFile()
	{
		File textFile = new File("./" + wrapper.stringBuilder().getOutput() + ".txt");

		if (!textFile.exists() && wrapper.debuggerSwitch().isAllowFileOutput())
			createFile(textFile);

		/* parse file to file opener */
		fileOpener.parseFile(textFile);

		setBuildTextFile(true);
	}

	public final FileOpener getFileOpener()
	{
		return fileOpener;
	}

	private final boolean isCreateFolder()
	{
		return wrapper.operatingAgent().isCreateFolder();
	}

	private final boolean isCreateWordFile()
	{
		return wrapper.operatingAgent().isCreateWordFile();
	}

	private final boolean isCreateNoFile()
	{
		return wrapper.operatingAgent().isCreateNoFile();
	}

	private final boolean isCreateTextFile()
	{
		return wrapper.operatingAgent().isCreateTextFile();
	}

	private final boolean isBuildFolder()
	{
		return buildFolder;
	}

	private final boolean isBuildTextFile()
	{
		return buildTextFile;
	}

	private final boolean isBuildWorldFile()
	{
		return buildWorldFile;
	}

	private final void setBuildFolder(boolean buildFolder)
	{
		this.buildFolder = buildFolder;
	}

	private final void setBuildTextFile(boolean buildTextFile)
	{
		this.buildTextFile = buildTextFile;
	}

	private final void setBuildWorldFile(boolean buildWorldFile)
	{
		this.buildWorldFile = buildWorldFile;
	}

//	public record Wrapper(OperatingAgent operatingAgent, StringBuilder stringBuilder, DebuggerSwitch debuggerSwitch)
//	{
//	}
}
