package org.slipover.frame.jquery.constant;

/**
 * 常用字符集
 */
public final class CharConstant {

    private CharConstant(){ }

    public static final char[] NUMBER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static final char[] LOWER_LETTER = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static final char[] UPPER_LETTER = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static final char[][] LETTER = new char[][]{LOWER_LETTER,UPPER_LETTER};

    public static final char[][] NUMBER_AND_LETTER = new char[][]{NUMBER,LOWER_LETTER,UPPER_LETTER};

}
