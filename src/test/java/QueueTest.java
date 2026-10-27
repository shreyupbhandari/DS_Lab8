import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class QueueTest
{
	Queue<Integer> queue;

	@BeforeEach
	void setUp() throws Exception
	{
		queue =new Queue<>();
		assertNotNull(queue);
	}

	@Test
	void pushAndPopTest()
	{
		queue.push(10);
		assertEquals(10,queue.pop());
		assertTrue(queue.isEmpty());
		queue.push(10);
		queue.push(20);
		assertEquals(10,queue.pop());
		assertEquals(20,queue.pop());
		assertTrue(queue.isEmpty());
	}

	@Test
	void isEmptyTest()
	{
		assertTrue(queue.isEmpty());
		queue.push(10);
		assertTrue(!(queue.isEmpty()));
		queue.pop();
		assertTrue(queue.isEmpty());
	}

	@Test
	void popEmptyTest()
	{
		assertThrows(QueueUnderFlowException.class,
				()->{
					queue.pop();
					});
		queue.push(10);
		queue.pop();
		assertThrows(QueueUnderFlowException.class,
				()->{
					queue.pop();
					});
	}

	@Test
	void pushAfterPopTest()
	{
		queue.push(10);
		queue.push(20);
		assertEquals(10,queue.pop());
		queue.push(30);
		assertEquals(20,queue.pop());
		assertEquals(30,queue.pop());
		assertTrue(queue.isEmpty());
		queue.push(40);
		assertEquals(40,queue.pop());
		assertTrue(queue.isEmpty());
	}
}
