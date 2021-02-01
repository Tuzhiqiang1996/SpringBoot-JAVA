package com.example.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tu
 * @Package com.example.common.lang
 * @date 2021/1/29-18:05
 */

/**
 * 统一结果封装
 * 这里我们用到了一个Result的类，这个用于我们的异步统一返回的结果封装。一般来说，结果里面有几个要素必要的
 * <p>
 * 是否成功，可用code表示（如200表示成功，400表示异常）
 * 结果消息
 * 结果数据
 */
@Data
public class Result implements Serializable {
    /**
     * 状态码 200/！200
     * msg 信息
     * 返回数据
     */
    private String code;
    private String msg;
    private Object data;

public static Result succ(String code,String msg,Object data){
    Result m=new Result();
    m.setCode(code);
    m.setData(data);
    m.setMsg("操作成功");
    return m;

}
    public static Result succ(Object data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg("操作成功");
        return m;
    }

    public static Result succ(String mess, Object data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }

    public static Result fail(String mess) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(null);
        m.setMsg(mess);
        return m;
    }

    public static Result fail(String mess, Object data) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }
}



