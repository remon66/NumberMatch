import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Application implements Runnable {
    public static void main(String args[]) {
        SaxionApp.start(new Application(), 1024, 720);
    }

    ArrayList<Number> numbers = new ArrayList<>();
    ArrayList<Number> tempNumbers = new ArrayList<>();
    ArrayList<Number> values = new ArrayList<>();

    public void run() {
        setEnvironment();
        SaxionApp.print("How many number blocks would you like?", Color.black);
        int blocks = SaxionApp.readInt();
        SaxionApp.clear();
        for (int i = 0; i < blocks; i++) {
            createNumber(1);
        }
    }

    public void createNumber(int num) {
        System.out.print("Adding object with num: " + num + "\n");
        Number newNum = new Number(num, SaxionApp.getRandomValueBetween(0, SaxionApp.getWidth() - 125),
                SaxionApp.getRandomValueBetween(0, SaxionApp.getHeight() - 125));
        tempNumbers.add(newNum);
        checkNum();
    }

    public void createMergedNumber(int num, int x, int y) {
        System.out.print("Adding object with num: " + num + "\n");
        Number newNum = new Number(num, x, y);
        tempNumbers.add(newNum);
        checkNum();
    }

    public void checkNum() {
        numbers.addAll(tempNumbers);
        tempNumbers.clear();
        System.out.print("Size: " + numbers.size() + "\n");
        for (int i = 0; i < numbers.size(); i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            for (Number number : numbers) {
                System.out.print(numbers.get(i).getNum() + " : " + number.getNum() + " ");
                if (number.getNum() == numbers.get(i).getNum()) {
                    System.out.print(" True " + " : isMerged: " + number.isMerged() + "\n");
                    if (number.isMerged()) {
                        System.out.print("Number is already merged!\n");
                    } else {
                        values.add(number);
                        if (values.size() == 2) {
                            for (Number value : values) {
                                value.setMerged();
                            }
                            if (Objects.equals(values.get(0).getNum(), values.get(1).getNum())) {
                                System.out.print("Merged!\n");
                                createMergedNumber(number.getNum() * 2, number.getX(), number.getY());
                                values.clear();
                                break;
                            }
                        }
                    }
                } else {
                    System.out.print(" False " + " : isMerged: " + number.isMerged() + "\n");
                }
            }
            values.clear();
            System.out.print("Cleared list!\n");
            for (int y = 0; y < numbers.size(); y++) {
                numbers.removeIf(Number::isMerged);
            }
            drawNumbers();
        }
    }

    public void drawNumbers() {
        SaxionApp.clear();
        for (Number number : numbers) {
            System.out.print("Drawing object: " + number.getNum() + "\n");
            number.drawNumber();
        }
    }

    public void setEnvironment() {
        SaxionApp.setBackgroundColor(SaxionApp.createColor(248, 200, 220));
    }
}
