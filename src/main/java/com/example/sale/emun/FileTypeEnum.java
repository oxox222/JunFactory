package com.example.sale.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件类型枚举类
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

    FILE_TYPE_IMAGE((byte)1,"图片"),
    FILE_TYPE_EXCEL((byte) 2, "Excel"),
    FILE_TYPE_WORD((byte)3, "word"),
    FILE_TYPE_OTHERS((byte)9, "其他");

    private Byte code;

    private String type;
}
