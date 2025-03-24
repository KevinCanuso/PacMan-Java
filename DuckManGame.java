package hws.hw8;

import java.util.ArrayList;

/**
 * JMU CS themed Pac-Man clone.
 *
 * @author Kevin Canuso
 * @version 03/25/2024
 */
public class DuckManGame implements Playable {

    public static final int GRID_SIZE = 32;
    public static final Point PLAYER_SPAWN = new Point(8.5, 5.5);
    public static final Point ENEMY_SPAWN = new Point(8.5, 11.5);

    private boolean running;
    private Level levelMap;

    private ArrayList<Drawable> drawables;
    private ArrayList<Dot> dots;
    private ArrayList<Updatable> updatables;
    private ArrayList<Enemy> enemies;

    private NumericDisplay score;
    private NumericDisplay lives;

    private Player player;

    /**
     * Default constructor.
     */
    public DuckManGame() {
        levelMap = new Level(GRID_SIZE);
        drawables = new ArrayList<Drawable>();
        dots = new ArrayList<Dot>();
        updatables = new ArrayList<Updatable>();
        enemies = new ArrayList<Enemy>();

    }

    /**
     * Draw an image on the screen.
     *
     * @param position where to draw the image
     * @param imagePath path to the image file
     */
    public static void drawImage(Point position, String imagePath) {
        GameDriver.picture(position, GRID_SIZE, imagePath);
    }

    /**
     * Add a dot to the game.
     *
     * @param dot the dot to add
     */
    public void addDot(Dot dot) {
        dots.add(dot);
        drawables.add(dot);
    }

    /**
     * Adds an enemy.
     *
     * @param enemy enemy to add
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        drawables.add(enemy);
        updatables.add(enemy);
    }

    /**
     * Update the game state when the player collides with dots and enemies.
     */
    public void handlePlayerCollisions() {
        ArrayList<Dot> toRemove = new ArrayList<>();
        for (Dot dot : dots) {
            if (player.collidesWith(dot)) {
                if (dot instanceof MagicEgg) {
                    score.setValue(score.getValue() + MagicEgg.POINTS);
                    for (Enemy enemy : enemies) {
                        enemy.makeEdible();
                    }
                } else {
                    score.setValue(score.getValue() + Dot.POINTS);
                }
                toRemove.add(dot);
            }
        }
        for (Dot dot : toRemove) {
            dots.remove(dot);
            drawables.remove(dot);
        }
        if (dots.isEmpty()) {
            running = false;
        }

        for (Enemy enemy : enemies) {
            if (player.collidesWith(enemy)) {
                if (enemy.isEdible()) {
                    score.setValue(score.getValue() + Enemy.POINTS);
                    enemy.reset();
                } else {
                    lives.setValue(lives.getValue() - 1);
                    player.reset();
                    if (lives.getValue() <= 0) {
                        running = false;
                    }
                }
            }
        }
    }

    /**
     * Iterate through the level map and create a new dot/egg object if the
     * map has a dot/egg at that position.
     */
    public void spawnNewDots() {
        for (int x = 0; x < levelMap.getWidth(); x++) {
            for (int y = 0; y < levelMap.getHeight(); y++) {
                if (levelMap.isDot(x, y)) {
                    Dot dot = new Dot(new Point(x + 0.5, y + 0.5));
                    addDot(dot);
                } else if (levelMap.isEgg(x, y)) {
                    MagicEgg egg = new MagicEgg(
                        new Point(x + 0.5, y + 0.5));
                    addDot(egg);
                }
            }
        }
    }

    /**
     * Create and add a player to the game.
     */
    public void spawnNewPlayer() {
        player = new Player(levelMap, PLAYER_SPAWN);
        drawables.add(player);
        updatables.add(player);
    }

    /**
     * Create and add enemies to the game.
     */
    public void spawnNewEnemies() {
        Enemy chaseEnemy = new ChaseEnemy(levelMap, ENEMY_SPAWN, player);
        Enemy wanderEnemy = new WanderEnemy(levelMap, ENEMY_SPAWN);
        Enemy wallEnemy = new WallEnemy(levelMap, ENEMY_SPAWN);

        addEnemy(chaseEnemy);
        addEnemy(wanderEnemy);
        addEnemy(wallEnemy);
    }

    @Override
    public void startGame() {
        Point upperLeft = new Point(10, GameDriver.SCREEN_HEIGHT - 10);
        score = new NumericDisplay(upperLeft, "Points", 0);
        drawables.add(score);

        Point upperRight = new Point(GameDriver.SCREEN_WIDTH - 80,
                GameDriver.SCREEN_HEIGHT - 10);
        lives = new NumericDisplay(upperRight, "Lives", 3);
        drawables.add(lives);

        spawnNewDots();
        spawnNewPlayer();
        spawnNewEnemies();

        running = true;
    }

    @Override
    public void updateAll() {
        if (!running) {
            return;
        }

        handlePlayerCollisions();

        for (Updatable updatable : updatables) {
            updatable.update();
        }
    }

    @Override
    public void drawAll() {
        levelMap.draw();
        for (Drawable drawable : drawables) {
            drawable.draw();
        }
    }
}
