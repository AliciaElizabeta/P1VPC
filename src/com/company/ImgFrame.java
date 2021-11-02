package com.company;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class ImgFrame extends JFrame implements MouseMotionListener {
    private Imagen imagen;
    private JFileChooser navegador;
    private JFrame editorFrame;
    private JLabel data;

    public ImgFrame(JFileChooser nav){
        this.navegador = nav;
        editorFrame = new JFrame("Imagen");
        imagen = new Imagen(navegador);
        ImageIcon imageIcon = new ImageIcon(imagen.getImg());
        data = new JLabel(imagen.getNumColumnas() + "x" + imagen.getNumFilas());
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        jLabel.addMouseMotionListener(this);
        //addMouseMotionListener(this);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(data);
        panel.add(jLabel);
        editorFrame.getContentPane().add(panel);

        editorFrame.pack();
        editorFrame.setLocationRelativeTo(null);
        editorFrame.setVisible(true);
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void actualizarLabel(MouseEvent e) {
        data.setText(e.getX() + "x" + e.getY() +
                " R:" + imagen.getPixel(e.getY(), e.getX()).getR() +
                " G:" + imagen.getPixel(e.getY(), e.getX()).getG() +
                " B:" + imagen.getPixel(e.getY(), e.getX()).getB());

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        actualizarLabel(e);
    }
}
