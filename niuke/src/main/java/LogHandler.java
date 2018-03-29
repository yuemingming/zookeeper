import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mingming
 * @Description
 * @Date Created in 19:27 2018/3/5
 * @Modificd By
 */
public class LogHandler implements InvocationHandler{
    private Logger logger =
            Logger.getLogger(this.getClass().getName());

    private Object delegate;

    public Object bind(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(
                delegate.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(),
                this);
    }

    public Object invoke(Object proxy, Method method,
                         Object[] args) throws Throwable {
        Object result = null;

        try {
            log("method starts..." + method);

            result = method.invoke(delegate, args);

            logger.log(Level.INFO, "method ends..." + method);
        } catch (Exception e){
            log(e.toString());
        }

        return result;
    }

    private void log(String message) {
        logger.log(Level.INFO, message);
    }
}
