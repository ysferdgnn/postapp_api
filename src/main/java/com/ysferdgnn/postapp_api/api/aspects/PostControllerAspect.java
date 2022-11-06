package com.ysferdgnn.postapp_api.api.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PostControllerAspect {


    @Before("execution(* com.ysferdgnn.postapp_api.api.controllers.PostController.deleteOnePost(..))")
    public void checkuser(JoinPoint point) throws Exception {
       // throw  new Exception("test");
    }
}
