package pt.c02classes.s01knowledge.s02app.actors;

// import java.util.Scanner;
import java.util.Stack;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

    IResponder responder;

    public void connect(IResponder responder) {
        this.responder = responder;
    }

    public boolean discover() {
        String[] direcoes = new String[]{"norte", "leste", "sul", "oeste"};
        Stack<Integer> stack = new Stack<>();
        int pos = -1, excp = -1, i;
        // Scanner scanner = new Scanner(System.in);
        String direction;
        Boolean cont;

        String stop = responder.ask("aqui");

        while (!stop.equalsIgnoreCase("saida")) {

            cont = true;
            if (!stack.empty())
                excp = (stack.peek() + 2) % 4;

            for (i = pos + 1; i < 4 && cont; i++) {
                direction = responder.ask(direcoes[i]);
                System.out.print("(P)ergunta, (M)ovimento ou (F)im? "); System.out.println("P");
                System.out.print("  --> "); System.out.println(direcoes[i]);
                System.out.println(" Resposta: " + direction);

                if ( ( direction.equalsIgnoreCase("passagem") || direction.equalsIgnoreCase("saida") ) && (i != excp)) {
                    System.out.print("(P)ergunta, (M)ovimento ou (F)im? "); System.out.println("M");
                    System.out.print("  --> "); System.out.println(direcoes[i]);
                    System.out.println("  Movimento executado!");
                    stack.push(i);
                    pos = -1;
                    responder.move(direcoes[i]);
                    cont = false;
                }
            }

            if (cont && !stack.empty()) {
                System.out.println("Voltando");
                pos = stack.peek();
                responder.move(direcoes[(stack.pop() + 2)%4]);
            }

            stop = responder.ask("aqui");

        }

        if (stop.equalsIgnoreCase("saida")) {
            System.out.println("ACHEEEEEEEEEEEI");
            return true;
        }
        return false;

    }

}
