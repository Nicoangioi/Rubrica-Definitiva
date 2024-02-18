package RubricaDefinitiva;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        // Settiamo la nostra finestra
        JFrame frame = new JFrame("Rubrica");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(900, 400);

        // Facciamo in modo che alla chiusura della finestra i dati vengano salvati
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Rubrica.SalvareDati(); // Salviamo i dati chiamando il nostro metodo
                System.exit(0); // Chiudiamo la finestra una volta salvati
            }
        });

        //Settiamo il nostro pannello
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // Specifichiamo i vincoli per il GridBagLayout
        GridBagConstraints vincoli = new GridBagConstraints();
        vincoli.anchor = GridBagConstraints.WEST;
        vincoli.insets = new Insets(5, 10, 5, 10);

        //Creiamo le label e i field
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField(20);
        JLabel cognomeLabel = new JLabel("Cognome:");
        JTextField cognomeField = new JTextField(20);
        JLabel indirizzoLabel = new JLabel("Indirizzo:");
        JTextField indirizzoField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        JLabel numeroLabel = new JLabel("Numero di telefono:");
        JTextField numeroField = new JTextField(20);

        // Creiamo un array che contenga le opzioni del nostro combobox
        String[] opzioni = { "per nome e cognome", "per email", "per numero di telefono" };

        // Aggiungiamo un comboBox per la ricerca
        JComboBox ricerca = new JComboBox(opzioni);

        //Settiamo le dimensioni dei Field
        nomeField.setColumns(30);
        cognomeField.setColumns(30);
        indirizzoField.setColumns(30);
        emailField.setColumns(30);
        numeroField.setColumns(30);

        // Aggiungiamo i bottoni Aggiungi,Cerca,Modifica,Stampa ed Elimina
        JButton AggiungiButton = new JButton("Aggiungi");
        JButton CercaButton = new JButton("Ricerca");
        JButton ModificaButton = new JButton("Modifica");
        JButton StampaContatti = new JButton("Contatti");
        JButton eliminaButton = new JButton("Elimina");

        // Settiamo le posizioni delle label e dei Field
        vincoli.gridx = 0;
        vincoli.gridy = 0;
        panel.add(nomeLabel, vincoli);

        vincoli.gridx = 1;
        panel.add(nomeField, vincoli);

        vincoli.gridx = 0;
        vincoli.gridy = 1;
        panel.add(cognomeLabel, vincoli);

        vincoli.gridx = 1;
        panel.add(cognomeField, vincoli);

        vincoli.gridx = 0;
        vincoli.gridy = 2;
        panel.add(indirizzoLabel, vincoli);

        vincoli.gridx = 1;
        panel.add(indirizzoField, vincoli);

        vincoli.gridx = 0;
        vincoli.gridy = 3;
        panel.add(emailLabel, vincoli);

        vincoli.gridx = 1;
        panel.add(emailField, vincoli);

        vincoli.gridx = 0;
        vincoli.gridy = 4;
        panel.add(numeroLabel, vincoli);

        vincoli.gridx = 1;
        panel.add(numeroField, vincoli);

        // Settiamo le posizioni dei bottoni e del ComboBox

        vincoli.gridx = 0;
        vincoli.gridy = 5;
        panel.add(AggiungiButton, vincoli);

        vincoli.gridx = 2;
        vincoli.gridy = 0;
        panel.add(CercaButton, vincoli);

        vincoli.gridx = 3;
        panel.add(ricerca, vincoli);

        vincoli.gridx = 2;
        vincoli.gridy = 1;
        panel.add(ModificaButton, vincoli);

        vincoli.gridx = 2;
        vincoli.gridy = 2;
        panel.add(eliminaButton, vincoli);

        vincoli.gridx = 2;
        vincoli.gridy = 3;
        panel.add(StampaContatti, vincoli);

        // Settiamo le dimensioni dei bottoni
        ModificaButton.setPreferredSize(new Dimension(100, 25));
        eliminaButton.setPreferredSize(new Dimension(100, 25));
        CercaButton.setPreferredSize(new Dimension(100, 25));
        StampaContatti.setPreferredSize(new Dimension(100, 25));

        // Creiamo un istanza della classe rubrica
        Rubrica rubrica = new Rubrica();

        // Facciamo in modo che il nostro bottone StampaContatti possa stampare tutto l'ArrayList
        StampaContatti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Rubrica.StampaTuttiContatti(); // Chiamiamo il metodo della classe rubrica che stampa tutto l'arraylist

            }
        });

        // Facciamo in modo che il nostro bottone Aggiungi possa aggiungere i contatti
        AggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Prendiamo in input i dati di un contatto
                String nome = nomeField.getText();
                String cognome = cognomeField.getText();
                String indirizzo = indirizzoField.getText();
                String email = emailField.getText();
                String numero = numeroField.getText();

                // Verifichiamo se l'email che l'utente vuole inserire è unica
                boolean emailtrovata = false;
                for (int i = 0; i < Rubrica.persone.size(); i++) {
                    Contatto Persona = Rubrica.persone.get(i);
                    if (email.equals(Persona.Email)) {
                        emailtrovata = true;
                    }
                }

                // Verifichiamo se il numero che l'utente vuole inserire è unico
                boolean numerotrovato = false;
                for (int i = 0; i < Rubrica.persone.size(); i++) {
                    Contatto Persona = Rubrica.persone.get(i);
                    if (numero.equals(Persona.numero)) {
                        numerotrovato = true;
                    }
                }

                // Se i campi non sono vuoti allora aggiunge un contatto altrimenti stampa un messaggio di errore
                if (!nome.isEmpty() && !cognome.isEmpty() && !indirizzo.isEmpty() && !email.isEmpty()
                        && !numero.isEmpty()) {
                    // Se sia il numero che l'email non sono corretti stampa un messaggio di errore per farlo sapere all'utente
                    if (numero.length() == 10 && Rubrica.contieneSoloNumeri(numero) && numerotrovato == false
                            || emailtrovata == false && Rubrica.verificaFormatoEmail(email)) {
                        // Se il numero è esattamente di 10 numeri, contiene solo numeri ed è unico aggiunge il contatto altrimenti stampa un messaggio di errore
                        if (numero.length() == 10 && Rubrica.contieneSoloNumeri(numero) && numerotrovato == false) {
                            // Se l'email è unica e rispecchia il formato valido aggiunge il contatto altrimenti stampa un messaggio di errore
                            if (emailtrovata == false && Rubrica.verificaFormatoEmail(email)) {
                                // Creiamo un oggetto Persona
                                Contatto Persona = new Contatto(nome, cognome, indirizzo, numero, email);

                                // Aggiungiamo all'arraylist la nuova Persona
                                Rubrica.persone.add(Persona);

                                // Resettiamo i campi di testo dopo che i dati sono stati aggiunti
                                nomeField.setText("");
                                cognomeField.setText("");
                                indirizzoField.setText("");
                                emailField.setText("");
                                numeroField.setText("");

                                // Facciamo sapere all'utente che il contatto è stato aggiunto
                                JOptionPane.showMessageDialog(null, "Il Contatto è stato aggiunto ");
                            } else {
                                JOptionPane.showMessageDialog(null, "L'Email non è corretta ");

                                // Resettiamo i campi di testo
                                nomeField.setText("");
                                cognomeField.setText("");
                                indirizzoField.setText("");
                                emailField.setText("");
                                numeroField.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,"Il numero di telefono non è corretto ");

                            // Resettiamo i campi di testo
                            nomeField.setText("");
                            cognomeField.setText("");
                            indirizzoField.setText("");
                            emailField.setText("");
                            numeroField.setText("");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Sia il numero che l'email non sono corretti");

                        // Resettiamo i campi di testo
                        nomeField.setText("");
                        cognomeField.setText("");
                        indirizzoField.setText("");
                        emailField.setText("");
                        numeroField.setText("");
                    }

                } else {
                    // Diciamo all'utente che deve prima inserire i dati per poter aggiungere un contatto
                    JOptionPane.showMessageDialog(null, "Per aggiungere un contatto devi prima Inserire tutti i dati ");

                    // Resettiamo i campi di testo
                    nomeField.setText("");
                    cognomeField.setText("");
                    indirizzoField.setText("");
                    emailField.setText("");
                    numeroField.setText("");
                }

            }
        });

        // Facciamo in modo che il nostro bottone Ricerca possa effettuare una ricerca in base alla scelta dell'utente
        CercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Se viene selezionato "per nome e cognome" esegue una ricerca per nome e cognome (quel campo corrisponde all'indice 0 dell'array)
                if (ricerca.getSelectedIndex() == 0) {
                    Rubrica.cercaNomeCognome();
                }

                // Se viene selezionato "per email" esegue una ricerca per email (quel campo corrisponde all'indice 1 dell'array)
                if (ricerca.getSelectedIndex() == 1) {
                    Rubrica.cercaEmail();
                }

                // Se viene selezionato "per numero di telefono" esegue una ricerca per numero di telefono (quel campo corrisponde all'indice 2 dell'array)
                if (ricerca.getSelectedIndex() == 2) {
                    Rubrica.cercaNumero();
                }

            }
        });

        // Facciamo in modo che il nostro bottone modifica possa modificare un contatto
        ModificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Chiamiamo il metodo che esegue la modifica in base all'email
                Rubrica.EmailModificaContatto();

            }
        });

        // Facciamo in modo che il nostro bottone elimina possa eliminare un contatto
        eliminaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Chiamiamo il metodo che esegue l'eliminazione in base all'email
                Rubrica.EmailEliminaContatto();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

}