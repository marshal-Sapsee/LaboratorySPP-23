package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> spisok = new ArrayList<String>();
        System.out.println("Кол-во элементов в списке = ");
        int size = in.nextInt();
        System.out.println("Ввод элементов списка = ");

        String str = null;

        for(int i=0;i<=size;i++)
        {
            str = in.nextLine();
            spisok.add(i,str);
        }
        System.out.println( enhanceList(spisok) + " ");
    }

    private static <String> List<String> enhanceList(List<String> list) {
        List<String> result = new ArrayList<>();
        int j;
        for (int i = 0; i < list.size(); i++) {
            result.add(list.get(i));
            j = i;
            while (j!=0) {
                result.add(list.get(j-1));
                j--;
            }
            System.out.println(result);
            result.clear();
        }
        return result;
    }
}
