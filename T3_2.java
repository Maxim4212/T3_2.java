import java.io.IOException;
import java.util.Scanner;

public class T3_2 {
    public static void main(String[] args) throws IOException{

        Scanner inString = new Scanner(System.in);
        String string = inString.nextLine();
        VarDefinition startOperation = new VarDefinition();
        ArithmeticOperations callFunc = new ArithmeticOperations();
        startOperation.quotesFinder(string);

        switch (startOperation.arithSign()){
            case '+' :
                System.out.printf(callFunc.plus(startOperation.firstVar(), startOperation.secondVarString()));
                break;
            case '-' :
                System.out.printf(callFunc.minus(startOperation.firstVar(), startOperation.secondVarString()));
                break;
            case '*' :
                System.out.printf(callFunc.multiplication(startOperation.firstVar(), startOperation.secondVarNumber()));
                break;
            case '/' :
                System.out.printf(callFunc.division(startOperation.firstVar(), startOperation.secondVarNumber()));
                break;
            default:
                throw new IOException();

        }
    }
}

class VarDefinition{
    int[] quotes = {-1, -1, -1, -1, -1};
    String newString = "";

    void quotesFinder(String string){

        if (string.length() > 4) {

            int a = 0;
            for (int i = 0; i < string.length(); i++) {

                    if (string.charAt(i) == '"') {
                        if (a < 4) {
                            quotes[a] = i;
                            a += 1;
                        }
                }
            }

            for (int i = 0; i < string.length(); i++) {
                if (i > quotes[1]) {
                    if (string.charAt(i) != ' ') {
                        newString += string.charAt(i);
                    } else {
                        if (quotes[2] > -1) {
                            quotes[2] -= 1;
                        }
                        if (quotes[3] > -1) {
                            quotes[3] -= 1;
                        }
                    }
                }else {
                    newString += string.charAt(i);
                }
            }
        }
    }

    public char arithSign(){
        char Symbol = 0;

        if (quotes[1] > 1) {
            try {
                Symbol = newString.charAt(quotes[1]+1);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("неверная запись выражения");
            }
        }

        return Symbol;
    }

    public String firstVar() throws IOException{
        String string1;

        if (quotes[1] > 1) {
            string1 = newString.substring(quotes[0] + 1, quotes[1]);
        }else {
            throw new IOException();
        }

        return string1;
    }

    public String secondVarString() throws IOException{

        String string2;

        if (quotes[3] > -1) {

            string2 = newString.substring(quotes[2] + 1, quotes[3]);

        }else {
            throw new IOException();
        }

        return string2;
    }

    public int secondVarNumber() throws IOException{

        String string3;
        String numberString = "";

        if (quotes[1] > 1 && quotes[2] == -1) {

            string3 = newString.substring(quotes[1] + 2);

            for (int i = 0; i < string3.length(); i++) {

                    if (Character.isDigit(string3.charAt(i))) {
                        numberString += string3.charAt(i);
                    }
                    else {
                        throw new IOException();
                    }
            }
        }  else {
            throw new IOException();
        }

        return Integer.parseInt(numberString);
    }
}

class ArithmeticOperations  {
    public String plus(String string1, String string2) throws IOException{

        String outLine;
        if (!string1.isEmpty() && !string2.isEmpty() && string1.length() < 11 && string2.length() < 11) {
            outLine = string1 + string2;
        }else {
            throw new IOException();
        }

        return outLine;
    }

    public String minus(String string1, String string2) throws IOException{
        String outLine;

        if (!string1.isEmpty() && !string2.isEmpty() && string1.length() < 11 && string2.length() < 11) {
            outLine = string1.replace(string2,"");
        }else {
            throw new IOException();
        }
        return outLine;
    }

    public String division(String string1, int digit) throws IOException {

        String outLine;
            if (!string1.isEmpty() && string1.length() < 11  && digit > 0 && digit < 11){

                outLine = string1.substring(0, string1.length() / digit);
            }else{
                throw new IOException();
            }
        return outLine;
    }

    public String multiplication(String string1, int digit) throws IOException{
        String outLine = "";

        if (!string1.isEmpty() && string1.length() < 11 && digit > 0 && digit < 11){
            for (int i = 0; i < digit; i++) {
                outLine += string1;
            }
        }else{
            throw new IOException();
        }
        if(outLine.length() > 40){
            outLine = outLine.substring(0, 40)+"...";
        }
        return outLine;
    }
}