package utitlity;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class FileOpener
{
	private File tempDir = null;
	private File tempFile = null;

	public final void openFile()
	{
		if (tempFile == null)
		{
			Printer.doErrorPrint("tempFile is null in FileOpener");
			return;
		}

		if (Desktop.isDesktopSupported())
		{
			try
			{
				Desktop.getDesktop().open(tempFile);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else
		{
			String message = "hmm... I could not find a desktop. Therefore, i could not open your file...";
			message += "im sure you know what your are doing";

			Printer.doDebugPrint(message);
			return;
		}
	}

	public final void openDirectory()
	{
		if (tempDir == null)
		{
			Printer.doErrorPrint("tempDir is null in FileOpener");
			return;
		}

		if (Desktop.isDesktopSupported())
		{
			/*
			 * this is cut out due to the fact that windows did not implement this feature
			 * yet...
			 */
//			Desktop.getDesktop().browseFileDirectory(tempDir); 

			/*
			 * Well this works...
			 * 
			 */
			try
			{
				Runtime.getRuntime().exec("explorer.exe /select," + tempDir.getPath());
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		} else
		{
			String message = "hmm... I could not find a desktop. Therefore, i could not open your directory...";
			message += "im sure you know what your are doing. Good luck :)";

			Printer.doDebugPrint(message);
			return;
		}
	}

	public static final void open(String path)
	{
		if (Desktop.isDesktopSupported())
		{
			try
			{
				Runtime.getRuntime().exec("explorer.exe /select," + path);
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		} else
		{
			String message = "hmm... I could not find a desktop. Therefore, i could not open your directory...";
			message += "im sure you know what your are doing. Good luck :)";

			Printer.doErrorPrint(message);
			return;
		}
	}

	public final void parseDir(File input)
	{
		tempDir = input;
	}

	public final void parseFile(File input)
	{
		tempFile = input;
	}
}
