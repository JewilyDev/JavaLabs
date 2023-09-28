import java.util.Arrays;

public class Homework {
    public static String Task1(String haystack) {
        System.out.println("Задача №1");
        int ALPHABET_LENGTH = 128;
        int[] needleSymbolsPos = new int[ALPHABET_LENGTH];
        Arrays.fill(needleSymbolsPos, -1);
        String mxSubseq = "";
        String curSubseq = "";
        for (int i = 0; i < haystack.length(); i++) {
            if (needleSymbolsPos[haystack.charAt(i)] == -1) {
                needleSymbolsPos[haystack.charAt(i)] = i;
                curSubseq += haystack.charAt(i);
            } else {
                i = needleSymbolsPos[haystack.charAt(i)] + 1;
                needleSymbolsPos = new int[ALPHABET_LENGTH];
                Arrays.fill(needleSymbolsPos, -1);
                curSubseq = "";
            }
            if (mxSubseq.length() < curSubseq.length()) {
                mxSubseq = curSubseq;
            }
        }
        System.out.println("Задача №1 : Результат");
        System.out.println(mxSubseq);
        return mxSubseq;
    }

    public static int[] Task2(int[] arF, int[] arS) {
        System.out.println("Задача №2");
        int[] newAr = new int[arF.length + arS.length];
        int mxLen = arF.length + arS.length;
        int i = 0;
        int j = 0;
        while (i + j < mxLen) {
            int fVal = i < arF.length ? arF[i] : Integer.MAX_VALUE;
            int sVal = j < arS.length ? arS[j] : Integer.MAX_VALUE;
            ;
            if (fVal < sVal) {
                newAr[i + j] = fVal;
                i++;
            } else {
                newAr[i + j] = sVal;
                j++;
            }
        }
        System.out.println("Задача №2 : Результат");
        System.out.println(Arrays.toString(newAr));
        return newAr;
    }

    public static int Task3(int[] ar) {

        System.out.println("Задача №3");
        int mxSubValue = Integer.MIN_VALUE;
        int mxFinValue = 0;
        for (int i = 0; i < ar.length; i++) {
            mxFinValue = mxFinValue + ar[i];
            mxFinValue = Integer.max(mxFinValue, ar[i]);
            mxSubValue = Integer.max(mxSubValue, mxFinValue);
        }
        System.out.println("Задача №3 : Результат");
        System.out.println(mxFinValue);

        return mxFinValue;
    }

    public static int[][] Task4(int[][] matrix) {
        System.out.println("Задача №4");
        int[][] rotatedMatrix = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                rotatedMatrix[j][i] = matrix[i][j];
            }
        }
        System.out.println("Задача №4 : Результат");
        System.out.println(Arrays.deepToString(rotatedMatrix).replace("], ", "]\n"));

        return rotatedMatrix;
    }

    public static int[] Task5(int target, int[] ar) {
        System.out.println("Задача №5");
        int[] resultPair = new int[2];
        boolean found = false;
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar.length; j++) {
                if (ar[i] + ar[j] == target) {
                    resultPair[0] = ar[i];
                    resultPair[1] = ar[j];
                    found = true;
                    break;
                }
            }
        }
        System.out.println("Задача №5 : Результат");
        System.out.println(Arrays.toString(found ? resultPair: new int[0]));
        return found ? resultPair : null;
    }

    public static int Task6(int[][] matrix) {
        System.out.println("Задача №6");
        int sum = 0;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }
        System.out.println("Задача №6 : Результат");
        System.out.println(sum);
        return sum;
    }

    public static int[] Task7(int[][] matrix) {
        System.out.println("Задача №7");
        int[] mxArray = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int mxElem = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (mxElem < matrix[i][j]) {
                    mxElem = matrix[i][j];
                }
            }
            mxArray[i] = mxElem;
        }
        System.out.println("Задача №7 : Результат");
        System.out.println(Arrays.toString(mxArray));
        return mxArray;
    }

    public static int[][] Task8(int[][] matrix) {
        System.out.println("Задача №8");
        int[][] rotatedMatrix = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                rotatedMatrix[j][i] = matrix[i][j];
            }
        }
        System.out.println("Задача №8 : Результат");
        System.out.println(Arrays.deepToString(rotatedMatrix).replace("], ", "]\n"));
        return rotatedMatrix;

    }
}
