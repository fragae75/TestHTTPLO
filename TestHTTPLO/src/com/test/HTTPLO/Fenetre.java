package com.test.HTTPLO;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Fenetre extends JFrame {

	private JTabbedPane onglet = new JTabbedPane();
	public JTextArea textPane = new JTextArea();
	private JScrollPane scroll = new JScrollPane(textPane);
	private JPanel panFenetre = new JPanel();
	private JPanel panConfig = new JPanel();
	private JLabel jlbReqBase = new JLabel("URL : ");
	private static JTextField jtfReqBase = new JTextField();
	private JLabel jlbNbPageLimit = new JLabel("Nb Page Limit : ");
	private static JTextField jtfNbPageLimit = new JTextField();
	private JLabel jlbKey = new JLabel("Key : ");
	private static JTextField jtfKey = new JTextField();
	private JLabel jlbStreamID = new JLabel("StreamID : ");
	private static JTextField jtfStreamID = new JTextField();
    public JButton boutonGet =  new JButton("Get Data");

	static void GatherValues()
	{
		TestHTTPLO.sGetDataLinkBase = jtfReqBase.getText();
		TestHTTPLO.sNumberOfPageLimit = jtfNbPageLimit.getText();
		TestHTTPLO.sAPIKey = jtfKey.getText();
		TestHTTPLO.sStreamID = jtfStreamID.getText();
	}
	
	public Fenetre(){
		this.setTitle("Tests HTTP Live Objects");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

	    // URL de requete du Get
	    JPanel jp1 = new JPanel();
	    jp1.setLayout(new BoxLayout(jp1, BoxLayout.LINE_AXIS));
		jp1.add(Box.createRigidArea(new Dimension(30, 0)));
	    jp1.add(jlbReqBase);
	    jtfReqBase.setMaximumSize(new Dimension(Integer.MAX_VALUE, jtfReqBase.getMinimumSize().height));
	    jp1.add(jtfReqBase);
	    jtfReqBase.setText(TestHTTPLO.sGetDataLinkBase);
	    
	    JPanel jp2 = new JPanel();
	    jp2.setLayout(new BoxLayout(jp2, BoxLayout.LINE_AXIS));
		jp2.add(Box.createRigidArea(new Dimension(30, 0)));
	    jp2.add(jlbNbPageLimit);
	    jtfNbPageLimit.setMaximumSize(new Dimension(Integer.MAX_VALUE, jtfNbPageLimit.getMinimumSize().height));
	    jp2.add(jtfNbPageLimit);
	    jtfNbPageLimit.setText(TestHTTPLO.sNumberOfPageLimit);
	    
	    // API Key
	    JPanel jp3 = new JPanel();
	    jp3.setLayout(new BoxLayout(jp3, BoxLayout.LINE_AXIS));
		jp3.add(Box.createRigidArea(new Dimension(30, 0)));
	    jp3.add(jlbKey);
	    jtfKey.setMaximumSize(new Dimension(Integer.MAX_VALUE, jtfKey.getMinimumSize().height));
	    jp3.add(jtfKey);
	    jtfKey.setText(TestHTTPLO.sAPIKey);

	    // Stream ID
	    JPanel jp4 = new JPanel();
	    jp4.setLayout(new BoxLayout(jp4, BoxLayout.LINE_AXIS));
		jp4.add(Box.createRigidArea(new Dimension(30, 0)));
	    jp4.add(jlbStreamID);
	    jtfStreamID.setMaximumSize(new Dimension(Integer.MAX_VALUE, jtfStreamID.getMinimumSize().height));
	    jp4.add(jtfStreamID);
	    jtfStreamID.setText(TestHTTPLO.sStreamID);
	    

	    JPanel jp5 = new JPanel();
	    boutonGet.addActionListener(new BoutonListenerGo()); 
	    jp5.setLayout(new BoxLayout(jp5, BoxLayout.LINE_AXIS));
	    jp5.add(boutonGet);

	    //Panneau REQ/Résultat
	    panFenetre.setLayout(new BoxLayout(panFenetre, BoxLayout.PAGE_AXIS));
	    panFenetre.add(scroll);
	    panFenetre.add(jp5);
	    
	    //Panneau Config
	    panConfig.setLayout(new BoxLayout(panConfig, BoxLayout.PAGE_AXIS));
	    panConfig.add(Box.createRigidArea(new Dimension(0, 10)));
	    panConfig.add(jp1);
	    panConfig.add(Box.createRigidArea(new Dimension(0, 5)));
	    panConfig.add(jp2);
	    panConfig.add(Box.createRigidArea(new Dimension(0, 5)));
	    panConfig.add(jp3);
	    panConfig.add(Box.createRigidArea(new Dimension(0, 5)));
	    panConfig.add(jp4);
	    
	    // Ajout des onglets 
	    onglet.add("Resultat", panFenetre);
	    onglet.add("Configuration", panConfig);
	    //On passe ensuite les onglets au content pane
		this.getContentPane().add(onglet, BorderLayout.CENTER);

	    
	    this.setContentPane(onglet);
	    this.setVisible(true);
	}

	
	class BoutonListenerGo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			GatherValues();
			
			RunGetHTTP services2LO = new RunGetHTTP();
			Thread threat = new Thread(services2LO);
			threat.start();
	    }
	  }

}


