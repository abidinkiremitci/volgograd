package io.oblomov.infra.rest;

import io.oblomov.infra.rest.config.MicrosoftResellerRestServiceConfiguration;
import io.oblomov.infra.rest.config.RestServiceConfiguration;
import io.oblomov.infra.rest.config.custom.microsoftreseller.MicrosoftResellerAuthenticationHandler;
import io.oblomov.infra.rest.config.custom.microsoftreseller.MicrosoftResellerOAuth2RequestAuthenticator;
import io.oblomov.infra.rest.config.custom.microsoftreseller.MicrosoftResellerTokenProvider;
import lombok.Data;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Burak Baldirlioglu
 * @since 11.8.2016 14:12
 */
@Configuration
@Data
@Lazy
public class RestServiceClientFactory {


    @Resource
    private MicrosoftResellerRestServiceConfiguration microsoftResellerRestServiceConfiguration;

    @Bean
    public OAuth2RestTemplate microsoftResellerRestTemplate() {
        OAuth2RestTemplate restTemplate = oAuth2RestTemplate(microsoftResellerRestServiceConfiguration, getDefaultConverters());
//        restTemplate.setErrorHandler(new MicrosoftResellerResponseErrorHandler());
        restTemplate.setAuthenticator(new MicrosoftResellerOAuth2RequestAuthenticator());
        restTemplate.setAccessTokenProvider(new MicrosoftResellerTokenProvider(new MicrosoftResellerAuthenticationHandler()));
        return restTemplate;
    }

    private OAuth2RestTemplate oAuth2RestTemplate(RestServiceConfiguration restServiceConfiguration, List<HttpMessageConverter<?>> messageConverters) {
        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(restServiceConfiguration.getResource(), clientContext);
        configureRestTemplate(restServiceConfiguration, messageConverters, restTemplate);
        return restTemplate;
    }

    private void configureRestTemplate(RestServiceConfiguration restServiceConfiguration, List<HttpMessageConverter<?>> messageConverters, RestTemplate restTemplate) {
        restTemplate.setMessageConverters(messageConverters);

        configureClientFactory(restServiceConfiguration, restTemplate);

//        configureErrorLogging(restServiceConfiguration, restTemplate);
    }

    private void configureClientFactory(RestServiceConfiguration restServiceConfiguration, RestTemplate restTemplate) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        if (restServiceConfiguration.isSSLDisabled()) {
            disableSSLVerification(requestFactory);
        }

        requestFactory.setConnectTimeout(restServiceConfiguration.getConnectTimeout());
        requestFactory.setReadTimeout(restServiceConfiguration.getReadTimeout());
        BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(requestFactory);
        restTemplate.setRequestFactory(bufferingClientHttpRequestFactory);
    }

//    private void configureErrorLogging(RestServiceConfiguration restServiceConfiguration, RestTemplate restTemplate) {
//        if (restServiceConfiguration.isLoggingEnabled()) {
//            List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = new ArrayList<>();
//            clientHttpRequestInterceptors.add(loggingRequestInterceptor);
//            restTemplate.setInterceptors(clientHttpRequestInterceptors);
//
//            //for token service
//            if (restTemplate instanceof OAuth2RestTemplate) {
//                ResourceOwnerPasswordAccessTokenProvider resourceOwnerPasswordAccessTokenProvider = new ResourceOwnerPasswordAccessTokenProvider();
//                resourceOwnerPasswordAccessTokenProvider.setInterceptors(clientHttpRequestInterceptors);
//                BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory2 = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory() {
//                    @Override
//                    protected void prepareConnection(HttpURLConnection connection, String httpMethod)
//                            throws IOException {
//                        super.prepareConnection(connection, httpMethod);
//                        connection.setInstanceFollowRedirects(false);
//                        connection.setUseCaches(false);
//                    }
//                });
//                resourceOwnerPasswordAccessTokenProvider.setRequestFactory(bufferingClientHttpRequestFactory2);
//                ((OAuth2RestTemplate)restTemplate).setAccessTokenProvider(resourceOwnerPasswordAccessTokenProvider);
//            }
//        }
//    }

    private void disableSSLVerification(HttpComponentsClientHttpRequestFactory requestFactory) {

        try {
            TrustStrategy trustStrategy = new TrustStrategy() {
                @Override
                public boolean isTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            };
            SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, trustStrategy)
                    .build();
            HostnameVerifier hostnameVerifier = new NoopHostnameVerifier();
            String[] sslSupportedProtocols = new String[]{"TLSv1.2"};

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,sslSupportedProtocols,null,hostnameVerifier);

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                    .<ConnectionSocketFactory> create()
                    .register("https", sslsf)
                    .register("http", new PlainConnectionSocketFactory())
                    .build();

            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

            CloseableHttpClient httpclient = HttpClientBuilder.create()
                    .setSslcontext(sslContext)
                    .setSSLHostnameVerifier(hostnameVerifier)
                    .setSSLSocketFactory(sslsf)
                    .setConnectionManager(cm)
                    .build();

            requestFactory.setHttpClient(httpclient);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

    private List<HttpMessageConverter<?>> getDefaultConverters() {
        List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();
        httpMessageConverters.add(new MappingJackson2HttpMessageConverter());
        httpMessageConverters.add(new FormHttpMessageConverter());
        httpMessageConverters.add(new StringHttpMessageConverter());
        return httpMessageConverters;
    }
}
