import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EndGame extends JFrame {

	private JPanel contentPane;
	
	int playerScore;
	int winPlayer,destinationScore;
	public JLabel label;
	private JLabel lblYouHaveWon;
	private JLabel lbldestinationScore;
	private JLabel lblTotalScore;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public EndGame(int player,int score,int dScore) {
		setTitle("Game Over");
		setBackground(new Color(51, 153, 0));
		setResizable(false);
		playerScore=score;
		winPlayer=player;
		destinationScore=dScore;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 276);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(111, 74, 184, 29);
		contentPane.add(label);
		
		label.setText("Welcome Player : "+winPlayer+""); //........................
		
		JLabel lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setForeground(Color.RED);
		lblGameOver.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblGameOver.setBounds(88, 11, 206, 52);
		contentPane.add(lblGameOver);
		
		lblYouHaveWon = new JLabel("You have won this Game"); //.................
		lblYouHaveWon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblYouHaveWon.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouHaveWon.setBounds(111, 101, 179, 23);
		contentPane.add(lblYouHaveWon);
		
		lbldestinationScore = new JLabel("New label");
		lbldestinationScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbldestinationScore.setHorizontalAlignment(SwingConstants.CENTER);
		lbldestinationScore.setBounds(111, 125, 184, 14);
		contentPane.add(lbldestinationScore);
		
		lbldestinationScore.setText("Destination Score was : "+destinationScore+""); //................
		
		lblTotalScore = new JLabel("");
		lblTotalScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalScore.setBounds(121, 150, 164, 14);
		contentPane.add(lblTotalScore);
		
		lblTotalScore.setText("Your Total Score Is : "+playerScore+"");
		
		JButton btnPlayMore = new JButton("Play More");
		btnPlayMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		btnPlayMore.setBounds(76, 185, 114, 23);
		contentPane.add(btnPlayMore);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(216, 185, 89, 23);
		contentPane.add(btnExit);
	}
}
