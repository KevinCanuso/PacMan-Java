package hws.hw8;

/**
 * A game that can be played.
 *
 * @author Kevin Canuso
 * @version 03/25/2024
 */
public class Player extends Actor {

    public static final double PLAYER_SPEED = 0.07;

    /**
     * Constructor.
     *
     * @param level the level
     * @param spawnLocation spawn location of player
     */
    public Player(Level level, Point spawnLocation) {
        super(level, spawnLocation, Direction.LEFT, PLAYER_SPEED);
    }

    @Override
    public void update() {
        if (GameDriver.upPressed()) {
            setDesiredDirection(Direction.UP);
        } else if (GameDriver.downPressed()) {
            setDesiredDirection(Direction.DOWN);
        } else if (GameDriver.leftPressed()) {
            setDesiredDirection(Direction.LEFT);
        } else if (GameDriver.rightPressed()) {
            setDesiredDirection(Direction.RIGHT);
        }

        super.update();
    }

    @Override
    public void draw() {
        String imagePath = "";
        switch (getCurrentDirection()) {
            case UP:
                imagePath = "hws/hw8/img/duck_up.png";
                break;
            case DOWN:
                imagePath = "hws/hw8/img/duck_down.png";
                break;
            case LEFT:
                imagePath = "hws/hw8/img/duck_left.png";
                break;
            case RIGHT:
                imagePath = "hws/hw8/img/duck_right.png";
                break;
        }

        DuckManGame.drawImage(getCurrentPosition(), imagePath);
    }

    /**
     * check if the player collides with a dot.
     *
     * @param dot dot
     * @return If the distance between the player's current position and
     * the dot's position is less than 0.5, this method should return true.
     */
    public boolean collidesWith(Dot dot) {
        double distance = getCurrentPosition().distance(dot.getPosition());
        return distance < 0.5;
    }
}
