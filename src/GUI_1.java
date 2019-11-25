import javax.swing.*;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_1 {
    private JButton search_confirm;
    private JPanel panel;
    private JButton button1;

    public GUI_1() {
        search_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Vi indlæser stadig filmene, vent venligst.");
            }

            // hint: opret først et vindue f.eks. JFrame window = new JFrame();
            // derefter lav en Container f.eks. Container contentPane = window.getContentPane();
            // derefter kan et JPanel tilføjes til containeren. Det ser dog ud til at du vil lave en knap?
            // --> lav en JButton, tilføj til dit JPanel, tilføj derefter JPanel til contentPane. kasser inde i kasser yo
            // det bliver hurtigt til rigtig meget kode - jeg synes vi skal bruge Scene Builder - det virker mere intuitivt, og så kan vi bruge javaFX (anbefalet af andre SWU'ere) 
            // jeg skal nok hitte ud af hvordan man får programmet til at snakke sammen med en controller klasse
            // der ligger ret mange videoer om det på youtube, hvis man er interesseret
            // ps. jeg ved ikke hvor smart det er at have main(String[] args) metoden i GUI klassen.
            // det er som regel "entry point" til programmet (starter hele molevitten) - jeg ville måske kalde en "opret GUI" metode i main metoden, et andet sted

            public static void main(String[] args) {
                JPanel p = new JPanel("search_button");
                p.setContentPane(new GUI_1().panel);
                p.setDefaultCloseOperation(JPanel.EXIT_ON_CLOSE);
                p.pack();
                p.setVisible(true);
            }
        });
    }
}
