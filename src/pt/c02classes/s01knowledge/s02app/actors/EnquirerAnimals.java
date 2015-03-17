package pt.c02classes.s01knowledge.s02app.actors;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IDeclaracao;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

import java.util.HashMap;

public class EnquirerAnimals implements IEnquirer {

	IResponder responder;
    IObjetoConhecimento obj;
    HashMap<String,String> memo;
    IBaseConhecimento bc;


    public EnquirerAnimals(IBaseConhecimento base){
        bc = base;
        memo = new HashMap<String, String>();

    }

    public String memoAsk (String pergunta) {

        if(memo.containsKey(pergunta)){
            return memo.get(pergunta) ;
        }
        else{
            String res = responder.ask(pergunta);
            memo.put(pergunta,res);
            return res;
        }
    }

	public void connect(IResponder responder) {
		this.responder = responder;
     }

	public boolean discover() {
        String animais[] = {"tiranossauro", "aranha", "camarao", "humano", "pikachu"};

        boolean ja_sei = false;
        int i;
        for (i = 0; i < animais.length && !ja_sei; i++) {
            obj = bc.recuperaObjeto(animais[i]);
            IDeclaracao decl = obj.primeira();

            ja_sei = true;
            while(decl != null){
                if(!memoAsk(decl.getPropriedade()).equalsIgnoreCase(decl.getValor())){
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

        return acertei;

    }

}
