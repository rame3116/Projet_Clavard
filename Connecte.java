package clavard;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
//import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Insets;
//import javax.swing.BoxLayout;
//import java.awt.SystemColor;

public class Connecte implements ActionListener, MouseListener, KeyListener {
	private JFrame frmConnecte;
	int nbCo;
	private String login;
	private ArrayList<String> ListCo;
	private JTextField textField;
	private String recepteur;
	private BDD connexion;
	//private ArrayList<JTextArea> tabTextArea = new ArrayList<JTextArea>();
	//private int j=0;
	ArrayList<String> l_messages; 
	JTextArea TextArea;
	JPanel panel_1;
	JLabel lblNewLabel_3;
	JPanel panel_3;
	JPanel panel_4;
	JList list;
	
	
	public Connecte(String log) {
		login = log;
		Controller ctrl = new Controller();
		ListCo = ctrl.getListeCo();
		System.out.println(ListCo);
		nbCo = ListCo.size();
		connexion = new BDD("C:/Users/Mehdi/Desktop/INSA/4IR/POO/Projet_Clavard/Clavard.db");
        connexion.connect();
		initialize();
	}
	
	
	public void initialize() {
		frmConnecte = new JFrame();
		frmConnecte.getContentPane().setBackground(new Color(0, 0, 51));
		frmConnecte.setForeground(new Color(0, 0, 0));
		frmConnecte.setBackground(new Color(0, 0, 0));
		frmConnecte.setBounds(100, 100, 1080, 720);
		frmConnecte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnecte.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(833, 0, 229, 673);
		frmConnecte.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Connect�(s) : "+nbCo);
		lblNewLabel.setBounds(56, 103, 130, 23);
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 128));
		panel_2.setBounds(0, 215, 228, 458);
		panel.add(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 228, 458);
		scrollPane.setBorder(null);
		DefaultListModel listModel = new DefaultListModel();
		list = new JList(listModel);
		list.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		list.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		list.setBorder(null);
		list.setBackground(new Color(0, 0, 128));
		list.setForeground(Color.WHITE);
		
		/*for(int k=0;k<25;k++) {
			listModel.addElement("test");

		}*/
		
		
		for(String i : ListCo) {
			
			//tabLabel.set(j,new JLabel(i)) ;
			//tabLabel.get(j).setForeground(Color.WHITE);
			//listModel.addElement(tabLabel.get(j));
			listModel.addElement(i);
			
			//j++;
			// EST-CE QUE JE DEVRAIS GARDER LE TABLEAU DE VARIABLE ?
			
		}
		panel_2.setLayout(null);
		list.setModel(listModel);
		list.setBounds(0, 0, 228, 347);
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setViewportView(list);
		list.addMouseListener(this);
		panel_2.add(scrollPane);
		
		
		
		panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(0, 0, 205));
		panel_1.setBounds(0, 0, 835, 673);
		frmConnecte.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Bonjour "+login+" !");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(22, 13, 301, 23);
		panel_1.add(lblNewLabel_1);
		
		lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(282, 65, 306, 33);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 205));
		panel_3.setBounds(62, 124, 773, 517);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.setBounds(637, 400, 97, 25);
		btnEnvoyer.addActionListener(this);
		panel_3.add(btnEnvoyer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 400, 597, 38);
		panel_3.add(scrollPane_1);
		
		textField = new JTextField();
		textField.setMargin(new Insets(2, 10, 2, 2));
		textField.setBorder(null);
		scrollPane_1.setViewportView(textField);
		textField.addKeyListener(this);
		textField.setColumns(10);
		
		panel_4 = new JPanel();
		panel_4.setBounds(12, 0, 720, 383);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
				
		
		TextArea = new JTextArea();
		TextArea.setBorder(null);
		TextArea.setFont(new Font("Nirmala UI", Font.PLAIN, 14));
		TextArea.setBackground(new Color(240, 240, 240));
		TextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(TextArea);
		scroll.setBounds(12, 13, 696, 357);
		
		
		
		
		scroll.setViewportView(TextArea);
		panel_4.add(scroll);
		panel_3.setVisible(false);
		
		frmConnecte.setTitle("Connect�");
		frmConnecte.setVisible(true);
	}

	public void afficheChat() throws ParseException {
		TextArea.setText("");
		l_messages = new ArrayList<String>();
		ArrayList<Message> liste_bdd = connexion.lire_mess(login, recepteur);
		for (Message obj : liste_bdd) {
			if(obj.emetteur.equals(login)) {
				l_messages.add("Moi : "+obj.contenu);
			}
			else {
				l_messages.add(recepteur+" : "+obj.contenu);
			}
			//listModel.addElement(tabLabel.get(j));
			
		}
		System.out.println("l_messages "+l_messages);
		System.out.println("ArrayList<Messages> : "+liste_bdd);
		
		
		for (String str : l_messages) {
			System.out.println(str);
			/*tabTextArea.add(new JTextArea(str)) ;
			tabTextArea.get(j).setEditable(false);*/
			
			//tabTextArea.get(j).setForeground(Color.WHITE);
			//tabTextArea.get(j).setText(str);
			
			    TextArea.append(str+"\n");
			//scrollPane_2.add(tabTextArea.get(j), j);
			     TextArea.setBounds(12, 13, 696, 357);
			     
			

			//j++;
		}
		
		lblNewLabel_3.setText("Discussion avec "+recepteur);
		lblNewLabel_3.setVisible(true);
		panel_3.setVisible(true);
		
		
		
	}

   
	public void envoi(String mess) {
		/*�criture dans la base de donn�es et refresh ?
		 �crire BDD */
		if(mess.isEmpty()) {
		}
		else {
		Date date=new Date();
		connexion.ecrire(login, recepteur, date, mess);
		TextArea.append("Moi : "+mess+"\n");
		textField.setText("");
		
		//connexion.close();
		}
	}
   
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		envoi(this.textField.getText());
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		if (ke.getKeyCode() == 10) {
			envoi(this.textField.getText());
		}
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		 if (e.getClickCount() == 2) {
			 recepteur = (String) list.getSelectedValue();
			 try {
				 //panel_3.setVisible(false);
				afficheChat();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        }
	}
}
