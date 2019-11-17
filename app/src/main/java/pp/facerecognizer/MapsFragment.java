package pp.facerecognizer;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import pp.facerecognizer.Interface.ItemClickListener;
import pp.facerecognizer.Models.Map;

import com.squareup.picasso.Picasso;


public class MapsFragment extends Fragment {
    View myFragment;
    RecyclerView listMaps;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    private DatabaseReference maps;
    FirebaseRecyclerAdapter<Map,MapsViewHolder> mapAdapter;
    public static MapsFragment newInstance(){
        MapsFragment mapsFragment=new MapsFragment();
        return  mapsFragment;
    }
    //0

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FIREBASE
        this.database=FirebaseDatabase.getInstance();
        this.maps=database.getReference("Maps");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment=inflater.inflate(R.layout.fragment_maps,container,false);
        this.listMaps=(RecyclerView)this.myFragment.findViewById(R.id.list_Maps);
        this.listMaps.setHasFixedSize(true);
        this.layoutManager= new LinearLayoutManager(container.getContext());
        this.listMaps.setLayoutManager(this.layoutManager);
        loadMaps();
        return myFragment;

    }

    private void loadMaps() {
        this.mapAdapter = new
                FirebaseRecyclerAdapter<Map, MapsViewHolder>(Map.class,R.layout.map_layout,
                                                             MapsViewHolder.class,maps) {
            @Override
            protected void populateViewHolder(final MapsViewHolder viewHolder, Map model, int position) {
                    viewHolder.mapName.setText(model.getName());
                System.out.printf("sss"+viewHolder.mapName.getText().toString());
                    Picasso.with(getActivity()).load(model.getImage()).into(viewHolder.mapImg);

                System.out.println("CHARGED ");
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void Onclick(View view, int position, boolean isLongClick) {
                        Toast.makeText(getActivity(), String.format("%s|%s",mapAdapter.getRef(position).getKey(),viewHolder.mapName), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        mapAdapter.notifyDataSetChanged();
        listMaps.setAdapter(mapAdapter);
    }
}
