package game.colony;

public class Colony {
    private int population;
    private int wealth;
//    private Resource resources;
    private double growingSize;

    public Colony(int population, int wealth) {
        this.population = population;
        this.wealth = wealth;
        this.growingSize = 1.0;
    }

    public void setGrowingSize(double growingSize) {
        this.growingSize = growingSize;
    }
}
