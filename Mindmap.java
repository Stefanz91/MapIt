import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Mindmap extends JFrame implements MouseListener {
    private ArrayList<Infobutton>infoButtons;
    private JPanel mapPanel;
    private int startXPosition;
    private int startYPosition;
    private int endXPosition;
    private int endYPosition;
    private Infobutton newInfoButton;
    boolean setNewInfoButton;
    ActionListener InfobuttonListener;
    private Graphics2D graphics2D;
    private ArrayList<Connections> ConnectionPoints;
    private Infobutton clickedInfobutton1;
    private Infobutton clickedInfobutton2;

    public Mindmap(Infobutton centereInfo){
        startXPosition = -1;
        startYPosition = -1;
        endXPosition = -1;
        endYPosition = -1;
        newInfoButton = null;
        setNewInfoButton = false;
        addMouseListener(this);
        ConnectionPoints = new ArrayList<>();
        clickedInfobutton1 = null;
        clickedInfobutton2 = null;
        InfobuttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getModifiers() == 1){
                    System.out.println("left klick");
                    if (clickedInfobutton1 == null){
                        clickedInfobutton1 = centereInfo;
                        System.out.println("Infobutton1 "+ centereInfo.getId());
                    }else {
                        clickedInfobutton2 = centereInfo;
                        System.out.println("Infobutton2 "+ centereInfo.getId());
                    }
                    buttonLeftClick();
                }
                if (e.getModifiers() == 3){
                    System.out.println("right klick");
                    buttonRightClick();
                }
            }
        };


        infoButtons = new ArrayList<>();
        infoButtons.add(centereInfo);
        mapPanel = new JPanel();


        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1200,800);
        this.setVisible(true);

        drawStarterMindMap();

        float mousePositionX = MouseInfo.getPointerInfo().getLocation().x;
        System.out.println(mousePositionX);
    }

    public void paint(Graphics g){
        super.paint(g);
        System.out.println("Paint called!");
        graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.BLACK);
        System.out.println("Start x position:"+startXPosition);
        System.out.println("Start y position:"+startYPosition);
        for(Connections con : ConnectionPoints){
            graphics2D.drawLine(con.getStartXPosition(),con.getStartYPosition(),con.getEndXPosition(),con.getEndYPosition());
        }
    }

    public void drawStarterMindMap(){
        this.add(mapPanel);
        mapPanel.setLayout(null);
        JButton newInfobutton = new JButton("New Infobutton");
        mapPanel.add(newInfobutton);
        newInfobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNewInfoButton = true;

            }
        });
        newInfobutton.setBounds(50,10,160,30);

        for(Infobutton button : infoButtons){
            mapPanel.add(button);
            button.setBounds(button.getHorizontalPosition(),button.getVerticalPosition(),160,30);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getModifiers() == 1){
                        System.out.println("left klick");
                        if (clickedInfobutton1 == null){
                            clickedInfobutton1 = button;
                            System.out.println("First Infobutton "+button.getId());
                        }else {
                            System.out.println("Second Infobutton "+button.getId());
                            clickedInfobutton2 = button;
                        }
                        buttonLeftClick();
                    }
                    if (e.getModifiers() == 3){
                        System.out.println("right klick");
                        buttonRightClick();
                    }
                }
            });

        }
    }

    public void buttonLeftClick(){
        leftClickAction();
    }

    public void buttonRightClick(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        leftClickAction();
    }

    private void leftClickAction(){
        System.out.println("Method called!");
        int horizontalPosition = MouseInfo.getPointerInfo().getLocation().x ;
        int verticalPosition = MouseInfo.getPointerInfo().getLocation().y ;

        if (startYPosition == -1 && startXPosition == -1){
            startXPosition = horizontalPosition;
            startYPosition = verticalPosition;
            return;
        }

        if (clickedInfobutton1 != null && clickedInfobutton2 != null)
        if (!setNewInfoButton && connectionAlreadyExists()){
            startXPosition = -1;
            startYPosition = -1;
            return;
        }
        if (setNewInfoButton){
            Infobutton secondInfoButton = new Infobutton("Second Infobutton", horizontalPosition, verticalPosition);
            clickedInfobutton2 = secondInfoButton;
            System.out.println("Hier wurde der zweite Infobutton kreirrt!" + clickedInfobutton2.getId());
            secondInfoButton.setBounds(horizontalPosition,verticalPosition,160,30);
            mapPanel.add(secondInfoButton);
            infoButtons.add(secondInfoButton);
            System.out.println(clickedInfobutton2.getId());
            secondInfoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getModifiers() == 1){
                        System.out.println("left klick");
                        if (clickedInfobutton1 == null){
                            clickedInfobutton1 = secondInfoButton;
                            System.out.println("First Infobutton "+secondInfoButton.getId());
                        }else {
                            System.out.println("Second Infobutton "+secondInfoButton.getId());
                            clickedInfobutton2 = secondInfoButton;
                        }
                        buttonLeftClick();
                    }
                    if (e.getModifiers() == 3){
                        System.out.println("right klick");
                        buttonRightClick();
                    }
                }
            });
            if (clickedInfobutton1 != null && clickedInfobutton1 != null && clickedInfobutton2 != null)
            newConnections(horizontalPosition,verticalPosition);
            secondInfoButton.setVisible(false);
            secondInfoButton.setVisible(true);
            return;
        } else{
            if (clickedInfobutton1 != null && clickedInfobutton2 != null)
            newConnections(horizontalPosition-30,verticalPosition-30);
        }
    }

    private boolean connectionAlreadyExists() {
        for(Connections con: ConnectionPoints){
            if (clickedInfobutton1.getId() == con.getStartPoint().getId() && clickedInfobutton2.getId() == con.getEndPoint().getId()
            || clickedInfobutton1.getId() == con.getEndPoint().getId() && clickedInfobutton2.getId() == con.getStartPoint().getId()){
                System.out.println(con.getEndPoint().getId()+"!!!!");
                System.out.println(con.getStartPoint().getId()+"????");
                clickedInfobutton1 = null;
                clickedInfobutton2 = null;
                ConnectionPoints.remove(con);
                repaint();
                return true;
            }
        }
        return false;
    }

    private void newConnections(int horizontalPosition, int verticalPosition){
        System.out.println("Set Infobutton Startpoint "+ clickedInfobutton1.getId());
        System.out.println("Set Infobutton Endpoint "+clickedInfobutton2.getId());
        if (clickedInfobutton1 != clickedInfobutton2)
        ConnectionPoints.add(new Connections(horizontalPosition+30,verticalPosition+30,startXPosition,startYPosition, clickedInfobutton1.clone(),clickedInfobutton2.clone()));
        repaint();
        setNewInfoButton = false;
        clickedInfobutton1 = null;
        clickedInfobutton2 = null;
        startXPosition = -1;
        startYPosition = -1;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
