package com.example.asavarikarandikar.chatall;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View v){
        String username;
        TextView userNameView = (TextView)findViewById(R.id.username);
        username = userNameView.getText().toString();
        Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
        Json jsonFile = new Json();
        jsonFile.put("query","login");
        jsonFile.put("username",username);
        String json = jsonFile.getJsonString();
        try {
            Socket socket = new Socket(InetAddress.getByName(String.valueOf(R.string.server_ip)), new Integer(R.string.server_port));
            OutputStream out= socket.getOutputStream();
            InputStream in = socket.getInputStream();
            out.write(json.getBytes());
            byte b[] = new byte[65000];
            int count = in.read(b);
            if(count>0){
                ByteStreamData data = new ByteStreamData();
                data.setData(b,count);
                Json responseJson = new Json(data.getString());
                if(responseJson.getValue("query").equalsIgnoreCase("ok")){

                }
            }
        } catch(NumberFormatException | IOException e) {

        }

    }
}
