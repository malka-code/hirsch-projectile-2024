package hirsch.projectile;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectileFrame extends JFrame {

    static final int ANGLE_MIN = 0;
    static final int ANGLE_MAX = 90;
    static final int ANGLE_INIT = 45;
    private final JSlider angleSlider;
    private final JTextField velocityField;
    private final JTextField secondsField;
    private final JLabel calculatedXLabel;
    private final JLabel calculatedYLabel;
    private final JLabel peakY;
    private final JLabel interceptX;
    private ProjectileGraph graph = new ProjectileGraph();

    public ProjectileFrame() {
        setSize(400, 600);
        setTitle("Projectile Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        setContentPane(main);

        JPanel west = new JPanel();
        main.add(west, BorderLayout.WEST);

        west.setLayout(new GridLayout(8, 2));
        JLabel velocityLabel = new JLabel("Velocity");
        JLabel angleLabel = new JLabel("Angle");

        angleSlider = new JSlider(JSlider.HORIZONTAL,
                ANGLE_MIN, ANGLE_MAX, ANGLE_INIT);

        angleSlider.setMajorTickSpacing(10);
        angleSlider.setMinorTickSpacing(5);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);

        JLabel secondsLabel = new JLabel("Seconds");
        JLabel XLabel = new JLabel("X");
        JLabel YLabel = new JLabel("Y");

        velocityField = new JTextField();
        secondsField = new JTextField("0");
        calculatedXLabel = new JLabel();
        calculatedYLabel = new JLabel();
        JLabel empty = new JLabel();
        JButton calculateButton = new JButton("Calculate");
        JLabel peakYLabel = new JLabel("Peak Y");
        JLabel interceptXLabel = new JLabel("Intercept X");
        peakY = new JLabel();
        interceptX = new JLabel();

        west.add (velocityLabel);
        west.add(velocityField);

        west.add(angleLabel);
        west.add(angleSlider);

        west.add(secondsLabel);
        west.add(secondsField);

        west.add(XLabel);
        west.add(calculatedXLabel);

        west.add(YLabel);
        west.add(calculatedYLabel);

        west.add(peakYLabel);
        west.add(peakY);

        west.add(interceptXLabel);
        west.add(interceptX);

        west.add(empty);
        west.add(calculateButton);

        velocityField.getDocument().addDocumentListener((SimpleDocumentListener)
                documentEvent -> createProjectile());

        angleSlider.addChangeListener(changeEvent -> createProjectile());

        calculateButton.addActionListener(actionEvent -> createProjectile());

        main.add(graph, BorderLayout.CENTER);

    }

    private void createProjectile() {
        Projectile projectile = new Projectile(
                angleSlider.getValue(),
                Double.parseDouble(String.valueOf(velocityField.getX()))
        );
        projectile.setSeconds(
                Double.parseDouble(secondsField.getText())
        );
        calculatedXLabel.setText(Double.toString(projectile.getX()));
        calculatedYLabel.setText(Double.toString(projectile.getY()));
        peakY.setText(Double.toString(projectile.getPeakY()));
        interceptX.setText(Double.toString(projectile.getInterceptX()));
        graph.setProjectile(projectile);
    }
}