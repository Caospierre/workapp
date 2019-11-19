package pp.facerecognizer;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import pp.facerecognizer.Models.Person;
import pp.facerecognizer.Models.PersonAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TempActivity extends AppCompatActivity {

    ListView listV_dados,lv;
    FirebaseDatabase firebaseDatabase;
    EditText edtNome,edtci, edtTelf,edtdireccion,edtsangre,edtenfermedades,edtmedicamento;
    DatabaseReference databaseReference;
    private List<Person> listPessoa = new ArrayList<Person>();
    private Classifier classifier;
    private ArrayAdapter<Person> arrayAdapterPessoa;
    private int selectOption=0;
    private boolean search=false;
    Person pessoaSelecionada;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        edtci = findViewById(R.id.edtci);
        edtTelf = findViewById(R.id.editTelfxd);
        edtNome = findViewById(R.id.editNamexd);
        edtdireccion = findViewById(R.id.edtDir);
        edtsangre = findViewById(R.id.edtBlood);
        edtenfermedades = findViewById(R.id.edtsick);
        edtci=findViewById(R.id.edtci);
        edtmedicamento = findViewById(R.id.edtmed);
        listV_dados =  findViewById(R.id.listV_datos);
        inicializarFirebase();
        eventoDatabase();

        this.bottomNavigationView=findViewById(R.id.navigationxd);
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.menu_novo){
                    if(!(edtTelf.getText().toString().isEmpty()&& edtNome.getText().toString().isEmpty()))
                    {

                        Person p = new Person();
                        p.setUid(UUID.randomUUID().toString());
                        p.setCedula(edtci.getText().toString());
                        p.setNome(edtNome.getText().toString());
                        p.setTelf(edtTelf.getText().toString());
                        p.setDir(edtdireccion.getText().toString());
                        p.setBloodtype(edtsangre.getText().toString());
                        p.setSickness(edtenfermedades.getText().toString());
                        p.setTakeMedicine(edtmedicamento.getText().toString());
                        databaseReference.child(p.getClass().getSimpleName()).child(p.getUid()).setValue(p);
                       // classifier=new Classifier();
                        //int idx = classifier.addPerson(edtNome.getText().toString());

                        limparCampos();

                    }else
                    {
                        Toast.makeText(TempActivity.this,"CAMPOS VACIOS", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                    if(id == R.id.menu_atualiza)
                    {
                        if(!(edtTelf.getText().toString().isEmpty()&& edtNome.getText().toString().isEmpty()))
                        {
                            Person p = new Person();
                            p.setUid(pessoaSelecionada.getUid());
                            p.setCedula(edtci.getText().toString());
                            p.setNome(edtNome.getText().toString().trim());
                            p.setTelf(edtTelf.getText().toString().trim());
                            p.setDir(edtdireccion.getText().toString());
                            p.setBloodtype(edtsangre.getText().toString());
                            p.setSickness(edtenfermedades.getText().toString());
                            p.setTakeMedicine(edtmedicamento.getText().toString());
                            databaseReference.child(p.getClass().getSimpleName()).child(p.getUid()).setValue(p);
                            limparCampos();
                            selectOption=0;

                        }
                        else
                        {

                        }

                }else
                    if (id == R.id.menu_campost){
                        int mode=0;
                        ///Eliminar Funcionalidad Incompleta
                        if(!(edtTelf.getText().toString().isEmpty()&& edtNome.getText().toString().isEmpty())&&mode==1)
                        {
                            Person p = new Person();
                            p.setUid(pessoaSelecionada.getUid());

                            databaseReference.child(p.getClass().getSimpleName()).child(p.getUid()).removeValue();
                            limparCampos();
                        }
                        else
                        {
                            MainActivity.ModeCamera=0;
                            Intent intent =new Intent(TempActivity.this, MainActivity.class);

                            startActivity(intent);
                            Toast.makeText(TempActivity.this,"Presione + Para Agregar Rostros", Toast.LENGTH_SHORT).show();
                            Toast.makeText(TempActivity.this,"Presione Camara Para Tomar Fotos", Toast.LENGTH_SHORT).show();
                            Toast.makeText(TempActivity.this,"Presione Botones Volumen para ver DATA STATUS", Toast.LENGTH_SHORT).show();
                        }
                }
                    else
                    if(id == R.id.menu_search)
                    {
                        databaseReference.child(Person.class.getSimpleName()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                                    Person p1= objSnapshot.getValue(Person.class);
                                    if(edtci.getText().toString().equals(p1.getCedula())){

                                        edtNome.setText(p1.getNome());
                                        edtci.setText(p1.getCedula());
                                        edtNome.setEnabled(false);
                                        edtTelf.setText(p1.getTelf());
                                        edtdireccion.setText(p1.getDir());
                                        edtenfermedades.setText(p1.getSickness());
                                        edtsangre.setText(p1.getBloodtype());
                                        edtmedicamento.setText(p1.isTakeMedicine());
                                        Toast.makeText(TempActivity.this,"Paciente Encontrado", Toast.LENGTH_SHORT).show();
                                        continue;
                                    }




                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }else
                    {
                        if(id==R.id.menu_camfront){
                            MainActivity.ModeCamera=1;
                            Intent intent =new Intent(TempActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(TempActivity.this,"Presione + Para Agregar Rostros", Toast.LENGTH_SHORT).show();
                            Toast.makeText(TempActivity.this,"Presione Camara Para Tomar Fotos", Toast.LENGTH_SHORT).show();
                            Toast.makeText(TempActivity.this,"Presione Botones Volumen para DATA STATUS", Toast.LENGTH_SHORT).show();
                        }
                    }

                return true;
            }

        });
        listV_dados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pessoaSelecionada = (Person)parent.getItemAtPosition(position);
                selectOption=1;
                edtNome.setText(pessoaSelecionada.getNome());
                edtci.setText(pessoaSelecionada.getCedula());
                edtNome.setEnabled(false);
                edtTelf.setText(pessoaSelecionada.getTelf());
                edtdireccion.setText(pessoaSelecionada.getDir());
                edtenfermedades.setText(pessoaSelecionada.getSickness());
                edtsangre.setText(pessoaSelecionada.getBloodtype());
                edtmedicamento.setText(pessoaSelecionada.isTakeMedicine());
            }
        });


    }

    private void eventoDatabase() {
        databaseReference.child(Person.class.getSimpleName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPessoa.clear();

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Person p = objSnapshot.getValue(Person.class);


                    listPessoa.add(p);
                }
                arrayAdapterPessoa = new ArrayAdapter<Person>(TempActivity.this,
                        android.R.layout.simple_list_item_1,listPessoa);

                final PersonAdapter adp = new PersonAdapter(TempActivity.this, arrayAdapterPessoa);
                listV_dados.setAdapter(adp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void eventoFindCi() {
        databaseReference.child(Person.class.getSimpleName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPessoa.clear();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(TempActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference(Person.class.getSimpleName());
    }


    private void limparCampos() {
        edtTelf.setText("");
        edtNome.setText("");
        edtNome.setEnabled(true);
        edtdireccion.setText("");
        edtenfermedades.setText("");
        edtsangre.setText("");
        edtmedicamento.setText("");
    }
}
