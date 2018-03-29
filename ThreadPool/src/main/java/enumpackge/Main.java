package enumpackge;


import javax.sound.midi.SoundbankResource;
import java.util.Scanner;

/**
 * @author Mingming
 * @Description
 * @Date Created in 18:57 2018/3/22
 * @Modificd By
 */
class Point{
    int x;
    int y;
    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }
    public double distance(Point point){
        return Math.sqrt((x-point.x)*(x-point.x)+(y-point.y)*(y-point.y));
    }
    public Point xiangliang(Point point){
        return new Point(x-point.x,y-point.y);
    }
    public boolean chuizhi(Point point){
        if(x*point.x+y*point.y == 0)
            return true;
        else
            return  false;
    }
}
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N;
        N = Integer.valueOf(scanner.nextLine());
        while (N-- >0){
            String xstr = scanner.nextLine();
            String ystr = scanner.nextLine();
            String[] xstrs = xstr.split(" ");
            String[] ystrs = ystr.split(" ");
            int x1 = Integer.valueOf(xstrs[0]);
            int x2 = Integer.valueOf(xstrs[1]);
            int x3 = Integer.valueOf(xstrs[2]);
            int x4 = Integer.valueOf(xstrs[3]);
            int y1 = Integer.valueOf(ystrs[0]);
            int y2 = Integer.valueOf(ystrs[1]);
            int y3 = Integer.valueOf(ystrs[2]);
            int y4 = Integer.valueOf(ystrs[3]);
            Point point1 = new Point(x1,y1);
            Point point2 = new Point(x2,y2);
            Point point3 = new Point(x3,y3);
            Point point4 = new Point(x4,y4);
            double distance1 = point1.distance(point2);
            double distance2 = point1.distance(point3);
            double distance3 = point1.distance(point4);
            boolean flag = false;
            if (distance1 == distance2 && distance1 != distance3){
                if(point4.distance(point2) == point4.distance(point3)){
                    Point pointx = point1.xiangliang(point2);
                    Point pointy = point1.xiangliang(point3);
                    if (pointx.chuizhi(pointy)){
                        flag = true;
                    }
                }
            }
            if (distance1 == distance3 && distance1 != distance2){
                if(point3.distance(point2) == point3.distance(point4)){
                    Point pointx = point1.xiangliang(point2);
                    Point pointy = point1.xiangliang(point4);
                    if (pointx.chuizhi(pointy)){
                        flag = true;
                    }
                }
            }
            if (distance2 == distance3 && distance1 != distance2){
                if(point2.distance(point3) == point2.distance(point4)){
                    Point pointx = point1.xiangliang(point3);
                    Point pointy = point1.xiangliang(point4);
                    if (pointx.chuizhi(pointy)){
                        flag = true;
                    }
                }
            }
            if(flag)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}
