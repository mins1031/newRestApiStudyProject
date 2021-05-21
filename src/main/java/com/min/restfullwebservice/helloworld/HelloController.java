package com.min.restfullwebservice.helloworld;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final MessageSource messageSource;

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }
    /**
     * RestController에서 반환값으로 pojo객체나 컬렉션등을 주게되면 문자열 반환하는거랑
     * 비슷하게 알아서 json으로 변환해 반환해줌.jackson이 대표적 컨버터임.
     * 그래서 백기선수업때 자바빈형태인 일단 Event객체를 응답 바디에 담아 반환할수 있었지만
     * Errors타입의 객체는 불가능해 따로 Serializer를 만들어줬던걸 생각하면됨.
     * */

    @GetMapping("/hello-world-bean/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }

    /**
     * 해당 메서드를 실행한다는건 다국어 처리를 진행한다는의미이다.
     * 그렇기에 요청헤더값에 원하는 지역을 설정해야 하기에 파라미터에 Locale을 추가함
     * 이 locale값은 RequestHeader에 넣어줄것이기 때문에 @RequestHeader를 넣어줌
     * 즉 밑의 설정은 RequestHeader에 Accept-Language설정이 없을경우 locale값으로 정의하려는 로직임.
     * */
    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language",required = false)
                                              Locale locale){
        return messageSource.getMessage("greeting.message",null,locale);
    }
    /* 위 리턴값이 어떤 원리냐면 내가 작성했던 메세지 파일의 변수값greeting.message을 가져옴
    * 이건 한국,영어,불어 3가지를 작성해놨는데 파라미터로 오는 locale값이 어떤값이냐에 따라 언어가 달라짐
    * 한국어면 안녕하세요, 불어면 Bonjour, 영어면 hello 이런식으로.
    * 일단 이런게 있다고만 알아두자. */
}
