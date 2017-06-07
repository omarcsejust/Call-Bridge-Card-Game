import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.image.*;
import java.sql.Time;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
public class player1 {

	private static JFrame frmCardGame;
    static String[] suit={"s","d","c","h"};
    static String[] rank={"2","3","4","5","6","7","8","9","t","k","q","j","a"};
    static String[] deck=new String[52];
    
    static JLabel p1Card;
    static JLabel p2Card;
    static JLabel p3Card;
    static JLabel p4Card;
    
    static JLabel lblp2Score;
    static JLabel lblp1Score;
    static JLabel lblp3Score;
    static JLabel lblp4Score;
    
    static int i1=0,i2=0,i3=0,i4=0,i5=0,i6=0,i7=0,i8=0,i9=0,i10=0,i11=0,i12=0,i13=0;
    static int j1=0,j2=0,j3=0,j4=0,j5=0,j6=0,j7=0,j8=0,j9=0,j10=0,j11=0,j12=0,j13=0;
    static int k1=0,k2=0,k3=0,k4=0,k5=0,k6=0,k7=0,k8=0,k9=0,k10=0,k11=0,k12=0,k13=0;
    static int l1=0,l2=0,l3=0,l4=0,l5=0,l6=0,l7=0,l8=0,l9=0,l10=0,l11=0,l12=0,l13=0;
    
    static int get=0;
    static GameCode gameCodeObj=new GameCode();  // Declaring GameCode Object.............. 
    public static int selectPlayer=1;
    public static String getCard[]=new String[4];
    public static int playerList[]=new int[4];
    public static int p1Score=0;
    public static int p2Score=0;
    public static int p3Score=0;
    public static int p4Score=0;
    private static int winPlayerIndex; // Get the value while SelectPlayer function is returning........
    private static int winPlayer;
    
    
    public static int confirm=0;
    public static char ch; 
    static int index=0;
    static int player1=0;
    static int player2=0;
    static int player3=0;
    static int player4=0;
    
    // for taking destination score from user........
    public static int destinationScore;
    
    Thread Th;
	/**
	 * Launch the application.
	 */
    
