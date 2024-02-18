package RubricaDefinitiva;

import javax.swing.JOptionPane;

public class Contatto {
    // dichiariamo le variabili di un contatto
    String nome;
    String cognome;
    String indirizzo;
    String numero;
    String Email;

    // Costruttore
    Contatto(String n, String c, String i, String nu, String e) {
        nome = n;
        cognome = c;
        indirizzo = i;
        numero = nu;
        Email = e;
    }

    // Creiamo un metodo che consenta la visualizzazione dell'intero contatto
    void interfaccia() {
        JOptionPane.showMessageDialog(null,"Nome: " + nome + "\nCognome: " + cognome + "\nIndirizzo: " + indirizzo + "\nNumero: " + numero + "\nEmail: " + Email);
    }
}