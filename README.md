# PEVS---OOP-Java-project---Dad-Jokes

SIMPLE GUI project for OOP at Paneuropska University in Bratislava


import javax.swing.*;
import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {


        JFrame appka = new SucharGUI("Suchar Joke Application");
        appka.setVisible(true);



        }
    }
    
    import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class SucharGUI extends JFrame
{
    private JPanel mainPanel;
    private JTextField searchField;
    private JButton generateRandom;
    private JButton generateSearched;
    private JTextPane jokesPane2;
    private JLabel logo;


    public SucharGUI (String title) throws IOException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(500,400));
        this.setMinimumSize(new Dimension(500,400));
        this.setMaximumSize(new Dimension(500,400));
        this.pack();
        SimpleAttributeSet bSet = new SimpleAttributeSet();
        StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_CENTER);
        StyledDocument doc = jokesPane2.getStyledDocument();
        doc.setParagraphAttributes(0, 104, bSet, false);
        searchField.setBorder(new LineBorder(Color.black,2));
        searchField.setForeground(Color.black);
        searchField.setBorder(new RoundedCornerBorder());
        searchField.setCaretColor(Color.black);
        searchField.setBackground(Color.white);
        jokesPane2.setMargin( new Insets(10,40,10,40));


        generateRandom.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Joke randomvtip = new Joke();
                randomvtip.generateRandom();
                jokesPane2.setText(randomvtip.getJokes());
                searchField.setText("");
            }
        });



        generateSearched.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                Joke searchJoke = new Joke();
                String searchWord = searchField.getText();
                searchJoke.generatesearch(searchWord);

                if(searchJoke.getSize()==0)
                {
                    jokesPane2.setText("No Jokes found");
                }

                else {
                    Random num = new Random();
                    int x = num.nextInt(searchJoke.getSize());
                    for (int i = 0; i < searchJoke.getSize(); i++)
                    {
                        if(i==x)
                        {
                            jokesPane2.setText(searchJoke.getSearchedVtip().get(i));
                        }
                    }
                }
                searchField.setText("");
            }
        });
    }



}
