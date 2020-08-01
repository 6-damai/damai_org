package com.alipay.config;

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
	public static String app_id = "2016102500760523";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCwDdmUj9P98LZRQgbKtFwt5dBwMnbftKU6cKoSXEnCDUbg3dmrQd5jJ41NjXPQU+czFGhprsCtKJLLLDJThRCTeC+qv/bhvfWyi1BsgqGt6dAOk0HfAlVifBA0u5SB0eCeU+Z5nMO4GuJHH2R0wvn/C3umnUg1seBDxIBtbxU5CGaqCjC5TfmUUxKOI7aSVfIeSgmpIO9j06y20WhjukanNUWSmG9RGwERGC0vBh3AS89/F58eA9/u+9Y9zqQeTzcmgqEUtVnBP4oovn1xIjvtf4pGVYHUmtg8iwFxUUi+dJKyYBuOnz5X4wPaAk+rG4A74qitVQMqwNr9FnTBrZ6fAgMBAAECggEAK3yATg3o00TuA8xHWTKLwlqNzejpLAC2/2Rs3fQl0+TrqQejiIXQpLVUVd+mx8KxzRAxaklcFml7OOxF0hOAMKzzOT4JgWEmm1+GEgeCYC7s0WzWTHuLE7SJXD0pRZfN383hlBCA16zsT50VdzHiBnmTpWgcOgch2htjjaRunZNnhqmMwspJMV8/CQgCEODzRqByh2zkzPti5gxMi6gYfwm/4jDVNDFUJYG1I8e3L4+AbpoPitdtNVnK+pY1QrQU4/8VBFT5zTFJEVIpSCVZ1Qdj7oBto37yNLMSrPn/oOcfNgdEdOSawL8ifAhnlr1k5t8oWxpCeV6FVmIoVWmSQQKBgQD3wh3BiAzxNQ6yGdIGVJX7k0BFqpSk4vLh20EJXUsH8VkZKQ/EAFL8GJXHpRB3AW17Zz12XcHnOwlxqPTN5J/xVPQtEenXWn9cUicE/KWJnqqRHaja9SxeFR+IE+JqqWKDVrgBZVms6HG8V+gLJ/6ZqdO+pvAF8Tk3f+SRO4KGxwKBgQC16RvCbtFihLVImilofnogC9VOt+Y9EDcDYvIwN0I0KQPaPiDf3Z0wclAAnvKBPuu9MU4jXCN7IEIXVsb9HSJ4O6NwOYs08NrGPuoK68+YRZO9iobips66HLWOvrYVw/7Dz7GNKM/qiu3oQbmN1xovsO015bZwW5wHGqYclqXxaQKBgBuAreLs4lroQ+BbY2gWfau2ldzl6ahAFSwjNZ3/gDO6g+R6HWjpjEy1wfKujRxYyTl4pWb9U2gCsrKuSbaNWX/lqVFTerzQK/EooXI5RXr4ZQDE5RihPLKY764gXvLuKKRhvP1Srdi22OFKdNTANNAD/SBd0aknNO3HQa0piPzDAoGBAIeez09aB0kH34/NP2nkQXB64gDaIhEMcbdYoW9JA1aX7YNcJm5AbtybnQGZfvrUyjQ2+iZqnxlAp4V28MvsWLKC5ArbG+nQV4rNhPe3bwH7fXYmPQHlkuI2XTWbAW2gveOGiLzQ3JHkGhK6TXhuEdvUcEVqiWWjJuQ0/fr6ZaVhAoGBALOwYmm/0qB4mlKSiwixsiBfMU2gfm4skj49UBf+uzYJUsr1jQhH2K/9bGBt6sGVwGS+j8AhfgQJxqplw569NjNNqEHlJWRMPenCm0gugLM25vNCh0xhLExwlzFtDFgr5DoWVrsgb/wSdQeyYWnniwjtJ1ReMRNSUxkFeRwoJmp/";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsAG9aepvFYuqCAZ/ZK8Q+XAP8G5ByMfzVGxHasPfcKEszlWVrE6atRV5MqByPhyIbCfPACTZIHZID3fRdaHjQIxzBBspzkND4VuyTZGoMROW2vv0VRchp49jKK3iIuC1kdzFyQI9zMrwEdMXCFsC06s1JeUgSOcGWgQ0YizKhSEynbe+rSRMB8rvqbN8ksjXTcS2s5HY4kNCxvCgB8nfX5+OxFmyCO4tiuhzOsC8TWHMgZeykIbijKMYbtRInt7eOxk8fgG1J1u24ypVjr9hMf3BY5kzcC5KTGkU97U7c40CHRyA9uWjG549kL+tgtm5lqylHU/RehJd7KsTwoTs8QIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8081/alipay1/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8081/alipay1/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = " https://openapi.alipaydev.com/gateway.do";
	
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

