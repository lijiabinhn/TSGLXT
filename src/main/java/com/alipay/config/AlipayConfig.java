package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016110100785118";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCwYL4e3tXE2mKqHwCaIEEVwDa4jXQJQMqbhLM1yBCs/8rvS75+8ZdIKNjZZ+xXFiWonETO1TkldKxbFQVRBNwV2aQJeYr8JVQE0F4dPLleVz25Hx5vjq6ZkEHOzgFLNUQrDQv2zN2OZ6WYxd6KECDGxApwxOQE/uqJ+9HJxQq9Wy3J74w41FzmjRhvUHG/3vdWv0tgHKXIg7xOawM+4VEb1Ps509w4jt66RrtBH+zMdd+sQ9dqcmUOLG10PNKkNGm+QLq8MJ1Y09NIJaWGtYaMoFU3sKPJiSTPwTP5QYoEsvindBcawi2UPFavyd9Mq28IHkw+Pu+vvTjz8vQrQ5DnAgMBAAECggEBAKYhKVJob49wZ5h3hRinIsa6+o/QZKhGh8/+KNKSc+qPMHU+sdpd8jF8j+wO79SDPstXDZSHz8DQ6XsNDKnoWuiVy+VEvz2KZ131aYA+bxdGMxQIFKHdEeYAs4t4DE3riR7HtiorgtAcHiUM0vTWBhlSE8duHJv8A6weLdbPqrL4+x8GuqBRm7RIj+9j1Lo2mpf1Erspre6l/Bu+EGBO5YELAQ6gNJ7J6yZ3La9Lk4TWqv2JMo5Im/1i8EkyaDrAy1yC22fSmXPTqKc00N3Jliw63vIyzBZHI+oGQpQ3xdCcc9KkjWr8Vz0rpGMuJtiKDgvq57FEx7ELQ23kovF0nnECgYEA3wkjOStZD2TNgHJfglCmuKxSrjDJY3dWImrRhVqlq4DDq+VvClHjINZz3kIRi6WAXQmtZDFWn1meLVNZOhEkha0Kt7BWZr0wa1LnKe4GnseK+NqterhegNRrBnPBh6aD4wB1mfJSTsHH8jmGTulaOBQDXuiD1tfaLDwgdqZcGO8CgYEAynI+RwFFfTdY5p0/FXjV6YwOVoIMAq12CEM/uutqLLZO5iBFb/Y2UcBMOaEjddUUtLgxkN+O5XOqVRARxuj2S00bhSs88p8vBKnBaqcb++4R06GWMU+xPW4d6SqAaVC961N14IerUanBQwlepiUuRszJQjn9EOonxCJCQLaPV4kCgYBNHra8PN3lscBRKrsVr1eGwacxLhNkvk3dRrw99TMV00dVd9bmZZw4rY6RCp1QgSCaiw8Wduzx2qYUHB7dMjJDuqrbCzFFAyPr6FWgLPrDClJmbA7SQVAEEe8T4xDHG++8nVsMqTqGmwDNI9DqFkwo2dq0hYeY0ThSVVQQUsPDpwKBgD5rkTY1dyaPnTKmA2BsfJBhJb6YpHli4ITVr13D3l3g7FzaUG6gHMmCID6Yp6VaSOMb8/R2acnnqvpFVSXeiKpZIQYjecxZNpcafE+VLPS1x/tX1EZqCv0k6cYN28AdLCHV/v929WTdkYBI8E0MI8Oiut9Xn+bKO+6mnYT0hSf5AoGBAKozKiDMUro92jZ4K6mo5O+VrgkME62nGOjKKjWQm1Fq6u4KSv118bNoOG8GxwE82S/F+9j/fUNsazEPDhyoQLrGlJku0EADuNwkJt1TgCp539A1blAQ58slyikY29hUXisyE8Rm9TEKQ3+3P/vQdD+r4jFr625VGq8i3mjSHk9g";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1PEd/e34vxmNkPUwze/dTJalGcbOK6XuT6Wwro2B/isBwY0nT3oWeg2O4i4Dk2FdaJlhHz9dxhbskotXWfweRQNsP/97zj5b2vRhrLJ5WXmu5dqI/hilAPT1uQ8r8hZDeuWcZLnt7CISQPeZI9IrDhRkrHXnajGM7Z55iwPGVgIl7xrnQnf1CLngRRHm3WtxUWYg6PzPB9PTSARCxFeehfQSaRgV1VhH/DnQql+0AV4lk+8+dHvOxGbRPM0LsXvyLud/yktoBVFqWqFRQNNvK0G9QWS2rtYurbwW1az6kSGJDtlQZAGw+G0h+vkupUXJcMAtBQvwcMv8G6MDPYzo5wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/ssm_student02_war_exploded/WEB-INF/views/aliPay/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/ssm_student02_war_exploded/WEB-INF/views/aliPay/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
