package web;

import dto.Exposer;
import dto.SeckillExcution;
import dto.SeckillResult;
import entity.Seckill;
import enums.SeckillStatEnum;
import exception.RepeatKillException;
import exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.SeckillService;

import java.util.Date;
import java.util.List;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/30
 * Time: 11:47
 * Description:
 */
@Controller
@RequestMapping("/seckill") //url:模块/资源/{id}/细分
public class SeckillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //获取列表页
        List<Seckill> seckillList = seckillService.getSeckillList();

        model.addAttribute("list", seckillList);
        //list.jsp + model = ModelAndView
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }

        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @RequestMapping(
            value = "/{seckillId}/exposer", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(
            value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"}
    )
    @ResponseBody
    public SeckillResult<SeckillExcution> execute(@PathVariable Long seckillId,
                                                  @PathVariable String md5,
                                                  @CookieValue(value = "killPhone", required = false) Long phone
    ) {

        //可以用 SpringMVC valid
        if (phone == null) {
            return new SeckillResult<>(false, "未注册");
        }

        SeckillResult<SeckillExcution> result;
        try {
            SeckillExcution seckillExcution = seckillService.executeSeckill(seckillId, phone, md5);
            result = new SeckillResult<>(true, seckillExcution);
        } catch (RepeatKillException e) {

            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStatEnum.REPEAT_KILL);
            result = new SeckillResult<>(true, seckillExcution);//正常情况
        } catch (SeckillCloseException e) {
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStatEnum.END);
            result = new SeckillResult<>(false, seckillExcution);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStatEnum.INNER_ERROR);
            result = new SeckillResult<>(false, seckillExcution);
        }
        return result;
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time() {
        Date date = new Date();
        return new SeckillResult<>(true, date.getTime());
    }

}
