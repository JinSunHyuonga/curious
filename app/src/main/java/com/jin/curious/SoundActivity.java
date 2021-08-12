package com.jin.curious;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jin.curious.databinding.ActivitySoundBinding;

import java.util.ArrayList;

public class SoundActivity extends AppCompatActivity {

    private ActivitySoundBinding binding;
    private ArrayList<UserDTO> array = new ArrayList<>();
    private ArrayList<String> uids = new ArrayList<>();
    public String ipValue = "";
    String shared = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sound);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sound);

        refreshIP();

        FirebaseFirestore.getInstance().collection("users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {//task is succesful
                            array.clear();
                            uids.clear();
                            for (QueryDocumentSnapshot document : task.getResult()){//never enter in this loop
                                Toast.makeText(SoundActivity.this, "data get :" + document.get("email").toString(), Toast.LENGTH_SHORT).show();
                                array.add(document.toObject(UserDTO.class));
                                uids.add(document.get("email").toString());
                            }
                            binding.peopleListRecyclerview.getAdapter().notifyDataSetChanged();
                            Toast.makeText(SoundActivity.this, "Get Firebase data", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SoundActivity.this, "Error Firebase data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        binding.peopleListRecyclerview.setAdapter(new RecyclerviewAdapter());
        binding.peopleListRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        if (ipValue.equals("")) {
            Toast.makeText(SoundActivity.this, "No Ip Auto start", Toast.LENGTH_SHORT).show();
            startService(new Intent(getApplicationContext(), VideoService.class));
        }
    }

    public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>{

        @NonNull
        @Override
        public RecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerviewAdapter.ViewHolder holder, int position) {
            String channelId;
            channelId = array.get(position).getEmail();
            holder.itemEmail.setText((CharSequence) channelId);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openVideoActivity("curious"+channelId.substring(0,channelId.indexOf("@")));
                }
            });
        }

        @Override
        public int getItemCount() {
            return array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView itemEmail;
            public ViewHolder(View view){
                super(view);
                itemEmail = view.findViewById(R.id.item_Email);
            }
        }

        public void openVideoActivity(String channelId) {
            Intent intent;
            intent = new Intent(getApplicationContext(),VidioActivity.class);
            intent.putExtra("channelId",channelId);
            startActivity(intent);
        }

    }

    private void refreshIP() {
        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        ipValue = sharedPreferences.getString("ip","");
    }
}