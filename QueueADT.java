
public interface QueueADT <T> {
  
  public boolean isEmpty();
  
  public int size();
  
  public void enqueue(T newObject);
  
  public void clear();
  
  public T peek();
  
  public T dequeue();
}
