#  console ; 转为$$
DELIMITER  $$
CREATE PROCEDURE seckill.execute_seckill
  (IN v_seckillId bigint,IN v_phone bigint,IN v_kill_time timestamp,OUT r_result int)
  BEGIN
    DECLARE insert_count int DEFAULT 0;

    -- 开启事务
    START TRANSACTION ;

    INSERT IGNORE INTO success_killed(seckill_id, user_phone, state)
    VALUES (v_seckillId,v_phone,0);

    -- 执行结果
    SELECT row_count() INTO insert_count;

    IF (insert_count=0) THEN
      ROLLBACK ;
      -- 定义了-1代表重复秒杀
      SET r_result = -1;
    ELSEIF (insert_count<0) THEN
      ROLLBACK ;
      -- 异常
      SET r_result = -2;
    ELSE
      -- 明细插入成功 减库存
      UPDATE seckill
      SET number = number-1
      WHERE seckill_id = v_seckillId
        AND end_time>v_kill_time
        AND start_time<v_kill_time
        AND number>0;

      SELECT row_count() INTO insert_count;
      IF (insert_count=0) THEN
        ROLLBACK ;
        -- 秒杀结束了
        SET r_result = 0;
      ELSEIF (insert_count<0) THEN
        ROLLBACK ;
        SET r_result = -2;
      ELSE
        COMMIT ;
        SET r_result=1;
      END IF ;
    END IF ;
  END;
$$

DELIMITER  ;
set @r_result = -3;
CALL execute_seckill(1002,13588888888,now(),@r_result);
SELECT @r_result;