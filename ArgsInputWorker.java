package utitlity.argsInputWorker;

import utitlity.Printer;

/**
 * This class can open an option pane wich will aks you to enter your programm
 * arguments. Therefore, there is no consol initial call anymore.
 * 
 * @author jaapi
 *
 */

public class ArgsInputWorker
{
	private String[] input = null;

	/**
	 * This will open a jOption pane. Next, it is asking you to enter the program
	 * input arguments
	 * 
	 */

	public final void askForInput()
	{
		String output = showInputDialouge();

		if (output == null | output.length() == 0)
			getDefaultOutput();

		setInput(output.split(" "));
	}

	private String showInputDialouge()
	{
		String title = getDefaultTitle();
		String output = getDefaultOutput();
		String message = getDefaultMessage();

		InputFrame inputFrame = new InputFrame(title, message);

		waitForInput(inputFrame);
		
		Printer.doDebugPrint("next");
		
		inputFrame.disposeFrame();
		
		output = inputFrame.getInput();

		return output;
	}
	
	private final void waitForInput(InputFrame inputFrame)
	{
		do 
		{
			sleep();
		} while (!inputFrame.isInputConfirmed());
	}
	
	private final void sleep()
	{
		try
		{
			Thread.sleep(250);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private final String getDefaultMessage()
	{
		return "please enter program Arguments";
	}

	private final String getDefaultTitle()
	{
		return "ArgsInput e.g.: Example -d";
	}

	private final String getDefaultOutput()
	{
		return "example -d";
	}

	public final String[] getInput()
	{
		return input;
	}

	private final void setInput(String[] input)
	{
		this.input = input;
	}
}
