package com.example.lab9_androidintents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    String[] listItem;
    Button OKBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        editText = (EditText)findViewById(R.id.nameEditText);
        OKBtn = (Button)findViewById(R.id.OKBtn);

        OKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(
                        "name",
                        editText.getText().toString());
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        listItem = getResources().getStringArray(R.array.array);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position == 0){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.typingclub.com"));
                    Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
                    startActivity(browserChooserIntent );
                    //String value=adapter.getItem(position);
                    //Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
                }
                else if (position == 1)
                {
                    Intent intent = new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent,1);

                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:0553277684"));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if (resultCode== AppCompatActivity.RESULT_OK){
                Uri ContactData=data.getData();
                Cursor c =getContentResolver().query(ContactData,null,null,null,null);
                if(c.moveToFirst()){
                    String name=c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    Toast.makeText(this, "You have piked: "+name, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}