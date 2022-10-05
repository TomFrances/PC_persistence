package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;
import java.awt.geom.Path2D;

public class Star implements SimpleShape {

    int x;

    int y;

    public Star(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Star(){}
    private static Shape createStar(double centerX, double centerY)
    {
        Path2D path = new Path2D.Double();
        double deltaAngleRad = Math.PI / 5;
        for (int i = 0; i < 5 * 2; i++)
        {
            double angleRad = Math.toRadians(-18) + i * deltaAngleRad;
            double ca = Math.cos(angleRad);
            double sa = Math.sin(angleRad);
            double relX = ca;
            double relY = sa;
            if ((i & 1) == 0)
            {
                //outter radius
                relX *= 25;
                relY *= 25;
            }
            else
            {
                //inner radius
                relX *= 10;
                relY *= 10;
            }
            if (i == 0)
            {
                path.moveTo(centerX + relX, centerY + relY);
            }
            else
            {
                path.lineTo(centerX + relX, centerY + relY);
            }
        }
        path.closePath();
        return path;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.YELLOW, x + 50f, y, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(createStar(x, y));
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.black);
        g2.setStroke(wideStroke);
        g2.draw(createStar(x, y));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
