public class LinkedListDeque<T> implements Deque<T> {
    private class IntNode{
       public T item;
       public IntNode prev;
       public IntNode next;
       public IntNode(IntNode a,T i,IntNode b){
       	     item=i;
             prev=a;
       	     next=b;
       }
    }
    
    /* The first item (if it exists) is at sentinel.next.*/
    private IntNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel=new IntNode(null,null,null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
        size=0;	
    }

    @Override
    public boolean isEmpty(){
    	if (sentinel.next == sentinel){
    		return true;
    	}
    	return false;
    }

    @Override
    public void addFirst(T x){
        sentinel.next=new IntNode(sentinel,x,sentinel.next);
        sentinel.next.next.prev=sentinel.next;
    	size+=1;
    }

    @Override
    public void addLast(T x){
        sentinel.prev=new IntNode(sentinel.prev,x,sentinel);
        sentinel.prev.prev.next=sentinel.prev;
        size+=1;}

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        IntNode p=sentinel;
            while(p.next!=sentinel){
                p=p.next;
                System.out.print(p.item+" ");
        }
    }

    @Override
    public T removeFirst(){
        if(sentinel.next==sentinel){
            return null;
        }else{
            T a=sentinel.next.item;
            sentinel.next=sentinel.next.next;
            sentinel.next.prev=sentinel;
            size = size - 1;
            return a;
        }
    }

    @Override
    public T removeLast(){
        if(sentinel.next==sentinel){
            return null;
        }else{
            T a=sentinel.prev.item;
            sentinel.prev=sentinel.prev.prev;
            sentinel.prev.next=sentinel;
            size = size - 1;
            return a;
        }
    }

    @Override
    public T get(int index){
        if(index>=size){
            return null;
        }
        IntNode p=sentinel;
        while(index>=0){
            p=p.next;
            index-=1;
        }
        return p.item;
    }

    public T getRecursive(int index){
        return null;
    }
}