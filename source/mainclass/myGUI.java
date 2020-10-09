package mainclass;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.glass.events.WindowEvent;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder.Body;

import history.history;
import javafx.scene.layout.Border;
import jdk.internal.dynalink.beans.StaticClass;
import sun.reflect.generics.tree.Tree;
import word_list.listwords;
import word_list.word;
import xmlfile_worker.xmlfile;

import org.w3c.dom.*;

import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.*;
public class myGUI extends JPanel implements ActionListener{
	public static JTextArea textscrollpaneArea;
	public static JTextArea textArea1;
	public static String scrollString="";
	public static String scrollString1="";
	public static String textareaString="";
	public static String actionCommand="";
	static listwords anhviet;
	static listwords vietanh;
	static listwords yeuthich;
	static List<history> history_list;
	static List<String> saved_string;
	static int mode;
	public myGUI() {
		setLayout(new BorderLayout());
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
			JPanel top = new JPanel();
			JPanel bottom = new JPanel();
				JLabel modeJLabel = new JLabel("che do");
				String[] data= {"viet anh", "anh viet","yêu thích","lịch sử"};
				JComboBox comboBox = new JComboBox(data);	
				
		
		
		textscrollpaneArea = new JTextArea("",8,20);
		textArea1 =  new JTextArea("",8,10);
		textArea1.setMaximumSize(getMinimumSize());
		JScrollPane aJScrollPane= new JScrollPane(textscrollpaneArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
				);
		textArea1.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		textareaString=textArea1.getText();
		JButton but2 =  new JButton("Thêm");
		JButton but3 = new JButton("xoa");
		JButton but0= new JButton("Tra từ");
		JButton but4 = new JButton("Thống kê");
		JButton showlistButton = new JButton("Xem danh sach");
		but2.addActionListener(this);
		but3.addActionListener(this);
		but0.addActionListener(this);
		but4.addActionListener(this);
		but2.setActionCommand("them");
		but3.setActionCommand("xoa");
		but0.setActionCommand("tratu");
		but4.setActionCommand("thongke");
		showlistButton.addActionListener(this);
		showlistButton.setActionCommand("xemdanhsach");
		but0.setSize(20,20);
		bottom.add(showlistButton); bottom.add(but2); bottom.add(but3); bottom.add(but4);
		top.add(but0) ;top.add(modeJLabel);	top.add(comboBox);
		container.add(top, BorderLayout.NORTH);
		container.add(aJScrollPane, BorderLayout.CENTER);
		container.add(textArea1, BorderLayout.WEST);
		container.add(bottom, BorderLayout.SOUTH);
		container.setAlignmentY(2);
		container.setAlignmentX(30);
		add(container);
		comboBox.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s =(String) comboBox.getSelectedItem();
				actionCommand=s;
					switch (s) {
					case "anh viet":
						mode = 1;
						break;
					case "viet anh":
						mode = -1;
						break;
					case "yêu thích":
						mode= 0;
						break;
					default:
						mode=2;
						break;
					}
			}
		});
	}
	static private String selectedString(ItemSelectable is) {
		    Object selected[] = is.getSelectedObjects();
		    return ((selected.length == 0) ? "null" : (String)selected[0]);
		  }  
	public static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame mainFrame = new JFrame("1712318-từ điển");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myGUI contenpane  = new myGUI();
		mainFrame.setContentPane(contenpane);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(mainFrame, 
		            "Are you sure you want to saved_string?", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	try {
						history.add_hitory(history_list, saved_string);
						xmlfile.writexmlfile("yeuthich.xml", yeuthich);
						xmlfile.writexmlfile_history("history.xml", history_list);

					} catch (TransformerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  	

		            System.exit(0);
		        }
		        else {
		        	System.exit(0);
				}
		    }
		});
	}
    public static void main(String[] args) throws TransformerConfigurationException {
    	mode =1;
    	myGUI.createAndShowGUI();
		anhviet = new listwords();
		vietanh = new listwords();
		history_list =  new ArrayList<history>();
		saved_string = new ArrayList<String>();
		yeuthich =new listwords();
		myGUI.textArea1.setText("Dang doc du lieu, xin doi vai giay");
		try {
			xmlfile.readxmlfile("Viet_Anh.xml",vietanh, 20);
			xmlfile.readxmlfile("Anh_Viet.xml", anhviet,10);
			xmlfile.readxmlfile("yeuthich.xml", yeuthich,1);
			xmlfile.readxml_history("history.xml",history_list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		myGUI.textArea1.setText("San sang");
    }
    public static void in(String s) {
    	System.out.println(s);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		String act = e.getActionCommand();
		String tempString;
		actionCommand=act;
		switch (act) {
				case "thongke":
								tempString= textArea1.getText();
								textscrollpaneArea.setText(history.counting(tempString.substring(0,10), tempString.substring(11,tempString.length()), history_list));
								break;
				case "xemdanhsach":
								switch (mode) {
								case -1:
									textscrollpaneArea.setText(vietanh.toString());
									break;
								case 1:
									textscrollpaneArea.setText(anhviet.toString());
									break;
								case 2:
									textscrollpaneArea.setText(history_list.toString());
									break;
								default:
									textscrollpaneArea.setText(yeuthich.toString());
									break;
								}
					break;
				case "them":			
								switch (mode) {
								case -1:
									vietanh.listwords.add(new word(textArea1.getText(), textscrollpaneArea.getText()));
									break;
								case 0:
									yeuthich.listwords.add(new word(textArea1.getText(), textscrollpaneArea.getText()));
									break;
								case 1:
									anhviet.listwords.add(new word(textArea1.getText(), textscrollpaneArea.getText()));
									break;
								default:
									break;
								}
								System.out.println("da them tu:");
					break;
				case "xoa":
							
								int i=0;
								tempString = textArea1.getText();
								switch (mode) {
								case -1:
									for (word wordi : vietanh.listwords) {	
										if(tempString.compareTo(wordi.word)==0)
											vietanh.listwords.remove(i);
										i++;
									}
									break;
								case 0:
									while(i<yeuthich.listwords.size()) {
										if(tempString.compareTo(yeuthich.listwords.get(i).word)==0)
											yeuthich.listwords.remove(i);
										i++;
									}
									break;
								case 1:
									for (word wordi : anhviet.listwords) {	
										if(tempString.compareTo(wordi.word)==0)
											anhviet.listwords.remove(i);
										i++;
									}
									break;
								default:
									break;
								}
							
								System.out.println("Da xoa tu"+i);
								System.out.println(yeuthich);
								break;
				
				case "tratu":
							System.out.println("tratu");
							int flag=0;	// kiem tra tu duoc tra co nam trong dnah sach
							tempString= textArea1.getText();
							System.out.println(tempString);
							switch (mode) {
							case 1:
								for (word wordi : anhviet.listwords) {
									if(tempString.compareTo(wordi.word)==0) {
										flag=1;
										textscrollpaneArea.setText(wordi.meaning);
										
									}
								}
								break;
							case -1:
								for (word wordi : vietanh.listwords) {
									if(tempString.compareTo(wordi.word)==0) {
										flag=1;
										textscrollpaneArea.setText(wordi.meaning);
									}
							}
								break;
							default:
								for (word wordi : yeuthich.listwords) {
									if(tempString.compareTo(wordi.word)==0) {
										flag=1;
										textscrollpaneArea.setText(wordi.meaning);
										flag=1;
										}
								}
								break;
							}
							if(flag==1)
								saved_string.add(tempString);
							break;
					default:
							break;
		}	
	}
	
}


