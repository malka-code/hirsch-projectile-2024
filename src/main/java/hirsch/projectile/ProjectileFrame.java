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
    private final JLabel calculatedXLabel;
    private final JLabel calculatedYLabel;
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
        JLabel velocityLabel = new JLabel("Velocity");


        angleSlider = new JSlider(JSlider.HORIZONTAL,
                ANGLE_MIN, ANGLE_MAX, ANGLE_INIT);
        angleSlider.addChangeListener(e-> createProjectile());

        angleSlider.setMajorTickSpacing(10);
        angleSlider.setMinorTickSpacing(5);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);

        velocityField = new JTextField();
        velocityField.getDocument().addDocumentListener((SimpleDocumentListener) e -> createProjectile());

        secondsField = new JTextField("0");

        secondsField.getDocument().addDocumentListener((SimpleDocumentListener) e -> createProjectile());




        JButton calculateButton = new JButton("Calculate");
        peakY = new JLabel();
        interceptX = new JLabel();


        west.add (velocityLabel);
        west.add(velocityField);

        JLabel angleLabel = new JLabel("Angle");
        west.add(angleLabel);
        west.add(angleSlider);

        JLabel secondsLabel = new JLabel("Seconds");
        west.add(secondsLabel);
        west.add(secondsField);

        JLabel XLabel = new JLabel("X");
        calculatedXLabel = new JLabel();
        west.add(XLabel);
        west.add(calculatedXLabel);

        JLabel YLabel = new JLabel("Y");
        calculatedYLabel = new JLabel();
        west.add(YLabel);
        west.add(calculatedYLabel);

        JLabel peakYLabel = new JLabel("Peak Y");
        west.add(peakYLabel);
        west.add(peakY);

        JLabel interceptXLabel = new JLabel("Intercept X");
        west.add(interceptXLabel);
        west.add(interceptX);

        JLabel empty = new JLabel();
        west.add(empty);
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
        calculatedXLabel.setText(Double.toString(projectile.getX()));
        calculatedYLabel.setText(Double.toString(projectile.getY()));
        peakY.setText(Double.toString(projectile.getPeakY()));
        interceptX.setText(Double.toString(projectile.getInterceptX()));
        graph.setProjectile(projectile);
    }
}