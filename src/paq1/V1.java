package paq1;

import paqG07.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class V1 extends JFrame{
    private JTextField n_identificacion;
    private JTextField n_peso;
    private JTextField n_remite;
    private JTextField n_recibe;
    private JComboBox n_pais;
    private JRadioButton a1;
    private JRadioButton a3;
    private JRadioButton a2;
    private JCheckBox inspeccionadoEnAduanasCheckBox;
    private JTextArea n_descripcion;
    private JTextArea n_estado;
    private JButton apilarButton;
    private JButton desapilarButton;
    private JTextField numeroDeColumnaTextField;
    private JButton mostrarDatosDelContenedorButton;
    private JTextField IDContenedorTextField;
    private JButton cuantosButton;
    private JComboBox comboBox2;
    private JPanel mainpanel;
    private JLabel lbv1;
    private JLabel logo;
    private JLabel error_peso;
    private JLabel error_prioridad;
    private JLabel cuantp;
    private JLabel matriz;
    private JTextField hubTextField;
    private JButton actualizarHubButton;
    private JTextField hºHubTextField;
    private JTextField hub;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    public V1 (){
        puerto p1 = new puerto();
        contenedor c = new contenedor();
        setContentPane(mainpanel);
        setTitle("GestionDeContenedores");
        setSize(1300,700);
        matriz.setText(p1.a[0].tostring());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        apilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (n_identificacion.getText().matches("[0-9]+")) {
                    lbv1.setText("Numero de identificacion correcto");
                    if ((a1.isSelected()== true && a2.isSelected()==false && a3.isSelected()==false) || (a1.isSelected()== false && a2.isSelected()==true && a3.isSelected()==false) || (a1.isSelected()== false && a2.isSelected()==false && a3.isSelected()==true)){
                        error_prioridad.setText("Prioridad correcta");
                        if (n_peso.getText().matches("[0-9]+.+[0-9]") || n_peso.getText().matches("[0-9]+")) {
                            if(n_descripcion.getText().length()<=100) {
                                if(n_recibe.getText().length()<=20 && n_remite.getText().length()<=20) {
                                    error_peso.setText("Peso correcto");


                                    c.setNumero_identificación(Integer.parseInt(n_identificacion.getText()));
                                    c.setPeso(Integer.parseInt(n_peso.getText()));
                                    if (a1.isSelected()) {
                                        c.setPrioridad(1);
                                    } else if (a2.isSelected()) {
                                        c.setPrioridad(2);
                                    } else if (a3.isSelected()) {
                                        c.setPrioridad(3);
                                    }
                                    c.setDescripción(n_descripcion.getText());
                                    c.setAduanas(inspeccionadoEnAduanasCheckBox.isSelected());
                                    c.setEstado(n_estado.getText());
                                    c.setEmpresa_receptora(n_recibe.getText());
                                    c.setEmpresa_remitente(n_remite.getText());
                                    c.setPaís_procedencia((String) n_pais.getSelectedItem());
                                    p1.apila(c);
                                    lbv1.setText("Contenedor apliado correctamente");
                                    error_peso.setText("");
                                    error_prioridad.setText("");
                                    matriz.setText(p1.a[0].tostring());
                                }else lbv1.setText("Los nombres de las empresas deben tener menos de 20 caracteres");
                            }else lbv1.setText("La descripcion del contenido debe ser menor a 100 caracteres");
                        } else error_peso.setText("Introduce un numero de peso correcto");
                    }else error_prioridad.setText("Presiona solo un numero de prioridad");
                }else lbv1.setText("Introduce un numero de identificacion entero");



            }
        });
        desapilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenedor c = new contenedor();
                c = p1.a[0].desapilacontenedor(Integer.parseInt(numeroDeColumnaTextField.getText()));
                gestioncontainers gest = new gestioncontainers(c);
                lbv1.setText("Has desapilado el contenedor seleccionado");
                matriz.setText(p1.a[Integer.parseInt(hub.getText())].tostring());
            }
        });
        mostrarDatosDelContenedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p1.a[0].getcontainer(Integer.parseInt(IDContenedorTextField.getText())) != null) {
                    gestioncontainers gest = new gestioncontainers(p1.a[0].getcontainer(Integer.parseInt(IDContenedorTextField.getText())));
                } else if (p1.a[1].getcontainer(Integer.parseInt(IDContenedorTextField.getText())) != null) {
                    gestioncontainers gest = new gestioncontainers(p1.a[1].getcontainer(Integer.parseInt(IDContenedorTextField.getText())));
                } else if (p1.a[2].getcontainer(Integer.parseInt(IDContenedorTextField.getText())) != null) {
                    gestioncontainers gest = new gestioncontainers(p1.a[2].getcontainer(Integer.parseInt(IDContenedorTextField.getText())));

                }

            }
        });
        cuantosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cuantp.setText(String.valueOf(p1.a[0].cuantosa((String)comboBox2.getSelectedItem())));



            }
        });
        a3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a1.setSelected(false);
                a2.setSelected(false);
            }
        });

        a2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a1.setSelected(false);
                a3.setSelected(false);
            }
        });

        a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a3.setSelected(false);
                a2.setSelected(false);
            }
        });
        actualizarHubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(hºHubTextField.getText())<3){
                matriz.setText(p1.a[Integer.parseInt(hºHubTextField.getText())].tostring());
            }
            }
        });
    }
}
