package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

import java.util.HashMap;

public class Enquirer implements IEnquirer {
    IObjetoConhecimento obj;
    HashMap<String,String> memo;

    public Enquirer() {
        memo = new HashMap<String, String>();
    }

    public String memoAsk (String pergunta,IResponder responder) {

        if(memo.containsKey(pergunta)){
            return memo.get(pergunta) ;
        }
        else{
            String res = responder.ask(pergunta);
            memo.put(pergunta,res);
            return res;
        }
    }


    @Override
    public void connect(IResponder responder) {
        IBaseConhecimento bc = new BaseConhecimento();

        String animais[] = {"tiranossauro", "aranha", "camarao", "humano", "pikachu"};

        boolean ja_sei = false;
        int i;
        for (i = 0; i < animais.length && !ja_sei; i++) {
            obj = bc.recuperaObjeto(animais[i]);
            IDeclaracao decl = obj.primeira();

            ja_sei = true;
            while(decl != null){
                if(!memoAsk(decl.getPropriedade(),responder).equalsIgnoreCase(decl.getValor())){
                    ja_sei = false;
                    break;
                }
                decl = obj.proxima();

            }


        }


        boolean acertei;
        if (ja_sei)
            acertei = responder.finalAnswer(animais[i-1]);
        else
            acertei = responder.finalAnswer("nao conheco");

        if (acertei)
            System.out.println("Oba! Acertei!");
        else
            System.out.println("fuem! fuem! fuem!");

    }
	
}
