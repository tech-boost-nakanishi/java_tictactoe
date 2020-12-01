import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
	
	private static int[][] combination = {
			{0, 1, 2},
			{3, 4, 5},
			{6, 7, 8},
			{0, 3, 6},
			{1, 4, 7},
			{2, 5, 8},
			{0, 4, 8},
			{2, 4, 6}
	};
	
	public static void computerLogic() {
		//コンピューターがリーチだったら
		for(int i = 0; i < combination.length; i++) {
			int blankcount = 0;
			int computercount = 0;
			int blankindex = 0;
			for(int j = 0; j < combination[i].length; j++) {
				if(Game.board[combination[i][j]].equals(State.BLANK.toString())) {
					blankcount++;
					blankindex = combination[i][j];
				}
				else if(Game.board[combination[i][j]].equals(State.COMPUTER.toString())) {
					computercount++;
				}
			}
			if(blankcount == 1 && computercount == 2) {
				Game.board[blankindex] = State.COMPUTER.toString();
				if(!checkWinner()) {
					Game.playerState = State.USER.toString();
				}
				return;
			}
		}
		
		//ユーザーがリーチだったら
		for(int i = 0; i < combination.length; i++) {
			int blankcount = 0;
			int usercount = 0;
			int blankindex = 0;
			for(int j = 0; j < combination[i].length; j++) {
				if(Game.board[combination[i][j]].equals(State.BLANK.toString())) {
					blankcount++;
					blankindex = combination[i][j];
				}
				else if(Game.board[combination[i][j]].equals(State.USER.toString())) {
					usercount++;
				}
			}
			if(blankcount == 1 && usercount == 2) {
				Game.board[blankindex] = State.COMPUTER.toString();
				if(!checkWinner()) {
					Game.playerState = State.USER.toString();
				}
				return;
			}
		}
		
		//それ以外はランダム
		List<Integer> blank = new ArrayList<Integer>();
		for(int i = 0; i < Game.board.length; i++) {
			if(Game.board[i].equals(State.BLANK.toString())) {
				blank.add(i);
			}
		}
		Game.board[blank.get(new Random().nextInt(blank.size()))] = State.COMPUTER.toString();
		if(!checkWinner()) {
			Game.playerState = State.USER.toString();
		}
		return;
	}
	
	public static boolean checkWinner() {
		for(int i = 0; i < combination.length; i++) {
			if(Game.board[combination[i][0]].equals(Game.board[combination[i][1]]) &&
			   Game.board[combination[i][0]].equals(Game.board[combination[i][2]]) &&
			   Game.board[combination[i][1]].equals(Game.board[combination[i][2]])) {
				if(Game.board[combination[i][0]].equals(State.USER.toString())) {
					//ユーザーの勝ち
					Game.playerWinner = State.USER.toString();
					Game.playerState = State.BLANK.toString();
					return true;
				}
				else if(Game.board[combination[i][0]].equals(State.COMPUTER.toString())) {
					//コンピューターの勝ち
					Game.playerWinner = State.COMPUTER.toString();
					Game.playerState = State.BLANK.toString();
					return true;
				}
			}
		}
		
		//引き分けの場合
		int count = 0;
		for(int i = 0; i < Game.board.length; i++) {
			if(Game.board[i].equals(State.BLANK.toString())) {
				count++;
			}
		}
		if(count == 0) {
			Game.playerWinner = State.DRAW.toString();
			Game.playerState = State.BLANK.toString();
			return true;
		}
		
		return false;
	}

}
