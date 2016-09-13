package ca.qc.bdeb.maveo.vue;

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

    MainFrame testFrame = new MainFrame();

    @Before
    public void setUp(){

    }

    @Test
    public void test1 () throws AWTException {
        testFrame.getBtnArreter().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        Robot bot = new Robot();


    }





}