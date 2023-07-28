package com.testapi.testdemo;

import com.welab.iam.service.verify.impl.Signature;

import static com.welab.iam.service.verify.impl.Signature.MD5;

public class test {
    public static void main(String[] args) {
        String cipherData = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArSYgIwg7ydRL/Cmip6bJw4QRDjphcIJYOjMjHwJ2dMM75GOcGzf7hF7afRxoujVGxTXaGKwEM5Tmhw5LVL3H8W3HBYksmiKets9p4FjFwkZNJ1FXNSJiw9rv1OBqRjyjN6aJ+RK50icUUtsvNh/Mq1H1Tkk85ioCyo3NoELphe1a33UOQ/mRPAGcdB3k2tJ4ZNa2C8aOSn1FaRrmqYqRek4magyo6T8AU486rEQuVRiPH1LoQFp5bOG3xrthA1e3u1eKE3mir0hVVYzWHKflfm5VtQafegcV+tCA/aRP5jWEG1s5KWO9Wi2n7uI+4ldPrkPHeJKzhR9sgSuoG88suQIDAQAB";
        String mobile = "13843210710";
        String code = "123456";
        String salt = "dev#cctvj%salt";
        String timestamp = String.valueOf(System.currentTimeMillis());
        System.out.println("timestamp===" + timestamp);
        String oriData = mobile + "&" + timestamp + "&" + salt;
        String sign = MD5(oriData);
        System.out.println("timestamp=" + timestamp + "&" + "sign=" + sign);

        boolean verify = Signature.verify(sign, cipherData);
        System.out.println("fasdfa:::" + verify);


        String oriDatass = code + "&" + timestamp + "&" + salt;
        String s = Signature.MD5(Signature.MD5(oriDatass) + salt);
        System.out.println("s"+s);


    }
}
