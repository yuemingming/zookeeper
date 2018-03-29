package enumpackge;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.channels.FileChannel.*;
import static java.nio.file.Paths.*;
import static java.nio.file.StandardOpenOption.*;

/**
 * @author Mingming
 * @Description
 * @Date Created in 19:48 2018/3/14
 * @Modificd By
 */
public class kruskal {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("temp.data");
        write(path);
        read(path);
    }

    private static void write(Path path) throws IOException {
        String input = "NIO Buffer Hello World!";
        byte[] inputBytes = input.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(inputBytes.length);
        byteBuffer.put(inputBytes);
        byteBuffer.flip();
        FileChannel channelWrite = FileChannel.open(path,
                StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        channelWrite.write(byteBuffer);
        channelWrite.close();
    }

    private static void read(Path path) throws IOException {
        FileChannel channelRead = FileChannel.open(path);
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        int readBytes = channelRead.read(byteBuffer);
        if(readBytes > 0) {
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);
            String fileContent = new String(bytes, "utf-8");
            System.out.println("File Content: " + fileContent);
        }
        channelRead.close();
    }

}
