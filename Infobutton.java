import javax.swing.*;
import java.awt.event.*;

public class Infobutton extends JButton implements MouseListener,  KeyListener {
    private int id;
    private String name;
    private int horizontalPosition;
    private int verticalPosition;
    private JTextField jTextField;
    private static int counter = 0;

    public Infobutton(String name, int horizontalPosition, int verticalPosition) {
        super(name);
        this.name = name;
        counter++;
        id = counter;
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
        jTextField = new JTextField(name);
        jTextField.addKeyListener(this);
        this.add(jTextField);
        //jTextField.setVisible(false);
        addMouseListener(this);

    }

    public int getId() {
        return id;
    }

    public void setjTextField(JTextField jTextField) {
        this.jTextField = jTextField;
    }

    public void setJTextFielVisible(){
        jTextField.setVisible(true);
    }

    public void setJTextFielInvisible(){
        jTextField.setVisible(false);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // Linksklick oder Rechtsklick
            ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, getText(),1);
            //setJTextFielVisible();
            for (ActionListener listener : getActionListeners()) {
                listener.actionPerformed(event);
            }
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setJTextFielVisible();
                }
            });
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            // Linksklick oder Rechtsklick
            ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, getText(),3);
            for (ActionListener listener : getActionListeners()) {
                listener.actionPerformed(event);
            }
        }
    }

    public Infobutton clone(){
        return this;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}


    @Override
    public String getName() {
        return name;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public int getVerticalPosition() {
        return verticalPosition;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            name = jTextField.getText();
            this.setText(name);
            jTextField.setVisible(false);
        }
        System.out.println("Pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
}
