package com.digger.common;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092000558785";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYI+W4W42x3JASJ/JOyWpzEAMw0WPeowK+AxOMIpySL9Mwme73wzvnh358GkZKL6MeNidCVadCNvFk4eG3sCFL+u+qlKwknFx9F9iNShnly9H7a+KF3GMWX5NYzoejCoanlAmQHFaPJuhZmA7NUBUS4e4V2UUGtiYGxcZuWggn1LqA6c80fJ0IF7Rpc5Y3zxfcZu++zOGDLghkqbZoZXOA2ArD0BOv3ZdtOPwnRlnFaHLrO6bf7nl6291rQg3Z9z+1fJ+gosmPeZFkauhq4bnXX6/NWfw4Wc585CeeCm2X7GeGyV4Q6DHazpYhtQPu4PGcbB24+P1YazRMSIvcgQuVAgMBAAECggEAOID8jF/UXD5ic5vKbmB33IwR40uhH8rC9fcGh4FDTTfd46sICZTE5tR3sXfAq0TN/CZ4pjZRjFIDjUgnIKWVeDaWVHArDt3Tq9HzZ2YiACqWrh3/X1/enSyZ+N2bmn4KTKq6cn9T1o7ItJ24waAAc6bdWdf7vuZfcRx6+fkOFlxjbzp7p898NO3v2PMdcsiNmnnhd0cYSOAHorzFeEiPrxywiYj+eRrHvUEsq5+e5Yv3c6vFUiyo6VK2wGt61lSC7WWCZILK2mjAD35ox0NZNCrSLVwYcoKfL+fB4Qa60be8WofD28r7dIXQ7qwziLFBenKNiHzv1zkOLNu4Y72sAQKBgQDjCZ9pC5fRjgOIeUglnOd6UA86uIpfrYwPniNmy5H8nNoRXLk/9y7iDVQvtqy/g3PCln99ehVCS6jT6vd7DgsfX/V4xrteLvQf2WMxJF3MQTOHaqIdIW9j+VxGWuC3MhIfTRyMtrjTt4cuHAYnPRNns8F3tuPP5HytwrD7sU331QKBgQCrjFkNJ/oqougoVPCZj9x2bsW494d3MOFzd21x7mEzaaWLne7m4jFpxfKT2K4FvGS2ZJqUMd7SEVVIJ0nS72/WGfOHK/is04vwQeKmF9DJSONvW1/Bp5tDZ0/9xyyd8zmtNo+C7+09lI/WMzvRU3jepG16WU+xw2+VaQ0t+0JkwQKBgQDFb3jRQOOluHoaFF5JV0l1dGnKBvFOgv1qF3txyVYK99wrfRccKTj8G9s6IwHXCdmBPp9f3urd3C3VHGBbArtpbWlqvmfyX6Ksj79S3O000v6cpH6WFp1ns1IrXJxOs5xO3CAXvk68zr0fu/8d4F8fr5JvKPyzrV8/PC/ssSO8gQKBgH0uMgFerbeVHtHngZadGuQ1WwTpY6m/OpT2jh+4MqfArPOfsKg8YJBtyODIGdzHp7pM2VvQtGcYDOJVARLyc+qJD2MQ3CrJR/FvMoO/FzT6gjRNOifnNW5PIljukYlE5k6WZMzIZZctJHTK+Bh8z9Ovqs/AAl6WXYNqoBpwofdBAoGAfhn+TagCdknNT2InX/QVz6WU+xZDANWu51GW8+W5/uABH/ZXNf4u/HjwVNkeFT2nKqVfpyxrtqAc09jj2ChfrP3r2R83KKXIrsg2swuyBKM1ucIC+dwR9JNJQ1SHZO7crO9FtczVrD2EUBiM4LDTkc+TMrhehUf0pBqWnh6lzFI=";
    
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0Mp+DZCcnAMlAx4nF4gg60CurvIdg83R/q9ohACW2Sa2x0WT+NdlWdwaXQ35/2/oviKtU1zdamrfKhVKR67ab8gFpFpuNIJqsrvjQoELogf6WW+JChbIxqv11rBaRNCPwNjoWNWGJlgJAVNhWboNhTKt3YJVH5zi5LsCd9zMgPUfT9LPgR0jQr/u6HGbk9tc1kOswYpqDgnnnXLEWqhXrGX+0KwbkeMfHgXWeXeNiDTBbi4mARZLdoCQGnX5GTSPqgFfDPLTsK1FdBpYdG3eWLi2XHHsgXdHOG/VNA3EMc0248vv7ZVAythrx04Qz2z6j4Fua1oDn29qiyfwY+x6gwIDAQAB";
    
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/order/alipayNotifyNotice";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/order/alipayReturnNotice";

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

