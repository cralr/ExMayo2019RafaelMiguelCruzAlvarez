package exMayo2019RafaelMiguelCruzAlvarez;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;

/**
 * 1. Escribe un programa que usando la interfaz gráfica de Java escoja un
 * fichero del sistema de archivos y lo almacene en otro de manera que se
 * sustituyan todas las ocurrencias de los siguientes caracteres (mayúscula o
 * minúscula) del fichero original por sus correspondientes
 * números:A4/B8/E3/I1/O0/S5/T7 
 * Componentes gráficos: 
 * •Etiquetas. 
 * •Fichero origen y destino. 
 * •Botón para ejecutar la acción. 
 * •Caja de texto con el contenido del fichero destino no editable
 *
 *
 * @author Rafael Miguel Cruz Álvarez
 * @version 1.0
 */

public class Ejercicio1 extends JFrame {

  private JPanel contentPane;
  private JFrame frame;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Ejercicio1 frame = new Ejercicio1();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public Ejercicio1() {
    setTitle("Ejercicio1");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 449);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel lblFichero1 = new JLabel("Fichero 1:");
    lblFichero1.setBounds(53, 53, 76, 14);
    contentPane.add(lblFichero1);

    JLabel labelFichero2 = new JLabel("Fichero 2:");
    labelFichero2.setBounds(53, 104, 76, 14);
    contentPane.add(labelFichero2);
    
    JTextPane textPaneSalida = new JTextPane();
    textPaneSalida.setEditable(false);
    textPaneSalida.setBounds(72, 219, 312, 174);
    contentPane.add(textPaneSalida);
    
    
    JFileChooser jfcFichero1 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    jfcFichero1.setDialogTitle("Seleccione un fichero...");

    JButton button_fichero1 = new JButton("Seleccione un fichero");
    button_fichero1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        int returnValue = jfcFichero1.showDialog(null, "Seleccionar");
        if (returnValue == JFileChooser.APPROVE_OPTION)
          if (jfcFichero1.getSelectedFile().getPath() != null)
            button_fichero1.setText("Seleccionado "+jfcFichero1.getSelectedFile().getName());
      }
    });
    button_fichero1.setBounds(139, 49, 233, 23);
    contentPane.add(button_fichero1);

    JFileChooser jfcFichero2 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    jfcFichero2.setDialogTitle("Seleccione un fichero...");

    JButton button_fichero2 = new JButton("Seleccione un fichero");
    button_fichero2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int returnValue = jfcFichero2.showDialog(null, "Seleccionar");
        if (returnValue == JFileChooser.APPROVE_OPTION)
          if (jfcFichero2.getSelectedFile().getPath() != null)
            button_fichero2.setText("Seleccionado "+jfcFichero2.getSelectedFile().getName());
      }
    });
    button_fichero2.setBounds(139, 100, 233, 23);
    contentPane.add(button_fichero2);

    JButton btnNewButton = new JButton("Convertir Palabras");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          BufferedReader br1 = new BufferedReader(new FileReader(jfcFichero1.getSelectedFile().getPath()));
          BufferedWriter bw = new BufferedWriter(new FileWriter(jfcFichero2.getSelectedFile().getPath()));
          Vector<String> escritura = new Vector<String>();

          String linea = "";
          String salida="";

          while (linea != null) {
            linea = br1.readLine();
            if (linea != null)
              escritura.addElement(linea);
          }

          for (String s : escritura) {
             s = s.replace('a', '4').replace('A','4').
                 replace('b','8').replace('B','8').replace('e','3').
                 replace('E','3').replace('i','1').replace('I', '1').
                 replace('o', '0').replace('O', '0').replace('s', '5').
                 replace('S','5').replace('t','7').replace('T','7');
             bw.write(s+"\n");  
             salida = salida + s + ("\n");
          }

          br1.close();
          bw.close();
          JOptionPane.showMessageDialog(frame, "Palabras convertidas a números.");
          textPaneSalida.setText("La salida es: \n"+salida);


        } catch (IOException | NullPointerException ioe) {
          JOptionPane.showMessageDialog(frame, "No ha seleccionado todos los ficheros.", "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    btnNewButton.setBounds(139, 156, 233, 23);
    contentPane.add(btnNewButton);

    JButton button_fichero21 = new JButton("New button");
    button_fichero21.setBounds(139, 100, 151, 23);
    contentPane.add(button_fichero21);
    
   
   
  }
}
