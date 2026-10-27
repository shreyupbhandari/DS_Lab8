public class Queue<T>
{
	private class Node<T>
	{
		private T val;
		private Node<T> next;

		private Node(T val, Node<T> node)
		{
			this.val=val;
			next=node;
		}
	}

	private Node<T> head = null;
	private Node<T> tail = null;

	public Queue()
	{

	}

	/**Adds val to the end of the queue
	 */
	public void push(T val)
	{
		if (head==null)
		{
			head= new Node<>(val, null);
			tail=head;
		}
		else
		{
			tail.next= new Node<>(val, null);
			tail=tail.next;
		}
	}

	/**
	 * removes and returns the oldest value in the queue
	 * throws QueueUnderFlowException if the queue is empty
	 */
	public T pop() throws QueueUnderFlowException
	{
		if (head==null)
		{
			throw new QueueUnderFlowException();
		}

		T value=head.val;
		head=head.next;

		if (head==null)
		{
			tail=null;
		}

		return value;
	}

	/**
	 * returns true if the queue is empty
	 */
	public boolean isEmpty()
	{
		return head==null;
	}

    // Keep larger priorities at the front. Equal priorities stay FIFO.
    protected void insertByPriority(T val, java.util.Comparator<T> comp)
    {
        Node<T> node = new Node<>(val, null);
        if (head == null)
        {
            head = node;
            tail = node;
            return;
        }
        if (comp.compare(val, head.val) > 0)
        {
            node.next = head;
            head = node;
            return;
        }
        Node<T> current = head;
        while (current.next != null && comp.compare(current.next.val, val) >= 0)
        {
            current = current.next;
        }
        node.next = current.next;
        current.next = node;
        if (node.next == null)
        {
            tail = node;
        }
    }
}
