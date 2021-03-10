package utitlity;

public class OperationDefiner
{
	private final String CREATE_FOLDER = "-f";
	private final String CREATE_NO_FILE = "-n";
	private final String CREATE_TXT_FILE = "-e";
	private final String CREATE_WORD_FILE = "-d";
	private final String DIRECTLY_OPEN_FILES_AND_DIRS = "-o";

	private OperatingAgent operatingAgent = null;

	public OperationDefiner(OperatingAgent operatingAgent)
	{
		this.operatingAgent = operatingAgent;
	}

	public final void defineCreateNoFile(String input)
	{
		if (input.equals(getMakeNoFile()))
		{
			operatingAgent.setCreateNoFile(true);
		} else
		{
			operatingAgent.setCreateNoFile(false);
		}
	}

	public final void defineCreateWordFile(String input)
	{
		if (input.equals(getMakeWordFile()))
		{
			operatingAgent.setCreateWordFile(true);
		} else
		{
			operatingAgent.setCreateWordFile(false);
		}
	}

	public final void defineCreateTextFile(String input)
	{
		if (input.equals(getMakeTextFile()))
		{
			operatingAgent.setCreateTextFile(true);
		} else
		{
			operatingAgent.setCreateTextFile(false);
		}
	}

	public final void defineCreateFolder(String input)
	{
		if (input.equals(getMakeFolder()))
		{
			operatingAgent.setCreateFolder(true);
		} else
		{
			operatingAgent.setCreateFolder(false);
		}
	}

	public final void defineDirectlyOpenFilesAndDirs(String input)
	{
		if (input.equals(DIRECTLY_OPEN_FILES_AND_DIRS))
		{
			operatingAgent.setDirectlyOpenFilesAndDirs(true);
		} else
		{
			operatingAgent.setDirectlyOpenFilesAndDirs(false);
		}
	}

	private final String getMakeFolder()
	{
		return CREATE_FOLDER;
	}

	private final String getMakeTextFile()
	{
		return CREATE_TXT_FILE;
	}

	private final String getMakeWordFile()
	{
		return CREATE_WORD_FILE;
	}

	private final String getMakeNoFile()
	{
		return CREATE_NO_FILE;
	}
}
