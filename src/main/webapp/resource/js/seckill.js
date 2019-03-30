// 主要交互逻辑代码
// js 模块化
var seckill={
    //封装秒杀相关ajax的url
    URL:{
        now:function () {
            return '/seckill/time/now';
        },
        exposer:function (seckillId) {
            return '/seckill/'+seckillId+'/exposer';
        },
        execution:function (seckillId,md5) {
            return '/seckill/'+seckillId+'/'+md5+'/execution';
        }

    },
    killHandler:function(seckillId,node,successNode){
        //处理秒杀逻辑
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId),{},function (result) {
            if(result && result['success']){
                var exposer = result['data'];
                if(exposer['exposed']){
                    //开启的
                    //拿到秒杀地址
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId,md5);
                    console.log('killUrl:'+killUrl);
                    //绑定一次点击事件
                    $('#killBtn').one('click',function () {
                        //先禁按钮
                        $(this).addClass('disabled');
                        //发送请求 执行秒杀
                        $.post(killUrl,{},function (result) {
                            console.log(result);
                            if(result){
                                var suceess = result['success'];
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                if(suceess){
                                    var successKilled = killResult['successKilled'];
                                    if(state===1){
                                        node.html('<span class="label label-success">'+stateInfo+'</span>');
                                        successNode.html('<span class="label label-success">秒杀商品:'+successKilled.seckill.name+' 购买者：'+successKilled.userPhone+'</span>');
                                    }else{
                                        node.html('<span class="label label-danger">'+stateInfo+'</span>');
                                    }
                                }else {
                                    node.html('<span class="label label-danger">'+stateInfo+'</span>');
                                }
                            }
                        });
                    });
                    node.show();

                }else{
                    //还未开始，重新计时
                    seckill.countDown(seckillId,exposer['now'],exposer['start'],exposer['end']);
                }
            }else{
                console.log('result'+result);
            }
        });
    },
    //验证手机号
    validatePhone:function(phone){
        if(phone && phone.length===11 && !isNaN(phone)){
            return true;
        }else{
            return false;
        }
    },
    countDown:function(seckillId,nowTime,startTime,endTime){
        var seckillBox = $('#seckill-box');
        var successBox = $('#success-box');
        if(nowTime>endTime){
            //秒杀结束 #seckill-box
            seckillBox.html('秒杀结束！');
        }else if(nowTime < startTime){
            //秒杀未开启,计时事件绑定
            var killTime = new Date(startTime+1000);
            seckillBox.countdown(killTime,function (event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒')
                seckillBox.html(format);
                //seckillBox.html(killTime);
            }).on('finish.countdown',function () {
                //倒计时结束获取秒杀地址,控制实现逻辑 执行秒杀
                seckill.killHandler(seckillId,seckillBox,successBox);

            });

        }else{
            //秒杀开始
            seckill.killHandler(seckillId,seckillBox,successBox);
        }
    },
    detail:{
        //详情页初始化
        init:function (params) {
            //手机验证和登录  计时

            //在cookie中查手机号
            var killPhone = $.cookie('killPhone');


            //验证手机号
            if(!seckill.validatePhone(killPhone)){
                //绑定手机号
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show:true,//显示弹出层
                    backdrop:'static',//禁止位置关闭
                    keyboard:false //关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $("#killPhoneKey").val();
                    if(seckill.validatePhone(inputPhone)){
                        //写入cookie
                        $.cookie('killPhone',inputPhone,{
                            expires:7,
                            path:'/seckill'
                        });
                        //刷新页面
                        window.location.reload();
                    }else{
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
                    }
                });

            }
            //手机号验证通过 已经登录
            //计时

            var seckillId = params['seckillId'];
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            $.get(seckill.URL.now(),{},function (result) {
                //
                if(result && result['success']){
                    var nowTime = result['data'];
                    //事件判断
                    seckill.countDown(seckillId,nowTime,startTime,endTime);

                }else {
                    console.log('result:'+result);
                }
            });
        }
    }
};