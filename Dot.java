package hws.hw8;

/**
 * Dot.
 *
 * @author Kevin Canuso
 * @version 03/25/2024
 */
public class Dot implements Drawable {

    public static final int POINTS = 10;
    protected Point position;

    /**
     * Construct of Dot.
     *
     * @param position position of dot
     */
    public Dot(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public void draw() {
        String imagePath = "hws/hw8/img/dot.png";
        DuckManGame.drawImage(position, imagePath);
    }
}
