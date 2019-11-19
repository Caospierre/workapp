package pp.facerecognizer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pp.facerecognizer.Models.Person;
import pp.facerecognizer.Models.User;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
     private MaterialEditText txtNewUser,txtNewEmail,txtNewPass; //Para el Registro
     private MaterialEditText txtUser,txtPass; //Para logearse
     private Button btnSignUp,btnSignIn;
     private FirebaseDatabase database;
     private DatabaseReference users;
     public static ArrayList<Person> ListPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FIREBASE

        this.database=FirebaseDatabase.getInstance();

        this.users=database.getReference("Users");
        chargeListPerson();
        //MAPPING COMPONENTS
        this.txtUser=findViewById(R.id.et_User);
        this.txtPass=findViewById(R.id.et_Password);
        this.btnSignUp=findViewById(R.id.bt_sing_up);
        this.btnSignIn=findViewById(R.id.bt_sing_in);
        //ACCTION BUTTON
        this.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnSignInOnClick(v);
            }
        });
        this.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
                //metodo para Registrar USUARIOS (NOMBRE ,EMAIL, CONTRASEÑA)
                //btnSignUpClick(v);
            }
        });
    }
    private void btnSignInOnClick(View v)
    {
        signUpValidate();

    }

    private void btnSignUpClick(View v)
    {
        AlertDialog.Builder altDialog= new AlertDialog.Builder(StartActivity.this);
        altDialog.setTitle("Registro");
        altDialog.setMessage("Ingrese Informacion Completa");
        LayoutInflater inflater =this.getLayoutInflater();
        View singUpLayout =inflater.inflate(R.layout.sign_up_layout,null);
        txtNewUser=singUpLayout.findViewById(R.id.et_newuser);
        txtNewPass=singUpLayout.findViewById(R.id.et_newPassword);
        txtNewEmail=singUpLayout.findViewById(R.id.et_newEmail);
        altDialog.setView(singUpLayout);
        altDialog.setIcon(R.drawable.ic_account_circle_black_24dp);


        altDialog.setNegativeButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //System.out.println("tex "+txtNewUser.getText());
                //String us=
                final User user =new User(txtNewUser.getText().toString(),txtNewEmail.getText().toString(),txtNewPass.getText().toString(),0);
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getName()).exists())
                        {
                            Toast.makeText(StartActivity.this,"USUARIO YA REGISTRADO", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            users.child(user.getName()).setValue(user);
                            Toast.makeText(StartActivity.this,"USUARIO REGISTRADO EXITOSAMENTE", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                dialog.dismiss();
            }

        });

        altDialog.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


                altDialog.show();

    }
    private void signUpValidate()
    {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(txtUser.getText().toString()).exists())
                {
                    System.out.println(txtUser.getText().toString());
                    if(!txtUser.getText().toString().isEmpty())
                    {
                        User login=dataSnapshot.child(txtUser.getText().toString()).getValue(User.class);
                        if(login.getPassword().equals(txtPass.getText().toString()))
                        {
                            Intent intent =new Intent(StartActivity.this, TempActivity.class);
                            startActivity(intent);

                            Toast.makeText(StartActivity.this,"INGRESO EXITOSO", Toast.LENGTH_SHORT).show();
                            Toast.makeText(StartActivity.this,"Presione buscar Para Buscar Por Cedula", Toast.LENGTH_SHORT).show();
                            Toast.makeText(StartActivity.this,"Presione Agregar Para Agregar Paciente", Toast.LENGTH_SHORT).show();
                            Toast.makeText(StartActivity.this,"Presione Actualizar  para Actualizar Paciente", Toast.LENGTH_SHORT).show();
                            Toast.makeText(StartActivity.this,"Presione Frontal o Posterior para Registrar Rostro", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(StartActivity.this,"CONTRASEÑA INCORRECTA", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(StartActivity.this,"INGRESE USUARIO", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(StartActivity.this,"USUARIO NO EXISTE", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public  void chargeListPerson(){


        DatabaseReference firereference =database.getReference(Person.class.getSimpleName());
        firereference.child(Person.class.getSimpleName()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StartActivity.ListPerson= new ArrayList<Person>();
                StartActivity.ListPerson.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Person p =  objSnapshot.getValue(Person.class);
                    StartActivity.ListPerson.add(p);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
