package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoTest {
    @Test
    public void run() {
        DemoComponent dc = new DemoComponent();
        assertEquals(5, dc.add(2, 3));
    }

    @Test
    public void run2() {
        DemoComponent dc = new DemoComponent();
        assertEquals(6, dc.add(3, 3));
    }

}
