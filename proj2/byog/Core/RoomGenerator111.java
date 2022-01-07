package byog.Core;

import java.util.Random;

public class RoomGenerator111 {

    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;

    private static final long SEED = 45221;
    private static final Random RANDOM = new Random(SEED);

    public Rectangular[] RoomArray;
    public Rectangular[] HallwayArray;

    public RoomGenerator111() {
        RoomArray = new Rectangular[IntGenerator(35,65)];
        GenerateRecArray();
        HallwayArray = new Rectangular[IntGenerator(35,45)];
        GenerateHallArray();
    }

    public int IntGenerator(int n1,int n2) {
        int a = RANDOM.nextInt(n2 + 1 - n1);
        return a + n1;
    }

    public Position PosGenerator(int xLimit, int yLimit){
        return new Position(RANDOM.nextInt(xLimit + 1) ,RANDOM.nextInt(yLimit + 1) );
    }

    public Rectangular RecGenerator() {
        Position xy1 = PosGenerator(WIDTH, HEIGHT);
        int W1 = IntGenerator(4, 9);
        int H1 = IntGenerator(4, 9);
        return new Rectangular(xy1, W1, H1);
    }

    public Rectangular HallGenerator() {
        Position xy1 = PosGenerator(WIDTH, HEIGHT);
        int W1 = IntGenerator(4, 14);
        int H1 = IntGenerator(4, 14);
        if (RANDOM.nextBoolean()) {
            W1 = 3;
        } else {
            H1 = 3;
        }
        return new Rectangular(xy1, W1, H1);
    }

    public boolean CheckisOk(Rectangular[] a,Rectangular b, int k) {
        for (int i = 0; i < k; i += 1) {
            if ((!b.isOutside(a[i]))|(!a[i].isOutside(b)))  {
                return false;
            }
        }
        return true;
    }

    public void GenerateRecArray() {
        int i = 0;
        while (i < RoomArray.length) {
            Rectangular a = RecGenerator();
            if ((a.InSceen(WIDTH, HEIGHT))&(CheckisOk(RoomArray, a, i))) {
            //if (a.InSceen(WIDTH, HEIGHT)) {
                RoomArray[i] = a;
                i = i + 1;
            }
        }
    }

    public void GenerateHallArray() {
        int i = 0;
        while (i < HallwayArray.length) {
            Rectangular a = HallGenerator();
            if ((a.InSceen(WIDTH, HEIGHT))&(CheckisOk(HallwayArray, a, i))) {
                HallwayArray[i] = a;
                i = i + 1;
            }
        }
    }



}
