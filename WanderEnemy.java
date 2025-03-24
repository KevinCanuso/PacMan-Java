package hws.hw8;

/**
 * WanderEnemy class.
 *
 * @author Kevin Canuso
 * @version 03/25/2024
 */
public class WanderEnemy extends Enemy {

    public static final int RELEASE_TIME = 180;

    /**
     * WanderEnemy Constructor.
     *
     * @param levelData level data
     * @param spawnLocation location of wall enemy spawn
     */
    public WanderEnemy(Level levelData, Point spawnLocation) {
        super(levelData, spawnLocation, RELEASE_TIME);
    }

    @Override
    public void update() {
        if (isCenteredOnGrid()) {
            setDesiredDirection(getDesiredDirection().getRandomTurn());
        }
        super.update();
    }

    @Override
    public void draw() {
        String imagePath = "";
        if (isEdible()) {
            imagePath = "hws/hw8/img/brackets_scared.png";
        } else {
            imagePath = "hws/hw8/img/brackets.png";
        }
        DuckManGame.drawImage(getCurrentPosition(), imagePath);
    }
}
