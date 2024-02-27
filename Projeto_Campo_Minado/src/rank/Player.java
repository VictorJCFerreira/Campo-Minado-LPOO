package rank;

public class Player {

	private String name;
    private int points;

    public Player(String name, int points){
        
        this.name = name;
        this.points = points;
    }

    public Player(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
	
}
