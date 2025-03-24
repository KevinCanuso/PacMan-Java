package hws.hw8;

/**
 * WallEnemy class.
 *
 * @author Kevin Canuso
 * @version 03/25/2024
 */
public class WallEnemy extends Enemy {
    public static final int RELEASE_TIME = 360;

    /**
     * WallEnemy Constructor.
     *
     * @param levelData level data
     * @param spawnLocation location of wall enemy spawn
     */
    public WallEnemy(Level levelData, Point spawnLocation) {
        super(levelData, spawnLocation, RELEASE_TIME);
    }

    @Override
    public void draw() {
        String imagePath = "";
        if (isEdible()) {
            imagePath = "hws/hw8/img/parentheses_scared.png";
        } else {
            imagePath = "hws/hw8/img/parentheses.png";
        }
        DuckManGame.drawImage(getCurrentPosition(), imagePath);
    }

    @Override
        public void update() {
    // Intentionally left blank to prevent inherited update behavior.
    }

}
