package hws.hw8;

/**
 * MagicEgg.
 *
 * @author Kevin Canuso
 * @version 03/25/2024
 */
public class MagicEgg extends Dot {

    public static final int POINTS = 50;

    /**
     * Constructor MagicEgg.
     *
     * @param position position of egg
     */
    public MagicEgg(Point position) {
        super(position);
    }

    @Override
    public void draw() {
        String imagePath = "hws/hw8/img/egg.png";
        DuckManGame.drawImage(position, imagePath);
    }
}
