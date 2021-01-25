package com.example.sale.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作结果返回枚举类
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    OPERATION_SUCCESS("201", "操作成功"),
    OPERATION_FAILED("202", "操作失败"),
    PARAMETER_ERROR("203", "参数错误"),
    UNIMPLEMENTED_INTERFACE_ERROR("204", "未实现的接口"),
    INTERNAL_SYSTEM_ERROR("205", "系统内部错误"),
    THIRD_PARTY_INTERFACE_ERROR("206", "第三方接口错误"),
    SESSION_TIMEOUT_ERROR("401", "Session过去,需重新验证"),
    PERMISSIONS_ERROR("402", "业务权限认证失败"),
    AUTHENTICATION_ERROR("403", "认证失败");

    private String statusCode;

    private String message;
}
