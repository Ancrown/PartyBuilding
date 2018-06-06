package zhuri.com.partybuilding.entity;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述: 接口实体规范
 */

public class BaseEntity<T> {

    /**
     * msg : 响应提示
     * status : 响应状态0，1，2，3~~~~
     * data : {}
     */

    private String msg;
    private String status;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
