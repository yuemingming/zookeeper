import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author Mingming
 * @Description
 * @Date Created in 21:26 2018/2/25
 * @Modificd By
 */

public class StrList {

        public ArrayList<String> Permutation(String str) {
            ArrayList<String> list = new ArrayList<String>();
            if(str == null||str.length()==0) {
                return list;
            }
            else{
                TreeSet<String> temp = new TreeSet<String>() ;
                permutation(str.toCharArray(),0,temp);
                list.addAll(temp);
                return list;
            }

        }
        public void permutation(char[] str,int begin,TreeSet<String> list){
            if(begin == str.length - 1){
                String str2 = new String(str);
                list.add(str2);
            }else{
                for(int index = begin;index <= str.length-1;index++){
                    if (str[index]==str[begin]&&index!=begin) {
                    continue;
                    }
                    else {
                        char temp = str[index];
                        str[index] = str[begin];
                        str[begin] = temp;
                        permutation(str, begin + 1, list);
                        temp = str[index];
                        str[index] = str[begin];
                        str[begin] = temp;
                    }

                }
            }
        }
    public static void main(String[] args){
        StrList strList = new StrList();
        ArrayList<String> abc = strList.Permutation("abc");
        for (String str:abc
             ) {
            System.out.println(str);
        }
    }
}
