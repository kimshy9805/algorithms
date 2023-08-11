import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
	
	
	public boolean isValidSudoku(char[][] board) {
		Set<Character> setRow = new HashSet<>();
		
		// Validate Entire Row
		for (int i = 0; i < 9; i++) {
			setRow = new HashSet<>();
			for (int j = 0 ; j < 9; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				
				if (setRow.contains(board[i][j])) {
					return false;
				}
				setRow.add(board[i][j]);
			}
		}
		
		Set<Character> setCol = new HashSet<>();
		
		// Validate Entire Row
		for (int i = 0; i < 9; i++) {
			setCol = new HashSet<>();
			for (int j = 0 ; j < 9; j++) {
				if (board[j][i] == '.') {
					continue;
				}
				
				if (setCol.contains(board[j][i])) {
					return false;
				}
				setCol.add(board[j][i]);
			}
		}
		
		// Validate Each 3x3 sub-boxes
		for (int i = 0; i < 9; i = i + 3) {
			for (int j = 0; j < 9; j = j + 3) {
				if (!checkBlock(i, j, board)) {
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	public boolean checkBlock(int idxI, int idxJ, char[][] board) {
		Set<Character> set = new HashSet<>();
		
		for (int i = idxI; i <idxI + 3; i++) {
			for (int j = idxJ; j < idxJ + 3; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				
				if (set.contains(board[i][j])) {
					return false;
				}
				
				set.add(board[i][j]);
			}
		}
		return true;
		
	}
}
