package com.sslablk.mytodo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sslablk.mytodo.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ListView list;
    private Button button;
    private ArrayAdapter<String> itemsAdapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);
        button = findViewById(R.id.button);
        sharedPreferences = getSharedPreferences("com.sslablk.dailynote", Context.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additem(view);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        list.setAdapter(itemsAdapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return remove(i);
            }
        });

        loadItems();
    }

    private boolean remove(int position) {
        Context context = getApplicationContext();
        Toast.makeText(context, "Task Removed Successfully", Toast.LENGTH_LONG).show();
        items.remove(position);
        itemsAdapter.notifyDataSetChanged();
        saveItems();
        return true;
    }

    private void additem(View view) {
        EditText input = findViewById(R.id.edit_text);
        String itemText = input.getText().toString();

        if (!itemText.equals("")) {
            itemsAdapter.add(itemText);
            input.setText("");
            saveItems();
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter Text", Toast.LENGTH_LONG).show();
        }
    }

    private void saveItems() {
        Set<String> set = new HashSet<>(items);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("tasks", set);
        editor.apply();
    }

    private void loadItems() {
        Set<String> set = sharedPreferences.getStringSet("tasks", null);
        if (set != null) {
            items.addAll(set);
            itemsAdapter.notifyDataSetChanged();
        }
    }
}
