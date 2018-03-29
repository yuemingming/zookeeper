/**
 * @author Mingming
 * @Description
 * @Date Created in 18:15 2018/3/26
 * @Modificd By
 */
public enum Instance {
    INSTANCE(10);

    Instance(int m) {
        this.m = m;
    }

    private int m;
    @Override
    public String toString() {
        return "str:"+m;
    }
}
