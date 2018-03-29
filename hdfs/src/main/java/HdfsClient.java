import org.junit.Before;

import java.net.URI;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mingming
 * @Description
 * @Date Created in 15:45 2018/3/6
 * @Modificd By
 */
public class HdfsClient {
    FileSystem fs = null;
    Configuration conf = null;
    @Before
    public void init() throws Exception{

        conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://zookeeper1:9000");

        //拿到一个文件系统操作的客户端实例对象
		/*fs = FileSystem.get(conf);*/
        //可以直接传入 uri和用户身份
        fs = FileSystem.get(new URI("hdfs://zookeeper1:9000"),conf,"hadoop"); //最后一个参数为用户名
    }

    @Test
    public void testUpload() throws Exception {

        Thread.sleep(2000);
        fs.copyFromLocalFile(new Path("G:/access.log"), new Path("/access.log.copy"));
        fs.close();
    }


    @Test
    public void testDownload() throws Exception {

        fs.copyToLocalFile(new Path("/06.spark远程debug_.avi"), new Path("d:/"));
        fs.close();
    }

    @Test
    public void testConf(){
        Iterator<Entry<String, String>> iterator = conf.iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            System.out.println(entry.getValue() + "--" + entry.getValue());//conf加载的内容
        }
    }

    /**
     * 创建目录
     */
    @Test
    public void makdirTest() throws Exception {
        boolean mkdirs = fs.mkdirs(new Path("/aaa/bbb"));
        System.out.println(mkdirs);
    }

    /**
     * 删除
     */
    @Test
    public void deleteTest() throws Exception{
        boolean delete = fs.delete(new Path("/aaa"), true);//true， 递归删除
        System.out.println(delete);
    }

    @Test
    public void listTest() throws Exception{

        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : listStatus) {
            System.err.println(fileStatus.getPath()+"================="+fileStatus.toString());
        }
        //会递归找到所有的文件
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while(listFiles.hasNext()){
            LocatedFileStatus next = listFiles.next();
            String name = next.getPath().getName();
            Path path = next.getPath();
            System.out.println(name + "---" + path.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://master:9000");
        //拿到一个文件系统操作的客户端实例对象
        FileSystem fs = FileSystem.get(conf);

        fs.copyFromLocalFile(new Path("G:/access.log"), new Path("/access.log.copy"));
        fs.close();
    }
}
