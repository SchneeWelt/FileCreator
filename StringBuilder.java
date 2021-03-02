package utitlity;

public class StringBuilder
{
	private String output = "";

	public final void addString(String addon)
	{
		output += addon;
	}
	
	public final String getOutput()
	{
		return output;
	}
}
