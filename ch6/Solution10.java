package ch6;

import java.util.ArrayList;

class Bottle {
	private boolean poisoned = false;
	private int id;
	public Bottle(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setPoison() {
		poisoned = true;
	}
	public boolean isPoison() {
		return poisoned;
	}
}

public class Solution10 {
	
	class TestStrip {
		public int DAYS_FOR_RESULT = 7;
		private ArrayList<ArrayList<Bottle>> dropsByDay = new ArrayList<ArrayList<Bottle>>();
		private int id;
		public TestStrip(int id) {
			this.id = id;
		}
		public int getId() {
			return id;
		}
		private void sizeDropsForDay(int day) {
			while (dropsByDay.size() <= day) {
				dropsByDay.add(new ArrayList<Bottle>());
			}
		}
		
		public void addDropOnDay(int day, Bottle bottle) {
			sizeDropsForDay(day);
			ArrayList<Bottle> drops = dropsByDay.get(day);
			drops.add(bottle);
		}
		
		private boolean hasPoison(ArrayList<Bottle> bottles) {
			for (Bottle b : bottles) {
				if (b.isPoison()) {
					return true;
				}
			}
			return false;
		}
		
		public boolean isPositiveOnDay(int day) {
			int testDay = day - DAYS_FOR_RESULT;
			if (testDay < 0 || testDay >= dropsByDay.size()) {
				return false;
			}
			for (int d = 0; d <= testDay; d++) {
				ArrayList<Bottle> bottles = dropsByDay.get(d);
				if (hasPoison(bottles)) {
					return true;
				}
			}
			return false;
		}
	}
	
	public static int findPoison(ArrayList<Bottle> bottles, ArrayList<TestStrip> strips) {
		runTests(bottles, strips);
		ArrayList<Integer> positive = getPositiveOnDay(strips, 7);
		return setBits(positive);
	}
	
	public static void runTests(ArrayList<Bottle> bottles, ArrayList<TestStrip> strips) {
		for (Bottle bottle : bottles) {
			int id = bottle.getId();
			int bitIndex = 0;
			while (id > 0) {
				if ((id & 1) == 1) {
					strips.get(bitIndex).addDropOnDay(0, bottle);
				}
				bitIndex++;
				id >>= 1;
			}
		}
	}

	public static ArrayList<Integer> getPositiveOnDay(ArrayList<TestStrip> strips, int day) {
		ArrayList<Integer> positive = new ArrayList<Integer>();
		for (TestStrip strip : strips) {
			int id = strip.getId();
			if (strip.isPositiveOnDay(day)) {
				positive.add(id);
			}
		}
		return positive;
	}
	
	public static int setBits(ArrayList<Integer> positive) {
		int id = 0;
		for (int bitIndex : positive) {
			id |= 1 << bitIndex;
		}
		return id;
	}
	
	public static void main(String[] args) {
		Solution10 s = new Solution10();
		ArrayList<Bottle> bottles = new ArrayList<Bottle>();
		for (int i = 0; i < 1000; i++) {
			Bottle bottle = new Bottle(i);
			if (i == 398) bottle.setPoison();
			bottles.add(bottle);
		}
		ArrayList<TestStrip> strips = new ArrayList<TestStrip>();
		for (int i = 0; i < 10; i++) {
			TestStrip strip = s.new TestStrip(i);
			strips.add(strip);
		}
		System.out.println(findPoison(bottles, strips));
	}

}