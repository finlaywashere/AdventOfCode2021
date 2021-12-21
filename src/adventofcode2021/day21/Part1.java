package adventofcode2021.day21;

public class Part1 {
	public static void main(String[] args) throws Exception{
		int p1Pos = 10;
		int p2Pos = 7;
		int curr = 0;
		int p1Score = 0, p2Score = 0;
		boolean p1Turn = true;
		int rolls = 0;
		while(true) {
			int move = 3*curr+6;
			curr += 3;
			while(curr > 100) curr -= 100;
			
			if(p1Turn) {
				p1Pos += move;
				while(p1Pos > 10) p1Pos -= 10;
				p1Score += p1Pos;
			}else {
				p2Pos += move;
				while(p2Pos > 10) p2Pos -= 10;
				p2Score += p2Pos;
			}
			p1Turn = !p1Turn;
			rolls += 3;
			
			if(p1Score >= 1000 || p2Score >= 1000) {
				break;
			}
		}
		int loser = (p1Score < p2Score ? p1Score : p2Score);
		System.out.println("Solution: "+(loser*rolls));
	}
}
