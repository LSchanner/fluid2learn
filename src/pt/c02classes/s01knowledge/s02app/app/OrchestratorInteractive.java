package pt.c02classes.s01knowledge.s02app.app;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class OrchestratorInteractive
{
	public static void main(String[] args)
	{
		IEnquirer enq;
		IResponder resp;
		IStatistics stat;
        Scanner in = new Scanner(System.in);

        System.out.println("Você quer jogar um jogo? [y|n]");
        in.nextLine();
        System.out.println("Ok, então. Animals ou Maze?");
        String s = in.nextLine();

        if(s.equalsIgnoreCase("animals")){
            IBaseConhecimento base = new BaseConhecimento();

            base.setScenario("animals");
            String listaAnimais[] = base.listaNomes();
            for (int animal = 0; animal < listaAnimais.length; animal++) {
                System.out.println("Enquirer com " + listaAnimais[animal] + "...");
                stat = new Statistics();
                resp = new ResponderAnimals(stat, listaAnimais[animal]);
                enq = new EnquirerAnimals();
                enq.connect(resp);
                enq.discover();
                System.out.println("----------------------------------------------------------------------------------------\n");
            }
        }else if(s.equalsIgnoreCase("maze")) {

            System.out.println("Enquirer com Mordor...");
            stat = new Statistics();
            resp = new ResponderMaze(stat, "mordor");
            enq = new EnquirerMaze();
            enq.connect(resp);
            enq.discover();
            System.out.println("----------------------------------------------------------------------------------------\n");
        }
	}
}
