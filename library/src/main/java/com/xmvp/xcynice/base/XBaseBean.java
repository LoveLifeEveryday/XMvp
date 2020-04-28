package com.xmvp.xcynice.base;

import java.io.Serializable;

/**
 * Description : XBaseBean 实体类的基类
 *
 * @author XuCanyou666
 * @date 2020/2/7
 */


public class XBaseBean<T> implements Serializable {


    /**
     * data :
     * errorCode : 0
     * errorMsg :
     */

    public int errorCode;
    public String errorMsg;
    public T data;

    public XBaseBean(int code, String data) {
        this.errorCode = code;
        this.data = (T) data;
    }
}
