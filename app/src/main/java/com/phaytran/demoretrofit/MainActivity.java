package com.phaytran.demoretrofit;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.phaytran.demoretrofit.adapter.ListTodoAdapter;
import com.phaytran.demoretrofit.databinding.ActivityMainBinding;
import com.phaytran.demoretrofit.databinding.DialogInsertBinding;
import com.phaytran.demoretrofit.model.AddTodo;
import com.phaytran.demoretrofit.model.AddTodoRes;
import com.phaytran.demoretrofit.model.Todo;
import com.phaytran.demoretrofit.pojo.MultipleResource;
import com.phaytran.demoretrofit.pojo.User;
import com.phaytran.demoretrofit.pojo.UserList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ListTodoAdapter.OnIconClicked {

    private ActivityMainBinding binding;
    private DialogInsertBinding dialogInsertBinding;
    TextView responseText;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
      apiInterface = APIClient.getClient().create(APIInterface.class);

      binding.btnInsert.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              dialogInsertBinding = DialogInsertBinding.inflate(LayoutInflater.from(MainActivity.this));
              Dialog dialog = new Dialog(MainActivity.this);
              dialog.setContentView(dialogInsertBinding.getRoot());
              dialog.show();
              dialogInsertBinding.btnAddTodo.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      addTodo(new AddTodo(dialogInsertBinding.edtTodoName.getText().toString(), dialogInsertBinding.edtPriority.getText().toString(), dialogInsertBinding.edtDesc.getText().toString(), "2020-01-20"));
                      Log.e("Data:: ",""+dialogInsertBinding.edtTodoName.getText().toString()+"\n"+dialogInsertBinding.edtPriority.getText().toString()+"\n"+dialogInsertBinding.edtDesc.getText().toString());
                  }
              });

          }
      });


/*        *//**
         GET List Resources
         **/

        Call<List<Todo>> call = apiInterface.getAllTodo();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful()) {
                    Log.d("TAG",response.code()+"");
                    List<Todo> todoList = response.body();
                    setData(todoList);
                }else {
                    Toast.makeText(MainActivity.this,"Failed to get data",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                call.cancel();
                Toast.makeText(MainActivity.this,"Failed to get data",Toast.LENGTH_SHORT).show();
            }
        });

        /**
         Create new user
         **/
       /* User user = new User("morpheus", "leader");
        Call<User> call1 = apiInterface.createUser(user);
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user1 = response.body();

                Toast.makeText(getApplicationContext(), user1.name + " " + user1.job + " " + user1.id + " " + user1.createdAt, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
            }
        });*/

/*        *//**
         GET List Users
         **//*
        Call<UserList> call2 = apiInterface.doGetUserList("2");
        call2.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {

                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });*/


        /**
         POST name and job Url encoded.
         **/
      /*  Call<UserList> call3 = apiInterface.doCreateUserWithField("morpheus","leader");
        call3.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });*/

    }

    private void setData(List<Todo> todoList) {
        ListTodoAdapter listTodoAdapter = new ListTodoAdapter(todoList);
        listTodoAdapter.setListener(this);
        binding.rvData.hasFixedSize();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.rvData.setLayoutManager(mLayoutManager);
        binding.rvData.setAdapter(listTodoAdapter);
    }

    private void addTodo(AddTodo item) {
        Call<AddTodoRes> addTodoCall = apiInterface.createTodo(item);
        addTodoCall.enqueue(new Callback<AddTodoRes>() {
            @Override
            public void onResponse(Call<AddTodoRes> call, Response<AddTodoRes> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AddTodoRes> call, Throwable t) {
                call.cancel();
            }
        });

    }

    @Override
    public void onDelete(int id) {
        Log.e("Phaydev:: ","delete");
    }

    @Override
    public void onEdit(int id) {
        Log.e("Phaydev:: ","edit");

    }
}
