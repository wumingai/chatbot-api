package cn.wuming.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.skyscreamer.jsonassert.comparator.JSONCompareUtil;

import java.io.IOException;

/**
 * @author 无名ai
 * @version 1.0
 * @description 单元测试
 * @time 2024.03
 */
public class ApiTest {

    /***
     * 抓取问题
     */
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");
        get.addHeader("Cookie", "zsxq_access_token=80E062FA-B144-B01B-7896-C8FFFE13D679_192C9DFF04C5A4F1; zsxqsessionid=662b70a22d6322df1ad9e20b338368ae; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184544242225212%22%2C%22first_id%22%3A%2218e3053797b7ae-07489f020969478-4c657b58-2073600-18e3053797c13f6%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThlMzA1Mzc5N2I3YWUtMDc0ODlmMDIwOTY5NDc4LTRjNjU3YjU4LTIwNzM2MDAtMThlMzA1Mzc5N2MxM2Y2IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTg0NTQ0MjQyMjI1MjEyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22184544242225212%22%7D%2C%22%24device_id%22%3A%2218e3053797b7ae-07489f020969478-4c657b58-2073600-18e3053797c13f6%22%7D");
        get.addHeader("Content-Type", "application/json;charset=utf8");
        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }


    /***
     * 完成对固定id的问题的回答
     */
    @Test
    public void answer() throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/2855824254244851/comments");
        post.addHeader("Cookie", "zsxq_access_token=80E062FA-B144-B01B-7896-C8FFFE13D679_192C9DFF04C5A4F1; zsxqsessionid=662b70a22d6322df1ad9e20b338368ae; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184544242225212%22%2C%22first_id%22%3A%2218e3053797b7ae-07489f020969478-4c657b58-2073600-18e3053797c13f6%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThlMzA1Mzc5N2I3YWUtMDc0ODlmMDIwOTY5NDc4LTRjNjU3YjU4LTIwNzM2MDAtMThlMzA1Mzc5N2MxM2Y2IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTg0NTQ0MjQyMjI1MjEyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22184544242225212%22%7D%2C%22%24device_id%22%3A%2218e3053797b7ae-07489f020969478-4c657b58-2073600-18e3053797c13f6%22%7D");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJason = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"我不会\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJason, ContentType.create("test/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response= httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

}


