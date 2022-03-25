import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PercolationTest {

    @Test
    public void testIsOpenReturnsTrue() {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 1);
        assertTrue(percolation.isOpen(1, 1));
    }

    @Test
    public void testIsOpenReturnsFalse() {
        Percolation percolation = new Percolation(3);
        assertFalse(percolation.isOpen(1, 1));
    }

    @Test
    public void testIsFullwithTopSiteOpenInFirstColumn() {
        Percolation percolation = new Percolation(3);

        // open top site
        percolation.open(1, 1);
        assertTrue(percolation.isFull(1, 1));
    }

    @Test
    public void testIsFullwithTopSiteOpenInLastColumn() {
        Percolation percolation = new Percolation(3);

        // open top site
        percolation.open(1, 3);
        assertTrue(percolation.isFull(1, 3));
    }

    @Test
    public void testIsNotFullIfSiteNotOpen() {
        Percolation percolation = new Percolation(3);
        assertFalse(percolation.isFull(1, 1));
    }

    @Test 
    public void testBottomSiteFullWhenConnectedToTopOpenSiteInFirstColumn() {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 1);
        percolation.open(2, 1);
        assertTrue(percolation.isFull(2, 1));
    }

    @Test 
    public void testBottomSiteFullWhenConnectedToTopOpenSiteInLastColumn() {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 3);
        percolation.open(2, 3);
        assertTrue(percolation.isFull(2, 3));
    }

    @Test 
    public void testRightSiteFullWhenConnectedToTopOpenSite() {
        Percolation percolation = new Percolation(4);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(2, 2);
        assertTrue(percolation.isFull(2, 2));
    }

    @Test 
    public void testLeftSiteFullWhenConnectedToTopOpenSite() {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 3);
        percolation.open(2, 3);
        percolation.open(2, 2);
        assertTrue(percolation.isFull(2, 2));
    }


    @Test
    public void testBottomSiteFullIfConnectedToTopFullSite() {
        Percolation percolation = new Percolation(3);
        percolation.open(3, 1);
        percolation.open(2, 1);
        percolation.open(1, 1);
        assertTrue(percolation.isFull(3, 1));
    }

    @Test
    public void testIsFullwithTwoFullRows() {
        Percolation percolation = new Percolation(3);

        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(1, 3);
        percolation.open(2, 1);
        percolation.open(2, 2);
        percolation.open(2, 3);

        assertTrue(percolation.isFull(1, 1));
        assertTrue(percolation.isFull(1, 2));
        assertTrue(percolation.isFull(1, 3));
        assertTrue(percolation.isFull(2, 1));
        assertTrue(percolation.isFull(2, 2));
        assertTrue(percolation.isFull(2, 3));

        // bottom row should not be full
        assertFalse(percolation.isFull(3, 1));
        assertFalse(percolation.isFull(3, 2));
        assertFalse(percolation.isFull(3, 3));
    }

    @Test
    public void testIsFullwithZigZagOpenRows() {
        Percolation percolation = new Percolation(4);

        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(2, 2);
        percolation.open(2, 3);
        percolation.open(3, 3);
        percolation.open(3, 4);

        assertTrue(percolation.isFull(1, 1));
        assertTrue(percolation.isFull(1, 2));
        assertTrue(percolation.isFull(2, 2));
        assertTrue(percolation.isFull(2, 3));
        assertTrue(percolation.isFull(3, 3));
        assertTrue(percolation.isFull(3, 4));

        // all other sites should be empty
        assertFalse(percolation.isFull(1, 3));
        assertFalse(percolation.isFull(1, 4));
        assertFalse(percolation.isFull(2, 1));
        assertFalse(percolation.isFull(2, 4));
        assertFalse(percolation.isFull(3, 1));
        assertFalse(percolation.isFull(3, 2));
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

    @Test
    public void whenSystemPercolatesItDoesNotBackwash() {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(3, 3);
        assertFalse(percolation.isFull(3, 3));
    }
}
