import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Mingming
 * @Description
 * @Date Created in 16:45 2018/3/5
 * @Modificd By
 */
class Test{
    private String name;
    public Test(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        Test obj1 = (Test) obj;
        return name.equals(obj1.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
public class HashTest {
    public static void main(String[] args){
        HashSet<Test> testSet = new HashSet<Test>();
        Test test1 = new Test("xiaoming");
        Test test2 = new Test("xiaoming");
        testSet.add(test1);
        testSet.add(test2);
        Iterator<Test> iterator = testSet.iterator();
        while (iterator.hasNext()){
            Test a = iterator.next();
            System.out.println(a.toString());
        }
    }
}
