package utitlity;

/**
 * This stores whether file, documents and others should be created or not.
 * (Also(!) whether created files shall be opened after creation or not.) // bad
 * practice!!!
 * 
 */

public class OperatingAgent
{
	private boolean createNoFile = false;
	private boolean createFolder = false;
	private boolean createWordFile = false;
	private boolean createTextFile = false;
	private boolean directlyOpenFilesAndDirs = false;

	public final boolean isCreateNoFile()
	{
		return createNoFile;
	}

	public final boolean isCreateFolder()
	{
		return createFolder;
	}

	public final boolean isCreateTextFile()
	{
		return createTextFile;
	}

	public final boolean isCreateWordFile()
	{
		return createWordFile;
	}

	public final boolean isDirectlyOpenFilesAndDirs()
	{
		return directlyOpenFilesAndDirs;
	}

	public final void setDirectlyOpenFilesAndDirs(boolean directlyOpenFilesAndDirs)
	{
		this.directlyOpenFilesAndDirs = directlyOpenFilesAndDirs;
	}

	public final void setCreateNoFile(boolean createNoFile)
	{
		this.createNoFile = createNoFile;
	}

	public final void setCreateTextFile(boolean createTextFile)
	{
		this.createTextFile = createTextFile;
	}

	public final void setCreateFolder(boolean createFolder)
	{
		this.createFolder = createFolder;
	}

	public final void setCreateWordFile(boolean createWordFile)
	{
		this.createWordFile = createWordFile;
	}
}
