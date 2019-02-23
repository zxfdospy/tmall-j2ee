<%--
  Created by IntelliJ IDEA.
  User: zxfdo
  Date: 2019/2/18
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<script>
    $(function () {
        setInterval(changeTimeout,1000);
        function changeTimeout() {
            var time=$("span.timecount").text();
            time=time-1;
            $("span.timecount").text(time);
            if(time==0){
                self.location.href="forehome";
            }
        }
    })
</script>

<div class="registerSuccessDiv">

    <img src="img/site/registerSuccess.png">
    恭喜注册成功

    <div class="backtohome text-center"><span class="timecount" style="color:orange">5</span>秒后跳转首页...
    </div>
</div>
