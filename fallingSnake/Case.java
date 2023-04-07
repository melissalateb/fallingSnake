package fallingSnake;

public class Case {
	
	private Obstacle obstacle ; 
	
	public Case() {
		
	}
	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}
	public Case(Obstacle ob) {
		this.obstacle = ob;
	}
	public Obstacle getObstacle() {
		return obstacle;
	}
}
