package com.agileframework.agilegateway.configure;

import com.agileframework.agilegateway.base.Head;
import com.agileframework.agilegateway.base.RETURN;
import com.google.common.base.Charsets;
import com.netflix.zuul.context.RequestContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;

@Component
public class FallbackProvider implements ZuulFallbackProvider {
  @Override
  public String getRoute() {
    return "*";
  }

  @Override
  public ClientHttpResponse fallbackResponse() {
    return new ClientHttpResponse() {
      @Override
      public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
      }

      @Override
      public int getRawStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
      }

      @Override
      public String getStatusText() {
        return HttpStatus.BAD_REQUEST.getReasonPhrase();
      }

      @Override
      public void close() {
      }

      @Override
      public InputStream getBody() throws IOException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        RequestContext.getCurrentContext().getResponseDataStream();
        HashMap<String,Object> map = new HashMap<>();
        map.put("head",new Head(RETURN.ERROR,request));

        return new ByteArrayInputStream((JSONObject.fromObject(map).toString()).getBytes(Charsets.UTF_8));
      }

      @Override
      public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setContentLanguage(Locale.CHINA);
        return headers;
      }
    };
  }
}