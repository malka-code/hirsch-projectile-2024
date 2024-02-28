package hirsch.projectile;

import javax.swing.*;
import java.awt.*;

public class ProjectileGraph extends JComponent {

    private Projectile projectile = new Projectile(0, 0);
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.translate(0, getHeight());
        int currX;
        int currY;

        g.setColor(Color.BLACK);

        projectile.setSeconds(0);

        for (int i = 0; 1 < projectile.getApexTime() * 2 + 1; i++) {
            currX = (int) projectile.getX();
            currY = (int) projectile.getY();
            projectile.setSeconds(i);
            g.drawLine(currX, -currY, (int) projectile.getX(), -(int) projectile.getY());
        }

        g.setColor(Color.CYAN);
        g.fillOval((int) projectile.getX() / 2, -(int) projectile.getPeakY(), 10, 10);


    }

        public void setProjectile(Projectile projectile){
            this.projectile = projectile;

            repaint();
        }
    }

