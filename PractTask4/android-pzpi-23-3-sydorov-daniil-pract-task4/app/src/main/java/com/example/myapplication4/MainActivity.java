 package com.example.myapplication4;

 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import java.util.List;

 public class MainActivity extends AppCompatActivity {
     private EditText etName, etAge;
     private Button btnAddUser;
     private RecyclerView rvUsers;
     private UserDatabaseHelper dbHelper;
     private UserAdapter userAdapter;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         etName = findViewById(R.id.etName);
         etAge = findViewById(R.id.etAge);
         btnAddUser = findViewById(R.id.btnAddUser);
         rvUsers = findViewById(R.id.rvUsers);

         dbHelper = new UserDatabaseHelper(this);

         // Налаштування RecyclerView
         rvUsers.setLayoutManager(new LinearLayoutManager(this));
         userAdapter = new UserAdapter(dbHelper.getAllUsers());
         rvUsers.setAdapter(userAdapter);

         btnAddUser.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String name = etName.getText().toString();
                 int age = Integer.parseInt(etAge.getText().toString());

                 dbHelper.addUser(name, age);

                 // Оновлення списку
                 userAdapter.updateData(dbHelper.getAllUsers());
             }
         });
     }
 }