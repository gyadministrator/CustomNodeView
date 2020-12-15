package com.android.custom.nodeview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.custom.nodeview.R;
import com.android.custom.nodeview.entity.NodeData;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: CustomNodeView
 * @Package: com.android.custom.nodeview.adapter
 * @ClassName: NodeAdapter
 * @Author: 1984629668@qq.com
 * @CreateDate: 2020/12/15 9:00
 */
public class NodeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private List<NodeData> traceList;
    private static final int TYPE_TOP = 0x0000;
    private static final int TYPE_NORMAL = 0x0001;
    private boolean isDivider;
    private Context context;

    public NodeAdapter(Context context, List<NodeData> traceList, boolean isDivider) {
        inflater = LayoutInflater.from(context);
        this.traceList = traceList;
        this.isDivider = isDivider;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_node, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;
        if (isDivider) {
            if (getItemViewType(position) == TYPE_TOP) {
                // 第一行头的竖线不显示
                itemHolder.tvTopLine.setVisibility(View.INVISIBLE);
                itemHolder.tvDot.setBackgroundResource(R.drawable.timelline_dot_first);
            } else if (getItemViewType(position) == TYPE_NORMAL) {
                itemHolder.tvTopLine.setVisibility(View.VISIBLE);
                itemHolder.tvDot.setBackgroundResource(R.drawable.timelline_dot_normal);
            }
        } else {
            itemHolder.tvTopLine.setVisibility(View.VISIBLE);
            itemHolder.tvDot.setBackgroundResource(R.drawable.timelline_dot_normal);
        }

        NodeData nodeData = traceList.get(position);
        if (nodeData != null) {
            int rowNum = nodeData.getRowNum();
            Map<Integer, String[]> rowContent = nodeData.getRowContent();
            if (rowNum == 0) {
                Toast.makeText(context, "行数不能为0", Toast.LENGTH_SHORT).show();
                return;
            }
            if (rowContent.size() != rowNum) {
                Toast.makeText(context, "行数和内容数量不一致", Toast.LENGTH_SHORT).show();
                return;
            }
            for (int i = 0; i < rowNum; i++) {
                String[] list = rowContent.get(i);
                LinearLayout linearLayout = new LinearLayout(context);
                if (list != null && list.length > 0) {
                    for (int j = 0; j < list.length; j++) {
                        String s = list[j];
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        @SuppressLint("InflateParams") TextView textView = (TextView) inflater.inflate(R.layout.row_content, null);
                        textView.setText(s);
                        textView.setLayoutParams(new LinearLayout.LayoutParams(0,
                                LinearLayout.LayoutParams.MATCH_PARENT, 1));
                        if (j == 0) {
                            textView.setGravity(Gravity.START);
                            if (i == 0) {
                                textView.setTextColor(Color.parseColor("#333333"));
                            } else {
                                textView.setTextColor(Color.parseColor("#666666"));
                            }
                        } else {
                            textView.setGravity(Gravity.END);
                            textView.setTextColor(Color.parseColor("#666666"));
                        }
                        if (j != 0 && i != 0) {
                            linearLayout.setPadding(0, 18, 0, 0);
                        }
                        if (i==rowNum-1){
                            linearLayout.setPadding(0, 18, 0, 20);
                        }
                        linearLayout.addView(textView);

                        if (linearLayout.getParent() != null) {
                            ViewGroup viewGroup = (ViewGroup) linearLayout.getParent();
                            viewGroup.removeView(linearLayout);
                        }
                        itemHolder.llContent.addView(linearLayout);
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return traceList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOP;
        }
        return TYPE_NORMAL;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llContent;
        private TextView tvTopLine, tvDot;

        ViewHolder(View itemView) {
            super(itemView);
            llContent = itemView.findViewById(R.id.ll_content);
            tvTopLine = itemView.findViewById(R.id.tvTopLine);
            tvDot = itemView.findViewById(R.id.tvDot);
        }
    }
}
