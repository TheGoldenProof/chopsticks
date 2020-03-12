package main.java.thegoldenproof.chopsticks;

import java.util.Random;
import java.util.Scanner;

public class Main {
	static Scanner input = new Scanner(System.in);
	
	static State pState = new State(1,1);
	static State cState = new State(1,1);
	
	static boolean gameOver = false;

	public static void main(String[] args) {
		
		System.out.println("Would you like to go first? (Y/N)");
		String first = input.next();
		boolean pTurn = first.startsWith("Y")||first.startsWith("y");
		
		printStates();
		
		while (!gameOver) {
			if (pTurn) {
				String play = input.next();
				if (play.charAt(0) == '(') {
					State s = new State(0,0);
					do {
						s = new State(play.substring(0, 5));
						if (pState.total() != s.total()) {
							play = input.next();
						}
					} while (pState.total() == s.total());
				} else {
					State.Hand from = play.charAt(0) == 'l'? ((pState.l().dead()||pState.l().v()==0)? pState.r(): pState.l()) : ((pState.r().dead()||pState.r().v()==0)? pState.l(): pState.r());
					State.Hand to = play.charAt(1) == 'l'? ((cState.l().dead()||cState.l().v()==0)? cState.r(): cState.l()) : ((cState.r().dead()||cState.r().v()==0)? cState.l(): cState.r());
					
					to.v(from.v());
				}
				pTurn = false;
			} else {
				Random rand = new Random();
				if (Math.random() < 0.5 && !(cState.l().dead() || cState.r().dead())) {
					if (Math.random() < 0.5) {
						int r = rand.nextInt(cState.l().v())+1;
						cState.l(cState.l().v()-r);
						cState.r(cState.r().v()+r);
					} else {
						int r = rand.nextInt(cState.r().v())+1;
						cState.r(cState.r().v()-r);
						cState.l(cState.l().v()+r);
					}
				} else {
					int r = rand.nextInt(cState.total()+1);
					State s = new State(r, cState.total()-r);
				}
			}
			printStates();
		}
	}
	
	static void printStates() {
		System.out.println("Player: "+pState);
		System.out.println("AI: "+cState);
	}

}
