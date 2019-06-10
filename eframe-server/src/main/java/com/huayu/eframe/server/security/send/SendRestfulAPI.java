package com.huayu.eframe.server.security.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *添加http client的能力，提供提供的API，
 * Created by Leo on 2019/5/6.
 * 由于这个比较简单，建议定制端自己写这个API，而不采用这个，在restfulTemplate中，我们放开text_plain作为json返回。
 */
@Service
public class SendRestfulAPI
{

    @Autowired
    @Qualifier("JsonRestTemplate")
    private RestTemplate jsonRestTemplate;

    @Autowired
    @Qualifier("XmlRestTemplate")
    private RestTemplate xmlRestTemplate;



    public <RequestType,ResponseType> ResponseType sendPostRestful(String url,RequestType data,Class<ResponseType> responseClass)
    {
        return jsonRestTemplate.postForObject(url,data,responseClass);
    }


    public <ResponseType> ResponseType sendGetRestful(String url,Class<ResponseType> responseClass)
    {
       return jsonRestTemplate.getForObject(url, responseClass);
    }

    public void sendDeleteRestful(String url)
    {
        jsonRestTemplate.delete(url);
    }

    public void sendUpdateRestful(String url,Object data)
    {
        jsonRestTemplate.put(url,data);
    }

    public RestTemplate getRestTemplate()
    {
        return jsonRestTemplate;
    }


    public <RequestType,ResponseType> ResponseType sendXmlPostRestful(String url,RequestType data,Class<ResponseType> responseClass)
    {
        return xmlRestTemplate.postForObject(url,data,responseClass);
    }

}
