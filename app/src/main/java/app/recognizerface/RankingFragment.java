package app.recognizerface;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class RankingFragment extends Fragment {
    View myFragment;
    public static RankingFragment newInstance(){
        RankingFragment rankFragment=new RankingFragment();
        return  rankFragment;
    }
    //0

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment=inflater.inflate(R.layout.fragment_ranking,container,false);
        return myFragment;

    }
}
