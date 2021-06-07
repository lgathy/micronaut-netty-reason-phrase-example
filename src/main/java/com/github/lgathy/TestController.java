package com.github.lgathy;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;

@Controller
public class TestController {

  @Get("hello")
  public String get() {
    return "Hello world!";
  }

  @Error(status = HttpStatus.NOT_FOUND)
  public HttpResponse<Void> custom404() {
    return HttpResponse.status(
        HttpStatus.NOT_FOUND, "These are not the droids you are looking for");
  }
}
