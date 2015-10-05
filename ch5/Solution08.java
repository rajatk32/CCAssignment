package ch5;

/*
 * We need to first locate the byte of the starting and ending point
 * of the line. If starting and ending points are in the same byte, 
 * fill the bits in range. If starting and ending points are in different
 * bytes, we also have to fill the bytes between the two.
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 */

public class Solution08 {

	private static void printByte(byte b) {
		for (int i = 7; i >= 0; i--) {
			System.out.print((b >> i) & 1);
		}
	}

	private static void printScreen(byte[] screen, int width) {
		for (int i = 0; i < screen.length; i++) {
			printByte(screen[i]);
			System.out.println("");
		}
		System.out.println("");
	}

	public static void drawHorizontalLine(byte[] screen, int width, int x1,
			int x2, int y) {
		int start_offset = x1 % 8;
		int first_full_byte = x1 / 8 + 1;

		int end_offset = x2 % 8;
		int last_full_byte = x2 / 8 - 1;

		int height_offset = (width / 8) * y;

		// Set all bytes to 1s
		for (int b = first_full_byte; b <= last_full_byte; b++) {
			screen[height_offset + b] = (byte) 0xFF;
		}

		byte start_mask = (byte) (0xFF >> start_offset);
		byte end_mask = (byte) ~(0xFF >> (end_offset + 1));

		// Set start and end of the line
		if ((x1 / 8) == (x2 / 8)) { // If x1 and x2 are in the same byte
			byte mask = (byte) (start_mask & end_mask);
			screen[height_offset + first_full_byte - 1] |= mask;
		} else {
			screen[height_offset + first_full_byte - 1] |= start_mask;
			screen[height_offset + last_full_byte + 1] |= end_mask;
		}
	}

	public static void main(String args[]) {
		byte[] screen = new byte[8];
		int width = 8;
		printScreen(screen, width);
		drawHorizontalLine(screen, width, 0, 5, 4);
		printScreen(screen, width);
	}

}