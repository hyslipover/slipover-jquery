package org.slipover.frame.jquery.tool;

import org.slipover.frame.share.code.ServerCode;
import org.slipover.frame.share.exception.ServerError;
import org.slipover.frame.share.exception.ServerException;
import org.slipover.frame.share.exception.ServerVerifyError;

public interface ThrowTool {

    /**
     * 效验是否错误，若错误 抛出 org.slipover.frame.share.exception.ServerVerifyError 异常信息
     * @param isError
     * @param message
     */
    default void verify(boolean isError, String message){
        verify(isError, message, null);
    }

    /**
     * 效验是否错误，若错误 执行 runnable ，抛出 org.slipover.frame.share.exception.ServerVerifyError 异常信息
     * @param isError
     * @param message
     * @param runnable
     */
    default void verify(boolean isError, String message, Runnable runnable){
        if (isError) {
            if (runnable != null) {
                runnable.run();
            }
            throw new ServerVerifyError(message);
        }
    }

    /**
     * 效验是否错误，若错误 抛出 org.slipover.frame.share.exception.ServerError 异常信息
     * @param isError
     * @param serverCode
     */
    default void error(boolean isError, ServerCode serverCode) {
        error(isError, serverCode.code(), serverCode.message());
    }

    /**
     * 效验是否错误，若错误 执行 runnable ，抛出 org.slipover.frame.share.exception.ServerError 异常信息
     * @param isError
     * @param serverCode
     * @param runnable
     */
    default void error(boolean isError, ServerCode serverCode, Runnable runnable) {
        error(isError, serverCode.code(), serverCode.message(), runnable);
    }

    /**
     * 效验是否错误，若错误 抛出 org.slipover.frame.share.exception.ServerError 异常信息
     * @param isError
     * @param code
     * @param message
     */
    default void error(boolean isError, String code, String message) {
        error(isError, code, message, null);
    }

    /**
     * 效验是否错误，若错误 执行 runnable ，抛出 org.slipover.frame.share.exception.ServerError 异常信息
     * @param isError
     * @param code
     * @param message
     * @param runnable
     */
    default void error(boolean isError, String code, String message, Runnable runnable) {
        if (isError) {
            if (runnable != null) {
                runnable.run();
            }
            throw new ServerError(code, message);
        }
    }

    /**
     * 抛出 org.slipover.frame.share.exception.ServerException 异常信息
     * @param cause
     */
    default void exception(Throwable cause) {
        throw new ServerException(cause);
    }

    /**
     * 抛出 org.slipover.frame.share.exception.ServerException 异常信息
     * @param cause
     * @param message
     * @param params
     */
    default void exception(Throwable cause, String message, Object... params) {
        throw new ServerException(String.format(message.replace("{}", "%s"), params), cause);
    }

}
