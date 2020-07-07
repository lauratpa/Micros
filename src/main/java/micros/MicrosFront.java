package micros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class MicrosFront implements ActionListener {

     JPanel panel = new JPanel();
     JRadioButton easyRadio = new JRadioButton("Easy");
     JRadioButton mediumRadio = new JRadioButton("Medium");
     JRadioButton hardRadio = new JRadioButton("Hard");
     String dziedziny[] = {"Całki", "Pochodne", "Macierze"};
     JComboBox dziedzina = new JComboBox(dziedziny);
     JButton generuj = new JButton("Generuj");
     JLabel zadanie = new JLabel();
     JLabel twojeRozwiazanieLabel = new JLabel("Odpowiedź:");
     JTextArea rozwiazanie = new JTextArea();
     JButton submit = new JButton("Sprawdź");
     JButton clear = new JButton("Wyczyść");
     File file;                                     //zmienna do przechowywania pliku z którego generowane jest zadanie
     Path path;                                     // zmienna przechowująca ścieżkę pliku file
     String prawidlowaOdpowiedz;                    //zmienna przechowująca wartość poprawnej odpowiedzi
    String absoluteIconPath = "/Users/soniaorlikowska/IdeaProjects/Micros/src/main/resources";


    //Deklaracja wszystkich plikow

    //EasyFiles
     File easyCalki = new File("EasyCalki.txt");
     Path easyCalkiPath = Paths.get("/Users/soniaorlikowska/IdeaProjects/Micros/EasyCalki.txt");

     File easyPochodne = new File("EasyPochodne.txt");
     Path easyPochodnePath = Paths.get("/Users/soniaorlikowska/IdeaProjects/Micros/EasyPochodne.txt");

     File easyMatrix = new File("EasyMacierze.txt");
     Path easyMatrixPath = Paths.get("/Users/soniaorlikowska/IdeaProjects/Micros/EasyMacierze.txt");

    //MediumFiles
    File mediumCalki = new File("MediumCalki.txt");
    Path mediumCalkiPath = Paths.get("/Users/soniaorlikowska/IdeaProjects/Micros/MediumCalki.txt");

    File mediumPochodne = new File("MediumPochodne.txt");
    Path mediumPochodnePath = Paths.get("/Users/soniaorlikowska/IdeaProjects/Micros/MediumPochodne.txt");

    File mediumMatrix = new File("MediumMacierze.txt");
    Path mediumMatrixPath = Paths.get("/Users/soniaorlikowska/IdeaProjects/Micros/MediumMacierze.txt");

    //HardFiles
    File hardCalki = new File("HardCalki.txt");
    Path hardCalkiPath = Paths.get("/Users/soniaorlikowska/IdeaProjects/Micros/HardCalki.txt");

    File hardPochodne = new File("HardPochodne.txt");
    Path hardPochodnePath = Paths.get("/Users/soniaorlikowska/IdeaProjects/Micros/HardPochodne.txt");

    File hardMatrix = new File("HardMacierze.txt");
    Path hardMatrixPath = Paths.get("/Users/soniaorlikowska/IdeaProjects/Micros/HardMacierze.txt");

    //Layout setup
    public MicrosFront(){

        // set panel layout and look
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gbl);
        //panel.setBackground(Color.white);

        // add components to panel
        JLabel wybierzPoziom = new JLabel("Wybierz poziom:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,0,0);
        gbc.weightx = 1;
        panel.add(wybierzPoziom,gbc);

        //RadioButton Easy
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,60,0,0);
        gbc.weightx = 1;
        panel.add(easyRadio,gbc);

        //RadioButton Medium
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,15,0,0);
        gbc.weightx = 1;
        panel.add(mediumRadio,gbc);

        //RadioButton Hard
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,10);
        gbc.weightx = 1;
        panel.add(hardRadio,gbc);

        //Set Radio Buttons in group
        ButtonGroup radioButtonsGroup = new ButtonGroup();
        radioButtonsGroup.add(easyRadio);
        radioButtonsGroup.add(mediumRadio);
        radioButtonsGroup.add(hardRadio);

        //JLabel Dziedzina
        JLabel wybierzDziedzine = new JLabel("Wybierz dziedzinę:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10,10,0,0);
        gbc.weightx = 1;
        panel.add(wybierzDziedzine,gbc);

        //JCombobox Dziedzina
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10,10,0,0);
        gbc.weightx = 1;
        panel.add(dziedzina,gbc);

        //Generuj JButton
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 0, 10);
        gbc.weightx = 1;
        panel.add(generuj, gbc);

        // tresc zadania
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 4;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(10,20,0,10);
        gbc.weightx = 1;
        panel.add(zadanie, gbc);

        //JLabel rozwiązanie
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10,10,0,0);
        gbc.weightx = 1;
        panel.add(twojeRozwiazanieLabel,gbc);

        //JTextArea Rozwiązanie
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(10,10,0,10);
        gbc.weightx = 1;
        panel.add(rozwiazanie, gbc);

       //Clear JButton
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(10,10,0,0);
        gbc.weightx = 1;
        panel.add(clear,gbc);

        JButton hint = new JButton("Podpowiedź");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(10,10,0,0);
        gbc.weightx = 1;
        panel.add(hint,gbc);

        //JButton Sprawdz
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.insets = new Insets(10,10,0,0);
        gbc.weightx = 1;
        panel.add(submit,gbc);

        //ActionListners
        easyRadio.addActionListener( this);
        mediumRadio.addActionListener( this);
        hardRadio.addActionListener( this);
        generuj.addActionListener(this);
        submit.addActionListener(this);
        clear.addActionListener(this);
        dziedzina.addActionListener(this);


    }
    //Metoda wyświetlająca zadanie do zrobienia
    public  String displayProblem()  {

        if (easyRadio.isSelected()) {
            if (dziedzina.getSelectedIndex() == 0) {
                file = easyCalki;
                path = easyCalkiPath;
            } else if (dziedzina.getSelectedIndex() == 1) {
                file = easyPochodne;
                path = easyPochodnePath;
            } else if (dziedzina.getSelectedIndex() == 2) {
                file = easyMatrix;
                path = easyMatrixPath;
            }
        }
        else if (mediumRadio.isSelected()) {
            if (dziedzina.getSelectedIndex() == 0) {
                file = mediumCalki;
                path = mediumCalkiPath;
            } else if (dziedzina.getSelectedIndex() == 1) {
                file = mediumPochodne;
                path = mediumPochodnePath;
            } else if (dziedzina.getSelectedIndex() == 2) {
                file = mediumMatrix;
                path = mediumMatrixPath;
            }
        }
        else if (hardRadio.isSelected()) {
            if (dziedzina.getSelectedIndex() == 0) {
                file = hardCalki;
                path = hardCalkiPath;
            } else if (dziedzina.getSelectedIndex() == 1) {
                file = hardPochodne;
                path = hardPochodnePath;
            } else if (dziedzina.getSelectedIndex() == 2) {
                file = hardMatrix;
                path = hardMatrixPath;
            }
        }
        ProblemGenerator problemGenerator = new ProblemGenerator(file, path);
        /////////////////

        File file = new File("/Users/soniaorlikowska/IdeaProjects/Micros/src/main/resources/");
        File[] files = file.listFiles();
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (File file1 : files) {

            System.out.print(file1.getName() + " ");
        }
        System.out.println();

        String name ="";

            if (files != null)
                name = files[files.length - 1].getPath();


        System.out.println(name);

        ///////////////

        /*Path dir = Paths.get("/Users/soniaorlikowska/IdeaProjects/Micros/src/main/resources/");  // specify your directory
        Optional<Path> lastFilePath = null;  // finally get the last file using simple comparator by lastModified field
        try {
            lastFilePath = Files.list(dir)    // here we get the stream with full directory listing
                    .filter(f -> !Files.isDirectory(f))  // exclude subdirectories from listing
                    .max(Comparator.comparingLong(f -> f.toFile().lastModified()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ( lastFilePath.isPresent() ) // your folder may be empty
        {
            System.out.println(lastFilePath);
          //todo tu powinna byc nazwa pliku a nie sciezka do niego
            // do your code here, lastFilePath contains all you need
        }*/
        zadanie.setIcon(null);
        panel.repaint();
        zadanie.setIcon(new ImageIcon(name));
        panel.repaint();

        deleteFiles(files);

        //zadanie.setText(problemGenerator.trescZadania);
        prawidlowaOdpowiedz = problemGenerator.trescOdpowiedzi;


        return zadanie.getText();

    }

    private void deleteFiles(File[] files) {
        for (File file1 : files) {

            file1.delete();
        }
    }

    //Metoda wyświetlająca panel
    public JPanel getUI () {return panel; }

    //Metoda zwracająca wartość wpisanego rozwiązania
    public JTextArea getRozwiazanie() { return rozwiazanie; }

    //Metoda zwracająca prawidłową wartość rozwiązania
    public void setPrawidlowaOdpowiedz(String prawidlowaOdpowiedz) {this.prawidlowaOdpowiedz = prawidlowaOdpowiedz;}

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //deklarcja sourceGeneruj dla generuj Button i sourceSumbit dla submitButton
        Object sourceGeneruj = e.getSource();
        Object sourceSumbit = e.getSource();

        //EasyCałki
        if (easyRadio.isSelected() && dziedzina.getSelectedIndex() == 0   && sourceGeneruj == generuj) displayProblem();
        //EasyPochodne
        else if (easyRadio.isSelected() && dziedzina.getSelectedIndex() == 1 && sourceGeneruj == generuj) displayProblem();
        //EasyMatrix
        else if (easyRadio.isSelected() && dziedzina.getSelectedIndex() == 2 && sourceGeneruj == generuj) displayProblem();
        //MediumCałki
        else if (mediumRadio.isSelected() && dziedzina.getSelectedIndex() == 0  && sourceGeneruj == generuj) displayProblem();
        //MediumPochodne
        else if (mediumRadio.isSelected() && dziedzina.getSelectedIndex() == 1  && sourceGeneruj == generuj) displayProblem();
        //MediumMatrix
        else if (mediumRadio.isSelected() && dziedzina.getSelectedIndex() == 2  && sourceGeneruj == generuj) displayProblem();
        //HardCałki
        else if (hardRadio.isSelected() && dziedzina.getSelectedIndex() == 0  && sourceGeneruj == generuj) displayProblem();
        //HardPochodne
        else if (hardRadio.isSelected() && dziedzina.getSelectedIndex() == 1  && sourceGeneruj == generuj) displayProblem();
        //HardMatrix
        else if (hardRadio.isSelected() && dziedzina.getSelectedIndex() == 2  && sourceGeneruj == generuj) displayProblem();

        if(rozwiazanie.getText() != "" && sourceSumbit == submit) {

            if (rozwiazanie.getText().equals(prawidlowaOdpowiedz)) {
                panel.setBackground(new java.awt.Color(2, 255, 102, 255).brighter());

            }
            if (!rozwiazanie.getText().equals(prawidlowaOdpowiedz)) {
                JOptionPane.showMessageDialog(null, "Spróbuj jeszcze raz :)");

            }
        }



    }
}

