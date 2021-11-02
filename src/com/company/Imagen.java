package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Imagen{
    private BufferedImage img;
    private ArrayList<ArrayList<Pixel>> Pixeles;
    private String tipoFichero;
    //HISTOGRAMA
    private double columnas;
    private double filas;
    //RANGO DE VALORES
    private float brillo;
    private float contraste;
    private float entropia;
    //CAPAS

    public Image getImg() {
        return img;
    }

    public double getNumColumnas() {
        return columnas;
    }

    public double getNumFilas() {
        return filas;
    }

    public String getTipoFichero() {
        return tipoFichero;
    }

    public String getFileExtension(JFileChooser navegador) {
        String name = navegador.getSelectedFile().getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }

    public ArrayList<ArrayList<Pixel>> getPixeles() {
        return Pixeles;
    }

    public Pixel getPixel(int x, int y) {
        return Pixeles.get(x).get(y);
    }

    public Imagen(JFileChooser navegador) {
        int returnVal = navegador.showOpenDialog(null);
        try {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                img = ImageIO.read(new File(navegador.getSelectedFile().getPath()));
                columnas = img.getWidth(null);
                filas = img.getHeight(null);
                tipoFichero = getFileExtension(navegador);
                Pixeles = new ArrayList<ArrayList<Pixel>>();
                for(int y = 0; y < filas; y++) {
                    Pixeles.add(new ArrayList<>());
                    for (int x = 0; x < columnas; x++) {
                        int srcPixel = img.getRGB(x, y);
                        Color c = new Color(srcPixel);
                        Pixeles.get(y).add(new Pixel(x, y, c.getRed(), c.getGreen(), c.getBlue()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
