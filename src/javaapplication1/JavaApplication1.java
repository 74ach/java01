/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class JavaApplication1 extends JFrame {

    public static void main(String[] args) {

        // Cargar imagen en memoria
        BufferedImage originalImg = null;

        try {
            //Leemos la imagen
            originalImg = ImageIO.read(new File("img.jpeg"));
            BufferedImage imgMod = ImageIO.read(new File("img.jpeg"));
            System.out.println("Imagen cargada");

            //Comprovar que la imagen esta en Byte
            if (imgMod.getRaster().getDataBuffer().getDataType() != DataBuffer.TYPE_BYTE) {
                throw new IllegalArgumentException("Esta imagen no es valida");
            }

            //Convertir a Arrayde Byte
            byte[] byteImg = ((DataBufferByte) imgMod.getRaster().getDataBuffer()).getData();
            System.out.println("Funciona");

            //Modificar la imagen
            int contador = 0, contadorMedia = 0;

            byte guardarByte[] = new byte[3];

            byte media[] = new byte[byteImg.length / 3];

            for (int a = 0; a < byteImg.length; a++) {

                contador++;

                if (contador == 3) {

                    media[contadorMedia] = (byte) ((guardarByte[0] + guardarByte[1] + guardarByte[2]) / guardarByte.length); 
                    contadorMedia++;
                    contador = 0;
                }
                guardarByte[contador] = byteImg[a];
            }

            contadorMedia = 0;
            contador = 0;
            for (int b = 0; b < byteImg.length; b++) {
                byteImg[b] = media[contadorMedia];
                System.out.println(byteImg[b]);
                contador++;
                if (contador == 3) {
                    contadorMedia++;
                    contador=0;
                }
            }

            
        } catch (IOException e) {
            System.out.println("No hay imagen");
        }

        // panel();
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
