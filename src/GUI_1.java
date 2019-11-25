import javax.swing.*;
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
