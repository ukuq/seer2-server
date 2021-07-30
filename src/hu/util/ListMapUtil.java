package hu.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class ListMapUtil {
    public static <E> String iPrint(Iterable<E> iterable){
            StringBuilder stringBuilder =new StringBuilder(iterable.getClass().getName());
            stringBuilder.append(":---------------\n");
            Iterator its = iterable.iterator();
            int i=0;
            while(its.hasNext()){
                stringBuilder.append(i++ +":");
                stringBuilder.append(its.next().toString());
                stringBuilder.append("\n");
            }
            stringBuilder.append("----------------total:"+i);
            return stringBuilder.toString();
    }
    public static <k,v> void mapPrint(Map<k,v> map){
        int i=0;
        System.out.println("Map:");
        for(Map.Entry<k,v> entry: map.entrySet()){
            System.out.println(i++ + ":"+ entry.getKey() + " "+entry.getValue());
        }
        System.out.println("Map total count:"+ i);
    }
    public static <E> void listPrint(List<E> list){
        int i=0;
        System.out.println("List:");
        for(E e:list){
            System.out.println(i++ + ":"+e);
        }
        System.out.println("List total count:"+ i);
    }
    public static <E> void setPrint(Set<E> set){
        int i=0;
        System.out.println("Set:");
        for(E e:set){
            System.out.println(i++ + ":"+e);
        }
        System.out.println("Set total count:"+ i);
    }
    public static <k,v> void mapToFile(Map<k,v> map,String fileName){
        FileWriter fw =null;
        try{
            fw=new FileWriter(fileName);
            int i=0;
            System.out.println("writing to "+fileName+" ...");
            for(Map.Entry<k,v> entry: map.entrySet()){
                fw.write(i++ + ":"+ entry.getKey() + " "+entry.getValue()+'\n');
            }
            System.out.println("writing success , total :"+i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
