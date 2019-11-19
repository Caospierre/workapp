package pp.facerecognizer.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import pp.facerecognizer.R;

public class PersonAdapter extends BaseAdapter {
    Context myContext,tempContext;
    ArrayAdapter<Person> listEmp;
    public static ArrayAdapter<Person> listTem;
    ImageButton btnCall;
  //  Persona person;
    int []ind;
    int i=0;
    public PersonAdapter(Context myContext , ArrayAdapter<Person> listEmp) {
        this.myContext = myContext;
        this.listEmp = listEmp;
        PersonAdapter.listTem=listEmp;
        this.ind=new int[listEmp.getCount()];
    }
    @Override
    public int getCount() {
        return this.listEmp.getCount();
    }
    @Override
    public Person getItem(int position) {
        return listEmp.getItem(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if (convertView == null)
        {
            convertView= LayoutInflater.from(myContext).inflate(R.layout.person_layout,parent,false);
        }
        TextView txtnombre=convertView.findViewById(R.id.et_itpNombre);
        TextView txtDir=convertView.findViewById(R.id.et_itpDir);
        TextView txtTelf=convertView.findViewById(R.id.et_itpTelf);
        TextView txtBlood=convertView.findViewById(R.id.et_itpblood);
        TextView txtSick=convertView.findViewById(R.id.et_itpsick);
        TextView txtMedicine=convertView.findViewById(R.id.et_itpmed);
        TextView txtci=convertView.findViewById(R.id.et_itpCi);
        final Person person1 =listEmp.getItem(position);
        this.ind[i]=i;
        txtDir.setText(person1.getDir());
        txtnombre.setText(person1.getNome());
        txtTelf.setText(person1.getTelf());
        txtSick.setText(person1.getSickness());
        txtMedicine.setText(person1.isTakeMedicine());
        txtBlood.setText(person1.getBloodtype());
        txtci.setText(person1.getCedula());
        return convertView;
    }
}

