package com.example.hellis.weatherapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MyActivity extends Activity implements FragmentOne.OnFragmentOneInteractionListener, FragmentTwo.OnFragmentTwoInteractionListener
{

    private FragmentOne fragOne;
    private FragmentTwo fragTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        fragOne = new FragmentOne();
        fragTwo = new FragmentTwo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Override the method here
    @Override
    public void onFragmentOneInteraction(String string)
    {
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG).show();
        FragmentTwo fragmentTwo=
                (FragmentTwo) getFragmentManager().findFragmentById(R.id.web_request_fragment);
        fragmentTwo.twiddleEditText(string);
    }

    public void onFragmentTwoInteraction(Uri uri)
    {
        Toast.makeText(getApplicationContext(), "Fragment One", Toast.LENGTH_LONG).show();
    }
}
