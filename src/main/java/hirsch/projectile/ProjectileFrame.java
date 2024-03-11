package hirsch.projectile;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;


public class ProjectileFrame extends JFrame {

    static final int ANGLE_MIN = 0;
    static final int ANGLE_MAX = 90;
    static final int ANGLE_INIT = 45;
    private final JSlider angleSlider;
    private final JTextField velocityField;
    private final JTextField secondsField;
    private final JLabel calculatedLabelX;
    private final JLabel calculatedLabelY;
    private final JLabel peakY;
    private final JLabel interceptX;
    private ProjectileGraph graph = new ProjectileGraph();

    public ProjectileFrame() {
        setSize(1000, 600);
        setTitle("Projectile Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        setContentPane(main);

        JPanel west = new JPanel();
        main.add(west, BorderLayout.WEST);

        west.setLayout(new GridLayout(8, 2));


        angleSlider = new JSlider(JSlider.HORIZONTAL,
                ANGLE_MIN, ANGLE_MAX, ANGLE_INIT);
        angleSlider.addChangeListener(e -> createProjectile());

        angleSlider.setMajorTickSpacing(10);
        angleSlider.setMinorTickSpacing(5);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);

        velocityField = new JTextField();
        velocityField.getDocument().addDocumentListener((SimpleDocumentListener) e -> createProjectile());

        secondsField = new JTextField("0");

        secondsField.getDocument().addDocumentListener((SimpleDocumentListener) e -> createProjectile());


        peakY = new JLabel();
        interceptX = new JLabel();

        JLabel velocityLabel = new JLabel("Velocity");
        west.add (velocityLabel);
        west.add(velocityField);

        JLabel angleLabel = new JLabel("Angle");
        west.add(angleLabel);
        west.add(angleSlider);

        JLabel secondsLabel = new JLabel("Seconds");
        west.add(secondsLabel);
        west.add(secondsField);

        JLabel labelX = new JLabel("X");
        calculatedLabelX = new JLabel();
        west.add(labelX);
        west.add(calculatedLabelX);

        JLabel YLabel = new JLabel("Y");

        calculatedLabelY = new JLabel();
        west.add(YLabel);
        west.add(calculatedLabelY);

        JLabel peakLabelY = new JLabel("Peak Y");
        west.add(peakLabelY);
        west.add(peakY);

        JLabel interceptLabelX = new JLabel("Intercept X");
        west.add(interceptLabelX);
        west.add(interceptX);

        JLabel empty = new JLabel();
        west.add(empty);

        JButton calculateButton = new JButton("Calculate");
        west.add(calculateButton);

        calculateButton.addActionListener(actionEvent -> createProjectile());

        main.add(graph, BorderLayout.CENTER);

    }

    private void createProjectile() {
        Projectile projectile = new Projectile(
                angleSlider.getValue(),
                Double.parseDouble(String.valueOf(velocityField.getText()))
        );
        projectile.setSeconds(
                Double.parseDouble(secondsField.getText())
        );
        calculatedLabelX.setText(Double.toString(projectile.getX()));
        calculatedLabelY.setText(Double.toString(projectile.getY()));
        peakY.setText(Double.toString(projectile.getPeakY()));
        interceptX.setText(Double.toString(projectile.getInterceptX()));
        graph.setProjectile(projectile);
    }
}