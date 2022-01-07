package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Rectangular {
    public Position UpLeft;
    public Position DownRight;
    public Position UpRight;
    public Position DownLeft;
    public int W;
    public int H;

    // Generator
    public Rectangular(Position cornerUpLeft, int width, int height) {
        this.W = width;
        this.H = height;
        this.UpLeft = cornerUpLeft;
        this.DownRight = cornerUpLeft.PosOffsetVal(width - 1, -height + 1);
        this.UpRight = cornerUpLeft.PosOffsetVal(width - 1,0);
        this.DownLeft = cornerUpLeft.PosOffsetVal(0, -height + 1);
    }

    public Rectangular RecOffsetVal(int offValue) {
        Rectangular a = new Rectangular(this.UpLeft.PosOffsetVal(- offValue , offValue),this.W + offValue * 2, this.H + offValue * 2);
        return a;
    }

    private boolean isOutside(Position a) {
        return (a.x < UpLeft.x)|(a.x > DownRight.x)|(a.y > UpLeft.y)|(a.y < DownRight.y);
    }

    public boolean isOutside(Rectangular a) {
        return isOutside(a.UpLeft) & isOutside(a.UpRight) & isOutside(a.DownLeft) & isOutside(a.DownRight);
    }

    public boolean InSceen(int x, int y) {
        return UpLeft.x >= 0 & UpLeft.y < y & DownRight.x < x & DownRight.y >=0;
    }

    public static void drawRec(TETile[][] world, Rectangular ab, TETile t) {
        for (int xx = ab.UpLeft.x; xx < ab.UpLeft.x + ab.W; xx += 1){
            for (int yy = ab.UpLeft.y; yy > ab.UpLeft.y - ab.H; yy -= 1) {
                world[xx][yy] = t;
            }
        }
    }

    public static void drawRoom(TETile[][] world, Rectangular ab) {
        drawRec(world, ab, Tileset.WALL);
        drawRec(world, ab.RecOffsetVal(-1), Tileset.FLOOR);
    }

    public static void drawHall(TETile[][] world,Rectangular ab) {
        if (ab.H == 3) {
            Rectangular a = new Rectangular(ab.UpLeft,ab.W,1);
            Rectangular b = new Rectangular(ab.UpLeft.PosOffsetVal(0,-1),ab.W,1);
            Rectangular c = new Rectangular(ab.DownLeft,ab.W,1);
            drawRec(world,a,Tileset.WALL);
            drawRec(world,b,Tileset.FLOOR);
            drawRec(world,c,Tileset.WALL);
        } else {
            Rectangular a = new Rectangular(ab.UpLeft,1,ab.H);
            Rectangular b = new Rectangular(ab.UpLeft.PosOffsetVal(1,0),1,ab.H);
            Rectangular c = new Rectangular(ab.UpRight,1, ab.H);
            drawRec(world,a,Tileset.WALL);
            drawRec(world,b,Tileset.FLOOR);
            drawRec(world,c,Tileset.WALL);
        }
        }


    /*public static void drawHallway(TETile[][] world,Rectangular ab) {
        if (ab.W == 3) {
            drawRec(world, ab, Tileset.FLOOR);
        }
        else {
            drawRec(world, ab, Tileset.FLOOR);
        }

    }*/

    public static void main(String[] args) {
        int WIDTH = 80;
        int HEIGHT = 40;
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Rectangular a = new Rectangular(new Position(54,26), 8,7);
        Rectangular b = new Rectangular(new Position(51,27),4,7);
        Rectangular c = new Rectangular(new Position(52,11),8,5);

        System.out.println(a.isOutside(b));
        System.out.println(b.isOutside(a));


        // 在指定x,y坐标画Room

        RoomGenerator111 oo = new RoomGenerator111();




        //drawRec(world,oo.HallwayArray[0],Tileset.WALL);
       int kk = 0;
        while (kk < oo.HallwayArray.length) {
            drawHall(world, oo.HallwayArray[kk]);
            kk = kk + 1;
        }

        int ii = 0;
        while (ii < oo.RoomArray.length) {
            drawRoom(world, oo.RoomArray[ii]);
            ii = ii + 1;
        }

        // draws the world to the screen
        ter.renderFrame(world);
    }


}
