package game.colony;

/**
 * Colony class, holds all the information about a colony
 * which player will create on a habitable planet
 */
public class Colony {
    /**
     * The Population.
     */
    private int population;
    /**
     * The Wealth.
     */
    private int wealth;
    /**
     * The Growing size.
     */
//    private Resource resources;
    private double growingSize;

    /**
     * Instantiates a new Colony with specified population and wealth.
     *
     * @param population the population
     * @param wealth     the wealth
     */
    public Colony(int population, int wealth) {
        this.population = population;
        this.wealth = wealth;
        this.growingSize = 1.0;
    }

    /**
     * Sets colonys growing factor
     *
     * @param growingSize the growing size
     */
    public void setGrowingSize(double growingSize) {
        this.growingSize = growingSize;
    }
}
