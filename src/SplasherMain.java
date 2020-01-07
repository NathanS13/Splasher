import org.osbot.Po;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import java.awt.*;
import java.util.Random;

@ScriptManifest(name = "Splash", author = "Mushy", version = 1.0, info = "", logo = "")

public class SplasherMain extends Script {

    private final Color color3 = new Color(255, 255, 255);
    private final Color color4 = new Color(43, 190, 201, 200);
    private final Font font2 = new Font("Gill Sans MT Regular", 0, 12);
    private long startTime;
    private long runTime;
    private String logB;

    private long splashTime;
    private long resetTime;
    private Random r;

    @Override
    public void onStart() {
        r  = new Random();
        startTime = System.currentTimeMillis();
        splashTime = System.currentTimeMillis();
        resetTime = generateActionTime();

    }

    @Override
    public void onExit() {
        //Code here will execute after the script ends
    }

    private enum State {
        RESETACTION, SPLASHING, WAIT
    };

    private State getState() {
        if ((System.currentTimeMillis() - splashTime) >= resetTime) {
            return State.RESETACTION;
        }
        if (myPlayer().isAnimating())
            return State.SPLASHING;

        return State.WAIT;
    }
    @Override
    public int onLoop() {
        switch (getState()) {

            case RESETACTION:
                resetTime = generateActionTime();
                //getMouse().click(myPosition().getX(), myPosition().getY(), false);
                walking.walk(new Position(3227, 9871, 0));
                log(myPosition().getY());
                logB = "reset!";
                splashTime = System.currentTimeMillis();
                break;

            case SPLASHING:
                if (1 == 1) {
                    //dothings
                    break;
                }
        }
        return 100; //The amount of time in milliseconds before the loop starts over
    }

    private int generateActionTime(){
        int max, min;
        //max = 1200000;
        max = 6000;
        min = 1000;
        return r.nextInt((max - min) + 1) + min;
    }








    public final String formatValue(final long v) {
        return (v > 1_000_000) ? String.format("%.2fm", (double) (v / 1_000_000))
                : (v > 1000) ? String.format("%.1fk", (double) (v / 1000)) : v + "";
    }

    public String formatTime(long ms) {
        long s = ms / 1000, m = s / 60, h = m / 60;
        s %= 60;
        m %= 60;
        h %= 24;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public double getHours(long time) {
        double sS = time / 1000, mM = sS / 60, hH = mM / 60;
        log(hH);
        return hH;
    }

    @Override
    public void onPaint(Graphics2D g) {
        //gpGained = itemsMade * costOfItem;
        //DecimalFormat df = new DecimalFormat("#.#");
        //DecimalFormat dr = new DecimalFormat("#");
        //totalGpGained = gpGained / 1000;
        //bankGpGained = leather * costOfItem;
        //bankTotalGained = bankGpGained / 1000;

        //gpPerHour = (int) (gpGained / ((System.currentTimeMillis() - startTime) / 3600000.0D));
        //totalGpPerHour = gpPerHour / 1000;

        Graphics2D gr = (Graphics2D) g;
        gr.setColor(color4);
        gr.fillRect(1, 263, 515, 75);
        gr.setFont(font2);
        gr.setColor(color3);
        runTime = (System.currentTimeMillis() - startTime);
        g.drawString("Script Name " + formatTime(runTime), 4, 274);
        g.drawString("Current State: " + getState(), 4, 286);
        g.drawString("_________________________________________________________________________", 1, 320);
        g.drawString("Log Message: " + logB, 4, 333);
        g.drawString("Reset Time: " + formatTime(resetTime), 180, 274);
        g.drawString("Splash Time: " + formatTime((System.currentTimeMillis() - splashTime)), 180, 286);
        //g.drawString(hidePaintName + " Made: " + itemsMade, 180, 298);
        //g.drawString("Runescape G/E Prices", 380, 274);
        //g.drawString("__________________", 380, 275);
        //g.drawString("Money Made: " + df.format(totalGpGained) + "k", 380, 287);
        //g.drawString("Money Per Hour: " + dr.format(totalGpPerHour) + "k/hr", 380, 299);
        //g.drawString("Total Money Made: " + dr.format(bankTotalGained) + "k", 380, 311);
        gr.drawString("Created By Mushy", 415, 333);
    }

    public void onMessage(Message message) throws java.lang.InterruptedException {
        //temp
    }
}