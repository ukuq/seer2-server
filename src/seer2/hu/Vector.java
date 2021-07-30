package seer2.hu;

import java.util.LinkedList;

public class Vector<E> {
    LinkedList<E> list;
    public Vector(){
        list=new LinkedList<>();
    }
    public int length(){
        return list.size();
    }
    public boolean push(E e){
        return list.add(e);
    }
    public E shift(){
        return list.peek();
    }
    public void clear(){
        list.clear();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
