package exMayo2019RafaelMiguelCruzAlvarez;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

/**
 * 2.- Escribe un programa que usando la interfaz gráfica de Java permita al
 * usuario introducir una fecha en formato dd/mm/aaaa y que tenga los siguientes
 * botones: 
 * •Validar fecha: muestra un mensaje diciendo si la fecha es o no válida. 
 * •Día posterior: Modifica la fecha sumándole un día. Debe validar la fecha antes de hacer la operación. 
 * •Día anterior: Modifica la fecha restándole un día. Debe validar la fecha antes de hacer la operación. 
 * •Días hasta hoy: Muestra el número de días que hay entre la fecha introducida y la fecha de hoy. Debe validar la fecha antes de hacer la operación. •Termina
 *
 *
 * @author Rafael Miguel Cruz Álvarez
 * @version 1.0
 */

public class Ejercicio2 extends JFrame {

  private JFrame frame;
  private JPanel contentPane;
  private JTextField textField_fecha;
  private JButton boton_suma;
  private JButton boton_restar;
  Calendar calendario = Calendar.getInstance();
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  String fecha;
  private JTextField textField_diasDiferencia;
  
  
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Ejercicio2 frame = new Ejercicio2();
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
  public Ejercicio2() {
    setTitle("Ejercicio2");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 405);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel label_fecha = new JLabel("Introduzca una fecha: ");
    label_fecha.setBounds(10, 51, 206, 14);
    contentPane.add(label_fecha);

    textField_fecha = new JTextField();
    textField_fecha.setBounds(152, 48, 86, 20);
    contentPane.add(textField_fecha);
    textField_fecha.setColumns(10);

    JButton boton_validar = new JButton("Validar Fecha");
    boton_validar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try {
          if (validarFecha() != null);{
            JOptionPane.showMessageDialog(frame, "Fecha Válida.");
          }
        } catch (fechaInvalidaException e) {
          JOptionPane.showMessageDialog(null, "La fecha es inválida.", "Error", JOptionPane.ERROR_MESSAGE);
          ;
        }
      }
    });
    boton_validar.setBounds(274, 47, 130, 23);
    contentPane.add(boton_validar);

    boton_suma = new JButton("Día posterior");
    boton_suma.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try {
          sumarDia();
        } catch (fechaInvalidaException e) {
          JOptionPane.showMessageDialog(null, "La fecha es inválida.", "Error", JOptionPane.ERROR_MESSAGE);
          ;
        }
      }
    });
    boton_suma.setBounds(35, 112, 119, 23);
    contentPane.add(boton_suma);

    boton_restar = new JButton("Día anterior");
    boton_restar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try {
          restarDia();
        } catch (fechaInvalidaException e) {
          JOptionPane.showMessageDialog(null, "La fecha es inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
      }
    });
    boton_restar.setBounds(259, 112, 130, 23);
    contentPane.add(boton_restar);
    
    JButton boton_terminar = new JButton("Terminar");
    boton_terminar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        System.exit(0);
      }
    });
    boton_terminar.setBounds(152, 238, 91, 23);
    contentPane.add(boton_terminar);
    
    JButton boton_diasDiferencia = new JButton("Diferencia Días");
    boton_diasDiferencia.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try {
          mostrarDias();
        } catch (ParseException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
    boton_diasDiferencia.setBounds(35, 179, 119, 23);
    contentPane.add(boton_diasDiferencia);
    
    textField_diasDiferencia = new JTextField();
    textField_diasDiferencia.setEditable(false);
    textField_diasDiferencia.setBounds(201, 180, 220, 20);
    contentPane.add(textField_diasDiferencia);
    textField_diasDiferencia.setColumns(10);
    
  }
  private void sumarDia() throws fechaInvalidaException{
    
    validarFecha();
    fecha = textField_fecha.getText();
    int diaSum = 1;
    calendario.add(Calendar.DATE, diaSum);
    textField_fecha.setText(sdf.format(calendario.getTime()));
    }
  
  private void restarDia() throws fechaInvalidaException {
    
    validarFecha();
    fecha = textField_fecha.getText();
    int diaRes = -1;
    calendario.add(Calendar.DATE, diaRes);
    textField_fecha.setText(sdf.format(calendario.getTime()));
  }
  
  public Calendar validarFecha() throws fechaInvalidaException {
    
    String verifica;

    fecha = textField_fecha.getText();
    calendario = obtenerFecha(fecha);
    verifica = sdf.format(calendario.getTime());
    if (!fecha.equals(verifica)) {
      throw new fechaInvalidaException();
    }
    return calendario;
  }

  public Calendar obtenerFecha(String Fecha) {
    Calendar calendario = Calendar.getInstance();
    String[] parts;
    int d, m, y;

    if (Fecha.contains("/") && Fecha.length() == 10) {
      parts = Fecha.split("/");
      d = Integer.parseInt(parts[0]);
      m = Integer.parseInt(parts[1]);
      y = Integer.parseInt(parts[2]);

      switch (m) {
      case 1:
        calendario.set(y, Calendar.JANUARY, d);
        break;
      case 2:
        calendario.set(y, Calendar.FEBRUARY, d);
        break;
      case 3:
        calendario.set(y, Calendar.MARCH, d);
        break;
      case 4:
        calendario.set(y, Calendar.APRIL, d);
        break;
      case 5:
        calendario.set(y, Calendar.MAY, d);
        break;
      case 6:
        calendario.set(y, Calendar.JUNE, d);
        break;
      case 7:
        calendario.set(y, Calendar.JULY, d);
        break;
      case 8:
        calendario.set(y, Calendar.AUGUST, d);
        break;
      case 9:
        calendario.set(y, Calendar.SEPTEMBER, d);
        break;
      case 10:
        calendario.set(y, Calendar.OCTOBER, d);
        break;
      case 11:
        calendario.set(y, Calendar.NOVEMBER, d);
        break;
      case 12:
        calendario.set(y, Calendar.DECEMBER, d);
        break;
      default:
        calendario.set(0, 0, 0);
        break;
      }
    } else {
      calendario.set(0, 0, 0);
    }
    return calendario;
  }
  
  public int diferenciaDias() throws ParseException {
    Calendar calendario1 = Calendar.getInstance();
    String f=calendario1.get(Calendar.DATE)+"/"+(calendario1.get(Calendar.MONTH) + 1)+"/"+calendario1.get(Calendar.YEAR);
    java.util.Date fechaFinal = sdf.parse(f);
    java.util.Date fechaInicial = sdf.parse(textField_fecha.getText());

    long hoy = fechaFinal.getTime();
    long fechaI = fechaInicial.getTime();
    if (fechaInicial.before(fechaFinal)) {
      return (int) (((hoy - fechaI)/(3600*24*1000))); 
    }else
      return (int) (((fechaI - hoy)/(3600*24*1000))); 
  }
  
  public void mostrarDias() throws ParseException {
    textField_diasDiferencia.setText("La diferencia de días es: "+Integer.toString(diferenciaDias()));
  }
}
