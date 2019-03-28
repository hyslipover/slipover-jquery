package org.slipover.frame.jquery.tool;

import org.slipover.frame.jquery.constant.CharConstant;

import java.util.Random;

/**
 * 随机数工具
 */
public interface RandomTool {

    Random random = new Random();

    /**
     * 随机 size 个数组和字母
     * @param size
     * @return
     */
    default String random(int size){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            char[] target = CharConstant.NUMBER_AND_LETTER[random.nextInt(CharConstant.NUMBER_AND_LETTER.length)];
            stringBuilder.append(target[random.nextInt(target.length)]);
        }
        return stringBuilder.toString();
    }

    /**
     * 随机 size 个数字
     * @param size
     * @return
     */
    default String randomNumber(int size){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(CharConstant.NUMBER[random.nextInt(CharConstant.NUMBER.length)]);
        }
        return stringBuilder.toString();
    }

    /**
     * 随机 size 个字母
     * @param size
     * @return
     */
    default String randomLetter(int size){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(CharConstant.LETTER[random.nextInt(CharConstant.LETTER.length)][random.nextInt(CharConstant.LOWER_LETTER.length)]);
        }
        return stringBuilder.toString();
    }

    /**
     * 随机 size 个小写字母
     * @param size
     * @return
     */
    default String randomLowerLetter(int size){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(CharConstant.LOWER_LETTER[random.nextInt(CharConstant.LOWER_LETTER.length)]);
        }
        return stringBuilder.toString();
    }

    /**
     * 随机 size 个大写字母
     * @param size
     * @return
     */
    default String randomUpperLetter(int size){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(CharConstant.UPPER_LETTER[random.nextInt(CharConstant.UPPER_LETTER.length)]);
        }
        return stringBuilder.toString();
    }

}
