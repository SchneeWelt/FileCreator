package utitlity;

public class Printer
{
	private static boolean allowDebugPrint = true;

	public static final void doDebugPrint(String message)
	{
		if (!isAllowDebugPrint())
			return;

		System.out.println("[Debug] " + message);
	}

	public static final void doErrorPrint(String errorMessage)
	{
		if (!isAllowDebugPrint())
			return;
		
		System.err.println("[error] " + errorMessage);
	}

	public static final void printWrongProgramInput()
	{
		System.err.println("please enter at least the period name");
	}

	public static final void printMissingSecondArgument()
	{
		System.err.println("please enter the second argument");
	}

	public static final void printNoMoreArguments()
	{
		System.out.println("No more Arguments available");
	}

	public static final void setAllowDebugPrint(boolean allowDebugPrint)
	{
		Printer.allowDebugPrint = allowDebugPrint;
	}

	private static final boolean isAllowDebugPrint()
	{
		return Printer.allowDebugPrint;
	}
}
