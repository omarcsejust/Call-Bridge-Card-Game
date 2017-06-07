
public class GameCode {
	
        public int SelectPlayer(String ss[],int player[]){
		
		char ch,getRank;
		int rank,P,index=0;
		int cardValue[]=new int[4];
		
        ch=ss[0].charAt(1);
		
		getRank=ss[0].charAt(0);        //................Player1 Trick..........................
		
		if(getRank=='a'){
			cardValue[index]=14;
			index++;
		}
		else if(getRank=='k'){
			cardValue[index]=13;
			index++;
		}
		else if(getRank=='q'){
			cardValue[index]=12;
			index++;
		}
		else if(getRank=='j'){
			cardValue[index]=11;
			index++;
		}
		else if(getRank=='9'){
			cardValue[index]=9;
			index++;
		}
		else if(getRank=='8'){
			cardValue[index]=8;
			index++;
		}
		else if(getRank=='7'){
			cardValue[index]=7;
			index++;
		}
		else if(getRank=='6'){
			cardValue[index]=6;
			index++;
		}
		else if(getRank=='5'){
			cardValue[index]=5;
			index++;
		}
		else if(getRank=='4'){
			cardValue[index]=4;
			index++;
		}
		else if(getRank=='3'){
			cardValue[index]=3;
			index++;
		}
		else if(getRank=='2'){
			cardValue[index]=2;
			index++;
		}
		else if(getRank=='t'){
			cardValue[index]=10;
			index++;
		}
		
		
		if(ss[1].charAt(1)==ch){             //..........Player2 Trick...................
			getRank=ss[1].charAt(0);
			
			if(getRank=='a'){
				cardValue[index]=14;
				index++;
			}
			else if(getRank=='k'){
				cardValue[index]=13;
				index++;
			}
			else if(getRank=='q'){
				cardValue[index]=12;
				index++;
			}
			else if(getRank=='j'){
				cardValue[index]=11;
				index++;
			}
			else if(getRank=='9'){
				cardValue[index]=9;
				index++;
			}
			else if(getRank=='8'){
				cardValue[index]=8;
				index++;
			}
			else if(getRank=='7'){
				cardValue[index]=7;
				index++;
			}
			else if(getRank=='6'){
				cardValue[index]=6;
				index++;
			}
			else if(getRank=='5'){
				cardValue[index]=5;
				index++;
			}
			else if(getRank=='4'){
				cardValue[index]=4;
				index++;
			}
			else if(getRank=='3'){
				cardValue[index]=3;
				index++;
			}
			else if(getRank=='2'){
				cardValue[index]=2;
				index++;
			}
			else if(getRank=='t'){
				cardValue[index]=10;
				index++;
			}
			
		}
		else if(ss[1].charAt(1)=='s'){       //..............Player2 Term................................
			getRank=ss[1].charAt(0);
			
			if(getRank=='a'){
				cardValue[index]=27;
				index++;
			}
			else if(getRank=='k'){
				cardValue[index]=26;
				index++;
			}
			else if(getRank=='q'){
				cardValue[index]=25;
				index++;
			}
			else if(getRank=='j'){
				cardValue[index]=24;
				index++;
			}
			else if(getRank=='9'){
				cardValue[index]=22;
				index++;
			}
			else if(getRank=='8'){
				cardValue[index]=21;
				index++;
			}
			else if(getRank=='7'){
				cardValue[index]=20;
				index++;
			}
			else if(getRank=='6'){
				cardValue[index]=19;
				index++;
			}
			else if(getRank=='5'){
				cardValue[index]=18;
				index++;
			}
			else if(getRank=='4'){
				cardValue[index]=17;
				index++;
			}
			else if(getRank=='3'){
				cardValue[index]=16;
				index++;
			}
			else if(getRank=='2'){
				cardValue[index]=15;
				index++;
			}
			else if(getRank=='t'){
				cardValue[index]=23;
				index++;
			}
			
		}
		
		else{
			cardValue[index]=-1;
			index++;
		}
		
		
		
        if(ss[2].charAt(1)==ch){                    //....................Player3 Trick..........................
			
            getRank=ss[2].charAt(0);
			
			if(getRank=='a'){
				cardValue[index]=14;
				index++;
			}
			else if(getRank=='k'){
				cardValue[index]=13;
				index++;
			}
			else if(getRank=='q'){
				cardValue[index]=12;
				index++;
			}
			else if(getRank=='j'){
				cardValue[index]=11;
				index++;
			}
			else if(getRank=='9'){
				cardValue[index]=9;
				index++;
			}
			else if(getRank=='8'){
				cardValue[index]=8;
				index++;
			}
			else if(getRank=='7'){
				cardValue[index]=7;
				index++;
			}
			else if(getRank=='6'){
				cardValue[index]=6;
				index++;
			}
			else if(getRank=='5'){
				cardValue[index]=5;
				index++;
			}
			else if(getRank=='4'){
				cardValue[index]=4;
				index++;
			}
			else if(getRank=='3'){
				cardValue[index]=3;
				index++;
			}
			else if(getRank=='2'){
				cardValue[index]=2;
				index++;
			}
			else if(getRank=='t'){
				cardValue[index]=10;
				index++;
			}
		}
		else if(ss[2].charAt(1)=='s'){       //..............Player3 Term................................
			getRank=ss[2].charAt(0);
			
			if(getRank=='a'){
				cardValue[index]=27;
				index++;
			}
			else if(getRank=='k'){
				cardValue[index]=26;
				index++;
			}
			else if(getRank=='q'){
				cardValue[index]=25;
				index++;
			}
			else if(getRank=='j'){
				cardValue[index]=24;
				index++;
			}
			else if(getRank=='9'){
				cardValue[index]=22;
				index++;
			}
			else if(getRank=='8'){
				cardValue[index]=21;
				index++;
			}
			else if(getRank=='7'){
				cardValue[index]=20;
				index++;
			}
			else if(getRank=='6'){
				cardValue[index]=19;
				index++;
			}
			else if(getRank=='5'){
				cardValue[index]=18;
				index++;
			}
			else if(getRank=='4'){
				cardValue[index]=17;
				index++;
			}
			else if(getRank=='3'){
				cardValue[index]=16;
				index++;
			}
			else if(getRank=='2'){
				cardValue[index]=15;
				index++;
			}
			else if(getRank=='t'){
				cardValue[index]=23;
				index++;
			}
			
		}
        else{
			cardValue[index]=-2;
			index++;
		}
        
        if(ss[3].charAt(1)==ch){              //......................Player4 Trick.............................
			
            getRank=ss[3].charAt(0);
			
			if(getRank=='a'){
				cardValue[index]=14;
				index++;
			}
			else if(getRank=='k'){
				cardValue[index]=13;
				index++;
			}
			else if(getRank=='q'){
				cardValue[index]=12;
				index++;
			}
			else if(getRank=='j'){
				cardValue[index]=11;
				index++;
			}
			else if(getRank=='9'){
				cardValue[index]=9;
				index++;
			}
			else if(getRank=='8'){
				cardValue[index]=8;
				index++;
			}
			else if(getRank=='7'){
				cardValue[index]=7;
				index++;
			}
			else if(getRank=='6'){
				cardValue[index]=6;
				index++;
			}
			else if(getRank=='5'){
				cardValue[index]=5;
				index++;
			}
			else if(getRank=='4'){
				cardValue[index]=4;
				index++;
			}
			else if(getRank=='3'){
				cardValue[index]=3;
				index++;
			}
			else if(getRank=='2'){
				cardValue[index]=2;
				index++;
			}
			else if(getRank=='t'){
				cardValue[index]=10;
				index++;
			}
		}
		else if(ss[3].charAt(1)=='s'){       //..............Player4 Term................................
			getRank=ss[3].charAt(0);
			
			if(getRank=='a'){
				cardValue[index]=27;
				index++;
			}
			else if(getRank=='k'){
				cardValue[index]=26;
				index++;
			}
			else if(getRank=='q'){
				cardValue[index]=25;
				index++;
			}
			else if(getRank=='j'){
				cardValue[index]=24;
				index++;
			}
			else if(getRank=='9'){
				cardValue[index]=22;
				index++;
			}
			else if(getRank=='8'){
				cardValue[index]=21;
				index++;
			}
			else if(getRank=='7'){
				cardValue[index]=20;
				index++;
			}
			else if(getRank=='6'){
				cardValue[index]=19;
				index++;
			}
			else if(getRank=='5'){
				cardValue[index]=18;
				index++;
			}
			else if(getRank=='4'){
				cardValue[index]=17;
				index++;
			}
			else if(getRank=='3'){
				cardValue[index]=16;
				index++;
			}
			else if(getRank=='2'){
				cardValue[index]=15;
				index++;
			}
			else if(getRank=='t'){
				cardValue[index]=23;
				index++;
			}
			
		}
        else{
			cardValue[index]=-3;
		}
		
		
		/*while(true){
			ch=(char)ss[0].charAt(1);
			if(ch=='s'){
				System.out.println("The card is Spade");
				break;
			}
		}*/
        
        int getIndexOfWinPlayer=0,winPlayer,max=0;
        for(int i=0;i<cardValue.length;i++){
        	if(max<cardValue[i]){
        		max=cardValue[i];
        		getIndexOfWinPlayer=i;
        	}
        }
        
        winPlayer=player[getIndexOfWinPlayer];
        System.out.println("Player "+winPlayer+" won this trick");
        return getIndexOfWinPlayer;
		
	}
}
