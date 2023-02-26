import nl.saxion.app.SaxionApp;

import java.awt.*;

public class Number {

    private int num;
    private int x;
    private int y;
    private int width;
    private int height;
    private int fontSize;
    private boolean merged;
    private Color color;

    public Number() {
        num = 1;
        x = 0;
        y = 0;
        width = 100;
        height = 100;
        color = Color.blue;
        fontSize = 30;
        merged = false;
    }

    public Number(int newX, int newY) {
        num = 1;
        x = newX;
        y = newY;
        width = 100;
        height = 100;
        color = Color.blue;
        fontSize = 30;
        merged = false;
    }

    public Number(int newNum, int newX, int newY) {
        num = newNum;
        x = newX;
        y = newY;
        width = 100;
        height = 100;
        color = Color.blue;
        fontSize = 30;
        merged = false;
    }

    public int getNum() {
        return num;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean isMerged(){
        return merged;
    }

    public void setMerged(){
        merged = true;
    }

    public void drawNumber() {
        SaxionApp.setFill(color);
        SaxionApp.setBorderSize(0);
        SaxionApp.drawRectangle(x, y, width, height);
        SaxionApp.setTextDrawingColor(Color.black);
        SaxionApp.drawText(String.valueOf(num), ((width / 2) - fontSize / 2) + x, ((height / 2) - fontSize / 2 + y), fontSize);
    }
}
