package io.oblomov.service.impl;

import io.oblomov.service.MicrosoftResellerService;
import io.oblomov.service.model.Result;
import org.springframework.stereotype.Service;

/**
 * Created by AbidinK on 27/11/2016.
 */
@Service("microsoftResellerService")
public class MicrosoftResellerServiceImpl implements MicrosoftResellerService {
    @Override
    public Result getAccessToken() {
        return null;
    }

    @Override
    public Result activation(Long orderLineAutoId) {
        return null;
    }

    @Override
    public Result deactivation(Long orderLineAutoId) {
        return null;
    }

    @Override
    public Result suspend(Long orderLineAutoId) {
        return null;
    }

    @Override
    public Result resume(Long orderLineAutoId) {
        return null;
    }

    @Override
    public Result update(Long orderLineAutoId) {
        return null;
    }
}
