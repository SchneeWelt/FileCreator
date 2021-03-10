package utitlity.argsInputWorker;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class InputFrame extends JFrame implements KeyListener, MouseListener
{
	private boolean inputConfirmed = false;
	private boolean firstKeyInteraction = true;

	private final int SELECTION_CONFIRM_KEY = KeyEvent.VK_ENTER;

	private String input = "";
	private JFrame frame = null;
	private JTextField textField = null;
	private Dimension frameDimension = new Dimension(360, 90);

	public InputFrame(String title, String message)
	{
		createJFrame(title, message);
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		/* do nothing */
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (isFirstKeyInteraction())
			textField.setText("");
		
		setFirstKeyInteraction(false);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();

		if (key == SELECTION_CONFIRM_KEY)
		{
			setInputConfirmed(true);
			setInput(textField.getText());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		/* do nothing */
	}

	@Override
	public void mousePressed(MouseEvent e)
	{ /* do nothing */
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		textField.setText("");
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{ /* do nothing */
	}

	@Override
	public void mouseExited(MouseEvent e)
	{ /* do nothing */
	}

	public final void disposeFrame()
	{
		frame.dispose();
	}

	private final void createJFrame(String title, String message)
	{
		frame = new JFrame();
		frame.setLayout(null);
		frame.setTitle(title);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setSize(frameDimension);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(createJPanel(message));

		frame.setVisible(true);
	}

	private final JPanel createJPanel(String message)
	{
		JPanel panel = new JPanel();
		panel.setSize(frameDimension);
		panel.setLocation(new Point());

		panel.add(createTextField(message));

		return panel;
	}

	private final JTextField createTextField(String message)
	{
		textField = new JTextField();
		textField.setText(message);
		textField.addKeyListener(this);
		textField.addMouseListener(this);

		return textField;
	}

	public final String getInput()
	{
		return input;
	}

	private final void setInput(String input)
	{
		this.input = input;
	}
	
	private final void setFirstKeyInteraction(boolean firstKeyInteraction)
	{
		this.firstKeyInteraction = firstKeyInteraction;
	}

	private final boolean isFirstKeyInteraction()
	{
		return firstKeyInteraction;
	}
	
	public final boolean isInputConfirmed()
	{
		return inputConfirmed;
	}

	private final void setInputConfirmed(boolean inputConfirmed)
	{
		this.inputConfirmed = inputConfirmed;
	}
}
