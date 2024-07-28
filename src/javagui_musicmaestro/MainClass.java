package javagui_musicmaestro;
//Instantiate all the images at once
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

class SubPanel extends JPanel
{
    private JRadioButton btnJM,btnEC,btnEP,btnBD,btnJH;
    private ButtonGroup musical;
    private JLabel lblPic;
    private ImageIcon imgArr[]=new ImageIcon[5];
    private JLabel makeLabel(String cap,int x,int y,int w,int h)
    {
        JLabel temp = new JLabel(cap);
        temp.setOpaque(true);
        temp.setBackground(Color.BLACK);
        temp.setFont(new Font("Courier New",1,24));
        temp.setBounds(x,y,w,h);
        add(temp);
        return temp;
    }
    private JRadioButton makeRadioButton(String caption,int x,int y,int width,int height,ButtonGroup btnGrp,int num)
    {
        JRadioButton temp=new JRadioButton(caption);
        temp.setOpaque(false);
        temp.putClientProperty("image",num);
        temp.setFont(new Font("Verdana",1,20));
        temp.setBounds(x, y, width, height);
        temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblPic.setVisible(true);
                JRadioButton ob=(JRadioButton)e.getSource();
                lblPic.setIcon(imgArr[(int)ob.getClientProperty("image")]);
                
            }
        });
        btnGrp.add(temp);
        add(temp);
        return temp;
    }
    private void displayImage()
    {
        
        try{
            for(int i=0;i<5;i++)
            {
            BufferedImage  bimg=ImageIO.read(new File("p"+i+".jpg"));
            Image scale=bimg.getScaledInstance(250,250,Image.SCALE_SMOOTH);
            ImageIcon img=new ImageIcon(scale);
            imgArr[i]=img;
            
            }
        }
        catch(IOException ex)
        {
                        
        }
    }
    public SubPanel()
    {
        musical=new ButtonGroup();
        btnJM=makeRadioButton("Jim Morrison",50,20,200,50,musical,0);
        btnEC=makeRadioButton("Eric Clapton",50,100,200,50,musical,1);
        btnEP=makeRadioButton("Elvis Presley",50,180,200,50,musical,2);
        btnJH=makeRadioButton("Jimi Hendrix",50,260,200,50,musical,3);
        Border br1=BorderFactory.createLineBorder(Color.BLACK,2);
        lblPic=makeLabel("",310,50,250,250);
        lblPic.setBorder(br1);
        
        
        displayImage();
        btnJM.setSelected(true);
        lblPic.setIcon(imgArr[0]);
        
        
    }
}
class MainPanel extends JPanel
{
    private JLabel basicText;
    SubPanel sub;
    public MainPanel()
    {
        sub=new SubPanel();
        sub.setBackground(Color.ORANGE);
        sub.setLayout(new BorderLayout());
        sub.setBounds(50,100,600,350);
        
        basicText=new JLabel("Indentification of Musical Maestros");
        basicText.setBounds(115,20,500,50);
        basicText.setFont(new Font("Verdana",1,24));
        Border br1=BorderFactory.createLineBorder(Color.BLUE,3);
        Border br2 = BorderFactory.createTitledBorder(br1, "Select a Stalwart", TitledBorder.LEFT, TitledBorder.TOP, new Font("verdana", 1, 17),Color.BLUE);
        sub.setBorder(br2);
        super.add(basicText);
        super.add(sub);
    }
}
class MainFrame extends JFrame
{
    MainPanel panel;
    public MainFrame()
    {
        panel=new MainPanel();
        panel.setBackground(Color.YELLOW);
        panel.setLayout(new BorderLayout());
        super.add(panel);
                
    }
}
public class MainClass {

    public static void main(String[] args) {
        MainFrame frame=new MainFrame();
        frame.setVisible(true);
        frame.setSize(720,550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
}
