public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    //nextLast=size , nextFirst=items.length-1
    public ArrayDeque(){
    	items = (T[]) new Object[8];
    	size = 0;
    	nextFirst = 7;
    	nextLast = 0;
    }

    private int minusOne(int index) {
    	if (index == 0) {
    		return items.length - 1;
    	}
    	return index - 1;
    }
    
    private int plusOne(int index) {
    	if (index == items.length - 1) {
    		return 0;
    	}
    	return index + 1;
    }

    private boolean usagefactor() {
        int usagefactor = size * 100 / items.length;
        if (usagefactor <= 25 & items.length > 8) {
            return true;
        }
        return false;
    }

    private T[] reconstruct() {
        T[] a=(T[]) new Object[size];
        System.arraycopy(items,plusOne(nextFirst),a,0,items.length-1-nextFirst);
        System.arraycopy(items,0,a,items.length-1-nextFirst,nextLast);
        return a;
    }

    //扩容并重构 ArrayDeque //
    //nextLast=size , nextFirst=items.length-1
    //这时候断点就是nextFirst也等于nextLast
    //以断点为界重构双向数组
    private void resize(int capcity) {
            T[] a=(T[]) new Object[capcity];
            //从nextFirst后一位一直选中到容器最后一位
            System.arraycopy(items,plusOne(nextFirst),a,0,items.length-1-nextFirst);
            //从index 0一直选中到 nextLast前一位 nextLast-1
            System.arraycopy(items,0,a,items.length-1-nextFirst,nextLast);
            nextFirst=a.length - 1;
            nextLast=size;
            items = a;
    }

    public void addLast(T x){
        //当nextFirst和nextLast相等的时候，这时候说明只剩下一格空间了,需要扩容? 此时size=length-1
        //扩容和重构ArrayDeque
    	if (nextFirst == nextLast){
    		resize(items.length * 2);
    	}
    	items[nextLast] = x;
        nextLast = plusOne(nextLast);
    	size = size + 1;
    }

    public void addFirst(T x){
    	if (nextFirst == nextLast){
    		resize(size * 2);
    	}
    	items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
    	size = size + 1;    	
    }

    public boolean isEmpty(){
    	if (size == 0){
    		return true;
    	}
    	return false;
    }

    public int size(){
    	return size;
    }

    public void printDeque(){
        T[] a = reconstruct();
        for(int i = 0; i < size; i += 1){
            System.out.print(a[i]+" ");
        }
    }

    public T removeLast(){
        if (size == 0) {
            return null;
        }
        T a = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
    	size = size - 1;
        if (usagefactor()) {
            resize(items.length / 2);
        }
    	return a;
    }

    public T removeFirst(){
        if (size == 0) {
            return null;
        }
        T a = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
    	nextFirst = plusOne(nextFirst);
    	size = size - 1;
        if (usagefactor()) {
            resize(items.length / 2);
        }
    	return a;
    }

    public T get(int index){
        if (index >= size) {
            return null;
        }
        else if (nextFirst + index >= items.length - 1){
            return items[nextFirst+index-(items.length-1)];
        }
        return items[plusOne(nextFirst)+index];
    }  
}