package main.java.thegoldenproof.chopsticks;

public class State {
	
	private Hand l, r;
	
	public State(int h1, int h2) {
		l = new Hand(h1); r = new Hand(h2);
	}
	
	public State(Hand h1, Hand h2) {
		l = h1; r = h2;
	}
	
	public State(String state) {
		state.replaceAll("\\D", "");
		l = new Hand(Integer.parseUnsignedInt(String.valueOf(state.charAt(0))));
		r = new Hand(Integer.parseUnsignedInt(String.valueOf(state.charAt(1))));
	}
	
	public Hand l() { return l; }
	public Hand r() { return r; }
	
	public boolean l(int h1) { return l.v(h1); }
	public boolean r(int h2) { return r.v(h2); }
	
	public int total() { return l().v()+r().v(); }
	
	@Override
	public String toString() {
		return "("+l+","+r+")";
	}
	
	public static class Hand {
		
		private int v;
		private boolean dead = false;
		
		public Hand(int v) {
			this.v = v;
		}
		
		public int v() { return v; }
		public boolean v(int v) {
			this.v = v;
			if (v >= 5) {
				dead = true;
				this.v = 0;
			}
			return dead;
		}
		
		public boolean dead() { return dead; }
		
		@Override
		public String toString() {
			return dead?"x":v+"";
		}
		
	}
	
}
