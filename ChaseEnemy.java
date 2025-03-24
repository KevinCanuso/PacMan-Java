package hws.hw8;

/**
 * WallEnemy class.
 *
 * @author Kevin Canuso
 * @version 03/25/2024
 */
public class ChaseEnemy extends Enemy {

    public static final int RELEASE_TIME = 0;
    private Player player;

    /**
     * ChaseEnemy Constructor.
     *
     * @param levelData level data
     * @param spawnLocation location of wall enemy spawn
     * @param player player
     */
    public ChaseEnemy(Level levelData, Point spawnLocation, Player player) {
        super(levelData, spawnLocation, RELEASE_TIME);
        this.player = player;
    }

    @Override
    public void update() {
        if (isCenteredOnGrid()) {
            if (getCurrentDirection().isUpDown()) {
                if (player.getCurrentPosition().getX()
                    < getCurrentPosition().getX()) {
                    setDesiredDirection(Direction.LEFT);
                } else if (player.getCurrentPosition().getX()
                    > getCurrentPosition().getX()) {
                    setDesiredDirection(Direction.RIGHT);
                } else {
                    setDesiredDirection(getCurrentDirection());
                }

            } else if (getCurrentDirection() == Direction.LEFT
                || getCurrentDirection() == Direction.RIGHT) {
                if (player.getCurrentPosition().getY()
                    > getCurrentPosition().getY()) {
                    setDesiredDirection(Direction.UP);
                } else if (player.getCurrentPosition().getY()
                    < getCurrentPosition().getY()) {
                    setDesiredDirection(Direction.DOWN);
                } else {
                    setDesiredDirection(getCurrentDirection());
                }
            }
        }
        super.update();
    }

    @Override
    public void draw() {
        String imagePath = "";
        if (isEdible()) {
            imagePath = "hws/hw8/img/semicolons_scared.png";
        } else {
            imagePath = "hws/hw8/img/semicolons.png";
        }
        DuckManGame.drawImage(getCurrentPosition(), imagePath);
    }
}
