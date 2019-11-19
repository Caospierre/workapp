package app.recognizerface.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.recognizerface.Interface.ItemClickListener;
import app.recognizerface.R;

public class MapsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView mapName;
    public ImageView mapImg;
    private ItemClickListener itemClickListener;
    public MapsViewHolder(View itemView) {
        super(itemView);
        this.mapImg=(ImageView) itemView.findViewById(R.id.img_map);
        this.mapName=itemView.findViewById(R.id.name_map);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
                itemClickListener.Onclick(v,getAdapterPosition(),false);
    }
}
