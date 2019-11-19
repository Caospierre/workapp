package pp.recognizerface;


import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.FragmentTransaction;
public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button btncrud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.btncrud=findViewById(R.id.bt_crud);
        this.btncrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCrudOnClick(v);
            }
        });
        this.bottomNavigationView=findViewById(R.id.navigationxd);
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                androidx.fragment.app.Fragment selectedFragment=null;
                switch (item.getItemId())
                {
                    case R.id.action_map:
                    {

                        selectedFragment= MapsFragment.newInstance();
                    }break;
                    case R.id.action_ranking:
                    {
                       selectedFragment= RankingFragment.newInstance();
                    }break;

                }
                FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_layout,selectedFragment);
                transaction.commit();
                return true;

            }

        });
        setDefaultFragment();
    }

    private void btnCrudOnClick(View v) {

        Intent intent = new Intent(this, TempActivity.class);
        startActivity(intent);

    }

    private void setDefaultFragment() {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_layout,MapsFragment.newInstance());
        transaction.commit();

    }
}
