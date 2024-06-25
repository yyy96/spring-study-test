package com.tempspring.test.common.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignClientErrorDecoderException extends RuntimeException implements ErrorDecoder {

    public FeignClientErrorDecoderException() {
        super("FeignClientErrorDecoderException");
    }

    public Exception decode(String methodKey, Response response) {
        return new FeignClientErrorDecoderException();
    }
}