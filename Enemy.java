package hws.hw8;

/**
 * Abstract Enemy class.
 *
 * @author Kevin Canuso
 * @version 03/25/2024
 */
abstract class Enemy extends Actor {

    public static final double ENEMY_SPEED = 0.06;
    public static final int EDIBLE_DURATION = 300;
    public static final int POINTS = 200;

    protected int timeUntilReleased;
    protected int timeUntilNormal;

    /**
     * Enemy constructor.
     *
     * @param levelData level
     * @param spawnLocation spawn location
     * @param timeUntilReleased release time
     */
    Enemy(Level levelData, Point spawnLocation, int timeUntilReleased) {
        super(levelData, spawnLocation, Direction.UP, ENEMY_SPEED);
        this.timeUntilReleased = timeUntilReleased;
        timeUntilNormal = 0;
    }

    /**
     * make enemy edible.
     */
    public void makeEdible() {
        if (timeUntilReleased <= 0) {
            timeUntilNormal = EDIBLE_DURATION;
            setDesiredDirection(getDesiredDirection().getOpposite());
        }
    }

    public boolean isEdible() {
        return timeUntilNormal > 0;
    }

    @Override
    public void reset() {
        super.reset();
        timeUntilNormal = 0;
    }

    @Override
    public void update() {
        if (timeUntilReleased > 0) {
            timeUntilReleased--;
            return;  // Stop update if the enemy isn't released yet.
        }

        if (isEdible()) {
            timeUntilNormal--;
        }

        if (timeUntilReleased <= 0 && !isEdible() && isStopped()) {
            setDesiredDirection(getDesiredDirection().getRandomTurn());
        }

        super.update();
    }
}
