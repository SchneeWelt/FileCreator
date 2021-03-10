package main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DefaultTypeNames;
import database.TypeKeys;
import utitlity.ConfigFileReader;
import utitlity.ConfigFileReader.DateMode;
import utitlity.DebuggerSwitch;
import utitlity.FileOpener;
import utitlity.OperatingAgent;
import utitlity.OperationDefiner;
import utitlity.Printer;
import utitlity.StringBuilder;
import utitlity.argsInputWorker.ArgsInputWorker;
import utitlity.fileBuilder.FileBuilder;
import utitlity.fileBuilder.Wrapper;

public class Main
{
	private String typeName = "";
	private String innerMessage = " Aufgabe vom ";
	private DebuggerSwitch debuggerSwitch = new DebuggerSwitch();
	private OperatingAgent operatingAgent = new OperatingAgent();
	private ConfigFileReader configFileReader = new ConfigFileReader();
	private OperationDefiner operationDefiner = new OperationDefiner(operatingAgent);

	public Main(String[] input)
	{
		/*
		 * input syntax:
		 * 
		 * first param (param 0) = typeName (type name)
		 * 
		 * second param (param 1) = -d or -e or -n (file type)
		 * 
		 * third param (param 2) = -f or -o (folder? | open created files and dirs?)
		 * 
		 * fourth param (param 3) = -o (open created files and dirs?)
		 * 
		 * The typeName is how the file is going to be named. E.c. typeName = Hello
		 * means the created file is going to be named: "Hello Aufgabe vom 05.03.2021"
		 * the "Aufgabe vom" part is changable. The Date will always be the latest.
		 * Default typeNames are also in existance. Therefore, special strings like "-d"
		 * or "-e" are also possible. In this case "-e" or "-d" are keys. Please look up
		 * the TypeKeys.java file. In there all valid keys can be found.
		 * 
		 * second param: The second param defines whether a word file (-d), a txt file
		 * (-e) or nothing (-n) will be created. Incase only a folder is wished to be
		 * created you will have to type "example -n -f" since it is not possible to say
		 * that a folder has to be created with only the first two arguments.
		 * 
		 * third param: The third param defines if a folder shall be created (-f) or
		 * whether the created word, editor or directory files shall be opened (-o).
		 * 
		 * the fourth param defines (again) whether the created files and dirs shall be
		 * openend or not. This fourth parameter comes in handy if the third was used to
		 * say that a folder shall be created
		 * 
		 * only the first and the second parameters are important therefore, the third
		 * and fourth ones can be left out. (Left out means dont do that)
		 * 
		 * possible input could be: "test -d -f -o". This would now create a word
		 * document wich is in a folder. Both the document and the folder are named:
		 * "test Aufgabe vom 05.03.2021". In adition the word file and the created
		 * direcoty will be opened afterwards
		 */

		/* setup debuggers */
		Printer.setAllowDebugPrint(false);
		debuggerSwitch.setAllowFileOutput(true);

		/* overwrite inner message based on config file */
		if (configFileReader.IsUseConfigFile())
			setInnerMessage(configFileReader.getInnerMessage());

		/*
		 * if there is no programm input given when the program is started than the
		 * ArgsInputWorker will ask for them. However, if there is input available than
		 * the worker will not ask. Therefore, the input args will be the processed
		 * ones.
		 */

		if (!isFirstArgumentAvailable(input))
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

		operationDefiner.defineCreateNoFile(input[1]);
		operationDefiner.defineCreateWordFile(input[1]);
		operationDefiner.defineCreateTextFile(input[1]);

		if (isThirdArgumentAvailable(input))
		{
			operationDefiner.defineCreateFolder(input[2]);
			operationDefiner.defineDirectlyOpenFilesAndDirs(input[2]);
		}

		if (isFourthArgumentAvailable(input))
		{
			operationDefiner.defineDirectlyOpenFilesAndDirs(input[3]);
		}

		/* build file name */
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.addString(getTypeName()); // add type name
		stringBuilder.addString(getInnerMessage()); // add inner message

		/* get latest date */
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

		if (configFileReader.IsUseConfigFile())
		{
			if (configFileReader.getDateMode().equals(DateMode.German))
			{
				formatter = new SimpleDateFormat("dd.MM.yyyy");
			} else if (configFileReader.getDateMode().equals(DateMode.English))
			{
				formatter = new SimpleDateFormat("yyyy-MM-dd");
			} else
			{
				formatter = new SimpleDateFormat("dd.MM.yyyy");
			}
		}

		String latestDate = formatter.format(new Date());
		stringBuilder.addString(latestDate); // add latest date

		/* create folder and files */
		Wrapper wrapper = new Wrapper(operatingAgent, stringBuilder, debuggerSwitch);
		FileBuilder fileBuilder = new FileBuilder(wrapper);
		fileBuilder.buildFiles();
		fileBuilder.printProtocol();

		/* open file(s) if wished */
		if (operatingAgent.isDirectlyOpenFilesAndDirs())
		{
			FileOpener fileOpener = fileBuilder.getFileOpener();
			Printer.doDebugPrint("open created file");
			fileOpener.openDirectory();
			fileOpener.openFile();
		}

		/* open dir from autoOpenDirPath -> config file */
		if (configFileReader.IsUseConfigFile())
		{
			if (!configFileReader.getAutoStartDir().equals(""))
				FileOpener.open(configFileReader.getAutoStartDir());
		}
	}

	/**
	 * This method determines the typeName of file that are going to be created. The
	 * typeName is the first word that is written in the file(s) that are going to
	 * be created. E.c. typeName = "test" -> "test Aufgabe vom 05.03.2021"
	 * 
	 * @param input
	 */

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

	private final boolean isSecondArgumentAvailable(String[] input)
	{
		return input.length > 1;
	}

	private final boolean isThirdArgumentAvailable(String[] input)
	{
		return input.length > 2;
	}

	private final boolean isFourthArgumentAvailable(String[] input)
	{
		return input.length > 3;
	}

	private final boolean isFirstArgumentAvailable(String[] input)
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

	private final void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}

	private final void setInnerMessage(String innerMessage)
	{
		this.innerMessage = innerMessage;
	}

	public static final void main(String[] input) throws IOException
	{
		new Main(input);
	}
}
