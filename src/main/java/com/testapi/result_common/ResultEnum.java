package com.testapi.result_common;

public enum ResultEnum {
    SUCCESS(200, "操作成功"),
    SUCCESS_MESSAGE(200,"发送成功"),
    SUCCESS_MESSAGE_SKU(0,"success"),
    SUCCESS_MESSAGE_REGISTER(0,"注册成功"),
    FAIL(-99, "失败"),
    UNKONW_ERROR(-1, "未知错误"),
    MOBILEPARAM_ERROR(-2, "手机号参数为空"),
    CNIDPARAM_ERROR(-3, "身份证参数为空"),
    PARAM_ERROR(-4, "参数错误"),
    SERVER_ERROR(500, "服务器异常"),
    ADD_FAIL(400, "添加失敗"),
    ADD_ERROR(400, "添加异常"),
    ADD_SUCCESS(0, "添加成功"),
    ADD_EXSIST(400, "记录已存在，不能重复添加"),
    DEL_SUCCESS(0, "删除成功"),
    DEL_FAIL(400, "删除失败"),
    DEL_RESTRICT(400, "该记录下有数据，不允许删除"),
    UPDATE_SUCCESS(0, "更新成功"),
    UPDATE_FAIL(400, "修改失败"),
    UPDATE_EXSIST(400, "记录已存在，修改失败"),
    FIND_SUCCESS(0, "查询成功"),
    FIND_ERROR(400, "查询失敗"),
    IMPORT_SUCCESS(0, "导入成功"),
    IMPORT_FAIL(400, "导入失败"),
    PROCESS_SUCCESS(0, "操作成功"),
    PROCESS_FAIL(400, "操作失败"),
    UPLOAD_SUCCESS(0, "上传成功"),
    UPLOAD_FAIL(400, "上传失败"),
    RUN_FAIL_NO_CASE(400, "无可运行的case");


    private Integer code;
    private String msg;
    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
