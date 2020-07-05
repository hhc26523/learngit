
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

/**
 * ��������
 * @author hhc
 *
 */
class Play{
    private String audioFile;
    /**
     * ��������
     */
    Play(String audiofile){
        this.audioFile = audiofile;
    }
    
    public void playMusic() {
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(audioFile));
            Clip clip = AudioSystem.getClip();  
            clip.open(inputStream);  
            clip.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 2018/02/25
 * @author hhc
 *�������
 */
public class Piano {
    //ʹ�÷�װ����������ť���ټ���
    public static final JButton DO = getButton("do", 0, 0, 45, 234);
    public static final JButton RE = getButton("re", 45, 0, 43, 234);
    public static final JButton MI = getButton("mi", 88, 0, 43, 234);
    public static final JButton FA = getButton("fa", 131, 0, 43, 234);
    public static final JButton SO = getButton("so", 174, 0, 42, 234);
    public static final JButton LA = getButton("la", 216, 0, 41, 234);
    public static final JButton SI = getButton("si", 257, 0, 42, 234);
    public static final JButton DO1 = getButton("do1", 299, 0, 41, 234);
    public static final JButton RE1 = getButton("re1", 340, 0, 44, 234);
    public static final JButton MI1 = getButton("mi1", 384, 0, 41, 234);
    //ʵ����swing����
    public static JFrame frame = new JFrame("piano");
    //������
    public static void main(String[] args) {
        frame.setSize(440, 234);
        frame.setLocation(100, 100);
        frame.setLayout(null);
        JMenu author = new JMenu("hhc");
        JMenuBar a = new JMenuBar();
        a.add(author);
        JMenuItem newItem=new JMenuItem("����");
        author.add(newItem);
        //ʹ�������ڲ��ഴ���˵��ļ����¼�
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame BUG=new JFrame("����");
                JTextArea jta=new JTextArea();
                jta.setText("�����Ұ�������ΪA,S,D,F,G,H,\nJ,K,L�Լ��ֺż�");
                jta.setEditable(false);
                jta.setBounds(0,10,200,290);
                BUG.add(jta);
                BUG.setSize(200,300);
                BUG.setLocation(90,90);
                BUG.setVisible(true);
            }
        });
        frame.setJMenuBar(a);
        addComponent(frame, DO, RE, MI, FA, SO, LA, SI, DO1, RE1, MI1);
        //�������̼����¼� ʵ�ּ��̵Ĳٿ�
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_A:DO.doClick();break;
                    case KeyEvent.VK_S:RE.doClick();break;
                    case KeyEvent.VK_D:MI.doClick();break;
                    case KeyEvent.VK_F:FA.doClick();break;
                    case KeyEvent.VK_G:SO.doClick();break;
                    case KeyEvent.VK_H:LA.doClick();break;
                    case KeyEvent.VK_J:SI.doClick();break;
                    case KeyEvent.VK_K:DO1.doClick();break;
                    case KeyEvent.VK_L:RE1.doClick();break;
                    case KeyEvent.VK_SEMICOLON:MI1.doClick();break;
                    default:break;
                }
            }
            @Override
            public void keyReleased(KeyEvent arg0) {}
            @Override
            public void keyTyped(KeyEvent arg0) {}
        });
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * ������
     * @param frame
     * @param components
     */
    public static void addComponent(JFrame frame, Component...components) {
        for (int i = 0; i < components.length; i++) {
            frame.add(components[i]);
        }
    }
    
    /**
     * ��װJbutton����
     * @param picUrl
     * @return
     */
    public static JButton getButton(String text, Integer x, Integer y, Integer width, Integer height) {
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        String picUrl = "image/" + text + ".png";
        String pressPicUrl = "image/" + text + "_click.png";
        ImageIcon imageIcon = new ImageIcon(Piano.class.getResource(picUrl));
        ImageIcon pressImageIcon = new ImageIcon(Piano.class.getResource(pressPicUrl));
        button.setIcon(imageIcon);
        button.setPressedIcon(pressImageIcon);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Play play = new Play("sound/" + text + ".wav");
                play.playMusic();
                frame.requestFocus();
            }
        });
        return button;
    }

}
