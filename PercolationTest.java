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

        // open top site
        percolation.open(1, 1);
        assertTrue(percolation.isFull(1, 1));

        percolation.open(2, 1);
        assertTrue(percolation.isFull(2, 1));

        percolation.open(2, 2);
        assertTrue(percolation.isFull(2, 2));

        percolation.open(3, 2);
        assertTrue(percolation.isFull(3, 2));
    }

    @Test
    public void testIsNotFull() {
        Percolation percolation = new Percolation(3);
        assertFalse(percolation.isFull(1, 1));
        assertFalse(percolation.isFull(1, 2));
        assertFalse(percolation.isFull(1, 3));
    }

    @Test
    public void testDoesNotPercolateEdgeCases() {
        Percolation percolation = new Percolation(1);
        assertFalse(percolation.percolates());

        percolation = new Percolation(2);
        percolation.open(1, 1);
        assertFalse(percolation.percolates());

        percolation = new Percolation(2);
        percolation.open(2, 1);
        assertFalse(percolation.percolates());
    }

    @Test
    public void testPercolatesEdgeCases() {
        Percolation percolation = new Percolation(1);
        percolation.open(1, 1);
        assertTrue(percolation.percolates());

        percolation = new Percolation(2);
        percolation.open(1, 1);
        percolation.open(2, 1);
        assertTrue(percolation.percolates());
    }

    @Test
    public void testPercolatesVerticalColumn() {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 3);
        percolation.open(2, 3);
        percolation.open(3, 3);
        assertTrue(percolation.percolates());
    }

    @Test
    public void testPercolatesZigZag() {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(2, 2);
        percolation.open(3, 2);
        assertTrue(percolation.percolates());
    }

    @Test(expected = IllegalArgumentException.class)
    public void openWithNegativeRowThrowsIllegalArgumentException() {
        Percolation percolation = new Percolation(3);
        percolation.open(-1, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void openWithOutOfBoundsRowThrowsIllegalArgumentException() {
        Percolation percolation = new Percolation(3);
        percolation.open(4, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void openWithNegativeColumnThrowsIllegalArgumentException() {
        Percolation percolation = new Percolation(3);
        percolation.open(3, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void openWithOutOfBoundsColumnThrowsIllegalArgumentException() {
        Percolation percolation = new Percolation(3);
        percolation.open(3, 4);
    }
}
