package labs;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class Homework {
    public static void SiracuzSequence()
    {
        System.out.println("1. Сиракузская последовательность");
        int counter = 0;
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        in.close();
        while(number != 1 || counter == 0)
        {
            if(number % 2 == 0)
            {
                number = number / 2;
            } else
            {
                number = 3 * number + 1;
            }
            counter++;
        }
        System.out.printf("Шагов требуется: %d",counter);
    }
    public static void RowSummary()
    {
        System.out.println("2. Сумма ряда");
        Scanner in = new Scanner(System.in);
        int AmountOfNumbers = in.nextInt();
        int counter = 0;
        int summary = 0;
        while(counter < AmountOfNumbers)
        {
            int currentNumber = in.nextInt();
            if(counter % 2 == 0 )
            {
                summary += currentNumber;
            }else
            {
                summary -= currentNumber;
            }
            counter++;
        }
        in.close();
        System.out.println(summary);
    }

    public static void FindTreasure()
    {
        System.out.println("3. Поиск сокровищ");
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        ArrayList<String> directions = new ArrayList<String>();
        ArrayList<Integer> stepsAmount = new ArrayList<Integer>();
        while(true) {
            String direction = in.next();
            if(direction.equals("стоп")) {
                in.close();
                break;
            }
            int steps = Integer.parseInt(in.next());
            directions.add(direction);
            stepsAmount.add(steps);

        }
        int current_x = 0;
        int current_y = 0;
        int counter = 0;
        while (current_x != x && current_y != y)
        {
            if(counter < directions.size() && counter < stepsAmount.size()) {
                String direction = directions.get(counter);
                int steps = stepsAmount.get(counter);
                switch (direction) {
                    case "север" -> current_y += steps;
                    case "юг" -> current_y -= steps;
                    case "восток" -> current_x += steps;
                    case "запад" -> current_x -= steps;
                }
            }
            counter++;
        }
        System.out.println(counter + 1);
    }

    public static void LogisticalMaximin()
    {
        System.out.println("4. Логистический максимин");
        Scanner in = new Scanner(System.in);

        int roadsAmount = Integer.parseInt(in.next());
        int roadsCounter = 0;
        int maxHeight = 0;
        int maxHeightRoad = 0;
        while(roadsCounter < roadsAmount)
        {
            int tunnelsAmount = Integer.parseInt(in.next());
            int tunnelsCounter = 0;
            int minOfThisRoad = Integer.MAX_VALUE;
            while(tunnelsCounter < tunnelsAmount)
            {
                int tunnelHeight = Integer.parseInt(in.next());
                if(tunnelHeight < minOfThisRoad){
                    minOfThisRoad = tunnelHeight;
                }
                tunnelsCounter++;
            }
            if(maxHeight < minOfThisRoad){
                maxHeight = minOfThisRoad;
                maxHeightRoad = roadsCounter + 1;
            }
            roadsCounter++;
        }
        System.out.printf("%d %d",maxHeightRoad,maxHeight);
    }
    public static boolean TwiceOddNumber(int number)
    {
        Scanner in = new Scanner(System.in);
        int base = 10;
        int sum = 0;
        int product = 1;
        while(number > 0) {
            sum += number % base;
            product *= number % base;
            number /= base;
        }
        return (sum % 2 == 0) && (product % 2 == 0);
    }
}
