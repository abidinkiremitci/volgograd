package io.oblomov.application.api.endpoint;

import io.oblomov.application.service.resource.OfferResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by AbidinK on 18.10.2017.
 */
@RestController
@RequestMapping(path = "/api/v1/offers")
public class OfferController extends BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<OfferResource>> getEligibleOffersByCountryCode(@RequestParam(value = "country", required = false) final String country) {
        return new ResponseEntity<>(microsoftResellerService.getEligibleOffersByCountryCode(country), HttpStatus.OK);
    }
}
