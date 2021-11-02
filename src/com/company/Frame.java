package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class Frame extends JFrame implements ActionListener{
    private JFileChooser navegador;
    private JFrame optionsFrame;
    private JButton bNavigateFile;
    private JButton bImgInfo;
    private JLabel sizeAndRGB;
    private Imagen imagen;
    private ImgFrame imgFrame;

    public Frame() {
        optionsFrame = new JFrame("Editor Demo");
        optionsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel bpanel = new JPanel();
        bpanel.setLayout(new BoxLayout(bpanel, BoxLayout.X_AXIS));
        bNavigateFile = new JButton("File");
        bNavigateFile.addActionListener(this);
        bImgInfo = new JButton("Información");
        bImgInfo.addActionListener(this);
        bpanel.add(bNavigateFile);
        bpanel.add(bImgInfo);

        JPanel optPanel = new JPanel();
        optPanel.setLayout(new BoxLayout(optPanel, BoxLayout.Y_AXIS));
        optPanel.add(bpanel);
        sizeAndRGB = new JLabel("   ");
        optPanel.add(sizeAndRGB);

        optionsFrame.getContentPane().add(optPanel);
        optionsFrame.pack();
        optionsFrame.setLocationRelativeTo(null);
        optionsFrame.setVisible(true);

    }

    private void getImgInfo() {
        JFrame Informacion = new JFrame("Información");
        double numColumnas = imagen.getNumColumnas();
        double numFilas = imagen.getNumFilas();
        String extension = imagen.getTipoFichero();

        JLabel columnText = new JLabel("Numero de columnas: " + numColumnas);
        JLabel filasText = new JLabel("Numero de filas: " + numFilas);
        JLabel extensionText = new JLabel("Extensión del archivo: " + extension);

        Informacion.getContentPane().add(columnText, BorderLayout.NORTH);
        Informacion.getContentPane().add(filasText, BorderLayout.CENTER);
        Informacion.getContentPane().add(extensionText, BorderLayout.SOUTH);

        Informacion.pack();
        Informacion.setLocationRelativeTo(null);
        Informacion.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bNavigateFile) {
            navegador = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, PNG & TIFF Images", "jpg", "png", "tiff");
            navegador.setFileFilter(filtro);
            imgFrame = new ImgFrame(navegador);
            imagen = imgFrame.getImagen();
        } else if(e.getSource() == bImgInfo) {
            JPanel info = new JPanel();
            info.setLayout(new BoxLayout(info, BoxLayout.X_AXIS));
            JLabel sizeAndRGB = new JLabel("" + imagen.getNumColumnas() + " x " + imagen.getNumFilas());
            info.add(sizeAndRGB);
            info.add(bImgInfo);
            getImgInfo();
        }
    }
}
