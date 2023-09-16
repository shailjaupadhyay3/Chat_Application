package chatting._application;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
import java.awt.geom.*;

public class C_A_Client implements ActionListener {
    RoundedJTextField text;
    static JPanel a1; 
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    
    
 C_A_Client() {
        f.setLayout(null);
        
        JPanel p1 = new JPanel();
        p1.setBackground(Color.white);
        p1.setBounds(0, 0, 380, 60);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30, 28,  Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/c1.jpg"));
        Image i5 = i4.getImage().getScaledInstance(40, 35, Image.SCALE_SMOOTH);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 8, 50, 50);
        p1.add(profile);
        
       
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i8 = i7.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel phone = new JLabel(i9);
        phone.setBounds(265, 16, 30, 30);
        p1.add(phone);
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i11 = i10.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel video = new JLabel(i12);
        video.setBounds(325, 16, 30, 30);
        p1.add(video);
        
        
        
        JLabel name = new JLabel("Shailja");
        name.setBounds(104, 12, 170, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 13));
        p1.add(name);
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/active.png"));
        Image i14 = i13.getImage().getScaledInstance(8, 8, Image.SCALE_SMOOTH);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel Active = new JLabel(i15);
        Active.setBounds(91, 16, 40, 40);
        p1.add(Active);
        
        JLabel status= new JLabel("Active Now");
        status.setBounds(122, 31, 160, 9);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 10));
        p1.add(status);
        
        a1 = new JPanel();
        a1.setBounds(0, 60, 390, 497);
        a1.setBackground(  new Color(15, 15,15) );
        f.add(a1);
       
        text = new RoundedJTextField(16);
        text.setBounds(35, 560, 265, 32);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        f.add(text);
        
      RoundedJButton send = new RoundedJButton("Send");
        send.setBounds(303, 560, 75, 32);
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
         send.setBackground(new Color(63, 114, 155));
        f.add(send);   

    f.setSize(380, 600);
    f.setLocation(800, 50);
    f.setUndecorated(true);
    f.getContentPane().setBackground(  new Color(15, 15, 15) );
    

    f.setVisible(true);
}

   public void actionPerformed(ActionEvent ae){
       try {
        String out = text.getText();
        JPanel p2=formatLabel(out);
        a1.setLayout(new BorderLayout());
        
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(8));

         a1.add(vertical, BorderLayout.PAGE_START);
         dout.writeUTF(out);
         text.setText("");
         
         f.repaint();
         f.invalidate();
         f.validate();
    }  catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   public static JPanel formatLabel(String out){
       JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 17));
        output.setBackground(new Color(15, 15, 15));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(2, 95, 2, 25));
        output.setForeground(Color.WHITE);
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        
        return panel;
        
   }
   
   
   class RoundedJTextField extends JTextField {
        private Shape shape;

        public RoundedJTextField(int size) {
            super(size);
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            super.paintComponent(g);
        }

        protected void paintBorder(Graphics g) {
            
            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            g.setColor( new Color(15, 15, 15));
        }

        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            }
            return shape.contains(x, y);
        }
    }
   

class RoundedJButton extends JButton {
    public RoundedJButton(String text) {
        super(text);
        setOpaque(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new Ellipse2D.Double(0, 0, getWidth() - 1, getHeight() - 1));
        super.paintComponent(g);
        g2.dispose();
    }
}
                 
public static void main(String [] args){
    new C_A_Client();  
    try {
            Socket s = new Socket("127.0.0.1", 6001);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
            while(true) {
                a1.setLayout(new BorderLayout());
                String msg = din.readUTF();
                JPanel panel = formatLabel(msg);

                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                
                vertical.add(Box.createVerticalStrut(15));
                a1.add(vertical, BorderLayout.PAGE_START);
                
                f.validate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
