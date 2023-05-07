/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.juegogato;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author sheil
 */
public class JuegoGato extends JFrame implements ActionListener {

    JButton iniciar;
    JButton tablero[][];//matriz de botones
    String jugador1, jugador2;
    int turno=-1;// a quien le corrsponde le turnmo del gato
    JLabel mensaje;//para ver a quien le toca al momento de que este jugando
    Color colorB;
    
    public JuegoGato(){
        
        setBackground(Color.cyan);
        this.setLayout(null);
        mensaje = new JLabel("BIENVENIDOS AL JUEGO :)",SwingConstants.CENTER);
        mensaje.setBounds(165,400,150,30);
        mensaje.setForeground(Color.black);
        mensaje.setFont(new Font("Agency FB",Font.BOLD,18));
        this.add(mensaje);
        
        iniciar = new JButton("INICIAR JUEGO");
        iniciar.setBounds(10,400,150,30);
        iniciar.setBackground(Color.CYAN);
        iniciar.addActionListener(this); // se le agrega el action listener de esta ventana
       this.add(iniciar);
        
       tablero = new JButton [3][3];
    for(int i=0; i<3; i++){
        for(int j=0; j<3; j++){
            tablero[i][j]= new JButton();
            tablero[i][j].addActionListener(this);
            tablero[i][j].setBounds((i*120)+10,(j*120)+10,120,120);
            tablero[i][j].setBackground(Color.pink);
            this.add(tablero[i][j]);
        }
    }
        colorB = tablero[0][0].getBackground();
        
    }
    
    public static void main(String[] args) {
        JuegoGato ventana = new JuegoGato();
        ventana.setDefaultCloseOperation(3);
        ventana.setSize(400,500);
        
        ventana.setLocationRelativeTo(null);//no se permite hacerlo mas grande
        
        ventana.setResizable(false);
        ventana.setTitle("Juego del gato por *** Las RESPONSABLES:) ***");
        ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource () ==iniciar){//procede a hacer la accion del boton iniciar
                turno = 0;
                JOptionPane.showMessageDialog(this,"Iniciando el juego");
                jugador1 = JOptionPane.showInputDialog(this,"Ingresa el nombre del jugador 1 ");//pedir el nombre del jugador
                jugador2 = JOptionPane.showInputDialog(this,"Ingresa el nombre del jugador 2 ");
                mensaje.setText("Turno del jugardor " + jugador1);
                limpiar();//declarar metodom limpiar para q se reinicie el juego
                        
            }else{
                JButton boton = (JButton)e.getSource();//detecta y guarde en un boton el elemntyo en donde ocurre un elemnto y se lamacena en un boton
                //acciones que debe de hacer
                if (turno==0 ){// a quien le toca el turno para saber qu eacciones debe hacer
                    if (boton.getText().equals("")){
                        
                        boton.setText("x");//jugador uno de texto a x
                        boton.setFont(new Font("Wide Latin",Font.BOLD,50));
                        boton.setBackground(Color.red);
                        boton.setEnabled(false);//desabilite el boton para que no se pueda estra dando click
                        turno=1;//se cambia de turno
                        mensaje.setText("Turno del jugador "+jugador2);// se le cambia al jugador 2
               
                    }
                    
                }else{
                    if (turno==1){//turno jugador 2
                        if (boton.getText().equals("")){
                        
                        boton.setText("0");//jugador uno de texto a o
                        boton.setFont(new Font("Wide Latin",Font.BOLD,50));
                        boton.setBackground(Color.black);
                        boton.setEnabled(false);//desabilite el boton para que no se pueda estra dando click
                        turno=0;//se cambia de turno
                        mensaje.setText("Turno del jugador "+jugador1);//nombre del jugador
                        
               
                    }
                        
                    }
                }
                
                //mande a llamar al boton de verificar
                verificar();

            }
            
    } 
    //ver si alguno ya gano, 8 formas
    public void verificar(){
        int ganador=0;//variable ganador que empieza en 0, para saber cuando alguien gano
        for (int i=0; i<3; i++){
            if (tablero[0][i].getText().equals("x")&& tablero [1][i].getText().equals("x") 
                     && tablero[2][i].getText().equals("x")) { // PARA GANAR DE FORMA HORIZONTAL -
            ganador = 1;
            
            }
            if (tablero[i][0].getText().equals("x")&& tablero [i][1].getText().equals("x") 
                     && tablero[i][2].getText().equals("x")) { // PARA GANAR DE FORMA VERTICAL |
            ganador = 1;
            
            }
        }
        if (tablero[0][0].getText().equals("x")&& tablero [1][1].getText().equals("x") 
                     && tablero[2][2].getText().equals("x")) { // PARA GANAR DE FORMA DIAGONAL \
            ganador = 1;
            
            }
        if (tablero[0][2].getText().equals("x")&& tablero [1][1].getText().equals("x") 
                     && tablero[2][0].getText().equals("x")) { // PARA GANAR DE FORMA DIAGONAL /
            ganador = 1;
            
            }
        
        for (int i=0; i<3; i++){
            if (tablero[0][i].getText().equals("0")&& tablero [1][i].getText().equals("0") 
                     && tablero[2][i].getText().equals("0")) { // PARA GANAR DE FORMA HORIZONTAL
            ganador = 2;
            
            }
            if (tablero[i][0].getText().equals("0")&& tablero [i][1].getText().equals("0") 
                     && tablero[i][2].getText().equals("0")) { // PARA GANAR DE FORMA VERTICAL
            ganador = 2;
            
            }
        }
        if (tablero[0][0].getText().equals("0")&& tablero [1][1].getText().equals("0") 
                     && tablero[2][2].getText().equals("0")) { // PARA GANAR DE FORMA DIAGONAL
            ganador = 2;
            
            }
        if (tablero[0][2].getText().equals("0")&& tablero [1][1].getText().equals("0") 
                     && tablero[2][0].getText().equals("0")) { // PARA GANAR DE FORMA DIAGONAL
            ganador = 2;
            
            }
        
        if(ganador==1){
           JOptionPane.showMessageDialog(this, "Felicidades "+ jugador1 + " ganó" );
           bloquear();
        }
        if(ganador==2){
           JOptionPane.showMessageDialog(this, "Felicidades "+ jugador2 + " ganó" );
           bloquear();
        }
    }
    
    public void bloquear(){// metodo para finalizar el juego
        for(int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                tablero[i][j].setEnabled(false);//bloquea el tablero una vez el jugadoer gane
            }
        }
        
    }
    
    public void limpiar(){//metodo para limpoiar el tablero por nosotros cada vez que se quiera inicar el juego
        //matriz de botones 
        for(int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                tablero[i][j].setEnabled(true);//para q se vuelva a activar y se controlan de forma independiente
                tablero[i][j].setText("");//limpiamos el texto
                tablero[i][j].setBackground(colorB);//se recupera el boton original   
            }
        }
        
    }
}
