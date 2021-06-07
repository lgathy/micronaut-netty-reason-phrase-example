package com.github.lgathy;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Filter("/**")
public class HttpReasonPhraseFilter implements HttpServerFilter {

  @Override
  public Publisher<MutableHttpResponse<?>> doFilter(
      HttpRequest<?> request, ServerFilterChain chain) {
    Publisher<MutableHttpResponse<?>> publisher = chain.proceed(request);
    return subscriber ->
        publisher.subscribe(
            new Subscriber<>() {
              @Override
              public void onSubscribe(Subscription s) {
                subscriber.onSubscribe(s);
              }

              @Override
              public void onNext(MutableHttpResponse<?> mutableHttpResponse) {
                subscriber.onNext(
                    mutableHttpResponse.header("Reason-Phrase", mutableHttpResponse.reason()));
              }

              @Override
              public void onError(Throwable t) {
                subscriber.onError(t);
              }

              @Override
              public void onComplete() {
                subscriber.onComplete();
              }
            });
  }
}
