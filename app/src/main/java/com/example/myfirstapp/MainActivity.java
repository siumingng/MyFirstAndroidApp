package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.*;
import java.sql.*;
import android.os.StrictMode;


public class MainActivity extends AppCompatActivity {

    private Connection connection = null;
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        TextView editText2 = findViewById(R.id.trackingIDs);
        EditText editText = findViewById(R.id.editText2);


                try {
                    Class.forName(Classes);
                    connection= DriverManager.getConnection("jdbc:jtds:sqlserver://136.23.100.241:1433/testdb","sa","password");
                    editText.setText("Success");
                    String query = "select top 10 * from tbl_inv";
                    ResultSet rs =   connection.createStatement().executeQuery(query);
                    while (rs.next())
                    {
                        editText2.append(rs.getString("scode") + "\n");
                        //
                    }


                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    editText.setText(e.getMessage());
                }
                catch (SQLException e)
                {

                    editText.setText(e.getMessage());
                } catch (Exception e)
                {

                    editText.setText(e.toString());
                    editText2.append("Error!");
                }







        editText.setOnKeyListener((new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode== KeyEvent.KEYCODE_ENTER))
                {
                    editText2.append(editText.getText() + "\n");
                    editText.setText("");
                    return true;
                }
                return false;
            }
        }));








    }

}