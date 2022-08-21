package App.count.MC_Database_A031;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<StudentModel> {

    public StudentAdapter(@NonNull Context context, ArrayList<StudentModel> ListViewArray) {
        super(context, R.layout.listview, ListViewArray);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        StudentModel list = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview, parent, false);
        TextView Name = convertView.findViewById(R.id.Name);
        Button del = convertView.findViewById(R.id.delete);
        TextView Rollnum = convertView.findViewById(R.id.RollNum);
        TextView status = convertView.findViewById(R.id.Status);
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
        return convertView;
    }
}
