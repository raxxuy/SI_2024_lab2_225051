import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    @Test
    public void everyBranchTest() {
        RuntimeException exception = null;

        // Case1
        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 0));
        assertEquals(exception.getMessage(), "allItems list can't be null!");

        // Case2
        List<Item> allItems1 = new ArrayList<>();
        allItems1.add(new Item(null, null, 10, 0));
        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems1, 30));
        assertEquals(exception.getMessage(), "No barcode!");

        // Case3
        List<Item> allItems2 = new ArrayList<>();
        allItems2.add(new Item("Item1", "a", 20, 0.5F));
        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems2, 20));
        assertEquals(exception.getMessage(), "Invalid character in item barcode!");

        // Case4
        List<Item> allItems3 = new ArrayList<>();
        allItems3.add(new Item("Item1", "0", 700, 0.5F));
        assertTrue(SILab2.checkCart(allItems3, 320));

        // Case5
        List<Item> allItems4 = new ArrayList<>();
        allItems4.add(new Item("Item1", "1", 200, 0));
        assertFalse(SILab2.checkCart(allItems4, 170));
    }

    @Test
    public void multipleConditionTest() {
        // Case1
        List<Item> allItems1 = new ArrayList<>();
        allItems1.add(new Item("Item1", "0", 600, 0.5F));
        assertTrue(SILab2.checkCart(allItems1, 270));

        // Case2
        List<Item> allItems2 = new ArrayList<>();
        allItems2.add(new Item("Item1", "1", 600, 0.5F));
        assertFalse(SILab2.checkCart(allItems2, 270));

        // Case3
        List<Item> allItems3 = new ArrayList<>();
        allItems3.add(new Item("Item1", "0", 500, 0));
        assertFalse(SILab2.checkCart(allItems3, 470));

        // Case4
        List<Item> allItems4 = new ArrayList<>();
        allItems4.add(new Item("Item1", "0", 200, 0.4F));
        assertFalse(SILab2.checkCart(allItems4, 50));
    }
}
