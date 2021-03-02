package main;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DefaultTypeNames;
import database.TypeKeys;
import utitlity.DebuggerSwitch;
import utitlity.Printer;
import utitlity.StringBuilder;
import utitlity.argsInputWorker.ArgsInputWorker;

public class Main
{
	private boolean createFolder = false;
	private boolean createWordFile = false;

	private String typeName = "";
	private final String MAKE_FOLDER = "-f";
	private final String MAKE_WORD_FILE = "-d";
	private String innerMessage = " Datei vom ";
	private DebuggerSwitch debuggerSwitch = new DebuggerSwitch();

	public Main(String[] input) throws IOException
	{
		Printer.setAllowDebugPrint(false);
		debuggerSwitch.setAllowFileOutput(true);

		/*
		 * if there is no programm input given when the program is started than the
		 * ArgsInputWorker will ask for them. However, if there is input available than
		 * the worker will not ask. Therefore, the input args will be the processed
		 * ones.
		 */

		if (!isProgramInputAvailable(input))
		{
			ArgsInputWorker argsInputWorker = new ArgsInputWorker();
			argsInputWorker.askForInput();
			input = argsInputWorker.getInput();
		}

		// ----------------

		createTypeName(input[0]);

		// ----------------

		if (!isSecondArgumentAvailable(input))
		{
			Printer.printMissingSecondArgument();
			return;
		}

		defineCreateFolder(input[1]);
		defineCreateWordFile(input[1]);

		if (isThirdArgumentAvailable(input))
		{
			defineCreateFolder(input[2]);
			defineCreateWordFile(input[2]);
		}

		/* build folder and file name */
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.addString(getTypeName()); // add period name
		stringBuilder.addString(getInnerMessage()); // add inner message

		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String dateToday = formatter.format(new Date());
		stringBuilder.addString(dateToday); // add date

		/* create folder and files */

		if (isCreateFolder())
		{
			File folder = new File("./" + stringBuilder.getOutput());

			if (!folder.exists() && debuggerSwitch.isAllowFileOutput())
				folder.mkdir();

			/*
			 * make file in folder if file is wished
			 */
			if (isCreateWordFile())
			{
				File wordFile = new File("./" + stringBuilder.getOutput() + "/" + stringBuilder.getOutput() + ".docx");

				if (!wordFile.exists() && debuggerSwitch.isAllowFileOutput())
					wordFile.createNewFile();

				setCreateWordFile(false);
			}
		}

		/*
		 * make word or docx file
		 * 
		 */
		if (isCreateWordFile())
		{
			File wordFile = new File("./" + stringBuilder.getOutput() + ".docx");

			if (!wordFile.exists() && debuggerSwitch.isAllowFileOutput())
				wordFile.createNewFile();
		}
	}

	private final void defineCreateWordFile(String input)
	{
		if (input.equals(getMakeWordFile()))
		{
			setCreateWordFile(true);
		} else
		{
			setCreateWordFile(false);
		}
	}

	private final void defineCreateFolder(String input)
	{
		if (input.equals(getMakeFolder()))
		{
			setCreateFolder(true);
		} else
		{
			setCreateFolder(false);
		}
	}

	private final void createTypeName(String input)
	{
		if (input.contains("-"))
		{
			int iterator = 0;

			/* use a default typeName */
			for (String key : TypeKeys.typeKeys)
			{
				if (input.equals(key))
				{
					setTypeName(DefaultTypeNames.values()[iterator].toString());
					break;
				} else
				{
					/* no match */
					/* use a coustom typeName */
					setTypeName(input);
				}

				iterator++;
			}
		} else
		{
			/* use a coustom typeName */
			setTypeName(input);
		}
	}

	private final boolean isCreateFolder()
	{
		return createFolder;
	}

	private final boolean isCreateWordFile()
	{
		return createWordFile;
	}

	private final void setCreateFolder(boolean createFolder)
	{
		this.createFolder = createFolder;
	}

	private final void setCreateWordFile(boolean createWordFile)
	{
		this.createWordFile = createWordFile;
	}

	private final boolean isSecondArgumentAvailable(String[] input)
	{
		return input.length >= 2;
	}

	private final boolean isThirdArgumentAvailable(String[] input)
	{
		return input.length >= 3;
	}

	private final boolean isProgramInputAvailable(String[] input)
	{
		return input.length > 0;
	}

	private final String getTypeName()
	{
		return typeName;
	}

	private final String getInnerMessage()
	{
		return innerMessage;
	}

	private final String getMakeFolder()
	{
		return MAKE_FOLDER;
	}

	private final String getMakeWordFile()
	{
		return MAKE_WORD_FILE;
	}

	private final void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}

	public static final void main(String[] input) throws IOException
	{
		new Main(input);
	}
}
