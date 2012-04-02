import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RectangleTest {

    @Test
    public void shouldCalculateArea() {
        assertEquals(10, new Rectangle(5, 2).area());
        assertEquals(24, new Rectangle(4, 6).area());
    }

    @Test
    public void shouldCalculatePerimeter() {
        assertEquals(20, new Rectangle(3, 7).perimeter());
        assertEquals(12, new Rectangle(2, 4).perimeter());
    }
}
