package com.bwie.week2.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.week2.R;
import com.bwie.week2.model.bean.ShopBean;
import com.bwie.week2.util.NetUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@auther:王彦敏
 *@Date: 2019/12/30
 *@Time:14:19
 *@Description:
 * */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<ShopBean.ResultBean> list;

    public MyAdapter(List<ShopBean.ResultBean> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ShopBean.ResultBean resultBean = list.get(position);
        holder.tvName.setText(resultBean.getCommodityName());
        holder.tvPrice.setText(resultBean.getPrice()+"");
        NetUtil.getInstance().getPhotoGet(resultBean.getMasterPic(),holder.imgTu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTagClickListner.onTagClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_tu)
        ImageView imgTu;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnTagClickListner(MyAdapter.onTagClickListner onTagClickListner) {
        this.onTagClickListner = onTagClickListner;
    }

    onTagClickListner onTagClickListner;
    public interface onTagClickListner{
        void onTagClick(int position);
    }

}