	public static void main(String[] args) {        // Main Method
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String score=JOptionPane.showInputDialog("Enter Destination Score.");
					destinationScore=Integer.parseInt(score);
					ShuffleCard();
					player1 window = new player1();
					window.frmCardGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
	
	static void ShuffleCard(){
		for(int i=0;i<deck.length;i++){
			deck[i]=rank[i%13]+suit[i/13];
			//System.out.println(deck[i]);
		}
		
		for(int i=0;i<deck.length;i++){
			int index=(int)(Math.random()*deck.length);
			
			String temp=deck[i];
			deck[i]=deck[index];
			deck[index]=temp;
			
			//System.out.println(deck[i]);    // Here
		}
		
		for(String u:deck){                   // Absolute Random Number
			//System.out.println(u);
			deck[get]=u;
			System.out.println(deck[get]);
			get++;
		}
	}
	
	// Setting played images to the label............................................ 
	public int SetPlayerCard(int getIndex,int cardValue){
		if (getIndex==0) {
			Image playCard1=new ImageIcon(this.getClass().getResource("/"+deck[cardValue]+".gif")).getImage();
			p1Card.setIcon(new ImageIcon(playCard1));
		}
		else if (getIndex==1) {
			Image playCard2=new ImageIcon(this.getClass().getResource("/"+deck[cardValue]+".gif")).getImage();
			p2Card.setIcon(new ImageIcon(playCard2));
		}
		else if (getIndex==2) {
			Image playCard3=new ImageIcon(this.getClass().getResource("/"+deck[cardValue]+".gif")).getImage();
			p3Card.setIcon(new ImageIcon(playCard3));
		}
		else {
			Image playCard4=new ImageIcon(this.getClass().getResource("/"+deck[cardValue]+".gif")).getImage();
			p4Card.setIcon(new ImageIcon(playCard4));
			
			Th=new Thread(){
				public void run(){
					try {
						sleep(3000);
						Image imgBlank=new ImageIcon(this.getClass().getResource("")).getImage();
						p1Card.setIcon(new ImageIcon(imgBlank));
						p2Card.setIcon(new ImageIcon(imgBlank));
						p3Card.setIcon(new ImageIcon(imgBlank));
						p4Card.setIcon(new ImageIcon(imgBlank));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			};
			Th.start();
		}
		return 0;
	}
	
	// This function set value along with win player...................... 
	static int SetScore(int p){
		if (p==1) {
			p1Score=p1Score+4;
			lblp1Score.setText(Integer.toString(p1Score));
			
			//check whether the score reaches to destination score or not..........
			
			if(p1Score>=destinationScore){
				frmCardGame.dispose();
				new EndGame(p,p1Score,destinationScore).setVisible(true);
			}
		}
		else if(p==2){
			p2Score=p2Score+4;
			lblp2Score.setText(Integer.toString(p2Score));
			
			if(p2Score>=destinationScore){
				frmCardGame.dispose();
				new EndGame(p,p2Score,destinationScore).setVisible(true);
			}
		}
		else if(p==3){
			p3Score=p3Score+4;
			lblp3Score.setText(Integer.toString(p3Score));
			
			if(p3Score>=destinationScore){
				frmCardGame.dispose();
				new EndGame(p,p3Score,destinationScore).setVisible(true);
			}
		}
		else {
			p4Score=p4Score+4;
			lblp4Score.setText(Integer.toString(p4Score));
			
			if(p4Score>=destinationScore){
				frmCardGame.dispose();
				new EndGame(p,p4Score,destinationScore).setVisible(true);
			}
		}
		return 0;
	}
	/**
	 * Create the application.
	 */
	public player1() {
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCardGame = new JFrame();
		frmCardGame.setTitle("Card Game ( Call Bridge )");
		frmCardGame.getContentPane().setBackground(new Color(0, 153, 51));
		frmCardGame.setBackground(new Color(0, 204, 0));
		frmCardGame.setBounds(100, 100, 1165, 700);
		frmCardGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCardGame.getContentPane().setLayout(null);
		
		JButton p1Img1 = new JButton("");
		p1Img1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectPlayer==1) {
					i1++;
					player1++;
					if(player1==1){
						if(i1==1){
							// Check from here
							if(index==0){
								getCard[index]=deck[0];
								playerList[index]=1;
								ch=deck[0].charAt(1);
								//Image playCard1=new ImageIcon(this.getClass().getResource("/"+deck[0]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard1));
								SetPlayerCard(index,0);
								
								Image img=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img1.setIcon(new ImageIcon(img));
								index++;
								selectPlayer++;
								deck[0]="ok";
							}
							else{
								if(deck[0].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i1=0;
									player1=0;
								}
								else{
									//Image playCard1=new ImageIcon(this.getClass().getResource("/"+deck[0]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard1));
									SetPlayerCard(index, 0);
									
									Image img=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img1.setIcon(new ImageIcon(img));
									getCard[index]=deck[0];
									playerList[index]=1;
									index++;
									selectPlayer++;
									deck[0]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						i1=0;
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Now its time for player "+selectPlayer);
				}
				
			}
		});
		Image img=new ImageIcon(this.getClass().getResource("/"+deck[0]+".gif")).getImage();
		p1Img1.setIcon(new ImageIcon(img));
		p1Img1.setBounds(10, 346, 72, 94);
		frmCardGame.getContentPane().add(p1Img1);
		
		JButton p1Img2 = new JButton("");
		p1Img2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectPlayer==1) {
					i2++;
					player1++;
					if(player1==1){
						if(i2==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[1];
								playerList[index]=1;
								ch=deck[1].charAt(1);
								//Image playCard2=new ImageIcon(this.getClass().getResource("/"+deck[1]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard2));
								SetPlayerCard(index, 1);
								
								Image img2=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img2.setIcon(new ImageIcon(img2));
								index++;
								selectPlayer++;
								deck[1]="ok";
							}
							else{                                  // To check whether the desired card is exist within player1 or not. 
								if(deck[1].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i2=0;
									player1=0;
								}
								else{
									//Image playCard2=new ImageIcon(this.getClass().getResource("/"+deck[1]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard2));
									SetPlayerCard(index, 1);
									
									Image img2=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img2.setIcon(new ImageIcon(img2));
									getCard[index]=deck[1];
									playerList[index]=1;
									index++;
									selectPlayer++;
									deck[2]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						i2=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Now its time for Player "+selectPlayer);
				}
			}
		});
		Image img2=new ImageIcon(this.getClass().getResource("/"+deck[1]+".gif")).getImage();
		p1Img2.setIcon(new ImageIcon(img2));
		p1Img2.setBounds(92, 346, 72, 94);
		frmCardGame.getContentPane().add(p1Img2);
		
		JButton p1Img3 = new JButton("");
		p1Img3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectPlayer==1) {
					i3++;
					player1++;
					if(player1==1){
						if(i3==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[2];
								playerList[index]=1;
								ch=deck[2].charAt(1);
								//Image playCard3=new ImageIcon(this.getClass().getResource("/"+deck[2]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard3));
								SetPlayerCard(index, 2);
								
								Image img3=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img3.setIcon(new ImageIcon(img3));
								index++;
								selectPlayer++;
								deck[2]="ok";
							}
							else{
								if(deck[2].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i3=0;
									player1=0;
								}
								else{
									//Image playCard3=new ImageIcon(this.getClass().getResource("/"+deck[2]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard3));
									SetPlayerCard(index, 2);
									
									Image img3=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img3.setIcon(new ImageIcon(img3));
									getCard[index]=deck[2];
									playerList[index]=1;
									index++;
									selectPlayer++;
									deck[2]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						i3=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Now its time for player "+selectPlayer);
				}
			}
		});
		Image img3=new ImageIcon(this.getClass().getResource("/"+deck[2]+".gif")).getImage();
		p1Img3.setIcon(new ImageIcon(img3));
		p1Img3.setBounds(174, 346, 72, 94);
		frmCardGame.getContentPane().add(p1Img3);
		
		JButton p1Img4 = new JButton("");
		p1Img4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==1) {
					i4++;
					player1++;
					if(player1==1){
						if(i4==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[3];
								playerList[index]=1;
								ch=deck[3].charAt(1);
								//Image playCard4=new ImageIcon(this.getClass().getResource("/"+deck[3]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard4));
								SetPlayerCard(index, 3);
								
								Image img4=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img4.setIcon(new ImageIcon(img4));
								index++;
								selectPlayer++;
								deck[3]="ok";
							}
							else{
								if(deck[3].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i4=0;
									player1=0;
								}
								else{
									//Image playCard4=new ImageIcon(this.getClass().getResource("/"+deck[3]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard4));
									SetPlayerCard(index, 3);
									
									Image img4=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img4.setIcon(new ImageIcon(img4));
									getCard[index]=deck[3];
									playerList[index]=1;
									index++;
									selectPlayer++;
									deck[3]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						i4=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for Player "+selectPlayer);
				}
			}
		});
		Image img4=new ImageIcon(this.getClass().getResource("/"+deck[3]+".gif")).getImage();
		p1Img4.setIcon(new ImageIcon(img4));
		p1Img4.setBounds(256, 346, 72, 94);
		frmCardGame.getContentPane().add(p1Img4);
		
		JButton p1Img5 = new JButton("");
		p1Img5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==1) {
					i5++;
					player1++;
					if(player1==1){
						if(i5==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[4];
								playerList[index]=1;
								ch=deck[4].charAt(1);
								//Image playCard5=new ImageIcon(this.getClass().getResource("/"+deck[4]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard5));
								SetPlayerCard(index, 4);
								
								Image img5=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img5.setIcon(new ImageIcon(img5));
								index++;
								selectPlayer++;
								deck[4]="ok";
							}
							else{
								if(deck[4].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i5=0;
									player1=0;
								}
								else{
									//Image playCard5=new ImageIcon(this.getClass().getResource("/"+deck[4]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard5));
									SetPlayerCard(index, 4);
									
									Image img5=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img5.setIcon(new ImageIcon(img5));
									getCard[index]=deck[4];
									playerList[index]=1;
									selectPlayer++;
									index++;
									deck[4]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						i5=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img5=new ImageIcon(this.getClass().getResource("/"+deck[4]+".gif")).getImage();
		p1Img5.setIcon(new ImageIcon(img5));
		p1Img5.setBounds(10, 451, 72, 94);
		frmCardGame.getContentPane().add(p1Img5);
		
		JButton p1Img6 = new JButton("");
		p1Img6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==1) {
					i6++;
					player1++;
					if(player1==1){
						if(i6==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[5];
								playerList[index]=1;
								ch=deck[5].charAt(1);
								//Image playCard6=new ImageIcon(this.getClass().getResource("/"+deck[5]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard6));
								SetPlayerCard(index, 5);
								
								Image img6=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img6.setIcon(new ImageIcon(img6));
								index++;
								selectPlayer++;
								deck[5]="ok";
							}
							else{
								if(deck[5].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i6=0;
									player1=0;
								}
								else{
									//Image playCard6=new ImageIcon(this.getClass().getResource("/"+deck[5]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard6));
									SetPlayerCard(index, 5);
									
									Image img6=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img6.setIcon(new ImageIcon(img6));
									getCard[index]=deck[5];
									playerList[index]=1;
									selectPlayer++;
									index++;
									deck[5]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						i6=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img6=new ImageIcon(this.getClass().getResource("/"+deck[5]+".gif")).getImage();
		p1Img6.setIcon(new ImageIcon(img6));
		p1Img6.setBounds(92, 451, 72, 94);
		frmCardGame.getContentPane().add(p1Img6);
		
		JButton p1Img7 = new JButton("");
		p1Img7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==1) {
					i7++;
					player1++;
					if(player1==1){
						if(i7==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[6];
								playerList[index]=1;
								ch=deck[6].charAt(1);
								//Image playCard7=new ImageIcon(this.getClass().getResource("/"+deck[6]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard7));
								SetPlayerCard(index, 6);
								
								Image img7=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img7.setIcon(new ImageIcon(img7));
								index++;
								selectPlayer++;
								deck[6]="ok";
							}
							else{
								if(deck[0].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i7=0;
									player1=0;
								}
								else{
									//Image playCard1=new ImageIcon(this.getClass().getResource("/"+deck[6]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard1));
									SetPlayerCard(index, 6);
									
									Image img=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img1.setIcon(new ImageIcon(img));
									getCard[index]=deck[0];
									playerList[index]=1;
									selectPlayer++;
									index++;
									deck[0]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						i7=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img7=new ImageIcon(this.getClass().getResource("/"+deck[6]+".gif")).getImage();
		p1Img7.setIcon(new ImageIcon(img7));
		p1Img7.setBounds(174, 451, 72, 94);
		frmCardGame.getContentPane().add(p1Img7);
		
		JButton p1Img8 = new JButton("");
		p1Img8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==1) {
					i8++;
					player1++;
					if(player1==1){
						if(i8==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[7];
								playerList[index]=1;
								ch=deck[7].charAt(1);
								//Image playCard8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard8));
								SetPlayerCard(index, 7);
								
								Image img8=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img8.setIcon(new ImageIcon(img8));
								index++;
								selectPlayer++;
								deck[7]="ok";
							}
							else{
								if(deck[7].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i8=0;
									player1=0;
								}
								else{
									//Image playCard8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard8));
									SetPlayerCard(index, 7);
									
									Image img8=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img8.setIcon(new ImageIcon(img8));
									getCard[index]=deck[7];
									playerList[index]=1;
									selectPlayer++;
									index++;
									deck[7]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						i8=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
		p1Img8.setIcon(new ImageIcon(img8));
		p1Img8.setBounds(256, 451, 72, 94);
		frmCardGame.getContentPane().add(p1Img8);
		
		JButton p1Img9 = new JButton("");
		p1Img9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==1) {
					i9++;
					player1++;
					if(player1==1){
						if(i9==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[8];
								playerList[index]=1;
								ch=deck[8].charAt(1);
								//Image playCard8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard8));
								SetPlayerCard(index, 8);
								
								Image img9=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img9.setIcon(new ImageIcon(img9));
								index++;
								selectPlayer++;
								deck[8]="ok";
							}
							else{
								if(deck[8].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i9=0;
									player1=0;
								}
								else{
									//Image playCard8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard8));
									SetPlayerCard(index, 8);
									
									Image img9=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img9.setIcon(new ImageIcon(img9));
									getCard[index]=deck[9];
									playerList[index]=1;
									selectPlayer++;
									index++;
									deck[8]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						i9=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img9=new ImageIcon(this.getClass().getResource("/"+deck[8]+".gif")).getImage();
		p1Img9.setIcon(new ImageIcon(img9));
		p1Img9.setBounds(10, 556, 72, 94);
		frmCardGame.getContentPane().add(p1Img9);
		
		JButton p1Img10 = new JButton("");
		p1Img10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==1) {
					i10++;
					player1++;
					if(player1==1){
						if(i10==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[9];
								playerList[index]=1;
								ch=deck[9].charAt(1);
								//Image playCard8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard8));
								SetPlayerCard(index, 9);
								
								Image img10=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img10.setIcon(new ImageIcon(img10));
								index++;
								selectPlayer++;
								deck[9]="ok";
							}
							else{
								if(deck[9].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i10=0;
									player1=0;
								}
								else{
									//Image playCard8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard8));
									SetPlayerCard(index, 9);
									
									Image img10=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img10.setIcon(new ImageIcon(img10));
									getCard[index]=deck[9];
									playerList[index]=1;
									selectPlayer++;
									index++;
									deck[9]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						i10=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img10=new ImageIcon(this.getClass().getResource("/"+deck[9]+".gif")).getImage();
		p1Img10.setIcon(new ImageIcon(img10));
		p1Img10.setBounds(92, 556, 72, 94);
		frmCardGame.getContentPane().add(p1Img10);
		
		JButton p1Img11 = new JButton("");
		p1Img11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==1) {
					i11++;
					player1++;
					if(player1==1){
						if(i11==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[10];
								playerList[index]=1;
								ch=deck[10].charAt(1);
								//Image playCard8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard8));
								SetPlayerCard(index, 10);
								
								Image img11=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img11.setIcon(new ImageIcon(img11));
								index++;
								selectPlayer++;
								deck[10]="ok";
							}
							else{
								if(deck[10].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i11=0;
									player1=0;
								}
								else{
									//Image playCard8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard8));
									SetPlayerCard(index, 10);
									
									Image img11=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img11.setIcon(new ImageIcon(img11));
									getCard[index]=deck[10];
									playerList[index]=1;
									selectPlayer++;
									index++;
									deck[10]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						i11=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img11=new ImageIcon(this.getClass().getResource("/"+deck[10]+".gif")).getImage();
		p1Img11.setIcon(new ImageIcon(img11));
		p1Img11.setBounds(174, 556, 72, 94);
		frmCardGame.getContentPane().add(p1Img11);
		
		JButton p1Img12 = new JButton("");
		p1Img12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==1) {
					i12++;
					player1++;
					if(player1==1){
						if(i12==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[11];
								playerList[index]=1;
								ch=deck[11].charAt(1);
								//Image playCard8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard8));
								SetPlayerCard(index, 11);
								
								Image img8=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img8.setIcon(new ImageIcon(img8));
								index++;
								selectPlayer++;
								deck[11]="ok";
							}
							else{
								if(deck[11].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i12=0;
									player1=0;
								}
								else{
									//Image playCard8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard8));
									SetPlayerCard(index, 11);
									
									Image img12=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img12.setIcon(new ImageIcon(img12));
									getCard[index]=deck[11];
									playerList[index]=1;
									selectPlayer++;
									index++;
									deck[11]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						i12=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img12=new ImageIcon(this.getClass().getResource("/"+deck[11]+".gif")).getImage();
		p1Img12.setIcon(new ImageIcon(img12));
		p1Img12.setBounds(256, 556, 72, 94);
		frmCardGame.getContentPane().add(p1Img12);
		
		JButton p1Img13 = new JButton("");
		p1Img13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==1) {
					i13++;
					player1++;
					if(player1==1){
						if(i13==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[12];
								playerList[index]=1;
								ch=deck[12].charAt(1);
								//Image playCard8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
								//p1Card.setIcon(new ImageIcon(playCard8));
								SetPlayerCard(index, 12);
								
								Image img13=new ImageIcon(this.getClass().getResource("")).getImage();
								p1Img13.setIcon(new ImageIcon(img13));
								index++;
								selectPlayer++;
								deck[12]="ok";
							}
							else{
								if(deck[12].charAt(1) != ch){
									for(int i=0;i<13;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									i13=0;
									player1=0;
								}
								else{
									//Image playCard8=new ImageIcon(this.getClass().getResource("/"+deck[7]+".gif")).getImage();
									//p1Card.setIcon(new ImageIcon(playCard8));
									SetPlayerCard(index, 12);
									
									Image img13=new ImageIcon(this.getClass().getResource("")).getImage();
									p1Img13.setIcon(new ImageIcon(img13));
									getCard[index]=deck[12];
									playerList[index]=1;
									selectPlayer++;
									index++;
									deck[12]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player1=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						i13=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img13=new ImageIcon(this.getClass().getResource("/"+deck[12]+".gif")).getImage();
		p1Img13.setIcon(new ImageIcon(img13));
		p1Img13.setBounds(337, 556, 72, 94);
		frmCardGame.getContentPane().add(p1Img13);
		
		JButton p2Img14 = new JButton("");                             // ................................Player 2 .................................................
		p2Img14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==2) {
					j1++;
					player2++;
					if(player2==1){
						if(j1==1){
							
							if(index==0){
								getCard[index]=deck[13];
								playerList[index]=2;
								ch=deck[13].charAt(1);
								
								//Image playCard14=new ImageIcon(this.getClass().getResource("/"+deck[13]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard14));
								SetPlayerCard(index, 13);
								
								Image img14=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img14.setIcon(new ImageIcon(img14));
								index++;
								selectPlayer++;
								deck[13]="ok";
							}
							else{
								if(deck[13].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j1=0;
									player2=0;
								}
								else{
									//Image playCard14=new ImageIcon(this.getClass().getResource("/"+deck[13]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard14));
									SetPlayerCard(index, 13);
									
									Image img14=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img14.setIcon(new ImageIcon(img14));
									getCard[index]=deck[13];
									playerList[index]=2;
									index++;
									selectPlayer++;
									deck[13]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						j1=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
				
			}
		});
		Image img14=new ImageIcon(this.getClass().getResource("/"+deck[13]+".gif")).getImage();
		p2Img14.setIcon(new ImageIcon(img14));
		p2Img14.setBounds(821, 346, 72, 94);
		frmCardGame.getContentPane().add(p2Img14);
		
		JButton p2Img15 = new JButton("");
		p2Img15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==2) {
					j2++;
					player2++;
					if(player2==1){
						if(j2==1){
							
							if(index==0){
								getCard[index]=deck[14];
								playerList[index]=2;
								ch=deck[14].charAt(1);
								
								//Image playCard15=new ImageIcon(this.getClass().getResource("/"+deck[14]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard15));
								SetPlayerCard(index, 14);
								
								Image img15=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img15.setIcon(new ImageIcon(img15));
								index++;
								selectPlayer++;
								deck[14]="ok";
							}
							else{
								if(deck[14].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j2=0;
									player2=0;
								}
								else{
									//Image playCard15=new ImageIcon(this.getClass().getResource("/"+deck[14]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard15));
									SetPlayerCard(index, 14);
									
									Image img15=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img15.setIcon(new ImageIcon(img15));
									getCard[index]=deck[14];
									playerList[index]=2;
									index++;
									selectPlayer++;
									deck[14]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						j2=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img15=new ImageIcon(this.getClass().getResource("/"+deck[14]+".gif")).getImage();
		p2Img15.setIcon(new ImageIcon(img15));
		p2Img15.setBounds(903, 346, 72, 94);
		frmCardGame.getContentPane().add(p2Img15);
		
		JButton p2Img16 = new JButton("");
		p2Img16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectPlayer==2) {
					j3++;
					player2++;
					if(player2==1){
						if(j3==1){
							
							if(index==0){
								getCard[index]=deck[15];
								playerList[index]=2;
								ch=deck[15].charAt(1);
								
								//Image playCard16=new ImageIcon(this.getClass().getResource("/"+deck[15]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard16));
								SetPlayerCard(index, 15);
								
								Image img16=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img16.setIcon(new ImageIcon(img16));
								index++;
								selectPlayer++;
								deck[15]="ok";
							}
							else{
								if(deck[15].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j3=0;
									player2=0;
								}
								else{
									//Image playCard16=new ImageIcon(this.getClass().getResource("/"+deck[15]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard16));
									SetPlayerCard(index, 15);
									
									Image img16=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img16.setIcon(new ImageIcon(img16));
									getCard[index]=deck[15];
									playerList[index]=2;
									index++;
									selectPlayer++;
									deck[15]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						j3=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for Player "+selectPlayer);
				}
			}
		});
		Image img16=new ImageIcon(this.getClass().getResource("/"+deck[15]+".gif")).getImage();
		p2Img16.setIcon(new ImageIcon(img16));
		p2Img16.setBounds(985, 346, 72, 94);
		frmCardGame.getContentPane().add(p2Img16);
		
		JButton p2Img17 = new JButton("");
		p2Img17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==2) {
					j4++;
					player2++;
					if(player2==1){
						if(j4==1){
							
							if(index==0){
								getCard[index]=deck[16];
								playerList[index]=2;
								ch=deck[16].charAt(1);
								
								//Image playCard17=new ImageIcon(this.getClass().getResource("/"+deck[16]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard17));
								SetPlayerCard(index, 16);
								
								Image img17=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img17.setIcon(new ImageIcon(img17));
								index++;
								selectPlayer++;
								deck[16]="ok";
							}
							else{
								if(deck[16].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j4=0;
									player2=0;
								}
								else{
									//Image playCard17=new ImageIcon(this.getClass().getResource("/"+deck[16]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard17));
									SetPlayerCard(index, 16);
									
									Image img17=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img17.setIcon(new ImageIcon(img17));
									getCard[index]=deck[16];
									playerList[index]=2;
									index++;
									selectPlayer++;
									deck[16]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your trick is on running! Please wait for next trick.");
						j4=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for Player "+selectPlayer);
				}
				/*if(j4==1){
					Image playCard17=new ImageIcon(this.getClass().getResource("/"+deck[16]+".gif")).getImage();
					p2Card.setIcon(new ImageIcon(playCard17));
					
					Image img17=new ImageIcon(this.getClass().getResource("")).getImage();
					p2Img17.setIcon(new ImageIcon(img17));
				}
				else{
					JOptionPane.showMessageDialog(null, "This card has already been played");
				}*/
			}
		});
		Image img17=new ImageIcon(this.getClass().getResource("/"+deck[16]+".gif")).getImage();
		p2Img17.setIcon(new ImageIcon(img17));
		p2Img17.setBounds(1067, 344, 72, 94);
		frmCardGame.getContentPane().add(p2Img17);
		
		JButton p2Img18 = new JButton("");
		p2Img18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==2) {
					j5++;
					player2++;
					if(player2==1){
						if(j5==1){
							
							if(index==0){
								getCard[index]=deck[17];
								playerList[index]=2;
								ch=deck[17].charAt(1);
								
								//Image playCard18=new ImageIcon(this.getClass().getResource("/"+deck[17]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard18));
								SetPlayerCard(index, 17);
								
								Image img18=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img18.setIcon(new ImageIcon(img18));
								index++;
								selectPlayer++;
								deck[17]="ok";
							}
							else{
								if(deck[17].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j5=0;
									player2=0;
								}
								else{
									//Image playCard18=new ImageIcon(this.getClass().getResource("/"+deck[17]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard18));
									SetPlayerCard(index, 17);
									
									Image img18=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img18.setIcon(new ImageIcon(img18));
									getCard[index]=deck[17];
									playerList[index]=2;
									selectPlayer++;
									index++;
									deck[17]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						j5=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img18=new ImageIcon(this.getClass().getResource("/"+deck[17]+".gif")).getImage();
		p2Img18.setIcon(new ImageIcon(img18));
		p2Img18.setBounds(821, 451, 72, 94);
		frmCardGame.getContentPane().add(p2Img18);
		
		JButton p2Img19 = new JButton("");
		p2Img19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==2) {
					j6++;
					player2++;
					if(player2==1){
						if(j6==1){
							
							if(index==0){
								getCard[index]=deck[18];
								playerList[index]=2;
								ch=deck[18].charAt(1);
								
								//Image playCard19=new ImageIcon(this.getClass().getResource("/"+deck[18]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard19));
								SetPlayerCard(index, 18);
								
								Image img19=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img19.setIcon(new ImageIcon(img19));
								index++;
								selectPlayer++;
								deck[18]="ok";
							}
							else{
								if(deck[18].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j6=0;
									player2=0;
								}
								else{
									//Image playCard19=new ImageIcon(this.getClass().getResource("/"+deck[18]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard19));
									SetPlayerCard(index, 18);
									
									Image img19=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img19.setIcon(new ImageIcon(img19));
									getCard[index]=deck[18];
									playerList[index]=2;
									selectPlayer++;
									index++;
									deck[18]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						j6=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img19=new ImageIcon(this.getClass().getResource("/"+deck[18]+".gif")).getImage();
		p2Img19.setIcon(new ImageIcon(img19));
		p2Img19.setBounds(903, 451, 72, 94);
		frmCardGame.getContentPane().add(p2Img19);
		
		JButton p2Img20 = new JButton("");
		p2Img20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==2) {
					j7++;
					player2++;
					if(player2==1){
						if(j7==1){
							
							if(index==0){
								getCard[index]=deck[19];
								playerList[index]=2;
								ch=deck[19].charAt(1);
								
								//Image playCard20=new ImageIcon(this.getClass().getResource("/"+deck[19]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard20));
								SetPlayerCard(index, 19);
								
								Image img20=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img20.setIcon(new ImageIcon(img20));
								index++;
								selectPlayer++;
								deck[19]="ok";
							}
							else{
								if(deck[19].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j7=0;
									player2=0;
								}
								else{
									//Image playCard20=new ImageIcon(this.getClass().getResource("/"+deck[19]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard20));
									SetPlayerCard(index, 19);
									
									Image img20=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img20.setIcon(new ImageIcon(img20));
									getCard[index]=deck[19];
									playerList[index]=2;
									selectPlayer++;
									index++;
									deck[19]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						j7=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img20=new ImageIcon(this.getClass().getResource("/"+deck[19]+".gif")).getImage();
		p2Img20.setIcon(new ImageIcon(img20));
		p2Img20.setBounds(985, 451, 72, 94);
		frmCardGame.getContentPane().add(p2Img20);
		
		JButton p2Img21 = new JButton("");
		p2Img21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==2) {
					j8++;
					player2++;
					if(player2==1){
						if(j8==1){
							
							if(index==0){
								getCard[index]=deck[20];
								playerList[index]=2;
								ch=deck[20].charAt(1);
								
								//Image playCard21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard21));
								SetPlayerCard(index, 20);
								
								Image img21=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img21.setIcon(new ImageIcon(img21));
								index++;
								selectPlayer++;
								deck[20]="ok";
							}
							else{
								if(deck[20].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j8=0;
									player2=0;
								}
								else{
									//Image playCard21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard21));
									SetPlayerCard(index, 20);
									
									Image img21=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img21.setIcon(new ImageIcon(img21));
									getCard[index]=deck[20];
									playerList[index]=2;
									selectPlayer++;
									index++;
									deck[20]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						j8=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
		p2Img21.setIcon(new ImageIcon(img21));
		p2Img21.setBounds(1067, 451, 72, 94);
		frmCardGame.getContentPane().add(p2Img21);
		
		JButton p2Img22 = new JButton("");
		p2Img22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==2) {
					j9++;
					player2++;
					if(player2==1){
						if(j9==1){
							
							if(index==0){
								getCard[index]=deck[21];
								playerList[index]=2;
								ch=deck[21].charAt(1);
								
								//Image playCard21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard21));
								SetPlayerCard(index, 21);
								
								Image img22=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img22.setIcon(new ImageIcon(img22));
								index++;
								selectPlayer++;
								deck[21]="ok";
							}
							else{
								if(deck[21].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j9=0;
									player2=0;
								}
								else{
									//Image playCard21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard21));
									SetPlayerCard(index, 21);
									
									Image img22=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img22.setIcon(new ImageIcon(img22));
									getCard[index]=deck[21];
									playerList[index]=2;
									selectPlayer++;
									index++;
									deck[21]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						j9=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img22=new ImageIcon(this.getClass().getResource("/"+deck[21]+".gif")).getImage();
		p2Img22.setIcon(new ImageIcon(img22));
		p2Img22.setBounds(739, 556, 72, 94);
		frmCardGame.getContentPane().add(p2Img22);
		
		JButton p2Img23 = new JButton("");
		p2Img23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==2) {
					j10++;
					player2++;
					if(player2==1){
						if(j10==1){
							
							if(index==0){
								getCard[index]=deck[22];
								playerList[index]=2;
								ch=deck[22].charAt(1);
								
								//Image playCard21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard21));
								SetPlayerCard(index, 22);
								
								Image img23=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img23.setIcon(new ImageIcon(img23));
								index++;
								selectPlayer++;
								deck[22]="ok";
							}
							else{
								if(deck[22].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j10=0;
									player2=0;
								}
								else{
									//Image playCard21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard21));
									SetPlayerCard(index, 22);
									
									Image img23=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img23.setIcon(new ImageIcon(img23));
									getCard[index]=deck[22];
									playerList[index]=2;
									selectPlayer++;
									index++;
									deck[22]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						j10=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img23=new ImageIcon(this.getClass().getResource("/"+deck[22]+".gif")).getImage();
		p2Img23.setIcon(new ImageIcon(img23));
		p2Img23.setBounds(821, 556, 72, 94);
		frmCardGame.getContentPane().add(p2Img23);
		
		JButton p2Img24 = new JButton("");
		p2Img24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==2) {
					j11++;
					player2++;
					if(player2==1){
						if(j11==1){
							
							if(index==0){
								getCard[index]=deck[23];
								playerList[index]=2;
								ch=deck[23].charAt(1);
								
								//Image playCard21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard21));
								SetPlayerCard(index, 23);
								
								Image img24=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img24.setIcon(new ImageIcon(img24));
								index++;
								selectPlayer++;
								deck[23]="ok";
							}
							else{
								if(deck[23].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j11=0;
									player2=0;
								}
								else{
									//Image playCard21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard21));
									SetPlayerCard(index, 23);
									
									Image img24=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img24.setIcon(new ImageIcon(img24));
									getCard[index]=deck[23];
									playerList[index]=2;
									selectPlayer++;
									index++;
									deck[23]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						j11=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img24=new ImageIcon(this.getClass().getResource("/"+deck[23]+".gif")).getImage();
		p2Img24.setIcon(new ImageIcon(img24));
		p2Img24.setBounds(903, 556, 72, 94);
		frmCardGame.getContentPane().add(p2Img24);
		
		JButton p2Img25 = new JButton("");
		p2Img25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==2) {
					j12++;
					player2++;
					if(player2==1){
						if(j12==1){
							
							if(index==0){
								getCard[index]=deck[24];
								playerList[index]=2;
								ch=deck[24].charAt(1);
								
								//Image playCard21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard21));
								SetPlayerCard(index, 24);
								
								Image img25=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img25.setIcon(new ImageIcon(img25));
								index++;
								selectPlayer++;
								deck[24]="ok";
							}
							else{
								if(deck[24].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j12=0;
									player2=0;
								}
								else{
									//Image playCard21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard21));
									SetPlayerCard(index, 24);
									
									Image img25=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img25.setIcon(new ImageIcon(img25));
									getCard[index]=deck[24];
									playerList[index]=2;
									selectPlayer++;
									index++;
									deck[24]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						j12=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img25=new ImageIcon(this.getClass().getResource("/"+deck[24]+".gif")).getImage();
		p2Img25.setIcon(new ImageIcon(img25));
		p2Img25.setBounds(985, 556, 72, 94);
		frmCardGame.getContentPane().add(p2Img25);
		
		JButton p2Img26 = new JButton("");
		p2Img26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==2) {
					j13++;
					player2++;
					if(player2==1){
						if(j13==1){
							
							if(index==0){
								getCard[index]=deck[25];
								playerList[index]=2;
								ch=deck[25].charAt(1);
								
								//Image playCard21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
								//p2Card.setIcon(new ImageIcon(playCard21));
								SetPlayerCard(index, 25);
								
								Image img26=new ImageIcon(this.getClass().getResource("")).getImage();
								p2Img26.setIcon(new ImageIcon(img26));
								index++;
								selectPlayer++;
								deck[20]="ok";
							}
							else{
								if(deck[25].charAt(1) != ch){
									for(int i=13;i<26;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									j13=0;
									player2=0;
								}
								else{
									//Image playCard21=new ImageIcon(this.getClass().getResource("/"+deck[20]+".gif")).getImage();
									//p2Card.setIcon(new ImageIcon(playCard21));
									SetPlayerCard(index, 25);
									
									Image img26=new ImageIcon(this.getClass().getResource("")).getImage();
									p2Img26.setIcon(new ImageIcon(img26));
									getCard[index]=deck[25];
									playerList[index]=2;
									selectPlayer++;
									index++;
									deck[25]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
								
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played");
							player2=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						j13=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img26=new ImageIcon(this.getClass().getResource("/"+deck[25]+".gif")).getImage();
		p2Img26.setIcon(new ImageIcon(img26));
		p2Img26.setBounds(1067, 556, 72, 94);
		frmCardGame.getContentPane().add(p2Img26);
		
		JButton p3Img27 = new JButton("");                   //...........................Player 3.................................................
		p3Img27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k1++;
					player3++;
					if(player3==1){
						if(k1==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[26];
								playerList[index]=4;
								ch=deck[26].charAt(1);
								//Image playCard27=new ImageIcon(this.getClass().getResource("/"+deck[26]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard27));
								SetPlayerCard(index, 26);
								
								Image img27=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img27.setIcon(new ImageIcon(img27));
								index++;
								selectPlayer=1;
								deck[26]="ok";
							}
							else{
								if(deck[26].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k1=0;
									player3=0;
								}
								else{
									//Image playCard27=new ImageIcon(this.getClass().getResource("/"+deck[26]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard27));
									SetPlayerCard(index, 26);
									
									Image img27=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img27.setIcon(new ImageIcon(img27));
									getCard[index]=deck[26];
									playerList[index]=4;
									index++;
									selectPlayer=1;
									deck[26]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k1=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for Player "+selectPlayer);
				}
			}
		});
		Image img27=new ImageIcon(this.getClass().getResource("/"+deck[26]+".gif")).getImage();
		p3Img27.setIcon(new ImageIcon(img27));
		p3Img27.setBounds(10, 11, 72, 94);
		frmCardGame.getContentPane().add(p3Img27);
		
		JButton p3Img28 = new JButton("");
		p3Img28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k2++;
					player3++;
					if(player3==1){
						if(k2==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[27];
								playerList[index]=4;
								ch=deck[27].charAt(1);
								//Image playCard28=new ImageIcon(this.getClass().getResource("/"+deck[27]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard28));
								SetPlayerCard(index, 27);
								
								Image img28=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img28.setIcon(new ImageIcon(img28));
								index++;
								selectPlayer=1;
								deck[27]="ok";
							}
							else{
								if(deck[27].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k2=0;
									player3=0;
								}
								else{
									//Image playCard28=new ImageIcon(this.getClass().getResource("/"+deck[27]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard28));
									SetPlayerCard(index, 27);
									
									Image img28=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img28.setIcon(new ImageIcon(img28));
									getCard[index]=deck[27];
									playerList[index]=4;
									index++;
									selectPlayer=1;
									deck[27]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k2=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for Player "+selectPlayer);
				}
			}
		}); 
		Image img28=new ImageIcon(this.getClass().getResource("/"+deck[27]+".gif")).getImage();
		p3Img28.setIcon(new ImageIcon(img28));
		p3Img28.setBounds(92, 11, 72, 94);
		frmCardGame.getContentPane().add(p3Img28);
		
		JButton p3Img29 = new JButton("");
		p3Img29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k3++;
					player3++;
					if(player3==1){ 
						if(k3==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[28];
								playerList[index]=4;
								ch=deck[28].charAt(1);
								//Image playCard29=new ImageIcon(this.getClass().getResource("/"+deck[28]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard29));
								SetPlayerCard(index, 28);
								
								Image img29=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img29.setIcon(new ImageIcon(img29));
								index++;
								selectPlayer=1;
								deck[28]="ok";
							}
							else{
								if(deck[28].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k3=0;
									player3=0;
								}
								else{
									//Image playCard29=new ImageIcon(this.getClass().getResource("/"+deck[28]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard29));
									SetPlayerCard(index, 28);
									
									Image img29=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img29.setIcon(new ImageIcon(img29));
									getCard[index]=deck[28];
									playerList[index]=4;
									index++;
									selectPlayer=1;
									deck[28]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k3=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for Player "+selectPlayer);
				}
			}
		});
		Image img29=new ImageIcon(this.getClass().getResource("/"+deck[28]+".gif")).getImage();
		p3Img29.setIcon(new ImageIcon(img29));
		p3Img29.setBounds(174, 11, 72, 94);
		frmCardGame.getContentPane().add(p3Img29);
		
		JButton p3Img30 = new JButton("");
		p3Img30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k4++;
					player3++;
					if(player3==1){
						if(k4==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[29];
								playerList[index]=4;
								ch=deck[29].charAt(1);
								//Image playCard30=new ImageIcon(this.getClass().getResource("/"+deck[29]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard30));
								SetPlayerCard(index, 29);
								
								Image img30=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img30.setIcon(new ImageIcon(img30));
								index++;
								selectPlayer=1;
								deck[29]="ok";
							}
							else{
								if(deck[29].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k4=0;
									player3=0;
								}
								else{
									//Image playCard30=new ImageIcon(this.getClass().getResource("/"+deck[29]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard30));
									SetPlayerCard(index, 29);
									
									Image img30=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img30.setIcon(new ImageIcon(img30));
									getCard[index]=deck[29];
									playerList[index]=4;
									index++;
									selectPlayer=1;
									deck[29]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k4=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for Player "+selectPlayer);
				}
			}
		});
		Image img30=new ImageIcon(this.getClass().getResource("/"+deck[29]+".gif")).getImage();
		p3Img30.setIcon(new ImageIcon(img30));
		p3Img30.setBounds(256, 11, 72, 94);
		frmCardGame.getContentPane().add(p3Img30);
		
		JButton p3Img31 = new JButton("");
		p3Img31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k5++;
					player3++;
					if(player3==1){
						if(k5==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[30];
								playerList[index]=4;
								ch=deck[30].charAt(1);
								//Image playCard31=new ImageIcon(this.getClass().getResource("/"+deck[30]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard31));
								SetPlayerCard(index, 30);
								
								Image img31=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img31.setIcon(new ImageIcon(img31));
								index++;
								selectPlayer=1;
								deck[30]="ok";
							}
							else{
								if(deck[30].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k5=0;
									player3=0;
								}
								else{
									//Image playCard31=new ImageIcon(this.getClass().getResource("/"+deck[30]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard31));
									SetPlayerCard(index, 30);
									
									Image img31=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img31.setIcon(new ImageIcon(img31));
									getCard[index]=deck[30];
									playerList[index]=4;
									selectPlayer=1;
									index++;
									deck[30]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k5=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img31=new ImageIcon(this.getClass().getResource("/"+deck[30]+".gif")).getImage();
		p3Img31.setIcon(new ImageIcon(img31));
		p3Img31.setBounds(337, 11, 72, 94);
		frmCardGame.getContentPane().add(p3Img31);
		
		JButton p3Img32 = new JButton("");
		p3Img32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k6++;
					player3++;
					if(player3==1){
						if(k6==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[31];
								playerList[index]=4;
								ch=deck[31].charAt(1);
								//Image playCard32=new ImageIcon(this.getClass().getResource("/"+deck[31]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard32));
								SetPlayerCard(index, 31);
								
								Image img32=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img32.setIcon(new ImageIcon(img32));
								index++;
								selectPlayer=1;
								deck[31]="ok";
							}
							else{
								if(deck[31].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k6=0;
									player3=0;
								}
								else{
									//Image playCard32=new ImageIcon(this.getClass().getResource("/"+deck[31]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard32));
									SetPlayerCard(index, 31);
									
									Image img32=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img32.setIcon(new ImageIcon(img32));
									getCard[index]=deck[31];
									playerList[index]=4;
									selectPlayer=1;
									index++;
									deck[31]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k6=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img32=new ImageIcon(this.getClass().getResource("/"+deck[31]+".gif")).getImage();
		p3Img32.setIcon(new ImageIcon(img32));
		p3Img32.setBounds(10, 116, 72, 94);
		frmCardGame.getContentPane().add(p3Img32);
		
		JButton p3Img33 = new JButton("");
		p3Img33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k7++;
					player3++;
					if(player3==1){
						if(k7==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[32];
								playerList[index]=4;
								ch=deck[32].charAt(1);
								//Image playCard33=new ImageIcon(this.getClass().getResource("/"+deck[32]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard33));
								SetPlayerCard(index, 32);
								
								Image img33=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img33.setIcon(new ImageIcon(img33));
								index++;
								selectPlayer=1;
								deck[32]="ok";
							}
							else{
								if(deck[32].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k7=0;
									player3=0;
								}
								else{
									//Image playCard33=new ImageIcon(this.getClass().getResource("/"+deck[32]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard33));
									SetPlayerCard(index, 32);
									
									Image img33=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img33.setIcon(new ImageIcon(img33));
									getCard[index]=deck[32];
									playerList[index]=4;
									selectPlayer=1;
									index++;
									deck[32]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k7=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img33=new ImageIcon(this.getClass().getResource("/"+deck[32]+".gif")).getImage();
		p3Img33.setIcon(new ImageIcon(img33));
		p3Img33.setBounds(92, 116, 72, 94);
		frmCardGame.getContentPane().add(p3Img33);
		
		JButton p3Img34 = new JButton("");
		p3Img34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k8++;
					player3++;
					if(player3==1){
						if(k8==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[33];
								playerList[index]=4;
								ch=deck[33].charAt(1);
								//Image playCard34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard34));
								SetPlayerCard(index, 33);
								
								Image img34=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img34.setIcon(new ImageIcon(img34));
								index++;
								selectPlayer=1;
								deck[33]="ok";
							}
							else{
								if(deck[33].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k8=0;
									player3=0;
								}
								else{
									//Image playCard34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard34));
									SetPlayerCard(index, 33);
									
									Image img34=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img34.setIcon(new ImageIcon(img34));
									getCard[index]=deck[33];
									playerList[index]=4;
									selectPlayer=1;
									index++;
									deck[33]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k8=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
		p3Img34.setIcon(new ImageIcon(img34));
		p3Img34.setBounds(174, 116, 72, 94);
		frmCardGame.getContentPane().add(p3Img34);
		
		JButton p3Img35 = new JButton("");
		p3Img35.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k9++;
					player3++;
					if(player3==1){
						if(k9==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[34];
								playerList[index]=4;
								ch=deck[34].charAt(1);
								//Image playCard34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard34));
								SetPlayerCard(index, 34);
								
								Image img35=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img35.setIcon(new ImageIcon(img35));
								index++;
								selectPlayer=1;
								deck[34]="ok";
							}
							else{
								if(deck[34].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k9=0;
									player3=0;
								}
								else{
									//Image playCard34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard34));
									SetPlayerCard(index, 34);
									
									Image img35=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img35.setIcon(new ImageIcon(img35));
									getCard[index]=deck[34];
									playerList[index]=4;
									selectPlayer=1;
									index++;
									deck[34]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k9=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img35=new ImageIcon(this.getClass().getResource("/"+deck[34]+".gif")).getImage();
		p3Img35.setIcon(new ImageIcon(img35));
		p3Img35.setBounds(256, 116, 72, 94);
		frmCardGame.getContentPane().add(p3Img35);
		
		JButton p3Img36 = new JButton("");
		p3Img36.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k10++;
					player3++;
					if(player3==1){
						if(k10==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[35];
								playerList[index]=4;
								ch=deck[35].charAt(1);
								//Image playCard34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard34));
								SetPlayerCard(index, 35);
								
								Image img36=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img36.setIcon(new ImageIcon(img36));
								index++;
								selectPlayer=1;
								deck[35]="ok";
							}
							else{
								if(deck[35].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k10=0;
									player3=0;
								}
								else{
									//Image playCard34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard34));
									SetPlayerCard(index, 35);
									
									Image img36=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img36.setIcon(new ImageIcon(img36));
									getCard[index]=deck[35];
									playerList[index]=4;
									selectPlayer=1;
									index++;
									deck[35]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k10=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img36=new ImageIcon(this.getClass().getResource("/"+deck[35]+".gif")).getImage();
		p3Img36.setIcon(new ImageIcon(img36));
		p3Img36.setBounds(10, 221, 72, 94);
		frmCardGame.getContentPane().add(p3Img36);
		
		JButton p3Img37 = new JButton("");
		p3Img37.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k11++;
					player3++;
					if(player3==1){
						if(k11==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[36];
								playerList[index]=4;
								ch=deck[36].charAt(1);
								//Image playCard34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard34));
								SetPlayerCard(index, 36);
								
								Image img37=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img37.setIcon(new ImageIcon(img37));
								index++;
								selectPlayer=1;
								deck[36]="ok";
							}
							else{
								if(deck[36].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k11=0;
									player3=0;
								}
								else{
									//Image playCard34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard34));
									SetPlayerCard(index, 36);
									
									Image img37=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img37.setIcon(new ImageIcon(img37));
									getCard[index]=deck[36];
									playerList[index]=4;
									selectPlayer=1;
									index++;
									deck[36]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k11=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img37=new ImageIcon(this.getClass().getResource("/"+deck[36]+".gif")).getImage();
		p3Img37.setIcon(new ImageIcon(img37));
		p3Img37.setBounds(92, 221, 72, 94);
		frmCardGame.getContentPane().add(p3Img37);
		
		JButton p3Img38 = new JButton("");
		p3Img38.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k12++;
					player3++;
					if(player3==1){
						if(k12==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[37];
								playerList[index]=4;
								ch=deck[37].charAt(1);
								//Image playCard34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard34));
								SetPlayerCard(index, 37);
								
								Image img38=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img38.setIcon(new ImageIcon(img38));
								index++;
								selectPlayer=1;
								deck[37]="ok";
							}
							else{
								if(deck[37].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k12=0;
									player3=0;
								}
								else{
									//Image playCard34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard34));
									SetPlayerCard(index, 37);
									
									Image img38=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img38.setIcon(new ImageIcon(img38));
									getCard[index]=deck[37];
									playerList[index]=4;
									selectPlayer=1;
									index++;
									deck[37]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k12=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img38=new ImageIcon(this.getClass().getResource("/"+deck[37]+".gif")).getImage();
		p3Img38.setIcon(new ImageIcon(img38));
		p3Img38.setBounds(174, 221, 72, 94);
		frmCardGame.getContentPane().add(p3Img38);
		
		JButton p3Img39 = new JButton("");
		p3Img39.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==4) {
					k13++;
					player3++;
					if(player3==1){
						if(k13==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[38];
								playerList[index]=4;
								ch=deck[38].charAt(1);
								//Image playCard34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
								//p3Card.setIcon(new ImageIcon(playCard34));
								SetPlayerCard(index, 38);
								
								Image img39=new ImageIcon(this.getClass().getResource("")).getImage();
								p3Img39.setIcon(new ImageIcon(img39));
								index++;
								selectPlayer=1;
								deck[38]="ok";
							}
							else{
								if(deck[38].charAt(1) != ch){
									for(int i=26;i<39;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									k13=0;
									player3=0;
								}
								else{
									//Image playCard34=new ImageIcon(this.getClass().getResource("/"+deck[33]+".gif")).getImage();
									//p3Card.setIcon(new ImageIcon(playCard34));
									SetPlayerCard(index, 38);
									
									Image img39=new ImageIcon(this.getClass().getResource("")).getImage();
									p3Img39.setIcon(new ImageIcon(img39));
									getCard[index]=deck[38];
									playerList[index]=4;
									selectPlayer=1;
									index++;
									deck[38]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player3=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						k13=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img39=new ImageIcon(this.getClass().getResource("/"+deck[38]+".gif")).getImage();
		p3Img39.setIcon(new ImageIcon(img39));
		p3Img39.setBounds(256, 221, 72, 94);
		frmCardGame.getContentPane().add(p3Img39);
		
		JButton p4Img40 = new JButton("");                         //............................Player 4..............................
		p4Img40.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l1++;
					player4++;
					if(player4==1){
						if(l1==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[39];
								playerList[index]=3;
								ch=deck[39].charAt(1);
								//Image playCard40=new ImageIcon(this.getClass().getResource("/"+deck[39]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard40));
								SetPlayerCard(index, 39);
								
								Image img40=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img40.setIcon(new ImageIcon(img40));
								index++;
								selectPlayer++;
								deck[39]="ok";
							}
							else{
								if(deck[39].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l1=0;
									player4=0;
								}
								else{
									//Image playCard40=new ImageIcon(this.getClass().getResource("/"+deck[39]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard40));
									SetPlayerCard(index, 39);
									
									Image img40=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img40.setIcon(new ImageIcon(img40));
									getCard[index]=deck[39];
									playerList[index]=3;
									index++;
									selectPlayer++;
									deck[39]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l1=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for Player "+selectPlayer);
				}
				/*if(l1==1){
					Image playCard40=new ImageIcon(this.getClass().getResource("/"+deck[39]+".gif")).getImage();
					p4Card.setIcon(new ImageIcon(playCard40));
					
					Image img40=new ImageIcon(this.getClass().getResource("")).getImage();
					p4Img40.setIcon(new ImageIcon(img40));
				}
				else{
					JOptionPane.showMessageDialog(null, "This card has already been played");
				}*/
			}
		});
		Image img40=new ImageIcon(this.getClass().getResource("/"+deck[39]+".gif")).getImage();
		p4Img40.setIcon(new ImageIcon(img40));
		p4Img40.setBounds(739, 11, 72, 94);
		frmCardGame.getContentPane().add(p4Img40);
		
		JButton p4Img41 = new JButton("");
		p4Img41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l2++;
					player4++;
					if(player4==1){
						if(l2==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[40];
								playerList[index]=3;
								ch=deck[40].charAt(1);
								//Image playCard41=new ImageIcon(this.getClass().getResource("/"+deck[40]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard41));
								SetPlayerCard(index, 40);
								
								Image img41=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img41.setIcon(new ImageIcon(img41));
								index++;
								selectPlayer++;
								deck[40]="ok";
							}
							else{
								if(deck[40].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l2=0;
									player4=0;
								}
								else{
									//Image playCard41=new ImageIcon(this.getClass().getResource("/"+deck[40]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard41));
									SetPlayerCard(index, 40);
									
									Image img41=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img41.setIcon(new ImageIcon(img41));
									getCard[index]=deck[40];
									playerList[index]=3;
									index++;
									selectPlayer++;
									deck[40]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l2=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for Player "+selectPlayer);
				}
			}
		});
		Image img41=new ImageIcon(this.getClass().getResource("/"+deck[40]+".gif")).getImage();
		p4Img41.setIcon(new ImageIcon(img41));
		p4Img41.setBounds(821, 11, 72, 94);
		frmCardGame.getContentPane().add(p4Img41);
		
		JButton p4Img42 = new JButton("");
		p4Img42.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l3++;
					player4++;
					if(player4==1){
						if(l3==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[41];
								playerList[index]=3;
								ch=deck[41].charAt(1);
								//Image playCard42=new ImageIcon(this.getClass().getResource("/"+deck[41]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard42));
								SetPlayerCard(index, 41);
								
								Image img42=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img42.setIcon(new ImageIcon(img42));
								index++;
								selectPlayer++;
								deck[41]="ok";
							}
							else{
								if(deck[41].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l3=0;
									player4=0;
								}
								else{
									//Image playCard42=new ImageIcon(this.getClass().getResource("/"+deck[41]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard42));
									SetPlayerCard(index, 41);
									
									Image img42=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img42.setIcon(new ImageIcon(img42));
									getCard[index]=deck[41];
									playerList[index]=3;
									index++;
									selectPlayer++;
									deck[41]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l3=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for Player "+selectPlayer);
				}
			}
		});
		Image img42=new ImageIcon(this.getClass().getResource("/"+deck[41]+".gif")).getImage();
		p4Img42.setIcon(new ImageIcon(img42));
		p4Img42.setBounds(903, 11, 72, 94);
		frmCardGame.getContentPane().add(p4Img42);
		
		JButton p4Img43 = new JButton("");
		p4Img43.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l4++;
					player4++;
					if(player4==1){
						if(l4==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[42];
								playerList[index]=3;
								ch=deck[42].charAt(1);
								//Image playCard43=new ImageIcon(this.getClass().getResource("/"+deck[42]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard43));
								SetPlayerCard(index, 42);
								
								Image img43=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img43.setIcon(new ImageIcon(img43));
								index++;
								selectPlayer++;
								deck[42]="ok";
							}
							else{
								if(deck[42].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l4=0;
									player4=0;
								}
								else{
									//Image playCard43=new ImageIcon(this.getClass().getResource("/"+deck[42]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard43));
									SetPlayerCard(index, 42);
									
									Image img43=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img43.setIcon(new ImageIcon(img43));
									getCard[index]=deck[42];
									playerList[index]=3;
									index++;
									selectPlayer++;
									deck[42]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l4=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for Player "+selectPlayer);
				}
			}
		});
		Image img43=new ImageIcon(this.getClass().getResource("/"+deck[42]+".gif")).getImage();
		p4Img43.setIcon(new ImageIcon(img43));
		p4Img43.setBounds(985, 11, 72, 94);
		frmCardGame.getContentPane().add(p4Img43);
		
		JButton p4Img44 = new JButton("");
		p4Img44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l5++;
					player4++;
					if(player4==1){
						if(l5==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[43];
								playerList[index]=3;
								ch=deck[43].charAt(1);
								//Image playCard44=new ImageIcon(this.getClass().getResource("/"+deck[43]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard44));
								SetPlayerCard(index, 43);
								
								Image img44=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img44.setIcon(new ImageIcon(img44));
								index++;
								selectPlayer++;
								deck[43]="ok";
							}
							else{
								if(deck[43].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l5=0;
									player4=0;
								}
								else{
									//Image playCard44=new ImageIcon(this.getClass().getResource("/"+deck[43]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard44));
									SetPlayerCard(index, 43);
									
									Image img44=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img44.setIcon(new ImageIcon(img44));
									getCard[index]=deck[43];
									playerList[index]=3;
									selectPlayer++;
									index++;
									deck[43]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l5=0;
					}
				} else {
					JOptionPane.showMessageDialog(null,"Its time for player "+selectPlayer);
				}
			}
		});
		Image img44=new ImageIcon(this.getClass().getResource("/"+deck[43]+".gif")).getImage();
		p4Img44.setIcon(new ImageIcon(img44));
		p4Img44.setBounds(1067, 11, 72, 94);
		frmCardGame.getContentPane().add(p4Img44);
		
		JButton p4Img45 = new JButton("");
		p4Img45.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l6++;
					player4++;
					if(player4==1){
						if(l6==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[44];
								playerList[index]=3;
								ch=deck[44].charAt(1);
								//Image playCard45=new ImageIcon(this.getClass().getResource("/"+deck[44]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard45));
								SetPlayerCard(index, 44);
								
								Image img45=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img45.setIcon(new ImageIcon(img45));
								index++;
								selectPlayer++;
								deck[44]="ok";
							}
							else{
								if(deck[44].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l6=0;
									player4=0;
								}
								else{
									//Image playCard45=new ImageIcon(this.getClass().getResource("/"+deck[44]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard45));
									SetPlayerCard(index, 44);
									
									Image img45=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img45.setIcon(new ImageIcon(img45));
									getCard[index]=deck[44];
									playerList[index]=3;
									selectPlayer++;
									index++;
									deck[44]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l6=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img45=new ImageIcon(this.getClass().getResource("/"+deck[44]+".gif")).getImage();
		p4Img45.setIcon(new ImageIcon(img45));
		p4Img45.setBounds(821, 116, 72, 94);
		frmCardGame.getContentPane().add(p4Img45);
		
		JButton p4Img46 = new JButton("");
		p4Img46.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l7++;
					player4++;
					if(player4==1){
						if(l7==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[45];
								playerList[index]=3;
								ch=deck[45].charAt(1);
								//Image playCard46=new ImageIcon(this.getClass().getResource("/"+deck[45]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard46));
								SetPlayerCard(index, 45);
								
								Image img46=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img46.setIcon(new ImageIcon(img46));
								index++;
								selectPlayer++;
								deck[45]="ok";
							}
							else{
								if(deck[45].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l7=0;
									player4=0;
								}
								else{
									//Image playCard46=new ImageIcon(this.getClass().getResource("/"+deck[45]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard46));
									SetPlayerCard(index, 45);
									
									Image img46=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img46.setIcon(new ImageIcon(img46));
									getCard[index]=deck[45];
									playerList[index]=3;
									selectPlayer++;
									index++;
									deck[45]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l7=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img46=new ImageIcon(this.getClass().getResource("/"+deck[45]+".gif")).getImage();
		p4Img46.setIcon(new ImageIcon(img46));
		p4Img46.setBounds(903, 116, 72, 94);
		frmCardGame.getContentPane().add(p4Img46);
		
		JButton p4Img47 = new JButton("");
		p4Img47.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l8++;
					player4++;
					if(player4==1){
						if(l8==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[46];
								playerList[index]=3;
								ch=deck[46].charAt(1);
								//Image playCard47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard47));
								SetPlayerCard(index, 46);
								
								Image img47=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img47.setIcon(new ImageIcon(img47));
								index++;
								selectPlayer++;
								deck[46]="ok";
							}
							else{
								if(deck[46].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l8=0;
									player4=0;
								}
								else{
									//Image playCard47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard47));
									SetPlayerCard(index, 46);
									
									Image img47=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img47.setIcon(new ImageIcon(img47));
									getCard[index]=deck[46];
									playerList[index]=3;
									selectPlayer++;
									index++;
									deck[46]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l8=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
		p4Img47.setIcon(new ImageIcon(img47));
		p4Img47.setBounds(985, 116, 72, 94);
		frmCardGame.getContentPane().add(p4Img47);
		
		JButton p4Img48 = new JButton("");
		p4Img48.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l9++;
					player4++;
					if(player4==1){
						if(l9==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[47];
								playerList[index]=3;
								ch=deck[47].charAt(1);
								//Image playCard47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard47));
								SetPlayerCard(index, 47);
								
								Image img48=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img48.setIcon(new ImageIcon(img48));
								index++;
								selectPlayer++;
								deck[47]="ok";
							}
							else{
								if(deck[47].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l9=0;
									player4=0;
								}
								else{
									//Image playCard47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard47));
									SetPlayerCard(index, 47);
									
									Image img48=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img48.setIcon(new ImageIcon(img48));
									getCard[index]=deck[47];
									playerList[index]=3;
									selectPlayer++;
									index++;
									deck[47]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l9=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img48=new ImageIcon(this.getClass().getResource("/"+deck[47]+".gif")).getImage();
		p4Img48.setIcon(new ImageIcon(img48));
		p4Img48.setBounds(1067, 111, 72, 99);
		frmCardGame.getContentPane().add(p4Img48);
		
		JButton p4Img49 = new JButton("");
		p4Img49.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l10++;
					player4++;
					if(player4==1){
						if(l10==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[48];
								playerList[index]=3;
								ch=deck[48].charAt(1);
								//Image playCard47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard47));
								SetPlayerCard(index, 48);
								
								Image img49=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img49.setIcon(new ImageIcon(img49));
								index++;
								selectPlayer++;
								deck[48]="ok";
							}
							else{
								if(deck[48].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l10=0;
									player4=0;
								}
								else{
									//Image playCard47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard47));
									SetPlayerCard(index, 48);
									
									Image img49=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img49.setIcon(new ImageIcon(img49));
									getCard[index]=deck[48];
									playerList[index]=3;
									selectPlayer++;
									index++;
									deck[48]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l10=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img49=new ImageIcon(this.getClass().getResource("/"+deck[48]+".gif")).getImage();
		p4Img49.setIcon(new ImageIcon(img49));
		p4Img49.setBounds(821, 221, 72, 94);
		frmCardGame.getContentPane().add(p4Img49);
		
		JButton p4Img50 = new JButton("");
		p4Img50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l11++;
					player4++;
					if(player4==1){
						if(l11==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[49];
								playerList[index]=3;
								ch=deck[49].charAt(1);
								//Image playCard47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard47));
								SetPlayerCard(index, 49);
								
								Image img50=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img50.setIcon(new ImageIcon(img50));
								index++;
								selectPlayer++;
								deck[49]="ok";
							}
							else{
								if(deck[49].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l11=0;
									player4=0;
								}
								else{
									//Image playCard47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard47));
									SetPlayerCard(index, 49);
									
									Image img50=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img50.setIcon(new ImageIcon(img50));
									getCard[index]=deck[49];
									playerList[index]=3;
									selectPlayer++;
									index++;
									deck[49]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l11=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img50=new ImageIcon(this.getClass().getResource("/"+deck[49]+".gif")).getImage();
		p4Img50.setIcon(new ImageIcon(img50));
		p4Img50.setBounds(903, 221, 72, 94);
		frmCardGame.getContentPane().add(p4Img50);
		
		JButton p4Img51 = new JButton("");
		p4Img51.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l12++;
					player4++;
					if(player4==1){
						if(l12==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[50];
								playerList[index]=3;
								ch=deck[50].charAt(1);
								//Image playCard47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard47));
								SetPlayerCard(index, 50);
								
								Image img51=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img51.setIcon(new ImageIcon(img51));
								index++;
								selectPlayer++;
								deck[50]="ok";
							}
							else{
								if(deck[50].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l12=0;
									player4=0;
								}
								else{
									//Image playCard47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard47));
									SetPlayerCard(index, 50);
									
									Image img51=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img51.setIcon(new ImageIcon(img51));
									getCard[index]=deck[50];
									playerList[index]=3;
									selectPlayer++;
									index++;
									deck[50]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l12=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img51=new ImageIcon(this.getClass().getResource("/"+deck[50]+".gif")).getImage();
		p4Img51.setIcon(new ImageIcon(img51));
		p4Img51.setBounds(985, 221, 72, 94);
		frmCardGame.getContentPane().add(p4Img51);
		
		JButton p4Img52 = new JButton("");
		p4Img52.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectPlayer==3) {
					l13++;
					player4++;
					if(player4==1){
						if(l13==1){
							
							// Check from here
							
							if(index==0){
								getCard[index]=deck[51];
								playerList[index]=3;
								ch=deck[51].charAt(1);
								//Image playCard47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
								//p4Card.setIcon(new ImageIcon(playCard47));
								SetPlayerCard(index, 51);
								
								Image img52=new ImageIcon(this.getClass().getResource("")).getImage();
								p4Img52.setIcon(new ImageIcon(img52));
								index++;
								selectPlayer++;
								deck[51]="ok";
							}
							else{
								if(deck[51].charAt(1) != ch){
									for(int i=39;i<52;i++){
										if(deck[i].charAt(1)==ch){
											confirm=1;
											break;
										}
									}
								}
								if(confirm ==1){
									JOptionPane.showMessageDialog(null, "Please don't cheat !");
									confirm=0;
									l13=0;
									player4=0;
								}
								else{
									//Image playCard47=new ImageIcon(this.getClass().getResource("/"+deck[46]+".gif")).getImage();
									//p4Card.setIcon(new ImageIcon(playCard47));
									SetPlayerCard(index, 51);
									
									Image img52=new ImageIcon(this.getClass().getResource("")).getImage();
									p4Img52.setIcon(new ImageIcon(img52));
									getCard[index]=deck[51];
									playerList[index]=3;
									selectPlayer++;
									index++;
									deck[51]="ok";
									
								}
							}
							if(index==4){
								winPlayerIndex=gameCodeObj.SelectPlayer(getCard, playerList);
								winPlayer=playerList[winPlayerIndex];
								SetScore(winPlayer);
								JOptionPane.showMessageDialog(null, "Player "+winPlayer+" has won this trick.");
								selectPlayer=winPlayer;
								index=0;
								player1=0;
								player2=0;
								player3=0;
								player4=0;
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "This card has already been played.");
							player4=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Your turn is on running! Please wait for next turn.");
						l13=0;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Its time for player "+selectPlayer);
				}
			}
		});
		Image img52=new ImageIcon(this.getClass().getResource("/"+deck[51]+".gif")).getImage();
		p4Img52.setIcon(new ImageIcon(img52));
		p4Img52.setBounds(1067, 221, 72, 94);
		frmCardGame.getContentPane().add(p4Img52);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 333, 318, -7);
		frmCardGame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.ORANGE);
		separator_1.setBackground(Color.ORANGE);
		separator_1.setBounds(10, 327, 318, 8);
		frmCardGame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.ORANGE);
		separator_2.setBackground(Color.ORANGE);
		separator_2.setBounds(821, 326, 318, 8);
		frmCardGame.getContentPane().add(separator_2);
		
		p1Card = new JLabel("");
		p1Card.setBounds(400, 285, 72, 94);
		frmCardGame.getContentPane().add(p1Card);
		
		p2Card = new JLabel("");
		p2Card.setBounds(482, 285, 72, 94);
		frmCardGame.getContentPane().add(p2Card);
		
		p3Card = new JLabel("");
		p3Card.setBounds(564, 285, 72, 94);
		frmCardGame.getContentPane().add(p3Card);
		
		p4Card = new JLabel("");
		p4Card.setBounds(646, 282, 72, 94);
		frmCardGame.getContentPane().add(p4Card);
		
		JLabel lblPlayerScore = new JLabel("Player 4 Score :");
		lblPlayerScore.setBounds(363, 133, 109, 22);
		frmCardGame.getContentPane().add(lblPlayerScore);
		
		lblp4Score = new JLabel("");
		lblp4Score.setBounds(373, 154, 53, 22);
		frmCardGame.getContentPane().add(lblp4Score);
		
		JLabel lblPlayerScore_1 = new JLabel("Player 3 Score :");
		lblPlayerScore_1.setBounds(690, 137, 121, 22);
		frmCardGame.getContentPane().add(lblPlayerScore_1);
		
		lblp3Score = new JLabel("");
		lblp3Score.setBounds(719, 170, 53, 22);
		frmCardGame.getContentPane().add(lblp3Score);
		
		JLabel lblNewLabel = new JLabel("Player 1 Score :");
		lblNewLabel.setBounds(373, 426, 121, 22);
		frmCardGame.getContentPane().add(lblNewLabel);
		
		lblp1Score = new JLabel("");
		lblp1Score.setBounds(383, 451, 53, 22);
		frmCardGame.getContentPane().add(lblp1Score);
		
		JLabel lblPlayerScore_2 = new JLabel("Player 2 Score :");
		lblPlayerScore_2.setBounds(690, 430, 109, 27);
		frmCardGame.getContentPane().add(lblPlayerScore_2);
		
		lblp2Score = new JLabel("");
		lblp2Score.setBounds(711, 451, 53, 22);
		frmCardGame.getContentPane().add(lblp2Score);
		//System.out.println(i);
	}
}
