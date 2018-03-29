package pool;

import java.util.List;

/**
 * @author Mingming
 * @Description
 * @Date Created in 11:29 2018/2/22
 * @Modificd By
 */
public interface IThreadPool {
    //加入任务
    void execute(Runnable task);

    //加入任务
    void execute(Runnable[] tasks);

    //加入任务
    void execute(List<Runnable> tasks);

    //销毁线程
    void destroy();
}
