package App.count.MC_Database_A031;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<StudentModel> {
    int click=0;
    public StudentAdapter(@NonNull Context context, ArrayList<StudentModel> ListViewArray) {
        super(context, R.layout.listview, ListViewArray);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        StudentModel list = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview, parent, false);
        TextView Name = convertView.findViewById(R.id.Name);
        TextView EditName = convertView.findViewById(R.id.EditName);
        Button del = convertView.findViewById(R.id.delete);
        Button Update = convertView.findViewById(R.id.Edit);
        Button Cancel= convertView.findViewById(R.id.cancel);
        TextView Rollnum = convertView.findViewById(R.id.RollNum);
        TextView EditRollnum = convertView.findViewById(R.id.EditRollNum);
        TextView status = convertView.findViewById(R.id.Status);
        Switch EditStatus = convertView.findViewById(R.id.EditSwitch);
        ImageView img = convertView.findViewById(R.id.img);
        Name.setText(list.getName());
        String roll=Integer.toString(list.getRollNmber());
            Rollnum.setText(Integer.toString(list.getRollNmber()));
        img.setImageResource(R.drawable.profile);
        if (list.isEnroll()) {
            status.setText("Enrolled");
        } else {
            status.setText("Not Enrolled");
        }
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("sgasjcsachshcasjcksklcl",getContext().toString());
                Log.d("sgasjcsasjcksklclhsdjh",StudentAdapter.this.toString());
                Log.e("roll",Rollnum.toString());
                DBHelper dbHelper = new DBHelper(getContext());
                Log.d("roll",roll);
                dbHelper.Delete(roll);
                remove(list);
            }
        });
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click+=1;
                if(click==1){
                    Name.setVisibility(View.INVISIBLE);
                    Rollnum.setVisibility(View.INVISIBLE);
                    status.setVisibility(View.INVISIBLE);
                    del.setVisibility(View.INVISIBLE);
                    EditName.setVisibility(View.VISIBLE);
                    EditRollnum.setVisibility(View.VISIBLE);
                    EditStatus.setVisibility(View.VISIBLE);
                    Cancel.setVisibility(View.VISIBLE);
                    EditName.setText(list.getName());
                    EditRollnum.setText(roll);
                }
                else if(click==2){
                    Name.setVisibility(View.VISIBLE);
                    Rollnum.setVisibility(View.VISIBLE);
                    status.setVisibility(View.VISIBLE);
                    del.setVisibility(View.VISIBLE);
                    EditName.setVisibility(View.INVISIBLE);
                    EditRollnum.setVisibility(View.INVISIBLE);
                    EditStatus.setVisibility(View.INVISIBLE);
                    Cancel.setVisibility(View.INVISIBLE);
                    Name.setText(EditName.getText().toString());
                    StudentModel model;
                    if (EditStatus.isChecked()) {
                        status.setText("Enrolled");
                         model= new StudentModel(Name.getText().toString(),Integer.valueOf(Rollnum.getText().toString()),true);
                    } else {
                        status.setText("Not Enrolled");
                         model= new StudentModel(Name.getText().toString(),Integer.valueOf(Rollnum.getText().toString()),false);
                    }

                    DBHelper db = new DBHelper(getContext());
                    db.Update(model);
                    click=0;
                }
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Name.setVisibility(View.VISIBLE);
                    Rollnum.setVisibility(View.VISIBLE);
                    status.setVisibility(View.VISIBLE);
                    del.setVisibility(View.VISIBLE);
                    EditName.setVisibility(View.INVISIBLE);
                    EditRollnum.setVisibility(View.INVISIBLE);
                    EditStatus.setVisibility(View.INVISIBLE);
                    Cancel.setVisibility(View.INVISIBLE);
                    click=0;
            }
        });
        return convertView;
    }
}
