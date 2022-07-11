package UI;

import Models.DataSource;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.JRadioButton;

import Models.Ingrediente;
import Models.Receta;
import Servicios.OrganizadorRecetas;
import Servicios.ResulRecetas;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ButtonGroup;

public class Vent2 extends JFrame {

	private JPanel contentPane;
	private JButton btnSelRecetas;
	private JLabel lblRecetas;
	private JButton btnSelIngredientes;
	private JLabel lblArchivoIngredientes;
	private JTextPane txtREsulForm;
	private JScrollPane jsp;
        private ButtonGroup buttonGroup;
        private JRadioButton rbDataSourceFile;
        private JRadioButton rbDataSourceSqlite;
	private JLabel lblDataSource;
        
	private JTextPane txtingred;
	private JScrollPane jsping;
	
	private String _pathFileIngredientes;
	private String _pathFolderRecetas;
	private JLabel lblRecetas_1;

        private DataSource selectedDataSource = DataSource.File;
        
	public Vent2() {		
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 810, 805);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            setResizable(false);
            setTitle("Organizador de Recetas");
            setLocationRelativeTo(null);

            JButton btnOrgRecet = new JButton("Organizar Recetas");
            btnOrgRecet.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(false && (_pathFileIngredientes == null || _pathFolderRecetas == null)) {
                            JOptionPane.showMessageDialog(null, "Debe seleccionar la carpeta de Recetas y el archivo de Ingredientes");
                    }
                    else {
                            ResulRecetas result;
                            OrganizadorRecetas rc = new OrganizadorRecetas(_pathFileIngredientes, _pathFolderRecetas);                                    
                            //OrganizadorRecetas rc = new OrganizadorRecetas("C:\\Users\\marco\\Downloads\\TP\\ingredientes.txt", "C:\\Users\\marco\\Downloads\\TP\\recetas");
                            result = rc.DeterminarFactibilidad(selectedDataSource);
                            SalidaResul(result);
                    }
                }
            });
            btnOrgRecet.setBounds(34, 84, 207, 23);
            contentPane.add(btnOrgRecet);

            btnSelRecetas = new JButton("Seleccionar Carpeta Recetas");
            btnSelRecetas.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            JFileChooser dirChooser = new JFileChooser();

                            dirChooser.setCurrentDirectory(new File(".")); //sets current directory

                            dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                            int response = dirChooser.showOpenDialog(null); //select file to open
                            //int response = fileChooser.showSaveDialog(null); //select file to save

                            if(response == JFileChooser.APPROVE_OPTION) {
                                    File file = new File(dirChooser.getSelectedFile().getAbsolutePath());
                                    lblRecetas.setText(file.getPath());
                                    _pathFolderRecetas = file.getPath();
                            }

                    }
            });
            btnSelRecetas.setBounds(34, 11, 207, 23);
            contentPane.add(btnSelRecetas);

            lblRecetas = new JLabel("Seleccione Carpeta...");
            lblRecetas.setForeground(Color.BLACK);
            lblRecetas.setBackground(Color.BLACK);
            lblRecetas.setBounds(251, 11, 533, 23);
            contentPane.add(lblRecetas);

            btnSelIngredientes = new JButton("Seleccionar Archivo Ingredientes");
            btnSelIngredientes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                            JFileChooser fileChooser = new JFileChooser();

                            fileChooser.setCurrentDirectory(new File(".")); //sets current directory

                            int response = fileChooser.showOpenDialog(null); //select file to open
                            //int response = fileChooser.showSaveDialog(null); //select file to save

                            if(response == JFileChooser.APPROVE_OPTION) {
                                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                                    lblArchivoIngredientes.setText(file.getPath());
                                    _pathFileIngredientes = file.getPath();
                            }


                    }
            });
            btnSelIngredientes.setBounds(34, 45, 207, 23);
            contentPane.add(btnSelIngredientes);

            lblArchivoIngredientes = new JLabel("Seleccione Archivo...");
            lblArchivoIngredientes.setBounds(251, 45, 533, 23);
            contentPane.add(lblArchivoIngredientes);

            txtREsulForm = new JTextPane();
            txtREsulForm.setBounds(34,150, 350, 580);
            contentPane.add(txtREsulForm);

            jsp = new JScrollPane(txtREsulForm);
            jsp.setBounds(34, 150, 350, 580);
            contentPane.add(jsp);

            txtingred = new JTextPane();
            txtingred.setBounds(400, 150, 350, 580);
            contentPane.add(txtingred);

            jsping = new JScrollPane(txtingred);
            jsping.setBounds(400, 150, 350, 580);
            contentPane.add(jsping);

            JLabel lblIng = new JLabel("Ingredientes disponibles");
            lblIng.setFont(new Font("Tahoma", Font.BOLD, 13));
            lblIng.setBounds(400, 121, 207, 23);
            contentPane.add(lblIng);

            lblRecetas_1 = new JLabel("Recetas");
            lblRecetas_1.setFont(new Font("Tahoma", Font.BOLD, 13));
            lblRecetas_1.setBounds(34, 121, 207, 23);
            contentPane.add(lblRecetas_1);
            
            buttonGroup = new ButtonGroup();
            // file radio button
            rbDataSourceFile = new JRadioButton("File");
            rbDataSourceFile.setBounds(600, 100, 207, 23);
            rbDataSourceFile.setSelected(true);
            rbDataSourceFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    if (rbDataSourceFile.isSelected())
                    {
                        selectedDataSource = DataSource.File;
                    }
                }
            
            });
            
            // Sqlite radio button
            rbDataSourceSqlite = new JRadioButton("Sqlite");
            rbDataSourceSqlite.setBounds(600, 120, 207, 23);
            rbDataSourceSqlite.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    if (rbDataSourceSqlite.isSelected())
                    {
                        selectedDataSource = DataSource.Sqlite;
                    }
                }
            });
            
            buttonGroup.add(rbDataSourceFile);
            buttonGroup.add(rbDataSourceSqlite);
            
            lblDataSource = new JLabel("Seleccione DataSource");
            lblDataSource.setForeground(Color.BLACK);
            lblDataSource.setBackground(Color.BLACK);
            lblDataSource.setBounds(580, 80, 207, 23);
            contentPane.add(lblDataSource);
            
            contentPane.add(rbDataSourceFile);
            contentPane.add(rbDataSourceSqlite);
            
            
	}
	
	private void SalidaResul(ResulRecetas result) {
		  SimpleAttributeSet TIT_VERDE = new SimpleAttributeSet();
		  SimpleAttributeSet TIT_ROJO = new SimpleAttributeSet();
		  SimpleAttributeSet TXT_BOLD = new SimpleAttributeSet();
		  SimpleAttributeSet TXT_LIGHT = new SimpleAttributeSet();

		  // Best to reuse attribute sets as much as possible.
		    StyleConstants.setForeground(TIT_VERDE, Color.black);
		    StyleConstants.setBackground(TIT_VERDE, Color.green);
		    StyleConstants.setBold(TIT_VERDE, true);
		    StyleConstants.setFontFamily(TIT_VERDE, "Helvetica");
		    StyleConstants.setFontSize(TIT_VERDE, 16);

		    StyleConstants.setForeground(TIT_ROJO, Color.white);
		    StyleConstants.setBackground(TIT_ROJO, Color.red);
		    StyleConstants.setBold(TIT_ROJO, true);
		    StyleConstants.setFontFamily(TIT_ROJO, "Helvetica");
		    StyleConstants.setFontSize(TIT_ROJO, 16);

		    StyleConstants.setForeground(TXT_BOLD, Color.black);
		    StyleConstants.setFontFamily(TXT_BOLD, "Helvetica");
		    StyleConstants.setBold(TXT_BOLD, true);
		    StyleConstants.setFontSize(TXT_BOLD, 14);
		    
		    StyleConstants.setForeground(TXT_LIGHT, Color.black);
		    StyleConstants.setFontFamily(TXT_LIGHT, "Helvetica");
		    StyleConstants.setFontSize(TXT_LIGHT, 14);
		

		
		ArrayList<Receta> recetasFactibles;
		ArrayList<Receta> recetasNoFactibles;
		ArrayList<Ingrediente> ingredientes;
		
		recetasFactibles = result.getRecetasFactibles();
		recetasNoFactibles = result.getRecetasNoFactibles();
		ingredientes = result.getIngredientes();

		txtREsulForm.setText("");
		
		insertText("  RECETAS FACTIBLES: " + recetasFactibles.size() + "  \n", TIT_VERDE, txtREsulForm);
		
		for(Receta rec : recetasFactibles) {
			insertText("\n" + rec.getNombreReceta() + "\n",TXT_BOLD, txtREsulForm);
			for (Ingrediente ing: rec.getIngredientes()) {
				insertText("	* " + ing.getCantidad() + " " + ing.getIngrediente() + "\n",TXT_LIGHT, txtREsulForm);
			}
		}
		
		insertText("\n---------------------------------------------------------\n", TXT_LIGHT, txtREsulForm);
		insertText("  RECETAS NO FACTIBLES: " + recetasNoFactibles.size() + "  \n", TIT_ROJO, txtREsulForm);
		for(Receta rec : recetasNoFactibles) {
			insertText("\n" + rec.getNombreReceta() + "\n",TXT_BOLD, txtREsulForm);
			for (Ingrediente ing: rec.getIngredientes()) {
				insertText("	* " + ing.getCantidad() + " " + ing.getIngrediente() + "\n",TXT_LIGHT, txtREsulForm);
			}
		}	
		
		txtingred.setText("");

		for (Ingrediente ing : ingredientes) {
			insertText(" * " + ing.getCantidad() + " ( " + ing.getMagnitud() + ") " + ing.getIngrediente() + "\n",TXT_BOLD, txtingred);
		}
	}
	
	protected void insertText(String text, javax.swing.text.AttributeSet set, JTextPane txtPane) {
		    try {
		    	txtPane.getDocument().insertString(
                            txtPane.getDocument().getLength(), text, set);
		    } catch (BadLocationException e) {
		      e.printStackTrace();
		    }
	  }
}
