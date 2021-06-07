package com.github.lgathy

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import javax.inject.Inject

@MicronautTest
class MicronautNettyReasonPhraseExampleSpec extends Specification {

    @Inject
    EmbeddedApplication<?> application

    @Client('/')
    @Inject
    HttpClient httpClient

    void 'standard Reason-Phrase header is present in 200 response'() {

        expect:
        application.running

        when:
        def response = httpClient.toBlocking().exchange('/hello', String, String)

        then:
        response.status == HttpStatus.OK
        response.header('Reason-Phrase') == HttpStatus.OK.reason
    }

    void 'custom Reason-Phrase header is present in 404 response'() {

        given:


        expect:
        application.running

        when:
        def response = httpClient.toBlocking().exchange('/test', String, String)

        then:
        response.status == HttpStatus.NOT_FOUND
        response.header('Reason-Phrase') == 'These are not the droids you are looking for'
    }

}
