import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

class PriorityQueueTest
{
    Customer a = new Customer(100, 2, 4);
    Customer b = new Customer(100, 5, 8);
    Customer c = new Customer(200, 1, 1);

    @Test
    void worthComparatorTest()
    {
        Comparator<Customer> comp = new Customer.WorthComparator();
        assertTrue(comp.compare(c, a) > 0);
        assertTrue(comp.compare(a, c) < 0);
        assertEquals(0, comp.compare(a, b));
        assertEquals(0, comp.compare(a, a));
       
    }

    @Test
    void loyaltyComparatorTest()
    {
        Comparator<Customer> comp = new Customer.LoyaltyComparator();
        assertTrue(comp.compare(b, a) > 0);
        assertTrue(comp.compare(a, b) < 0);
        assertEquals(0, comp.compare(a, new Customer(900, 2, 99)));
    }

    @Test
    void worthPoliteComparatorTest()
    {
        Comparator<Customer> comp = new Customer.WorthPoliteComparator();
        assertTrue(comp.compare(c, b) > 0);
        assertTrue(comp.compare(b, c) < 0);
        assertTrue(comp.compare(b, a) > 0);
        assertTrue(comp.compare(a, b) < 0);
        assertEquals(0, comp.compare(a, new Customer(100, 99, 4)));
    }


    @Test
    void insertionAndReuseTest()
    {
        PriorityQueue<Customer> queue = new PriorityQueue<>(new Customer.WorthComparator());
        assertTrue(queue.isEmpty());
        assertThrows(QueueUnderFlowException.class, queue::pop);
        Customer low = new Customer(20, 0, 0);
        Customer middle = new Customer(50, 0, 0);
        Customer high = new Customer(90, 0, 0);
        Customer tie = new Customer(50, 9, 9);
        queue.push(middle);
        queue.push(high);
        queue.push(low);
        queue.push(tie);
        queue.push(middle);
        assertSame(high, queue.pop());
        assertSame(middle, queue.pop());
        assertSame(tie, queue.pop());
        assertSame(middle, queue.pop());
        assertSame(low, queue.pop());
        assertTrue(queue.isEmpty());
        assertThrows(QueueUnderFlowException.class, queue::pop);
        queue.push(a);
        assertSame(a, queue.pop());
        queue.push(c);
        queue.push(a);
        assertSame(c, queue.pop());
        queue.push(b);
        assertSame(a, queue.pop());
        assertSame(b, queue.pop());
        assertTrue(queue.isEmpty());
    }

}
