package indicadoresSalud;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.*;
/**
 *
 * @author whoAngel
 * 
 * La aplicación GUI propuesta es un programa que sirve para calcular el índice
 * de masa corporal (IMC) y el índice de cintura-cadera(ICC), los cuales se
 * consideran como indicadores básicos para conocer si la salud	de una persona
 * está	en riesgo de padecer algún problema debido a su	peso o medidas de su
 * cintura
 *
 */
public class IndicadoresSalud extends JFrame implements ActionListener {
    
    private JButton cIMC;               // activa el calculo de INC
    private JButton cICC;               // activa el calculo de ICC
    private JButton borrar;             // activa el borrado de datos
    private JLabel resultadoIMC;        // para mostrar el resultado de IMC
    private JLabel resultadoICC;        // para mostrar el resutlado de ICC
    private JTextField edad;            // recibe el dato descrito
    private JTextField estatura;        // recibe el dato descrito
    private JTextField peso;            // reccibe el dato descrito
    private JTextField cadera;          // ""
    private JTextField cintura;         // ""
    private JRadioButton hombre;        // Seleccion del dato descrito
    private JRadioButton mujer;         //  Seleccion del dato descrito
    
    
    // CONSTRUCTOR
    public IndicadoresSalud(){
        String path = "/imagenes/bascula.png"; // Ubicacion de la imagen
        URL url = this.getClass().getResource(path); // Obtiene un recurso de la ubicacion 
        ImageIcon icon = new ImageIcon(path); // crea un objeto imageIcon a partir del recurso
        
        
        
        // creacion y construccion del titulo proncipal
        JLabel presentacion = new JLabel("INDICACIONES BASICOS DE RIESGOS A LA SALUD", icon, SwingConstants.CENTER);
        
        // construccion de elementos a visualizar
        cIMC = new JButton("Calcular IMC");
        cICC = new JButton("Calcular ICC");
        edad = new JTextField(4);
        estatura = new JTextField(4);
        peso = new JTextField(4);
        resultadoIMC = new JLabel("__________");
        cintura = new JTextField(4);
        cadera = new JTextField(4);
        hombre = new JRadioButton(" Hombre ");
        mujer = new JRadioButton(" Mujer ");
        resultadoICC = new JLabel("__________");
        borrar = new JButton("Borrar Datos");
        JLabel tEdad =      new JLabel("Proporciona tu edad(>19):");
        JLabel tIMC =       new JLabel("CALCULO DEL INDICE DE MASA CORPORAL (IMC)");
        JLabel tEstatura =  new JLabel(" Estatura (cms) : ");
        JLabel tPeso =      new JLabel(" Peso (kgs) : ");
        JLabel tICC =       new JLabel("CALCULO DEL INDICE DE CINTURA-CADERA (ICC)");
        JLabel tCadera =    new JLabel(" Cadera (cms) : ");
        JLabel tCintura =    new JLabel(" Cintura (cms) : ");
        JLabel tSexo =      new JLabel(" Sexo : ");
        
        presentacion.setHorizontalTextPosition(SwingConstants.CENTER);
        presentacion.setVerticalTextPosition(SwingConstants.TOP);
        add(presentacion);
        add(tEdad);
        add(edad);
        add(tIMC);
        add(tEstatura);
        add(estatura);
        add(tPeso);
        add(peso);
        add(cIMC);
        add(resultadoIMC);
        add(tICC);
        add(tCintura);
        add(cintura);
        add(tCadera);
        add(cadera);
        add(tSexo);
        add(hombre);
        add(mujer);
        add(cICC);
        add(resultadoICC);
        System.out.println("");
        add(borrar);
        
        ButtonGroup opcion = new ButtonGroup(); // hacer que los botones sean excluyentes uno del otro
            opcion.add(hombre);
            opcion.add(mujer);
            hombre.setSelected(true); // 7b hacer que un valor sea el predeterminado
        
        cIMC.addActionListener(this); // evento para calcular el IMC
        cICC.addActionListener(this); // evento para calcular el ICC
        borrar.addActionListener(this);
        
        setLayout(new FlowLayout());
        setSize(310,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        // convierte el objeto productor a un objeto boton
        JButton boton = (JButton)ae.getSource();
        String resultado = ""; //para el reaultado a desplegar
        String situacion = ""; // para la situacion de acuerdo a la tabla
        
        String riesgoCardiovascular = ""; // para el riesgo cardiovascular de la tabla del ICC
        
        // calcula el valor del IMC
        if(boton == cIMC){ // si el objeto se produce en el evento cIMC
            double mtsCms = Double.parseDouble(estatura.getText())/100;
            double pesoKgs = Double.parseDouble(peso.getText());
            if(mtsCms<1.40 || mtsCms>2 || pesoKgs<40 || pesoKgs>160)
                JOptionPane.showMessageDialog(this, "Se requieren valores dentro del rango.");
            else{
                // calcula el valor del IMC
                double vIMC = pesoKgs / Math.pow(mtsCms, 2);
                // asignar a la variable situacion segun el vIMC
                if(vIMC < 18.5){
                    situacion = "PESO BAJO";
                }if ( vIMC >= 18.5 && vIMC<=24.9){
                    situacion = "PESO NORMAL";
                }if(vIMC >=25 && vIMC <=29.9){
                    situacion = "SOBRE PESO";
                }else if(vIMC >= 30){
                    situacion = "OBESIDAD";
                }
                BigDecimal bd = new BigDecimal(vIMC);
                bd = bd.setScale(2, RoundingMode.HALF_UP); // para limitar la cantidad de decimales
                
                resultado = bd.doubleValue() + "=> " + situacion;
            }
            resultadoIMC.setText(resultado);
        }// fin del codigo del boton calcular IMC
        
        // si el boton es el de calcular ICC
        if( boton == cICC){
            double tamCintura = Double.parseDouble(cintura.getText());
            double tamCadera =  Double.parseDouble(cadera.getText());
            
            if (tamCintura<50 || tamCintura>140 || tamCadera<60 || tamCadera >150) {
                JOptionPane.showMessageDialog(this, "Se requieren valores dentro del rango.");
            }else{
                double vICC = tamCintura / tamCadera; // calcular el valor del indice CADERA-CINTURA
                if(hombre.isSelected()){
                    if (vICC <= 0.95) {
                        situacion = "| BAJO |";
                    }if(vICC>0.95 && vICC<1.0){
                        situacion = "| MEDIO |";
                    }if(vICC>=1){
                        situacion = "| ALTO |";
                    }
                }else{
                    if (vICC <= 0.80) {
                        situacion = "| BAJO |";
                    }if(vICC>0.80 && vICC<0.85){
                        situacion = "| MEDIO |";
                    }if(vICC>=0.85){ 
                        situacion = "| ALTO |";
                    }
                }
                resultado = "Riesgo "+situacion;    
            }
            resultadoICC.setText(resultado);
        }//FIN del codigo para calcular el ICC
        
        // para borrar datos
        if(boton == borrar){
            resultadoICC.setText("__________");
            resultadoIMC.setText("__________");
            edad.setText("");
            estatura.setText("");
            peso.setText("");
            cintura.setText("");
            cadera.setText("");
        }
        
        
    }
}
