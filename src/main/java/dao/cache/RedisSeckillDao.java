package dao.cache;

import entity.Seckill;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/4/2
 * Time: 09:24
 * Description:
 */
public class RedisSeckillDao {
    private final JedisPool jedisPool;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Schema<Seckill> seckillSchema = RuntimeSchema.getSchema(Seckill.class);

    public RedisSeckillDao(String ip, int port) {
        this.jedisPool = new JedisPool(ip, port);
    }


    public Seckill get(Long seckillId) {
        try {
            Jedis resource = jedisPool.getResource();
            try {
                String key = getKey(seckillId);
                byte[] bytes = resource.get(key.getBytes());
                if (bytes == null) return null;
                Seckill seckill = seckillSchema.newMessage();
                ProtobufIOUtil.mergeFrom(bytes, seckill, seckillSchema);
                return seckill;
            } finally {
                resource.close();
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String set(Seckill seckill) {
        try {
            Jedis resource = jedisPool.getResource();
            try {
                String key = getKey(seckill.getSeckillId());
                LinkedBuffer linkedBuffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
                byte[] bytes = ProtobufIOUtil.toByteArray(seckill, seckillSchema, linkedBuffer);
                return resource.setex(key.getBytes(), 3600, bytes);
            } finally {
                resource.close();
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private String getKey(Long seckillId) {
        return "seckill:" + seckillId;
    }
}
