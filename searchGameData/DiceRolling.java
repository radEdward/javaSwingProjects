package dndBoard;

public class DiceRolling {



		public static int d4(int modifier) {
			  return (int) (Math.ceil((4*Math.random())) + modifier);
		}

		public static int d6(int modifier) {
			  return (int) (Math.ceil((6*Math.random())) + modifier);
		}

		public static int d8(int modifier) {
			  return (int) (Math.ceil((8*Math.random())) + modifier);
		}

		public static int d10(int modifier) {
			  return (int) (Math.ceil((10*Math.random())) + modifier);
		}

		public static int d12(int modifier) {
			  return (int) (Math.ceil((12*Math.random())) + modifier);
		}

		public static int d20(int modifier) {
			  return (int) (Math.ceil((20*Math.random())) + modifier);
		}

		public static int d100(int modifier) {
			  return (int) (Math.ceil((100*Math.random())) + modifier);
		}



}