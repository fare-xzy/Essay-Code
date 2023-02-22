package com.xzy.essay.java.spi;

/**
 * @author Xuziyu(xuziyu @ bjca.org.cn)
 * @date 2022/11/25 10:17
 * @apiNote
 * @since
 */
public interface IBase64 {
    /**
     * Base64编码接口
     * @param binaryData 待编码数据
     * @return
     */
    String encodeBase64String(byte[] binaryData);

    /**
     * Base64解码接口
     * @param base64String base64字符串
     * @return
     */
    byte[] decodeBase64(String base64String);
}
