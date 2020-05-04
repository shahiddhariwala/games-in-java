package oldClassics;
/*
Code by  : Shahid Dhariwala
LinkedIn : https://www.linkedin.com/in/shahiddhariwala/
Twitter  : https://twitter.com/shahiddhariwala
Date     : 04-May-2020
*/

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener
{
	public static int BOARD_SIZE = 3;

	public static enum GameStatus
	{
		Incomplete, Xwins, Owins, Tie
	}

	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	boolean crossTurn = true;

	public TicTacToe()
	{
		super.setTitle("Tic-Tac-Toe by Shahid Dhariwala");
		super.setSize(800, 800);

		GridLayout gridLayout = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(gridLayout);
		Font font = new Font("Comic Sans", 1, 150);

		for (int row = 0; row < buttons.length; row++)
		{
			for (int col = 0; col < buttons.length; col++)
			{
				JButton button = new JButton(""); // Empty text on button
				buttons[row][col] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(false);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton ClickedButton = (JButton) e.getSource();
		this.makeMove(ClickedButton);
		GameStatus gs = this.getGameStatus();
		if (gs == GameStatus.Incomplete)
		{
			return;
		}
		this.declareWinner(gs);

	}

	private void declareWinner(GameStatus gs)
	{
		if (gs == GameStatus.Xwins)
		{
			JOptionPane.showMessageDialog(this, "X Wins");
		} else if (gs == GameStatus.Owins)
		{
			JOptionPane.showMessageDialog(this, "O Wins");
		} else
		{
			JOptionPane.showMessageDialog(this, "Its Tie");
		}

		int choice = JOptionPane.showConfirmDialog(this, "Play Again ?", "Tic-Tac-Toe", JOptionPane.YES_NO_OPTION);
		if (choice == 0)
		{

			// refresh the game
			for (int row = 0; row < buttons.length; row++)
			{
				for (int col = 0; col < buttons.length; col++)
				{
					this.buttons[row][col].setText("");
				}
			}
			this.crossTurn = true;
		} else
		{
			this.dispose();
		}

	}

	private GameStatus getGameStatus()
	{
		String text1 = "";
		String text2 = "";
		int row = 0;
		int col = 0;
		// text inside rows
		row = 0;
		while (row < BOARD_SIZE)
		{
			col = 0;
			while (col < BOARD_SIZE - 1)
			{
				text1 = this.buttons[row][col].getText();
				text2 = this.buttons[row][col + 1].getText();
				if (text1.length() == 0 || (!text1.equals(text2)))
				{
					break;
					// not a winner in this row
				}
				col++;
			}
			if (col == BOARD_SIZE - 1)
			{

				if (text1.equals("X"))
				{
					return GameStatus.Xwins;
				} else
				{
					return GameStatus.Owins;

				}
			}
			row++;
		}

		// text inside col
		col = 0;
		while (col < BOARD_SIZE)
		{
			row = 0;
			while (row < BOARD_SIZE - 1)
			{
				text1 = this.buttons[row][col].getText();
				text2 = this.buttons[row + 1][col].getText();
				if (text1.length() == 0 || (!text1.equals(text2)))
				{
					break;
					// not a winner in this row
				}
				row++;
			}
			if (row == BOARD_SIZE - 1)
			{

				if (text1.equals("X"))
				{
					return GameStatus.Xwins;
				} else
				{
					return GameStatus.Owins;

				}
			}
			col++;
		}

		// test for diagonal1

		row = 0;
		col = 0;
		while (row < BOARD_SIZE - 1)
		{
			text1 = this.buttons[row][col].getText();
			text2 = this.buttons[row + 1][col + 1].getText();
			if (text1.length() == 0 || (!text1.equals(text2)))
			{
				break;
				// not a winner in this diagonal
			}
			row++;
			col++;
		}
		if (row == BOARD_SIZE - 1)
		{

			if (text1.equals("X"))
			{
				return GameStatus.Xwins;
			} else
			{
				return GameStatus.Owins;
			}
		}
		// test for diagonal2
		row = BOARD_SIZE - 1;
		col = 0;
		while (row > 0)
		{
			text1 = this.buttons[row][col].getText();
			text2 = this.buttons[row - 1][col + 1].getText();
			if (text1.length() == 0 || (!text1.equals(text2)))
			{
				break;
				// not a winner in this diagonal
			}
			row--;
			col++;
		}
		if (row == 0)
		{

			if (text1.equals("X"))
			{
				return GameStatus.Xwins;
			} else
			{
				return GameStatus.Owins;
			}
		}

		//
		for (row = 0; row < BOARD_SIZE; row++)
		{
			for (col = 0; col < BOARD_SIZE; col++)
			{
				if (this.buttons[row][col].getText().length() == 0)
				{
					return GameStatus.Incomplete;
				}
			}
		}
		return GameStatus.Tie;
	}

	private void makeMove(JButton clickedButton)
	{
		String buttonText = clickedButton.getText();
		if (buttonText.length() > 0)
		{
			// Something is already present
			JOptionPane.showMessageDialog(this, "Invalid Move");
		} else
		{
			if (this.crossTurn == true)
			{
				clickedButton.setText("X");
			} else
			{
				clickedButton.setText("O");
			}
			crossTurn = !crossTurn;
		}

	}

	public static void main(String[] args)
	{
		TicTacToe startGame = new TicTacToe();
	}
}

/* https://github.com/shahiddhariwala */