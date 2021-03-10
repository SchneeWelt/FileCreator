package utitlity.fileBuilder;

import utitlity.DebuggerSwitch;
import utitlity.OperatingAgent;
import utitlity.StringBuilder;

public class Wrapper
{
	private StringBuilder stringBuilder = null;
	private OperatingAgent operatingAgent = null;
	private DebuggerSwitch debuggerSwitch = null;

	public Wrapper(OperatingAgent operatingAgent, StringBuilder stringBuilder, DebuggerSwitch debuggerSwitch)
	{
		this.stringBuilder = stringBuilder;
		this.operatingAgent = operatingAgent;
		this.debuggerSwitch = debuggerSwitch;
	}

	public final StringBuilder stringBuilder()
	{
		return stringBuilder;
	}

	public final OperatingAgent operatingAgent()
	{
		return operatingAgent;
	}

	public final DebuggerSwitch debuggerSwitch()
	{
		return debuggerSwitch;
	}
}