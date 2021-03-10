package utitlity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigFileReader
{
	private final boolean USE_CONFIG_FILE = false;

	private File configFile = null;
	private DateMode dateMode = DateMode.German;

	public ConfigFileReader()
	{
		if (!IsUseConfigFile())
			return;

		configFile = getConfigFile();

		defineDateMode();
	}

	private final void defineDateMode()
	{
		String mode = readDateMode();

		if (mode.equals("English"))
		{
			setDateMode(DateMode.English);
		} else if (mode.equals("German"))
		{
			setDateMode(DateMode.German);
		}
	}

	/**
	 * @return the dateMode read out from the config file
	 */

	private final String readDateMode()
	{
		String output = "";

		output = readLine(9).substring(9);

		return output;
	}

	/**
	 * will return the marked line in the config file.
	 * 
	 * @param line : the line to return text from
	 * @return
	 */

	private final String readLine(int line)
	{
		String output = "";
		BufferedReader reader = null;

		try
		{
			reader = new BufferedReader(new FileReader(configFile));
		} catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}

		for (int i = 0; i < line; i++)
		{
			try
			{
				output = reader.readLine();
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		try
		{
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return output;
	}

	private final File getConfigFile()
	{
		return new File("./config.txt");
	}

	private final void setDateMode(DateMode mode)
	{
		this.dateMode = mode;
	}

	public final String getAutoStartDir()
	{
		String output = "";

		output = readLine(13).substring(19);

		return output;
	}

	/**
	 * 
	 * 
	 * @return the innerMessage read out from the config file
	 */

	public final String getInnerMessage()
	{
		String output = "";

		output = readLine(11).substring(13);

		return output;
	}

	public final DateMode getDateMode()
	{
		return dateMode;
	}

	public final boolean IsUseConfigFile()
	{
		return USE_CONFIG_FILE;
	}

	public enum DateMode
	{
		English, German;
	}

	/*
	 * (so sah das config file mal aus:...)
	 * 
	 * Config File
	 * 
	 * !!! Ich habe mich dazu entschieden momentan kein config file zu benutzen !!!
	 * 
	 * This is a config file. In here the program can be configurated
	 * 
	 * How does it work: A string is always read out after the equals(=) symbole.
	 * Space symbols are also read out.
	 * 
	 * dateMode=German
	 * 
	 * innerMessage= Aufgabe vom
	 * 
	 * autoStartDirectory=C:/Users/jaapi/OneDrive/Dokumente
	 * 
	 * If the autoStartDirectory path is empty than there will not be any directory
	 * opened
	 * 
	 */
}
