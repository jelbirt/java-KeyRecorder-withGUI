import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class MKRecorder implements ActionListener,KeyListener {

       private JFrame frmKmcRecorder;
       private JTextArea keystrokeWindow;
       private boolean recording = false;
       private JButton btnNewButton;
       private JCheckBox chkbxRK;

       /**
       * Launch the application.
       */
       public static void main(String[] args) {
              EventQueue.invokeLater(new Runnable() {
                     public void run() {
                           try {
                                  MKRecorder window = new MKRecorder();
                                  window.frmKmcRecorder.setVisible(true);
                           } catch (Exception e) {
                                  e.printStackTrace();
                           }
                     }
              });
       }

       /**
       * Create the application.
       */
       public MKRecorder() {
              initialize();
       }

       /**
       * Initialize the contents of the frame.
       */
       private void initialize() {
              frmKmcRecorder = new JFrame();
              frmKmcRecorder.setTitle("Keystroke Recorder 1.0");
              frmKmcRecorder.setBounds(100, 100, 306, 300);
              frmKmcRecorder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frmKmcRecorder.addKeyListener(this);
              GridBagLayout gridBagLayout = new GridBagLayout();
              gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
              gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
              gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
              gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
              frmKmcRecorder.getContentPane().setLayout(gridBagLayout);

              JLabel lblNewLabel = new JLabel("Keystroke Recording");
              GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
              gbc_lblNewLabel.gridwidth = 3;
              gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
              gbc_lblNewLabel.gridx = 0;
              gbc_lblNewLabel.gridy = 0;
              frmKmcRecorder.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

              chkbxRK = new JCheckBox("Record Keystrokes");
              chkbxRK.setSelected(true);
              GridBagConstraints gbc_chkbxRK = new GridBagConstraints();
              gbc_chkbxRK.anchor = GridBagConstraints.EAST;
              gbc_chkbxRK.insets = new Insets(0, 5, 5, 5);
              gbc_chkbxRK.gridx = 0;
              gbc_chkbxRK.gridy = 1;
              frmKmcRecorder.getContentPane().add(chkbxRK, gbc_chkbxRK);
             
              btnNewButton = new JButton("Start Recording");
              btnNewButton.addActionListener(this);
              btnNewButton.addKeyListener(this);
              GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
              gbc_btnNewButton.anchor = GridBagConstraints.WEST;
              gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
              gbc_btnNewButton.gridx = 2;
              gbc_btnNewButton.gridy = 1;
              frmKmcRecorder.getContentPane().add(btnNewButton, gbc_btnNewButton);
            
              keystrokeWindow = new JTextArea();
              keystrokeWindow.setEditable(false);
              keystrokeWindow.setText("");
              keystrokeWindow.addKeyListener(this);
              GridBagConstraints gbc_keystrokeWindow = new GridBagConstraints();
              gbc_keystrokeWindow.gridwidth = 3;
              gbc_keystrokeWindow.fill = GridBagConstraints.BOTH;
              gbc_keystrokeWindow.gridx = 0;
              gbc_keystrokeWindow.gridy = 2;
              gbc_keystrokeWindow.insets = new Insets(0, 5, 5, 5);
              JScrollPane sp = new JScrollPane(keystrokeWindow);
              frmKmcRecorder.getContentPane().add(sp, gbc_keystrokeWindow);
       }
 
       @Override public void keyTyped(KeyEvent e) { }
 
       private ArrayList<Integer> held_keys = new ArrayList<Integer>();
       @Override public synchronized void keyPressed(KeyEvent e) {
              if(recording && chkbxRK.isSelected()) {
                     if(!held_keys.contains(e.getKeyCode())) {
                           held_keys.add(e.getKeyCode());
                     }
              }
       }
 
       @Override public void keyReleased(KeyEvent e) {
              if(recording && chkbxRK.isSelected()) {
                     String text = "";
                     for(int i=0;i<held_keys.size();i++) {
                           if(i > 0) { text = text + "-"; }
                           text += KeyEvent.getKeyText(held_keys.get(i));
                     }
                     held_keys.clear();
                     if(!text.isBlank()) { keystrokeWindow.setText(keystrokeWindow.getText() + text + "\n"); }
              }
       }
 
       @Override public void actionPerformed(ActionEvent e) {
              if(recording) {
                     btnNewButton.setText("Start Recording");
                     recording = false;
              } else {
                     btnNewButton.setText("Stop Recording");
                     keystrokeWindow.requestFocusInWindow();
                     recording = true;
              }
       }
 
}