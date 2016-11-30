package io.oblomov.service;

import io.oblomov.service.model.Result;

/**
 * Created by AbidinK on 27/11/2016.
 */
public interface MicrosoftResellerService {
    Result getAccessToken();

    Result activation(Long orderLineAutoId);

    Result deactivation(Long orderLineAutoId);

    Result suspend(Long orderLineAutoId);

    Result resume(Long orderLineAutoId);

    Result update(Long orderLineAutoId);
}
