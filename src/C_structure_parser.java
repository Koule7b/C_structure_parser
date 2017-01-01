import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;

import java.rmi.UnexpectedException;
import java.util.Stack;

import static java.lang.Character.isAlphabetic;

/**
 * Created by stepanmudra on 01.01.17.
 * Gramatika:
 * S -> IK
 * I -> structure
 * K -> CO
 * O -> {U}B
 * U -> S
 * U -> intA | charA | stringA | floatA | doubleA
 * A -> aBU | +...+ |zBU
 * B -> C;
 * C -> a | +...+ |z
 * C -> λ
 */
public class C_structure_parser {
    public Stack<Character> zasobnik = new Stack<>();
    public int pozice = 0;
    public static void main(String[] args){
        C_structure_parser CP = new C_structure_parser();
        if(CP.zkontroluj(args[0])){
            System.out.println("A");
        }else {
            System.out.println("N");
        }
    }
    public boolean zkontroluj(String retezec){
        for (int i = retezec.length() - 1; 0 <= i; i--) {
            zasobnik.push(retezec.charAt(i));
        }
        try{
            S();
            return true;
        } catch (SyntaxException se){
            return false;
        }
    }
    public void S() throws SyntaxException{
        I();
        K();
    }
    public void I() throws SyntaxException{
        if(zasobnik.peek() == 's'){
            zasobnik.pop();
            pozice++;
            if(zasobnik.peek() == 't'){
                zasobnik.pop();
                pozice++;
                if(zasobnik.peek() == 'r'){
                    zasobnik.pop();
                    pozice++;
                    if(zasobnik.peek() == 'u'){
                        zasobnik.pop();
                        pozice++;
                        if(zasobnik.peek() == 'c'){
                            zasobnik.pop();
                            pozice++;
                            if(zasobnik.peek() == 't'){
                                zasobnik.pop();
                                pozice++;
                            }else {
                                throw new SyntaxException("chyba");
                            }
                        }else {
                            throw new SyntaxException("chyba");
                        }
                    }else {
                        throw new SyntaxException("chyba");
                    }
                }else {
                    throw new SyntaxException("chyba");
                }
            }else {
                throw new SyntaxException("chyba");
            }
        }else {
            throw new SyntaxException("chyba");
        }
    }
    public void K() throws SyntaxException{
        C();
        O();
    }
    public void O() throws SyntaxException{
        if(zasobnik.peek() == '{'){
            zasobnik.pop();
            U();
            if(zasobnik.peek() == '}'){
                zasobnik.pop();
                B();
            }
        }else {
            throw new SyntaxException("chyba");
        }
    }
    public void U() throws SyntaxException{
        switch (zasobnik.peek()){
            case 'i':
                zasobnik.pop();
                if(zasobnik.peek() == 'n'){
                    zasobnik.pop();
                    if(zasobnik.peek() == 't'){
                        zasobnik.pop();
                        A();
                    }else {
                        throw new SyntaxException("chyba");
                    }
                }else{
                    throw new SyntaxException("chyba");
                }
                break;
            case 'c':
                zasobnik.pop();
                if(zasobnik.peek() == 'h'){
                    zasobnik.pop();
                    if(zasobnik.peek() == 'a'){
                        zasobnik.pop();
                            if(zasobnik.peek() == 'r'){
                                zasobnik.pop();
                                A();
                            }else {
                                throw new SyntaxException("chyba");
                            }
                    }else{
                        throw new SyntaxException("chyba");
                    }
                }else {
                    throw new SyntaxException("chyba");
                }
                break;
            case 's':
                zasobnik.pop();
                if(zasobnik.peek() == 't'){
                    zasobnik.pop();
                    if(zasobnik.peek() == 'r'){
                        zasobnik.pop();
                        if(zasobnik.peek() == 'i'){
                            zasobnik.pop();
                            if(zasobnik.peek() == 'n'){
                                zasobnik.pop();
                                if(zasobnik.peek() == 'g'){
                                    zasobnik.pop();
                                    A();
                                }
                            }else {
                                throw new SyntaxException("chyba");
                            }
                        }else {
                            zasobnik.push('r');
                            zasobnik.push('t');
                            zasobnik.push('s');
                            I();
                            return;
                        }
                    }else {
                        throw new SyntaxException("chyba");
                    }
                }else {
                    throw new SyntaxException("chyba");
                }
                break;
            case 'f':
                zasobnik.pop();
                if(zasobnik.peek() == 'l'){
                    zasobnik.pop();
                    if(zasobnik.peek() == 'o'){
                        zasobnik.pop();
                        if(zasobnik.peek() == 'a'){
                            zasobnik.pop();
                            if(zasobnik.peek() == 't'){
                                zasobnik.pop();
                                A();
                            }else {
                                throw new SyntaxException("chyba");
                            }
                        }else {
                            throw new SyntaxException("chyba");
                        }
                    }else {
                        throw new SyntaxException("chyba");
                    }
                }else {
                    throw new SyntaxException("chyba");
                }
                break;
            case 'd':
                zasobnik.pop();
                if(zasobnik.peek() == 'o'){
                    zasobnik.pop();
                    if(zasobnik.peek() == 'u'){
                        zasobnik.pop();
                        if(zasobnik.peek() == 'b'){
                            zasobnik.pop();
                            if(zasobnik.peek() == 'l'){
                                zasobnik.pop();
                                if(zasobnik.peek() == 'e'){
                                    zasobnik.pop();
                                    A();
                                }else {
                                    throw new SyntaxException("chyba");
                                }
                            }else {
                                throw new SyntaxException("chyba");
                            }
                        }else {
                            throw new SyntaxException("chyba");
                        }
                    }else {
                        throw new SyntaxException("chyba");
                    }
                }else {
                    throw new SyntaxException("chyba");
                }
                break;
        }
    }
    public void A() throws SyntaxException{
        if(isAlphabetic(zasobnik.peek())){
            zasobnik.pop();
            B();
            U();
        }else {
            throw new SyntaxException("chyba");
        }
    }
    public void B() throws SyntaxException{
        C();
        if(zasobnik.peek() == ';'){
            zasobnik.pop();

        }
    }
    public void C() throws SyntaxException{
        if(isAlphabetic(zasobnik.peek())) {
            zasobnik.pop();
        }
    }
}
