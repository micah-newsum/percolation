import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PercolationTest {
    
    @Test
    public void testIsOpen() {
        Percolation percolation = new Percolation(3);
        assertFalse(percolation.isOpen(1, 1));
        percolation.open(1, 1);
        assertTrue(percolation.isOpen(1, 1));
    }

    @Test
    public void testIsFull() {
        Percolation percolation = new Percolation(3);
        percolation.open(2, 1);
        percolation.open(2, 2);
        assertFalse(percolation.isFull(2, 1));
        assertFalse(percolation.isFull(2, 2));
        percolation.open(3, 2);
        assertFalse(percolation.isFull(3, 2));

        // open top site
        percolation.open(1, 1);
        assertTrue(percolation.isFull(1, 1));
        assertTrue(percolation.isFull(2, 1));
        assertTrue(percolation.isFull(2, 2));
        assertTrue(percolation.isFull(3, 2));
    }
}
