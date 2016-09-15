package ca.qc.bdeb.maveo.vue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import static org.junit.Assert.*;

/**
 * Created by WuTchanKi on 2016-09-12.
 */
public class MainFrameTest {

    //  MainFrame testFrame = new MainFrame();
    private int test = 0;

    @Before
    public void setUp() {

    }

    @Test
    public void testBoutonArreter() throws AWTException, InterruptedException {


        //testFrame.getBtnArreter().setLocation(25, 25);

        Robot bot = new Robot();
        bot.mouseMove(26, 26);
        bot.mousePress(InputEvent.BUTTON1_MASK);
//add time between press and release or the input event system may
//not think it is a click
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
        }
        bot.mouseRelease(InputEvent.BUTTON1_MASK);


      /*  testFrame.getBtnArreter().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                test = 5;
            }
        });*/

        assertEquals(5, test);


    }

    @Test
    public void testBoutonPlay() throws AWTException {
        // testFrame.getBtnJouerPause().setLocation(50, 50);

        Robot bot = new Robot();
        bot.mouseMove(52, 52);
        bot.mousePress(InputEvent.BUTTON1_MASK);
//add time between press and release or the input event system may
//not think it is a click
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
        }
        bot.mouseRelease(InputEvent.BUTTON1_MASK);


        /*testFrame.getBtnJouerPause().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                test = 5;
            }
        });*/

        assertEquals(5, test);

    }


    @Test
    public void testPresenceLabel() {
        //Object objet = testFrame.getFenetre();

        // assertNotEquals(null, objet);
    }

    @Test
    public void testPresencePanel() {
        //  Object objet = testFrame.getContent();
        // Object objet2 = testFrame.getContent2();
        // assertNotEquals(null, objet2);
        // assertNotEquals(null, objet);
        //   assertNotEquals(objet, objet2);
    }


}