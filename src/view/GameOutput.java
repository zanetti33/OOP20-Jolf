package view;

public interface GameOutput extends MapOutput {
	
	void updateTotalShotsCount(int totalShots);
	
	void gameFinished();
	
}
