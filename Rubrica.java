package RubricaDefinitiva;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Rubrica {
    static ArrayList<Contatto> persone;

    Rubrica() {

        persone = new ArrayList<Contatto>(); // Creiamo l'ArrayList persone
        CaricareDati(); // Carichiamo eventuali dati precedentemente inseriti
    }

    // Creiamo un metodo che ci consenta di salvare i dati
    static void SalvareDati() {
        try {
            Contatto persona; // Creiamo un oggetto persona
            String linea; // Creiamo una variabile che contenga il contatto

            // Creiamo un file di testo dove poter salvare i dati
            FileWriter file = new FileWriter("Persone.txt");
            PrintWriter print = new PrintWriter(file);

            // Scorriamo il nostro ArrayList
            for (int i = 0; i < persone.size(); i++) {
                persona = persone.get(i);
                // Scriviamo in ogni linea del file di testo i nostri dati separati dalla virgola
                linea = persona.nome + "," + persona.cognome + "," + persona.indirizzo + "," + persona.numero + ","+ persona.Email;
                print.println(linea);
            }
            print.flush();
            print.close();
            file.close();
        } catch (IOException IO) {
            System.out.println(IO);
        }
    }

    // Creiamo un metodo che ci consenta di caricare i nostri dati
    void CaricareDati() {
        String array[] = null;
        String nome;
        String cognome;
        String indirizzo;
        String numero;
        String Email;
        String linea;

        try {
            FileReader file = new FileReader("Persone.txt");
            BufferedReader buffer = new BufferedReader(file);
            linea = buffer.readLine();

            // Usiamo un ciclo per leggere tutto il file finchè trova qualcosa
            while (linea != null) {
                array = linea.split(","); // questa istruzione andrà a separare i dati in base alla virgola e verranno inseriti nell'array

                // inseriamo i campi in questo modo
                nome = array[0];
                cognome = array[1];
                indirizzo = array[2];
                numero = array[3];
                Email = array[4];

                // Creiamo un oggetto persona e lo inseriamo nell'ArrayList persone
                Contatto persona = new Contatto(nome, cognome, indirizzo, numero, Email);
                persone.add(persona);

                // Leggiamo la nuova riga finchè non usciremo dal ciclo
                linea = buffer.readLine();
            }
            buffer.close();
            file.close();
        } catch (IOException IO) {
            System.out.println(IO);
        }
    }

    // Facciamo un metodo che ci consente di cercare in base al nome e cognome
    static void cercaNomeCognome() {

        // Prendiamo in input il nome e cognome della persona che vogliamo cercare
        String Nome = JOptionPane.showInputDialog(null, "inserisci il nome della persona che vuoi cercare: ");
        String Cognome = JOptionPane.showInputDialog(null, "inserisci il cognome della persona che vuoi cercare: ");

        // Se non vengono inseriti dati in input viene mandato a schermo un errore
        if (Nome != null && Cognome != null) {
            // Inizilizziamo una variabile booleana per non far stampare il messaggio tutte le volte che riparte il ciclo
            boolean contattoTrovato = false;

            // Scorriamo l'arraylist
            for (int i = 0; i < Rubrica.persone.size(); i++) {
                Contatto Persona = Rubrica.persone.get(i);

                // Se il nome e il cognome corrisponde al nome e cognome che sono stati cercati chiamiamo un metodo che stampi il contatto
                if (Nome.equals(Persona.nome) && Cognome.equals(Persona.cognome)) {
                    Persona.interfaccia();
                    contattoTrovato = true; // la variabile booleana viene messa a true se il contatto è stato trovato
                }
            }

            // Se il contatto non è stato trovato stampa il seguente messaggio
            if (contattoTrovato == false) {
                JOptionPane.showMessageDialog(null, "La persona che stai cercando non è stata trovata! ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Non hai inserito nome e cognome, Riprova");
        }
    }

    // Facciamo un metodo che ci consente di cercare in base all'email
    static void cercaEmail() {

        // Prendiamo in input l'email della persona che vogliamo cercare
        String Email = JOptionPane.showInputDialog(null, "inserisci l'email della persona che vuoi cercare: ");

        // Se non vengono inseriti dati in input viene mandato a schermo un errore
        if (Email != null) {
            // Inizilizziamo una variabile booleana per non far stampare il messaggio tutte le volte che riparte il ciclo
            boolean contattoTrovato = false;

            // Scorriamo l'arraylist
            for (int i = 0; i < Rubrica.persone.size(); i++) {
                Contatto Persona = Rubrica.persone.get(i);

                // Se l'email corrisponde all'email cercata Chiamiamo un metodo che stampi il contatto
                if (Email.equals(Persona.Email)) {
                    Persona.interfaccia();
                    contattoTrovato = true; // la variabile booleana viene messa a true se il contatto è stato trovato
                }

            }

            // Se il contatto non è stato trovato stampa il seguente messaggio
            if (contattoTrovato == false) {
                JOptionPane.showMessageDialog(null, "La persona che stai cercando non è stata trovata! ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Non hai inserito l'email, Riprova");
        }
    }

    // Facciamo un metodo che ci consente di cercare in base al numero di telefono
    static void cercaNumero() {
        // Prendiamo in input il numero della persona che vogliamo cercare
        String Numero = JOptionPane.showInputDialog(null,"inserisci il numero di telefono della persona che vuoi cercare: ");

        // Se non vengono inseriti dati in input viene mandato a schermo un errore
        if (Numero != null) {
            // Inizilizziamo una variabile booleana per non far stampare il messaggio tutte le volte che riparte il ciclo
            boolean contattoTrovato = false;

            // Scorriamo l'arraylist
            for (int i = 0; i < Rubrica.persone.size(); i++) {
                Contatto Persona = Rubrica.persone.get(i);

                // Se il numero corrisponde al numero cercato chiamiamo un metodo che stampi il contatto
                if (Numero.equals(Persona.numero)) {
                    Persona.interfaccia();
                    contattoTrovato = true; // la variabile booleana viene messa a true se il contatto è stato trovato
                }

            }
            // Se il contatto non è stato trovato stampa il seguente messaggio
            if (contattoTrovato == false) {
                JOptionPane.showMessageDialog(null, "La persona che stai cercando non è stata trovata! ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Non hai inserito il numero, Riprova");
        }
    }

    // Facciamo un metodo che ci consente di modificare un contatto in base all'email
    static void EmailModificaContatto() {
        // Prendiamo in input l'email da cercare
        String Email = JOptionPane.showInputDialog("Inserisci l'email del contatto da modificare: ");

        // Se non vengono inseriti dati in input viene mandato a schermo un errore
        if (Email != null) {

            // Inizilizziamo una variabile booleana per non far stampare il messaggio tutte le volte che riparte il ciclo
            boolean contattoTrovato = false;

            // Inizializziamo una variabile booleana che tenga conto se l'email è unica o no
            boolean emailTrovata = false;

            // Inizializziamo una variabile booleana che tenga conto se il numero è unico o no
            boolean numeroTrovato = false;

            // Scorriamo l'arraylist
            for (int i = 0; i < Rubrica.persone.size(); i++) {
                Contatto Persona = Rubrica.persone.get(i);

                // Se l'email corrisponde all'email cercata chiediamo in input i nuovi dati
                if (Email.equals(Persona.Email)) {

                    // Prendiamo i nuovi dati in input
                    String nomeModificato = JOptionPane.showInputDialog("Inserisci il nuovo nome: ");
                    String cognomeModificato = JOptionPane.showInputDialog("Inserisci il nuovo cognome: ");
                    String indirizzoModificato = JOptionPane.showInputDialog("Inserisci il nuovo indirizzo: ");
                    String emailModificata = JOptionPane.showInputDialog("Inserisci la nuova email: ");
                    String numeroModificato = JOptionPane.showInputDialog("Inserisci il nuovo numero: ");

                    // Se la nuova email esiste già la variabile booleana viene messa a true
                    if (emailModificata.equals(Persona.Email)) {
                        emailTrovata = true;
                    }

                    // Se il nuovo numero esiste già la variabile booleana viene messa a true
                    if (numeroModificato.equals(Persona.numero)) {
                        numeroTrovato = true;
                    }

                    // Se i nuovi campi sono vuoti stampa un messaggio di errore
                    if (!nomeModificato.isEmpty() && !cognomeModificato.isEmpty() && !indirizzoModificato.isEmpty()&& !emailModificata.isEmpty()&& !numeroModificato.isEmpty()) {

                        // Se sia il numero che l'email non sono corrette stampa un messaggio di errore
                        if (numeroModificato.length() == 10 && Rubrica.contieneSoloNumeri(numeroModificato) && numeroTrovato == false|| emailTrovata == false && Rubrica.verificaFormatoEmail(emailModificata)) {

                            // Se il numero è esattamente di 10 numeri, contiene solo numeri ed è unico procede con l'operazione altrimenti stampa un messaggio di errore
                            if (numeroModificato.length() == 10 && Rubrica.contieneSoloNumeri(numeroModificato)
                                    && numeroTrovato == false) {

                                // Se l'email rispecchia il formato indicato ed è unica procede con l'operazione altrimenti stampa un messaggio di errore
                                if (emailTrovata == false && Rubrica.verificaFormatoEmail(emailModificata)) {

                                    // Salviamo i nuovi dati
                                    Persona.nome = nomeModificato;
                                    Persona.cognome = cognomeModificato;
                                    Persona.indirizzo = indirizzoModificato;
                                    Persona.Email = emailModificata;
                                    Persona.numero = numeroModificato;

                                    contattoTrovato = true; // la variabile booleana viene messa a true se il contatto è stato trovato

                                    // Diciamo all'utente che i dati sono stati modificati
                                    JOptionPane.showMessageDialog(null, "La modifica è avvenuta con successo! ");
                                } else {
                                    JOptionPane.showMessageDialog(null, "l'email non è corretta ");
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "il numero non è corretto ");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "l'email e il numero non sono corretti ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Non hai compilato i dati, Riprova");
                    }
                }
            }
            // Se il contatto non è stato trovato diciamo all'utente che non è stato possibile modificare i dati
            if (contattoTrovato == false) {
                JOptionPane.showMessageDialog(null,"La modifica non è avvenuta ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Non hai inserito l'email, Riprova");
        }
    }

    // Facciamo un metodo che ci consente di eliminare un contatto in base all'email
    static void EmailEliminaContatto() {

        // Prendiamo in input l'email da cercare
        String Email = JOptionPane.showInputDialog(null, "Inserisci l'email del contatto da eliminare ");

        // Se non vengono inseriti dati in input viene mandato a schermo un errore
        if (Email != null) {

            // Inizilizziamo una variabile booleana per non far stampare il messaggio tutte le volte che riparte il ciclo
            boolean contattoTrovato = false;

            // Scorriamo l'arraylist
            for (int i = 0; i < Rubrica.persone.size(); i++) {
                Contatto Persona = Rubrica.persone.get(i);

                // Se l'email corrisponde all'email cercata
                if (Email.equals(Persona.Email)) {
                    // il contatto viene rimosso
                    Rubrica.persone.remove(i);

                    contattoTrovato = true; // la variabile booleana viene messa a true se il contatto è stato trovato

                    // Diciamo all'utente che il contatto è stato eliminato
                    JOptionPane.showMessageDialog(null, "Persona eliminata con successo! ");
                }
            }

            // Se il contatto non è stato trovato diciamo all'utente che non è stato possibile eliminare i dati
            if (contattoTrovato == false) {
                JOptionPane.showMessageDialog(null, "La Persona non è stata eliminata ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Non hai inserito l'email, Riprova");
        }
    }

    // Facciamo un metodo che ci consente di stampare tutta la lista di contatti
    static void StampaTuttiContatti() {
        // Creiamo una variabile messaggio che contenga tutto ciò che vogliamo stampare
        String messaggio = "";
        // Scorriamo l'Arraylist
        for (Contatto persona : Rubrica.persone) {

            // Componiamo la nostra stringa prendendo tutti gli elementi
            messaggio = messaggio + "   Nome: " + persona.nome + "\n";
            messaggio = messaggio + "   Cognome: " + persona.cognome + "\n";
            messaggio = messaggio + "   Indirizzo: " + persona.indirizzo + "\n";
            messaggio = messaggio + "   Numero: " + persona.numero + "\n";
            messaggio = messaggio + "   Email: " + persona.Email + "\n";
            messaggio = messaggio + "----------------------------------------------------------\n";
        }

        // Creiamo un pannello scorrevole con la variabile messaggio dentro
        JScrollPane scroll = new JScrollPane(new JTextArea(messaggio));
        // Settiamo le dimensioni del pannello
        scroll.setPreferredSize(new Dimension(150, 200));

        // Se il messaggio non è vuoto stampa la lista dei contatti altrimenti dice all'utente che non ci sono contatti
        if (!messaggio.isEmpty()) {
            JOptionPane.showMessageDialog(null, scroll, "Lista Contatti", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Non ci sono contatti", "Lista Contatti",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    // Implementiamo un metodo che verifichi se la stringa numero ha solo numeri
    public static boolean contieneSoloNumeri(String n) {
        Pattern formato = Pattern.compile("\\d+");
        return formato.matcher(n).matches();
    }

    // Implementiamo un metodo che verifichi se l'email corrisponde al formato di un
    // email valida
    public static boolean verificaFormatoEmail(String e) {
        Pattern formato = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        return formato.matcher(e).matches();
    }

}