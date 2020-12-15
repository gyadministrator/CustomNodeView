package com.android.custom.nodeview.entity;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: CustomNodeView
 * @Package: com.android.custom.nodeview.entity
 * @ClassName: NodeData
 * @Author: 1984629668@qq.com
 * @CreateDate: 2020/12/15 9:01
 */
public class NodeData {
    //显示的行数
    private int rowNum;
    //行内容
    private Map<Integer, String[]> rowContent;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public Map<Integer, String[]> getRowContent() {
        return rowContent;
    }

    public void setRowContent(Map<Integer, String[]> rowContent) {
        this.rowContent = rowContent;
    }

    public NodeData(int rowNum, Map<Integer, String[]> rowContent) {
        this.rowNum = rowNum;
        this.rowContent = rowContent;
    }

    @NonNull
    @Override
    public String toString() {
        return "NodeData{" +
                "rowNum=" + rowNum +
                ", rowContent=" + rowContent +
                '}';
    }
}
