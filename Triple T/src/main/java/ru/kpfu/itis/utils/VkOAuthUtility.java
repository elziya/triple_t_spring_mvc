package ru.kpfu.itis.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.dto.oAuth.VkAccessTokenDto;
import ru.kpfu.itis.dto.oAuth.VkAccountDto;
import ru.kpfu.itis.dto.oAuth.VkAccountInfoResponse;
import ru.kpfu.itis.exceptions.TripleTVkOAuthException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class VkOAuthUtility {

    @Value("${oauth2.vk.v}")
    private String vkApiVersion;

    @Value("${oauth2.vk.client.id}")
    private String vkClientId;

    @Value("${oauth2.vk.client.secret}")
    private String vkClientSecret;

    @Value("${oauth2.vk.redirect-uri}")
    private String vkRedirectUri;

    @Value("${oauth2.vk.api.authorize.url}")
    private String vkAuthorizeUrl;

    @Value("${oauth2.vk.api.access-token.url}")
    private String vkApiTokenUrl;

    @Value("${oauth2.vk.api.users.get.url}")
    private String vkApiUsersGetUrl;

    public String getAuthorizeRequest(HttpServletRequest request) {

        vkRedirectUri = String.format(vkRedirectUri, request.getScheme(), request.getServerName());

        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id", vkClientId);
        parameters.put("redirect_uri", vkRedirectUri);
        parameters.put("response_type", "code");
        parameters.put("scope", "email");
        parameters.put("v", vkApiVersion);

        return GeneratingUriUtility.generateURI(vkAuthorizeUrl, parameters);
    }

    public VkAccessTokenDto getAccessToken(String code) throws TripleTVkOAuthException {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id", vkClientId);
        parameters.put("client_secret", vkClientSecret);
        parameters.put("redirect_uri", vkRedirectUri);
        parameters.put("code", code);
        String uri = GeneratingUriUtility.generateURI(vkApiTokenUrl, parameters);

        try {
            return restTemplate.getForObject(uri, VkAccessTokenDto.class);
        }
        catch (Exception e){
            throw new TripleTVkOAuthException();
        }
    }

    public VkAccountDto getAccount(VkAccessTokenDto token) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("user_ids", token.getUserId().toString());
        parameters.put("access_token", token.getAccessToken());
        parameters.put("v", vkApiVersion);
        String uri = GeneratingUriUtility.generateURI(vkApiUsersGetUrl, parameters);

        VkAccountInfoResponse response = restTemplate.getForObject(uri, VkAccountInfoResponse.class);

        if (response != null){
            return response.getResponse().get(0);
        }

        throw new TripleTVkOAuthException();
    }
}
