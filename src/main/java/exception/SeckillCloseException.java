package exception;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/29
 * Time: 20:52
 * Description: 秒杀关闭异常
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
